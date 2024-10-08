package me.rochblondiaux.kingsnpigs.entities.animation;

import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import me.rochblondiaux.kingsnpigs.entities.environment.Bomb;

public class BombAnimationController extends EntityAnimationController<Bomb> {

    /**
     * Initializes a new instance of the {@code PropAnimationController} class.
     *
     * @param prop The prop related to this controller.
     */
    public BombAnimationController(Bomb prop) {
        super(prop);

        this.setDefault(new Animation("prop-bomb-intact", false, false));
        this.add(new Animation("prop-bomb-damaged", false, false));
        this.add(new Animation("prop-bomb-destroyed", false, false));

        addRule(door -> door.getBombStatus().equals(Bomb.Status.OFF), door -> "prop-bomb-intact");
        addRule(door -> door.getBombStatus().equals(Bomb.Status.ON), door -> "prop-bomb-damaged");
        addRule(door -> door.getBombStatus().equals(Bomb.Status.EXPLODING), door -> "prop-bomb-destroyed");
    }
}