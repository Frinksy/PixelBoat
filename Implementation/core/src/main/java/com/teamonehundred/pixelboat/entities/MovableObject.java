package com.teamonehundred.pixelboat.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents the movable object as an abstract class that extends from game object.
 *
 * @author James Frost
 * @author William Walton JavaDoc by Umer Fakher
 */
public abstract class MovableObject extends GameObject {
  /* ################################### //
           ATTRIBUTES
  // ################################### */

  public float maxSpeed = 15;
  public float speed = 0;
  public float drag = .04f;  // amount speed is reduced by every frame naturally
  public float acceleration = .2f;
  public float rotationSpeed = 2.f;

  /* ################################### //
          CONSTRUCTORS
  // ################################### */

  /**
   * A constructor for MovableObject.
   *
   * @param x      float for horizontal position of object
   * @param y      float for vertical position of object
   * @param w      int for width of object
   * @param h      int for height of object
   * @param texturePath String of object's file path
   */
  MovableObject(float x, float y, int w, int h, String texturePath) {
    super(x, y, w, h, texturePath);
  }

  MovableObject(float x, float y, int w, int h, Texture texture) {
    super(x, y, w, h, texture);
  }

  /**
   * A constructor for MovableObject.
   *
   * @param x      float for horizontal position of object
   * @param y      float for vertical position of object
   * @param w      int for width of object
   * @param h      int for height of object
   * @param t String of object's file path
   * @param frameCount  int frame count
   */
  MovableObject(float x, float y, int w, int h, String texturePath, int frameCount) {
    super(x, y, w, h, texturePath, frameCount);
  }

  /**
   * A constructor for MovableObject.
   *
   * @param x       float for horizontal position of object
   * @param y       float for vertical position of object
   * @param w       int for width of object
   * @param h       int for height of object
   * @param t       Direct Texture
   * @param frameCount int frame count
   */
  MovableObject(float x, float y, int w, int h, Texture t, int frameCount) {
    super(x, y, w, h, t, frameCount);
  }

  /* ################################### //
          METHODS
  // ################################### */

  /**
   * Rotates the Movable object by some given value.
   *
   * <p>Note: turn left (1) or right (-1)
   *
   * @param amount Integer value that dictates how much the movable object will be rotated
   * @author James Frost
   * @author William Walton
   */
  public void turn(int amount) {
    sprite.rotate(amount * rotationSpeed);
  }


  /**
   * Move forwards x, y in whatever direction currently facing.
   *
   * @param distance to be moved
   * @author James Frost
   * @author William Walton
   */
  private void move(float distance) {
    double dy = Math.cos((Math.toRadians(sprite.getRotation()))) * distance;
    double dx = Math.sin((Math.toRadians(sprite.getRotation()))) * distance;

    sprite.translate((float) (-dx), (float) dy);
  }

  /**
   * Updates position of movable object based on speed and decreases
   * speed according to drag calculation.
   *
   * @author James Frost
   * @author William Walton
   */
  public void updatePosition() {
    move(speed);
    speed -= speed - drag < 0 ? speed : drag;
  }

  /**
   * Increase speed based on current acceleration attribute.
   *
   * <p>If max_speed (terminal velocity) is reached for the movable
   * object then don't increase speed.
   *
   * @author James Frost
   * @author William Walton
   */
  public void accelerate() {
    speed += speed >= maxSpeed ? 0 : acceleration;
  }

  /**
   * Resets speed to 0 and rotation to 0.
   */
  public void reset_motion() {
    speed = 0;
    sprite.setRotation(0);
  }
}
