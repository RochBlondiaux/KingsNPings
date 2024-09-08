package me.rochblondiaux.kingsnpigs.manager;

import me.rochblondiaux.kingsnpigs.KingsNPigs;

public abstract class Manager {

    protected final KingsNPigs game;

    public Manager(KingsNPigs game) {
        this.game = game;
    }

    public abstract void enable();

    public abstract void disable();


}
