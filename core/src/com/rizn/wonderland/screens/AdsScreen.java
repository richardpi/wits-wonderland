
package com.rizn.wonderland.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rizn.wonderland.AdsInterface;
import com.rizn.wonderland.Levels;
import com.rizn.wonderland.Save;
import com.rizn.wonderland.Sfx;
import com.rizn.wonderland.Status;

public class AdsScreen extends BaseScreen {
    TextureRegion intro;
    SpriteBatch batch;
    float time = 0;

    BitmapFont font;

    protected AdsInterface ads;

    public AdsScreen(Game game, AdsInterface ads) {
        super(game);
        this.ads = ads;
    }

    @Override
    public void show() {
        intro = new TextureRegion(new Texture(Gdx.files.internal("data/interim.png")), 0, 0, 480, 320);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(intro, 0, 0);
        batch.end();

        if (ads != null) {

            if(ads.isWifiConnected()) {
                ads.showInterstitialAd(new Runnable() {
                    @Override
                    public void run() {
                        //todo: go to game start
                        Gdx.app.exit();
                    }
                });
            }

        } else {
            //todo: go to game start
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        intro.getTexture().dispose();
    }

}
