package me.rochblondiaux.kingsnpigs.entities.environment;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import lombok.Getter;
import lombok.Setter;
import me.rochblondiaux.kingsnpigs.entities.animation.DoorAnimationController;

@AnimationInfo(spritePrefix = "prop-door")
public class Door extends Prop {

    @Getter
    @Setter
    private State doorState;

    public Door() {
        super("door");
        this.doorState = State.CLOSED;

        getHitPoints().setMaxBaseValue(3);
        getHitPoints().setBaseValue(3);
    }



    @Override
    protected IEntityAnimationController<?> createAnimationController() {
        return new DoorAnimationController(this);
    }

    public enum State {
        OPENING,
        CLOSING,
        CLOSED
    }
}