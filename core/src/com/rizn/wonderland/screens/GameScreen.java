
package com.rizn.wonderland.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.rizn.wonderland.*;

public class GameScreen extends BaseScreen {
    Map map;
    MapRenderer renderer;
    OnscreenControlRenderer controlRenderer;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        map = new Map();
        renderer = new MapRenderer(map);
        controlRenderer = new OnscreenControlRenderer(map);
        Sfx.setCurrentMusic(Save.getLevel(Status.getSlot()));
        Sfx.playMusic();
    }

    @Override
    public void render(float delta) {

        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        map.update(delta);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(delta);
        controlRenderer.render();

        if (Counter.checkAllSelected() || (Gdx.input.isKeyPressed(Keys.F10) && DevMode.isDevMode()) || (DevMode.checkIfDevPressed() && DevMode.isDevMode())) {

            if (Levels.MAX_LEVELS == Save.getLevel(Status.getSlot())) {
                endGame();
            } else {
                Save.setLevel(Status.getSlot(), Save.getLevel(Status.getSlot()) + 1);
                gameOver();
            }
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            gameOver();
        }
    }

    @Override
    public void hide() {
        renderer.dispose();
    }

    private void gameOver() {
        Counter.reset();
        Sfx.disposeMusic();
        game.setScreen(new StartScreen(game));
    }

    private void endGame() {
        Counter.reset();
        Sfx.disposeMusic();
        game.setScreen(new EndScreen(game));
    }
}
