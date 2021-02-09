package com.teamonehundred.pixelboat.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * SceneGameOver.
 */
public class SceneGameOver implements Scene {
    
  protected int sceneId = 9;

  protected Texture bg;
  protected Sprite bgSprite;

  protected Texture restart;
  protected Texture restartHovered;
  protected Sprite restartSprite;

  protected Viewport fillViewport;
  protected OrthographicCamera fillCamera;


  /**
   * Create a SceneGameOver.
   */
  public SceneGameOver() {
    fillCamera = new OrthographicCamera();
    fillViewport = new FillViewport(1280, 720, fillCamera);
    fillViewport.apply();
    fillCamera.position.set(fillCamera.viewportWidth / 2, fillCamera.viewportHeight / 2, 0);
    fillViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    
    bg = new Texture("game_over_screen.png");
    bgSprite = new Sprite(bg);
    bgSprite.setPosition(0, 0);
    bgSprite.setSize(1280, 720); 

    restart = new Texture("restart.png");
    restartHovered = new Texture("restart_hovered.png");
    restartSprite = new Sprite(restart);
    restartSprite.setCenter(640, 180);
    restartSprite.setSize(512, 128);

  }

  /**
   * Update the scene.
   */
  public int update() {
      
    Vector3 mousePos = fillCamera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));  
    
    if (restartSprite.getBoundingRectangle().contains(mousePos.x, mousePos.y)) {
        
      restartSprite.setTexture(restartHovered);

      if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
        System.out.println("restart");
        return 0;
      }
    } else {
      restartSprite.setTexture(restart);
    }

    return sceneId;
  }

  /**
   * Draw the scene.
   */
  public void draw(SpriteBatch batch) {
    Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.setProjectionMatrix(fillCamera.combined);
    batch.begin();
    bgSprite.draw(batch);
    restartSprite.draw(batch);
    batch.end();
  }

  public void resize(int width, int height) {
    fillViewport.update(width, height);
    fillCamera.position.set(fillCamera.viewportWidth / 4, fillCamera.viewportHeight / 4, 0);
  }

}