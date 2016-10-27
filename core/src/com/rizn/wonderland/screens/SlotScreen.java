package com.rizn.wonderland.screens;

import com.rizn.wonderland.AdsInterface;
import com.rizn.wonderland.InputTransform;
import com.rizn.wonderland.Levels;
import com.rizn.wonderland.Save;
import com.rizn.wonderland.Sfx;
import com.rizn.wonderland.Status;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SlotScreen extends BaseScreen implements InputProcessor {
    TextureRegion intro;
    SpriteBatch batch;

    BitmapFont font;
    private Sprite slotBtn1;
    private Sprite slotBtn2;
    private Sprite slotBtn3;

    boolean isVisible = false;

    float marginX = 60;
    float marginY = 29;

    protected AdsInterface ads;

    public SlotScreen(Game game, AdsInterface ads) {
        super(game);
        this.ads = ads;
    }

    @Override
    public void show() {

        isVisible = true;

        Gdx.input.setInputProcessor(this);
        font = new BitmapFont(Gdx.files.internal("data/freesans.fnt"));

        intro = new TextureRegion(new Texture(Gdx.files.internal("data/slot.png")), 0, 0, 480, 320);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0f, 0f, 480, 320);

        //
        slotBtn1 = new Sprite(new Texture(Gdx.files.internal("data/slotBtn.png")));
        slotBtn1.setPosition(33, 160);
        slotBtn1.setSize(232, 45);

        slotBtn2 = new Sprite(new Texture(Gdx.files.internal("data/slotBtn.png")));
        slotBtn2.setPosition(33, 100);
        slotBtn2.setSize(232, 45);

        slotBtn3 = new Sprite(new Texture(Gdx.files.internal("data/slotBtn.png")));
        slotBtn3.setPosition(33, 40);
        slotBtn3.setSize(232, 45);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(intro, 0, 0);

        slotBtn1.draw(batch);
        font.draw(batch, Levels.getLevelName(Save.getLevel(1)), slotBtn1.getX() + marginX, slotBtn1.getY() + marginY);

        slotBtn2.draw(batch);
        font.draw(batch, Levels.getLevelName(Save.getLevel(2)), slotBtn2.getX() + marginX, slotBtn2.getY() + marginY);

        slotBtn3.draw(batch);
        font.draw(batch, Levels.getLevelName(Save.getLevel(3)), slotBtn3.getX() + marginX, slotBtn3.getY() + marginY);

        batch.end();
    }

    @Override
    public void hide() {

        isVisible = false;

        batch.dispose();
        intro.getTexture().dispose();
        font.dispose();
        slotBtn1.getTexture().dispose();
        slotBtn2.getTexture().dispose();
        slotBtn3.getTexture().dispose();
        Sfx.disposeMusic();
    }

    private void play() {
        //game.setScreen(new StartScreen(game));
        game.setScreen(new AdsScreen(game, this.ads));
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

            if (InputTransform.checkIfClicked(slotBtn1, pointerX, pointerY)) {
                Status.setSlot(1);
                play();
            }

            if (InputTransform.checkIfClicked(slotBtn2, pointerX, pointerY)) {
                Status.setSlot(2);
                play();
            }

            if (InputTransform.checkIfClicked(slotBtn3, pointerX, pointerY)) {
                Status.setSlot(3);
                play();
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
}
