package com.gnocchigames.pixelboat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.teamonehundred.pixelboat.BoatRace;
import com.teamonehundred.pixelboat.GameState;
import com.teamonehundred.pixelboat.PixelBoat;
import com.teamonehundred.pixelboat.entities.AiBoat;
import com.teamonehundred.pixelboat.entities.Boat;
import com.teamonehundred.pixelboat.entities.CollisionObject;
import com.teamonehundred.pixelboat.entities.Obstacle;
import com.teamonehundred.pixelboat.entities.PowerUp;
import com.teamonehundred.pixelboat.scenes.SceneMainGame;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit tests for the GameState class.
 */
public class GameStateTest {

  private static Application application;
  private BoatRace race;
  private static PixelBoat game;

  /**
   * .
   */
  @BeforeAll
  public static void setupTest() {
    
    application = new HeadlessApplication(new ApplicationListener() {

      @Override public void create() {}

      @Override public void resize(int width, int height) {}

      @Override public void render() {}

      @Override public void pause() {}

      @Override public void resume() {}
      
      @Override public void dispose() {}
    });
    Gdx.gl20 = Mockito.mock(GL20.class);
    Gdx.gl = Gdx.gl20;
  }

  /**
   * .
   */
  @AfterAll
  public static void cleanUp() {
    // Exit the application first
    application.exit();
    application = null;
  }

  /**
   * Setup before each test.
   */
  @BeforeEach
  public void setupTestEach() {
    game = Mockito.mock(PixelBoat.class);
    game.assets = Mockito.mock(AssetManager.class);
    Texture boatTexture = new Texture("boat.png"); 
    when(game.assets.get(anyString(), eq(Texture.class))).thenReturn(boatTexture);
    when(game.assets.get(anyString())).thenReturn(boatTexture);
  }

  /**
   * Test getPlayerIndex.
   */
  @Test
  public void testPlayerIndex() {

    // PixelBoat app = Mockito.mock(PixelBoat.class);
    // app.assets = Mockito.mock(AssetManager.class);
    // Texture boatTexture = new Texture("boat.png"); 
    // when(app.assets.get(anyString(), eq(Texture.class))).thenReturn(boatTexture);
    // when(app.assets.get(anyString())).thenReturn(boatTexture);

    SceneMainGame gameScene = new SceneMainGame(game);

    // Create GameState object

    GameState testState = new GameState(
        gameScene.getAllBoats(),
        gameScene.getPlayer(),
        gameScene.getRace().obstacles,
        gameScene.getRace().powerups,
        gameScene.getLegNumber(),
        gameScene.isLastRun(),
        gameScene.getRace().isFinished(),
        gameScene.getRace().totalFrames
    );

    // Test getPlayerIndex
    int expectedPlayerIndex = gameScene.getAllBoats().indexOf(gameScene.getPlayer());

    assertEquals(
        expectedPlayerIndex,
        testState.getPlayerIndex(),
        "GameState player index does not match.");
  }

  /**
   * Test getBoatList().
   */
  @Test
  public void testBoatList() {

    SceneMainGame gameScene = new SceneMainGame(game);

    // Create GameState object

    GameState testState = new GameState(
        gameScene.getAllBoats(),
        gameScene.getPlayer(),
        gameScene.getRace().obstacles,
        gameScene.getRace().powerups,
        gameScene.getLegNumber(),
        gameScene.isLastRun(),
        gameScene.getRace().isFinished(),
        gameScene.getRace().totalFrames
    );

    // Test getAllBoats.
    List<Boat> expectedAllBoats = gameScene.getAllBoats();

    List<Boat> actualAllBoats = testState.getBoatList(game);

    assertEquals(
        expectedAllBoats.size(),
        actualAllBoats.size()
    );

    for (int i = 0; i < expectedAllBoats.size(); i++) {
      Boat expected = expectedAllBoats.get(i);
      Boat actual = actualAllBoats.get(i);

      // Check position
      assertEquals(expected.getSprite().getX(), actual.getSprite().getX(), "X does not match");
      assertEquals(expected.getSprite().getY(), actual.getSprite().getY(), "Y does not match");

      // Check size
      assertEquals(
          expected.getSprite().getHeight(),
          actual.getSprite().getHeight(),
          "Height does not match"
      );
      assertEquals(
          expected.getSprite().getWidth(),
          actual.getSprite().getWidth(),
          "Width does not match"
      );
      
      // Check both boats are of same class
      assertEquals(
          expected.getClass(),
          actual.getClass(),
          "Class does not match"    
      );

    }



  }

  @Test
  public void testPowerUps() {
    


    SceneMainGame gameScene = new SceneMainGame(game);

    // Create GameState object

    GameState testState = new GameState(
        gameScene.getAllBoats(),
        gameScene.getPlayer(),
        gameScene.getRace().obstacles,
        gameScene.getRace().powerups,
        gameScene.getLegNumber(),
        gameScene.isLastRun(),
        gameScene.getRace().isFinished(),
        gameScene.getRace().totalFrames
    );

    // Test getPowerupsList.
    List<CollisionObject> expectedPowerups = gameScene.getRace().powerups;

    List<CollisionObject> actualPowerups = testState.getPowerupsList(game);

    assertEquals(
        expectedPowerups.size(),
        actualPowerups.size(),
        "List size does not match"
    );

    for (int i = 0; i < expectedPowerups.size(); i++) {
      PowerUp expected = (PowerUp) expectedPowerups.get(i);
      PowerUp actual = (PowerUp) actualPowerups.get(i);

      // Check position
      assertEquals(expected.getSprite().getX(), actual.getSprite().getX(), "X does not match");
      assertEquals(expected.getSprite().getY(), actual.getSprite().getY(), "Y does not match");

      // Check size
      assertEquals(
          expected.getSprite().getHeight(),
          actual.getSprite().getHeight(),
          "Height does not match"
      );
      assertEquals(
          expected.getSprite().getWidth(),
          actual.getSprite().getWidth(),
          "Width does not match"
      );
      
      // Check both powerups are of same class
      assertEquals(
          expected.getClass(),
          actual.getClass(),
          "Class does not match"    
      );
    }
  }

  @Test
  public void testCollisionObstacles() {
    

    SceneMainGame gameScene = new SceneMainGame(game);
    
    // Create GameState object

    GameState testState = new GameState(
        gameScene.getAllBoats(),
        gameScene.getPlayer(),
        gameScene.getRace().obstacles,
        gameScene.getRace().powerups,
        gameScene.getLegNumber(),
        gameScene.isLastRun(),
        gameScene.getRace().isFinished(),
        gameScene.getRace().totalFrames
    );

    // Test getCollionObjects.
    List<CollisionObject> expectedObjects = gameScene.getRace().obstacles;

    List<CollisionObject> actualObjects = testState.getCollisionObjects(game);

    assertEquals(
        expectedObjects.size(),
        actualObjects.size(),
        "List size does not match"
    );

    for (int i = 0; i < expectedObjects.size(); i++) {
      Obstacle expected = (Obstacle) expectedObjects.get(i);
      Obstacle actual = (Obstacle) actualObjects.get(i);

      // Check position
      assertEquals(expected.getSprite().getX(), actual.getSprite().getX(), "X does not match");
      assertEquals(expected.getSprite().getY(), actual.getSprite().getY(), "Y does not match");

      // Check size
      assertEquals(
          expected.getSprite().getHeight(),
          actual.getSprite().getHeight(),
          "Height does not match"
      );
      assertEquals(
          expected.getSprite().getWidth(),
          actual.getSprite().getWidth(),
          "Width does not match"
      );
      
      // Check both obstacles are of same class
      assertEquals(
          expected.getClass(),
          actual.getClass(),
          "Class does not match"    
      );
    }
  }


}
