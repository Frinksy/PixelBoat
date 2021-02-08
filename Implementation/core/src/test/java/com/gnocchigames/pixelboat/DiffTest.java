package com.gnocchigames.pixelboat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.teamonehundred.pixelboat.scenees.SceneDifficulty;
import com.teamonehundred.pixelboat.entities.PlayerBoat;


public class DiffTest {
    int diffLevel;

    public static void setUpTest(){

    }
    @Test
    public void testEasy(){
        diffLevel = 1;
        PlayerBoat testBoat = new PlayerBoat();
        float expectedStaminaRegain = .002f;
        float expectedStaminaUsage = 0.005;
        float expectedDurabilityPerHit = .1f;
        testBoat.setDiff(diffLevel)
        assertEquals(expectedStaminaRegain, testBoat.getStaminaRegain, "Easy: Stamina regain does not match")
        assertEquals(expectedStaminaUsage, testBoat.getStaminaUsage, "Easy: Stamina usage does not match")
        assertEquals(expectedStamina, testBoat.getStaminaRegain, "Easy: Stamina regain does not match")



    }
    @Test
    public void testMedium(){
        diffLevel = 2;
        PlayerBoat testBoat = new PlayerBoat();
        float expectedStaminaRegain = .002f*0.7f;
        float expectedStaminaUsage = 0.005*1.3f;
        float expectedDurabilityPerHit = .1f*3.0f;
        testBoat.setDiff(diffLevel)
        assertEquals(expectedStaminaRegain, testBoat.getStaminaRegain, "Easy: Stamina regain does not match")
        assertEquals(expectedStaminaUsage, testBoat.getStaminaUsage, "Easy: Stamina usage does not match")
        assertEquals(expectedStamina, testBoat.getStaminaRegain, "Easy: Stamina regain does not match")

    }
    @Test
    public void testHard(){
        diffLevel = 3;
        PlayerBoat testBoat = new PlayerBoat();
        float expectedStaminaRegain = .002f*0.4f;
        float expectedStaminaUsage = 0.005*1.7f;
        float expectedDurabilityPerHit = .1f;
        testBoat.setDiff(diffLevel)
        assertEquals(expectedStaminaRegain, testBoat.getStaminaRegain, "Easy: Stamina regain does not match")
        assertEquals(expectedStaminaUsage, testBoat.getStaminaUsage, "Easy: Stamina usage does not match")
        assertEquals(expectedStamina, testBoat.getStaminaRegain, "Easy: Stamina regain does not match")

    }
    
}
