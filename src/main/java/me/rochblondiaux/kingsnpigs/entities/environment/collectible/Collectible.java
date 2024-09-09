package me.rochblondiaux.kingsnpigs.entities.environment.collectible;

import java.util.function.Consumer;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import lombok.Getter;
import lombok.Setter;
import me.rochblondiaux.kingsnpigs.entities.Player;
import me.rochblondiaux.kingsnpigs.entities.animation.CollectableAnimationController;

@Setter
@Getter
public abstract class Collectible extends Prop {

    public Collectible(String spritesheetName, Consumer<Player> giveFunction) {
        super(spritesheetName);

        onCollision(c -> {
            if (c.getInvolvedEntities().contains(Player.get())) {
                giveFunction.accept(Player.get());
                Game.world().environment().remove(this);
            }
        });
    }

    @Override
    protected IEntityAnimationController<?> createAnimationController() {
        return new CollectableAnimationController(this);
    }
}
