package me.rochblondiaux.kingsnpigs.entities.environment;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;

@AnimationInfo(spritePrefix = "prop-box")
@CollisionInfo(collision = true, collisionBoxHeight = 32, collisionBoxWidth = 32)
public class Box extends Prop {

    public Box() {
        super("box");
    }
}
