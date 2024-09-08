package me.rochblondiaux.kingsnpigs.utils;

import java.awt.*;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WindowUtils {

    public static Dimension screenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static int screenWidth() {
        return screenSize().width;
    }

    public static int screenHeight() {
        return screenSize().height;
    }

    public static Point screenCenter() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Container container = Game.window().getHostControl();

        int x = (int) ((dimension.getWidth() - container.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - container.getHeight()) / 2);
        return new Point(x, y);
    }

    public static Resolution fullScreenResolution() {
        return Resolution.custom(screenWidth(), screenHeight(), "fullscreen");
    }
}