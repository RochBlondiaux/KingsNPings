package me.rochblondiaux.kingsnpigs.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.kingsnpigs.entities.Player;

public class HUD extends GuiComponent implements IUpdateable {

    private static final int PADDING = 15;
    private static final BufferedImage HEARTS_CONTAINER = Resources.images().get("hud/bar.png");
    private static final Spritesheet HEART_IDLE = Resources.spritesheets().load("hud/heart-idle.png", 18, 14);
    private static final Spritesheet DIAMOND_IDLE = Resources.spritesheets().load("hud/diamond.png", 18, 14);
    private static final Spritesheet NUMBERS = Resources.spritesheets().load("hud/numbers.png", 6, 8);

    private final Animation heartIdleAnimation = new Animation(HEART_IDLE, true);
    private final Animation diamondIdleAnimation = new Animation(DIAMOND_IDLE, true);

    public HUD() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());

        this.heartIdleAnimation.start();
        this.diamondIdleAnimation.start();

        Game.loop().attach(this);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        if (Game.world().environment() == null)
            return;

        this.renderHeartsContainer(g);
        this.renderHearts(g);
        this.renderDiamond(g);
        this.renderDiamondCount(g);
    }

    private void renderHeartsContainer(Graphics2D g) {
        double x = Game.window().getResolution().getWidth() / 20;
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING;

        ImageRenderer.renderScaled(g, HEARTS_CONTAINER, x - PADDING, y, 2.5);
    }

    private void renderHearts(Graphics2D g) {
        double x = Game.window().getResolution().getWidth() / 20;
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + 43;

        for (int i = 0; i < Player.get().getHealth(); i++) {
            Spritesheet currentHeart = HEART_IDLE;

            ImageRenderer.renderScaled(g, currentHeart.getSprite(heartIdleAnimation.getCurrentKeyFrame().getSpriteIndex()), x + 20, y, 2);
            x += currentHeart.getSpriteWidth() + 8;
        }
    }

    private void renderDiamond(Graphics2D g) {
        Dimension dimension = Game.window().getResolution();
        double x = dimension.getWidth() - dimension.getWidth() / 10;
        double y = dimension.getHeight() - dimension.getHeight()  + DIAMOND_IDLE.getSpriteHeight() * 2;
        ImageRenderer.renderScaled(g, DIAMOND_IDLE.getSprite(diamondIdleAnimation.getCurrentKeyFrame().getSpriteIndex()), x + 20, y, 2);
    }

    private void renderDiamondCount(Graphics2D g) {
        Dimension dimension = Game.window().getResolution();
        double x = dimension.getWidth() - dimension.getWidth() / 10 + 20;
        double y = dimension.getHeight() - dimension.getHeight()  + DIAMOND_IDLE.getSpriteHeight() * 2 + 5;

        int diamondCount = Player.get().getDiamonds();
        String diamondCountString = String.valueOf(diamondCount);

        for (int i = 0; i < diamondCountString.length(); i++) {
            int digit = Character.getNumericValue(diamondCountString.charAt(i));
            ImageRenderer.renderScaled(g, NUMBERS.getSprite(digit), x + 40, y, 2);
            x += NUMBERS.getSpriteWidth() + 8;
        }
    }

    @Override
    public void update() {
        this.heartIdleAnimation.update();
    }
}
