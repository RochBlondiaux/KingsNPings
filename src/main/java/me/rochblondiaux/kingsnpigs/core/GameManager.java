package me.rochblondiaux.kingsnpigs.core;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.EnvironmentListener;
import de.gurkenlabs.litiengine.environment.GameWorld;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.LocationLockCamera;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.kingsnpigs.entities.Player;

public class GameManager {

    public static void launchGame() {
        final GameWorld world = Game.world();

        // Game info
        Resources.load("game.litidata");

        // Spritesheets
        Resources.spritesheets().loadFrom("sprites/sprites.info");

        // Props
        // PropMapObjectLoader.registerCustomPropType(Door.class);
        // PropMapObjectLoader.registerCustomPropType(Bottle.class);
        // PropMapObjectLoader.registerCustomPropType(Bomb.class);

        world.addListener(new EnvironmentListener() {

            @Override
            public void loaded(Environment e) {
                final GameWorld world = Game.world();
                final Player player = Player.get();


                // Spawn
                Spawnpoint spawn = e.getSpawnpoint("spawnpoint");
                if (spawn == null)
                    throw new IllegalStateException("No spawn point detected!");

                // Camera
                Camera camera = new LocationLockCamera(player);
                world.setCamera(camera);
                camera.setZoom(1f, 0);
                camera.setFocus(player.getCenter());

                // Player
                player.setControlsEnabled(true);

                camera.setClampToMap(true);
                spawn.spawn(player);
            }
        });

        // Load the game world
        world.setGravity(400);
        world.loadEnvironment("level_0");
    }
}
