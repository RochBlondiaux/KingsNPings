package me.rochblondiaux.kingsnpigs.input;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.input.Input;
import me.rochblondiaux.kingsnpigs.ui.HUD;

public class UserInput {

    public static void init() {
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_F3, event -> HUD.DEBUG_MODE = !HUD.DEBUG_MODE);
    }

}
