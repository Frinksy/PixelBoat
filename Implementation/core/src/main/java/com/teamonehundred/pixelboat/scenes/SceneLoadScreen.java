package com.teamonehundred.pixelboat.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teamonehundred.pixelboat.PixelBoat;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SceneLoadScreen.
 */
public class SceneLoadScreen implements Scene {

  Stage stage = new Stage(new ScreenViewport());
  boolean goToMenu = false;
  boolean shouldInitialize = false;
  boolean loadGame = false;
  boolean shouldDelete = false;
  PixelBoat parent;
  SelectBox<String> choose;
  Preferences prefs;
  Table layout;
  protected Texture bg;
  protected Sprite bgSprite;

  /**
   * Create a SceneLoadScreen.
   */
  public SceneLoadScreen(PixelBoat parent) {
    this.parent = parent;
    initialize();

  }

  private void initialize() {
    // Find all the saves available
    prefs = Gdx.app.getPreferences("Saves");


    Skin skin = new Skin(Gdx.files.internal("clean-crispy/clean-crispy-ui.json"));
    choose = new SelectBox<String>(skin);
    choose.setItems((String[]) prefs.get().keySet().toArray(new String[prefs.get().size()]));
    
    final TextButton return_button = new TextButton("Back to menu", skin);
    return_button.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        goToMenu = true;
      }
    });

    final TextButton load_button = new TextButton("Load Selected", skin);
    load_button.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        loadGame = true;
      }
    });

    final TextButton remove_button = new TextButton("Delete Selected", skin);
    remove_button.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        shouldDelete = true;
      }

    });
    layout = new Table();
    layout.setFillParent(true);

    layout.add(choose).colspan(4);
    layout.row().pad(20, 50, 0, 50);
    layout.add(return_button).colspan(4);
    layout.row().pad(20, 50, 0, 50);
    layout.add(load_button).colspan(4);
    layout.row().pad(20, 50, 0, 50);
    layout.add(remove_button).colspan(4);

    stage.addActor(layout);
    Gdx.input.setInputProcessor(stage);

    bg = new Texture("start_screen.png");
    bgSprite = new Sprite(bg);
    bgSprite.setPosition(0, 0);
    bgSprite.setSize(1280, 720);

  }

  @Override
  public void draw(SpriteBatch batch) {
    Gdx.gl.glClearColor(1f, 1f, 1f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();
    bgSprite.draw(batch);
    batch.end();

    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();

  }

  @Override
  public int update() {

    if (goToMenu) {
      shouldInitialize = true;
      goToMenu = false;
      // reset the race in case something failed to load
      parent.allScenes[1] = new SceneMainGame();
      return 0;
    } else if (shouldInitialize) {
      shouldInitialize = false;
      initialize();
      return 7;
    } else if (loadGame) {
      loadGame = false;
      shouldInitialize = true;
      try {
        String saveName = choose.getSelected();
        if (saveName == null) {
          shouldInitialize = false;
          return 7;
        }
        ((SceneMainGame) parent.allScenes[1]).restoreGame(saveName);
      } catch (IOException e) {
        // If it fails then do not initialize
        shouldInitialize = false;
        return 7;
      }

      loadGame = false;
      return 1;
    } else if (shouldDelete) {
      shouldDelete = false;

      // Don't do anything if the selection is empty
      if (choose.getSelected() == null) {
        return 7;
      }

      // Delete selected save from the preferenes
      prefs.remove(choose.getSelected());
      prefs.flush();

      // Regenerate the choose button
      choose.clearItems();
      choose.setItems((prefs.get().keySet().toArray(new String[prefs.get().size()])));

      return 7;


    }

    return 7;

  }

  @Override
  public void resize(int width, int height) {
    // TODO Auto-generated method stub

  }



}
