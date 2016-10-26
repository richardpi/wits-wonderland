package com.rizn.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Lives {

    public static int INIT_LIVES = 3;
    public static int lives;
    Map map;
    SpriteBatch batch;

    TextureRegion empty;
    TextureRegion full;

    public Lives(Map map) {
        lives = INIT_LIVES;
        this.map = map;
        loadAssets();
    }

    private void loadAssets() {
        Texture texture = new Texture(Gdx.files.internal("data/live.png"));
        TextureRegion[] buttons = TextureRegion.split(texture, 10, 10)[0];
        empty = buttons[0];
        full = buttons[1];

        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
    }

    public static void reduce() {
        lives--;
    }

    public void render() {
        batch.begin();
        batch.draw(lives >= 1 ? full : empty, 460, 300);
        batch.draw(lives >= 2 ? full : empty, 445, 300);
        batch.draw(lives >= 3 ? full : empty, 430, 300);

        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }

}
