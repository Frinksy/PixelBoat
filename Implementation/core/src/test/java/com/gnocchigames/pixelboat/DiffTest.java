package com.gnocchigames.pixelboat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.teamonehundred.pixelboat.scenees.SceneDifficulty;
import com.teamonehundred.pixelboat.entities.PlayerBoat;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
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

public class DiffTest {

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
    int diffLevel;

    @Test
    public void testEasy(){
        diffLevel = 1;
        PlayerBoat testBoat = new PlayerBoat(40,40);
        float expectedStaminaRegain = .002f;
        testBoat.setDiff(diffLevel);

        assertEquals(expectedStaminaRegain, testBoat.getStaminaRegain()
        , "Easy: Stamina regain does not match");

        float expectedStaminaUsage = 0.005;
        assertEquals(expectedStaminaUsage, testBoat.getStaminaUsage(),
         "Easy: Stamina usage does not match");

        float expectedDurabilityPerHit = .1f;
        assertEquals(expectedStamina, testBoat.getStaminaRegain(), 
        "Easy: Stamina regain does not match");
    }
    @Test
    public void testMedium(){
        diffLevel = 2;
        PlayerBoat testBoat = new PlayerBoat(40 ,40);
        testBoat.setDiff(diffLevel);

        float expectedStaminaRegain = .002f*0.7f;
        assertEquals(expectedStaminaRegain, testBoat.getStaminaRegain(), 
        "Med: Stamina regain does not match");

        float expectedStaminaUsage = 0.005*1.3f;
        assertEquals(expectedStaminaUsage, testBoat.getStaminaUsage(), 
        "Med: Stamina usage does not match");

        float expectedDurabilityPerHit = .1f*1.7f;
        assertEquals(expectedStamina, testBoat.getStaminaRegain(), 
        "Med: Stamina regain does not match");

    }
    @Test
    public void testHard(){
        diffLevel = 3;
        PlayerBoat testBoat = new PlayerBoat(40, 40);
        testBoat.setDiff(diffLevel);
        
        float expectedStaminaRegain = .002f*0.4f;
        assertEquals(expectedStaminaRegain, testBoat.getStaminaRegain(), 
        "Hard: Stamina regain does not match");

        float expectedStaminaUsage = 0.005*1.6f;
        assertEquals(expectedStaminaUsage, testBoat.getStaminaUsage(), 
        "Hard: Stamina usage does not match");

        float expectedDurabilityPerHit = .1f*2.5f;
        assertEquals(expectedStamina, testBoat.getStaminaRegain(), 
        "Hard: Stamina regain does not match");

    }
}