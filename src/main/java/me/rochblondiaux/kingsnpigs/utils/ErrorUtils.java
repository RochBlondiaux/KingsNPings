package me.rochblondiaux.kingsnpigs.utils;

import javax.swing.*;

import de.gurkenlabs.litiengine.Game;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorUtils {

    public static void displayError(String message) {
        displayError("Kings N Pigs", message);
    }

    public static void displayError(String title, String message) {
        JOptionPane.showMessageDialog(Game.window().getHostControl(),
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayErrorAndExit(String message) {
        displayErrorAndExit("Kings N Pigs", message);
    }

    public static void displayErrorAndExit(String title, String message) {
        ((JFrame) Game.window().getHostControl()).dispose();
        displayError(title, message);
        Game.exit();
    }

}