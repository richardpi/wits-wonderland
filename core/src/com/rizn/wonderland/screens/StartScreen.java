
package com.rizn.wonderland.screens;

import com.rizn.wonderland.AdsInterface;
import com.rizn.wonderland.Levels;
import com.rizn.wonderland.Save;
import com.rizn.wonderland.Sfx;
import com.rizn.wonderland.Status;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StartScreen extends BaseScreen {
    TextureRegion intro;
    SpriteBatch batch;
    float time = 0;

    BitmapFont font;

    protected AdsInterface ads;

    public StartScreen(Game game, AdsInterface ads) {
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

        if (0 == Save.getLevel(Status.getSlot())) {
            Save.setLevel(Status.getSlot(), 1);
        }

        font = new BitmapFont(Gdx.files.internal("data/freesans.fnt"));

        String startScreen = Levels.getLevelStartScreen(Save.getLevel(Status.getSlot()));
        intro = new TextureRegion(new Texture(Gdx.files.internal(startScreen)), 0, 0, 480, 320);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(intro, 0, 0);

        font.draw(batch, Levels.getLevelName(Save.getLevel(Status.getSlot())), 73, 113);

        batch.end();

        time += delta;

        if (time > 1) {
            if (time > 3 || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()) {
                game.setScreen(new GameScreen(game, this.ads));
            }
        }

    }

    @Override
    public void resume() {
        super.resume();
        game.setScreen(new IntroScreen(game, this.ads));
    }

    @Override
    public void hide() {
        Sfx.disposeMusic();
        batch.dispose();
        font.dispose();
        intro.getTexture().dispose();
    }

}
