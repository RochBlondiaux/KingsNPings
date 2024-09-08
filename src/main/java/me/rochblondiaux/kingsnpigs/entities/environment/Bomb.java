package me.rochblondiaux.kingsnpigs.entities.environment;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import lombok.Getter;
import lombok.Setter;
import me.rochblondiaux.kingsnpigs.entities.animation.BombAnimationController;
import me.rochblondiaux.kingsnpigs.entities.behavior.BombBehavior;

@Setter
@Getter
@EntityInfo(renderType = RenderType.SURFACE)
@AnimationInfo(spritePrefix = "prop-bomb")
public class Bomb extends Prop {

    private Status bombStatus;

    public Bomb() {
        super("bomb");
        this.bombStatus = Status.OFF;


        // Controllers
        addController(new BombBehavior(this));

        attachControllers();
    }

    @Override
    protected IEntityAnimationController<?> createAnimationController() {
        return new BombAnimationController(this);
    }


    public enum Status {
        OFF,
        ON,
        EXPLODING
    }
}