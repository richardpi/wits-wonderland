
package com.rizn.witswonderland.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rizn.witswonderland.AdsInterface;
import com.rizn.witswonderland.Sfx;

public class CreditsScreen extends BaseScreen  implements InputProcessor {
    TextureRegion intro;
    SpriteBatch batch;
    float time = 0;

    protected AdsInterface ads;

    public CreditsScreen(Game game, AdsInterface ads) {
        super(game);
        this.ads = ads;
    }

    @Override
    public void show() {

        if (!Sfx.isPlaying()) {
            Sfx.init();
            Sfx.setCurrentMusic(0);
            Sfx.playMusic();
        }

        Gdx.input.setInputProcessor(this);

        intro = new TextureRegion(new Texture(Gdx.files.internal("data/credits.png")), 0, 0, 480, 320);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0f, 0f, 480, 320);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(intro, 0, 0);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (time > 1) {
                gotoIntro();
            }
        }
    }

    @Override
    public void resume() {
        super.resume();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            game.setScreen(new IntroScreen(game, this.ads));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        intro.getTexture().dispose();
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
        gotoIntro();

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

    private void gotoIntro() {
        game.setScreen(new IntroScreen(game, this.ads));
    }
}
