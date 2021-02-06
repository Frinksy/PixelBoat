package com.teamonehundred.pixelboat.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the Health power up
 *
 * @author Ben Dunbar
 */
public class PowerUpHealth extends PowerUp {

  /**
   * A constructor for an PowerUp taking its position (x and y).
   *
   * <p>A power up image is taken by default from C:\...\ENG1-Team-12\Implementation\core\assets.
   * Rotation is set.
   *
   * @author Ben Dunbar
   */
  public PowerUpHealth(float x, float y) {
    super(x, y, 60, 60, "power_up_health.png");
    sprite.setRotation(0);
  }

   /**
   * Returns a new collision bounds object for the health power-up.
   *
   * <p>Creates a new collision bounds object representing the current position of this power up.
   * See the collision bounds visualisation folder in assets for a visual representation.
   *
   * @return CollisionBounds of power up object
   * @author Ben Dunbar
   */
  @Override
  public CollisionBounds getBounds() {
    CollisionBounds myBounds = new CollisionBounds();
    Rectangle mainRect = new Rectangle(
        sprite.getX() + (0.31f * sprite.getWidth()),
        sprite.getY() + (0.06f * sprite.getHeight()),
        0.31f * sprite.getWidth(),
        0.88f * sprite.getHeight());
    myBounds.addBound(mainRect);

    myBounds.setOrigin(new Vector2(
        sprite.getX() + (sprite.getWidth() / 2),
        sprite.getY() + (sprite.getHeight() / 2)));
    myBounds.setRotation(sprite.getRotation());

    return myBounds;
  }
}
