package com.teamonehundred.pixelboat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.teamonehundred.pixelboat.scenes.Scene;
import com.teamonehundred.pixelboat.scenes.SceneAwardScreen;
import com.teamonehundred.pixelboat.scenes.SceneBoatSelection;
import com.teamonehundred.pixelboat.scenes.SceneDifficulty;
import com.teamonehundred.pixelboat.scenes.SceneGameOver;
import com.teamonehundred.pixelboat.scenes.SceneLoadScreen;
import com.teamonehundred.pixelboat.scenes.SceneMainGame;
import com.teamonehundred.pixelboat.scenes.SceneOptionsMenu;
import com.teamonehundred.pixelboat.scenes.SceneResultsScreen;
import com.teamonehundred.pixelboat.scenes.SceneSaveScreen;
import com.teamonehundred.pixelboat.scenes.SceneStartScreen;
import com.teamonehundred.pixelboat.scenes.SceneTutorial;


/**
 * Main class for the PixelBoat game.
 *
 * <p>Extends Libgdx ApplicationAdapter.
 *
 * @author William Walton
 * @author James Frost
 * @author Umer Fakher JavaDoc by Umer Fakher
 */
public class PixelBoat extends ApplicationAdapter {


  public Scene[] allScenes;  // stores all game scenes and their data
  protected SpriteBatch batch;  // thing that draws the sprites

  // id of current game state
  // 0 = start menu
  // 1 = game
  // 2 = options
  // 3 = tutorial
  // 4 = results
  // 5 =boat selection
  // 6 = difficulty options
  protected int sceneId = 0;

  public static int MAIN_MENU = 0;
  public static int GAME_SCENE = 1;
  public static int OPTIONS_SCENE = 2;
  public static int TUTORIAL_SCENE = 3;
  public static int RESULTS_SCENE = 4;
  public static int BOAT_SELECT = 5;
  public static int DIFFICULTY_SELECT = 6;
  public static int LOAD_SCENE = 7;
  public static int SAVE_SCENE = 8;
  public static int GAME_OVER_SCENE = 9;
  public static int AWARD_SCENE = 10;

  public AssetManager assets;

  /**
   * Create method runs when the game starts.
   *
   * <p>Runs every scene in Game.
   */
  @Override
  public void create() {

    assets = new AssetManager();
    loadAssets();
    assets.finishLoading();

    allScenes = new Scene[11];
    allScenes[MAIN_MENU] = new SceneStartScreen();
    allScenes[GAME_SCENE] = new SceneMainGame(this);
    allScenes[OPTIONS_SCENE] = new SceneOptionsMenu();
    allScenes[TUTORIAL_SCENE] = new SceneTutorial();
    allScenes[RESULTS_SCENE] = new SceneResultsScreen();
    allScenes[BOAT_SELECT] = new SceneBoatSelection();
    allScenes[DIFFICULTY_SELECT] = new SceneDifficulty();
    allScenes[LOAD_SCENE] = new SceneLoadScreen(this);
    allScenes[SAVE_SCENE] = new SceneSaveScreen(this);
    allScenes[GAME_OVER_SCENE] = new SceneGameOver();
    allScenes[AWARD_SCENE] = new SceneAwardScreen();
    batch = new SpriteBatch();
  }

  /**
   * Render function runs every frame.
   *
   * <p>Controls functionality of frame switching.
   */
  @Override
  public void render() {
    // run the current scene
    int newSceneId = allScenes[sceneId].update();
    allScenes[sceneId].draw(batch);

    if (sceneId != newSceneId) {
      // special case updates
      if (newSceneId == RESULTS_SCENE) {
        ((SceneResultsScreen) allScenes[RESULTS_SCENE]).setBoats(
            ((SceneMainGame) allScenes[GAME_SCENE]).getAllBoats());
      } else if (newSceneId == TUTORIAL_SCENE && sceneId == DIFFICULTY_SELECT) {
        ((SceneMainGame) allScenes[GAME_SCENE]).setPlayerStats(
            ((SceneBoatSelection) allScenes[BOAT_SELECT]).getSpecId(),
            ((SceneDifficulty) allScenes[DIFFICULTY_SELECT]).getdiffLevel());
        ((SceneMainGame) allScenes[GAME_SCENE]).initialize();
      }


      // check if we need to change scene
      sceneId = newSceneId;
    }
  }

  /**
   * Disposes unneeded SpriteBatch and exits application.
   *
   * <p>Runs when the game needs to close.
   */
  @Override
  public void dispose() {
    batch.dispose();
    assets.dispose();

    Gdx.app.exit();
    System.exit(0);
  }

  /**
   * Resize used and passed to resize method of each scene based on width and height attributes.
   *
   * @param width  int for scene
   * @param height int for scene
   */
  @Override
  public void resize(int width, int height) {
    allScenes[sceneId].resize(width, height);
  }

  private void loadAssets() {

    // Load textures
    assets.load("bleachers_l.png", Texture.class);
    assets.load("bleachers_r.png", Texture.class);
    assets.load("boat_body.png", Texture.class);
    assets.load("boat_people.png", Texture.class);
    assets.load("boat_selection_debug.png", Texture.class);
    assets.load("boat_selection_default.png", Texture.class);
    assets.load("boat_selection_fastlowdurability.png", Texture.class);
    assets.load("boat_selection_screen.png", Texture.class);
    assets.load("boat.png", Texture.class);
    assets.load("difficulty_options_screen.png", Texture.class);
    assets.load("durability_texture.png", Texture.class);
    assets.load("easy_hovered.png", Texture.class);
    assets.load("easy.png", Texture.class);
    assets.load("easyButton.png", Texture.class);
    assets.load("hard_hovered.png", Texture.class);
    assets.load("hard.png", Texture.class);
    assets.load("lane_buoy.png", Texture.class);
    assets.load("load_game_hovered.png", Texture.class);
    assets.load("load_game.png", Texture.class);
    assets.load("medium_hovered.png", Texture.class);
    assets.load("medium.png", Texture.class);
    assets.load("object_placeholder.png", Texture.class);
    assets.load("obstacle_branch.png", Texture.class);
    assets.load("obstacle_duck.png", Texture.class);
    assets.load("obstacle.png", Texture.class);
    assets.load("options_menu_back_hovered.png", Texture.class);
    assets.load("options_menu_back.png", Texture.class);
    assets.load("options_menu_checkbox_no.png", Texture.class);
    assets.load("options_menu_checkbox_yes.png", Texture.class);
    assets.load("options_menu_fullscreen.png", Texture.class);
    assets.load("power_up_drag.png", Texture.class);
    assets.load("power_up_energy.png", Texture.class);
    assets.load("power_up_health.png", Texture.class);
    assets.load("power_up_rotation.png", Texture.class);
    assets.load("power_up_speed.png", Texture.class);
    assets.load("ready_hovered.png", Texture.class);
    assets.load("ready.png", Texture.class);
    assets.load("readyButton.png", Texture.class);
    assets.load("select_arrow.png", Texture.class);
    assets.load("stamina_texture.png", Texture.class);
    assets.load("start_banner.png", Texture.class);
    assets.load("start_menu_options_hovered.png", Texture.class);
    assets.load("start_menu_options.png", Texture.class);
    assets.load("start_menu_play_hovered.png", Texture.class);
    assets.load("start_menu_play.png", Texture.class);
    assets.load("start_screen.png", Texture.class);
    assets.load("temp_background.png", Texture.class);
    assets.load("tutorial_screen.png", Texture.class);
    assets.load("water_background.png", Texture.class);

  }

}
