
package com.rizn.wonderland.screens;

import com.rizn.wonderland.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class IntroScreen extends BaseScreen implements InputProcessor {
    TextureRegion intro;
    SpriteBatch batch;
    float time = 0;

    private Sprite skin;
    private Sprite credits;

    protected AdsInterface ads;

    public IntroScreen(Game game, AdsInterface ads) {
        super(game);
        this.ads = ads;
    }

    @Override
    public void show() {

        DevMode.setIsDevMode(true);

        Sfx.init();
        Sfx.setCurrentMusic(0);
        Sfx.playMusic();

        Gdx.input.setInputProcessor(this);

        intro = new TextureRegion(new Texture(Gdx.files.internal("data/title.png")), 0, 0, 480, 320);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0f, 0f, 480, 320);

        skin = new Sprite(new Texture(Gdx.files.internal("data/startBtn.png")));
        skin.setPosition(33, 60);
        skin.setSize(216, 138);

        credits = new Sprite(new Texture(Gdx.files.internal("data/creditsBtn.png")));
        credits.setPosition(380, 0);
        credits.setSize(88, 41);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(intro, 0, 0);
        skin.draw(batch);
        credits.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.F1) && DevMode.isDevMode()) {
            Sfx.disposeMusic();
            Status.setSlot(1);
            game.setScreen(new GameScreen(game, this.ads));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        intro.getTexture().dispose();
        skin.getTexture().dispose();
        credits.getTexture().dispose();
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

        float pointerX = InputTransform.getCursorToModelX(Gdx.graphics.getWidth(), screenX);
        float pointerY = InputTransform.getCursorToModelY(Gdx.graphics.getHeight(), screenY);

        if (InputTransform.checkIfClicked(skin, pointerX, pointerY)) {
            game.setScreen(new SlotScreen(game, this.ads));
        }

        if (InputTransform.checkIfClicked(credits, pointerX, pointerY)) {
            game.setScreen(new CreditsScreen(game, this.ads));
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
}
