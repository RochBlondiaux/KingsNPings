package me.rochblondiaux.kingsnpigs.screen;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import me.rochblondiaux.kingsnpigs.ui.HUD;

public class InGameScreen extends GameScreen {

    private HUD hud;

    public InGameScreen() {
        super("Game");
    }

    @Override
    protected void initializeComponents() {
        this.hud = new HUD();
        this.getComponents().add(this.hud);
    }
}