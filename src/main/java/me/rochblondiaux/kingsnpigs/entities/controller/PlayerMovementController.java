package me.rochblondiaux.kingsnpigs.entities.controller;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import me.rochblondiaux.kingsnpigs.entities.Player;

public class PlayerMovementController extends PlatformingMovementController<Player> {

    public PlayerMovementController(Player entity) {
        super(entity);
    }

    @Override
    public void handlePressedKey(KeyEvent keyCode) {
        if (getEntity().isControlsEnabled())
            super.handlePressedKey(keyCode);
    }
}