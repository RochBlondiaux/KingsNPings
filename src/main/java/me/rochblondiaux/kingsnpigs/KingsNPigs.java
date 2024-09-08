package me.rochblondiaux.kingsnpigs;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.swing.*;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameInfo;
import de.gurkenlabs.litiengine.GameWindow;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.gui.screens.ScreenManager;
import de.gurkenlabs.litiengine.resources.Resources;
import lombok.extern.log4j.Log4j2;
import me.rochblondiaux.kingsnpigs.core.GameManager;
import me.rochblondiaux.kingsnpigs.screen.InGameScreen;
import me.rochblondiaux.kingsnpigs.utils.ErrorUtils;
import me.rochblondiaux.kingsnpigs.utils.WindowUtils;

@Log4j2
public class KingsNPigs {

    public static final Path DATA_FOLDER = Path.of(System.getProperty("user.home") + "/.kingsnpigs/");

    public KingsNPigs(String... args) {
        log.info("KingsNPigs is starting...");

        log.info("Initializing game engine...");
        Game.init(args);
        this.initGameInfo();
        this.writeLckFile();
        log.info("Game engine initialized.");

        this.registerScreens();

        GameManager.launchGame();

        log.info("Starting game engine...");
        Game.start();
        log.info("Game engine started.");
    }

    private void initGameInfo() {
        // Info
        GameInfo info = Game.info();
        info.setDevelopers("Roch Blondiaux");
        info.setName("KingsNPigs");
        info.setVersion("0.0.1");
        info.setCompany("Vulkan Technologies");
        info.setDescription("A simple game where you have to kill pigs to become the king.");

        // Window
        GameWindow window = Game.window();
        window.setIcon(Resources.images().get("icon.png"));
        window.setTitle("KingsNPigs");
        window.setResolution(Resolution.Ratio16x9.RES_1360x768);

        JFrame frame = (JFrame) window.getHostControl();
        frame.setLocation(WindowUtils.screenCenter());
    }

    private void writeLckFile() {
        if (!Files.isDirectory(DATA_FOLDER)) {
            try {
                Files.createDirectories(DATA_FOLDER);
            } catch (IOException e) {
                log.error("Failed to create data folder.", e);
            }
        }

        try (FileChannel fc = FileChannel.open(DATA_FOLDER.resolve("game.lock"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            FileLock lock = fc.tryLock();
            if (lock == null)
                ErrorUtils.displayErrorAndExit("Another instance of PirateBay is already running.");
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    private void registerScreens() {
        log.info("Registering screens...");
        ScreenManager manager = Game.screens();
        manager.add(new InGameScreen());
        log.info("Screens registered.");
    }

}
