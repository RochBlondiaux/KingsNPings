package me.rochblondiaux.kingsnpigs.core;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.*;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.LocationLockCamera;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.kingsnpigs.entities.Player;
import me.rochblondiaux.kingsnpigs.entities.enemies.Pig;
import me.rochblondiaux.kingsnpigs.entities.environment.Bomb;
import me.rochblondiaux.kingsnpigs.entities.environment.collectible.Diamond;
import me.rochblondiaux.kingsnpigs.entities.environment.collectible.Heart;
import me.rochblondiaux.kingsnpigs.entities.environment.furniture.Box;
import me.rochblondiaux.kingsnpigs.entities.environment.furniture.Door;

public class GameManager {

    public static void launchGame() {
        final GameWorld world = Game.world();

        // Game info
        Resources.load("game.litidata");

        // Spritesheets
        Resources.spritesheets().loadFrom("sprites/sprites.info");

        // Props
        PropMapObjectLoader.registerCustomPropType(Box.class);
        PropMapObjectLoader.registerCustomPropType(Door.class);
        PropMapObjectLoader.registerCustomPropType(Bomb.class);
        PropMapObjectLoader.registerCustomPropType(Diamond.class);
        PropMapObjectLoader.registerCustomPropType(Heart.class);

        // Creature
        CreatureMapObjectLoader.registerCustomCreatureType(Pig.class);

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
                camera.setZoom(2f, 750);


                Door door = (Door) e.getProp("start");
                if (door != null) {
                    player.setControlsEnabled(false);
                    player.setVisible(false);

                    spawn.spawn(player);
                    Game.loop().perform(750, () -> {
                        door.setDoorState(Door.State.OPENING);

                        Game.loop().perform(350, () -> {
                            player.setVisible(true);
                            player.animations().play("player-doorout-right");
                        });

                        camera.setClampToMap(true);
                        Game.loop().perform(1500, () -> {
                            camera.setFocus(player.getCenter());
                            camera.setZoom(1f, 750);
                            player.setControlsEnabled(true);
                        });
                    });
                } else {
                    camera.setZoom(1f, 0);
                    camera.setFocus(player.getCenter());
                    camera.setClampToMap(true);
                    spawn.spawn(player);
                }
            }
        });

        // Load the game world
        world.setGravity(250);
        world.loadEnvironment("level_0");
    }
}
