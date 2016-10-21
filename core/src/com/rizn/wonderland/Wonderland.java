
package com.rizn.wonderland;

import com.rizn.wonderland.screens.IntroScreen;
import com.badlogic.gdx.Game;

public class Wonderland extends Game {
    @Override
    public void create() {
        setScreen(new IntroScreen(this));
    }
}
