
package com.rizn.wonderland.colours;

import com.rizn.wonderland.Counter;
import com.rizn.wonderland.Map;
import com.rizn.wonderland.Sfx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Colour {
    public Rectangle bounds = new Rectangle();
    public Vector2 pos = new Vector2();
    Map map;
    boolean selected = false;

    TextureRegion colour;
    TextureRegion colourFill;

    TextureRegion defaultTexture;

    public Colour(Map map, float x, float y) {
        this.map = map;
        bounds.x = x;
        bounds.y = y;
        bounds.width = bounds.height = 1;

        pos.x = bounds.x;
        pos.y = bounds.y;
    }

    public void setTextures(TextureRegion defaultTexture, TextureRegion filledTexture) {
        this.colour = defaultTexture;
        this.colourFill = filledTexture;
        this.defaultTexture = this.colour;
    }

    public void pressed() {

        if (false == selected) {
            this.defaultTexture = this.colourFill;
            Counter.addCount();
            selected = true;
            Sfx.sound.play();
        }
    }

    public TextureRegion getTexture() {
        return this.defaultTexture;
    }
}
