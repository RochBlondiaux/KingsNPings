package me.rochblondiaux.kingsnpigs.screen;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class UpdatableScreen extends Screen implements IUpdateable {

    public UpdatableScreen(String screenName) {
        super(screenName);
    }

    @Override
    public void prepare() {
        super.prepare();

        log.info("Attaching screen {} to main loop.", this.getName());
        Game.loop().attach(this);
    }

    @Override
    public void suspend() {
        super.suspend();

        log.info("Detaching screen {} to main loop.", this.getName());
        Game.loop().detach(this);
    }
}