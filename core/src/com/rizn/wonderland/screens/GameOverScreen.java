
package com.rizn.wonderland.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rizn.wonderland.AdsInterface;
import com.rizn.wonderland.InputTransform;
import com.rizn.wonderland.Sfx;

public class GameOverScreen extends BaseScreen implements InputProcessor {
    TextureRegion intro;
    SpriteBatch batch;
    float time = 0;

    Sprite mainmenuBtn;
    Sprite tryagainBtn;

    boolean isVisible = false;

    protected AdsInterface ads;

    public GameOverScreen(Game game, AdsInterface ads) {
        super(game);
        this.ads = ads;
    }

    @Override
    public void show() {
        isVisible = true;
        Gdx.input.setInputProcessor(this);

        Sfx.init();
        Sfx.setCurrentMusic(0);
        Sfx.playMusic();

        intro = new TextureRegion(new Texture(Gdx.files.internal("data/gameover.png")), 0, 0, 480, 320);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0f, 0f, 480, 320);

        mainmenuBtn = new Sprite(new Texture(Gdx.files.internal("data/mainmenuBtn.png")));
        mainmenuBtn.setPosition(280, 0);
        mainmenuBtn.setSize(180, 163);

        tryagainBtn = new Sprite(new Texture(Gdx.files.internal("data/tryagainBtn.png")));
        tryagainBtn.setPosition(15, 0);
        tryagainBtn.setSize(180, 163);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(intro, 0, 0);
        mainmenuBtn.draw(batch);
        tryagainBtn.draw(batch);
        batch.end();
    }

    @Override
    public void hide() {
        isVisible = false;
        batch.dispose();
        intro.getTexture().dispose();
        mainmenuBtn.getTexture().dispose();
        tryagainBtn.getTexture().dispose();
        Sfx.disposeMusic();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (isVisible) {
            float pointerX = InputTransform.getCursorToModelX(Gdx.graphics.getWidth(), screenX);
            float pointerY = InputTransform.getCursorToModelY(Gdx.graphics.getHeight(), screenY);

            if (InputTransform.checkIfClicked(mainmenuBtn, pointerX, pointerY)) {
                goMainMenu();
            }

            if (InputTransform.checkIfClicked(tryagainBtn, pointerX, pointerY)) {
                goTryAgain();
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void goMainMenu() {
        game.setScreen(new IntroScreen(game, this.ads));
    }

    private void goTryAgain() {
        game.setScreen(new AdsScreen(game, this.ads));
    }

}
