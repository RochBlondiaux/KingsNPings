package me.rochblondiaux.kingsnpigs.entities.behavior;

import java.awt.geom.Point2D;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.AnimationListener;
import me.rochblondiaux.kingsnpigs.entities.environment.Bomb;

public class BombBehavior implements IBehaviorController {

    private final Bomb bomb;
    private final AnimationListener listener;
    private static final long DELAY = 3_000;
    private long onSince;
    private boolean exploded;

    public BombBehavior(Bomb bomb) {
        this.bomb = bomb;
        this.exploded = false;

        this.listener = new AnimationListener() {
            @Override
            public void finished(Animation animation) {
                if (animation.getName().contains("destroyed") && !bomb.isDead())
                    bomb.die();
            }
        };
        bomb.animations().addListener(listener);
    }

    @Override
    public void update() {
        switch (bomb.getBombStatus()) {
            case OFF -> {
                if (bomb.getEnvironment()
                        .getCombatEntities()
                        .stream()
                        .filter(iCombatEntity -> !iCombatEntity.equals(bomb))
                        .noneMatch(iCombatEntity -> iCombatEntity.getLocation().distance(bomb.getLocation()) < 130))
                    return;
                bomb.setBombStatus(Bomb.Status.ON);
                onSince = Game.time().now();
            }
            case ON -> {
                if (bomb.getEnvironment()
                        .getCombatEntities()
                        .stream()
                        .noneMatch(iCombatEntity -> iCombatEntity.getLocation().distance(bomb.getLocation()) < 130)) {
                    onSince = 0;
                    bomb.setBombStatus(Bomb.Status.OFF);
                    return;
                }

                if (Game.time().since(onSince) > DELAY)
                    bomb.setBombStatus(Bomb.Status.EXPLODING);
            }
            case EXPLODING -> {
                if (!bomb.isDead() || exploded)
                    return;
                Environment environment = bomb.getEnvironment();
                double x = bomb.getX();
                double y = bomb.getY();

                environment.getCombatEntities()
                        .stream()
                        .filter(entity -> entity.getLocation().distance(x, y) < 130)
                        .forEach(entity -> entity.hit(1));
                environment.getCreatures()
                        .stream()
                        .filter(creature -> creature.getLocation().distance(x, y) < 130)
                        .forEach(creature -> {
                            Point2D creatureLocation = creature.getLocation();
                            float angle = (float) Math.toDegrees(Math.atan2(creatureLocation.getY() - y, creatureLocation.getX() - x));
                            Direction direction = Direction.fromAngle(angle);

                            Game.physics().move(creature, direction.getOpposite(), 5);
                        });

                bomb.animations().removeListener(listener);
                bomb.getEnvironment().remove(bomb);
                bomb.detachControllers();
                exploded = true;
            }
        }
    }

    @Override
    public IEntity getEntity() {
        return bomb;
    }
}