package me.rochblondiaux.kingsnpigs.entities.enemies;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@CombatInfo(hitpoints = 1)
@MovementInfo(velocity = 45)
@CollisionInfo(collision = true, collisionBoxWidth = 20, collisionBoxHeight = 20)
public class Pig extends Creature {

    public Pig() {
        super("pig");

        this.setTeam(1);
        this.addTag("enemy");
    }
}
