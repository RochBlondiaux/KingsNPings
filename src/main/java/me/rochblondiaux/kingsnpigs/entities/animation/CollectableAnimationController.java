package me.rochblondiaux.kingsnpigs.entities.animation;

import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import me.rochblondiaux.kingsnpigs.entities.Player;
import me.rochblondiaux.kingsnpigs.entities.environment.collectible.Collectible;

public class CollectableAnimationController extends EntityAnimationController<Collectible> {

    public CollectableAnimationController(Collectible entity) {
        super(entity);

        this.setDefault(new Animation("prop-%s-intact".formatted(entity.getSpritesheetName()), false, false));
        this.add(new Animation("prop-%s-destroyed".formatted(entity.getSpritesheetName()), false, false));

        addRule(prop -> !Player.get().getBoundingBox().contains(entity.getBoundingBox()), prop -> "prop-%s-intact".formatted(entity.getSpritesheetName()));
        addRule(prop -> Player.get().getBoundingBox().contains(entity.getBoundingBox()), prop -> "prop-%s-destroyed".formatted(entity.getSpritesheetName()));
    }
}
