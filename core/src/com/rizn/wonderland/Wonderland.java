
package com.rizn.wonderland;

import com.badlogic.gdx.Gdx;
import com.rizn.wonderland.screens.AdsScreen;
import com.rizn.wonderland.screens.IntroScreen;
import com.badlogic.gdx.Game;

public class Wonderland extends Game {

    protected AdsInterface ads;

    public Wonderland() {}

    public Wonderland(AdsInterface ads) {
        this.ads = ads;
    }

    @Override
    public void create() {
        setScreen(new AdsScreen(this, this.ads));
        setScreen(new IntroScreen(this, this.ads));
    }

    @Override
    public void dispose() {
        // Clear you Screen Explicitly
        getScreen().dispose();
        Gdx.app.exit();
    }

    @Override
    public void pause () {
        //getScreen().dispose();
        //Gdx.app.exit();
    }
}
