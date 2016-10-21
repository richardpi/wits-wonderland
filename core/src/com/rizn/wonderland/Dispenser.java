
package com.rizn.wonderland;

import com.badlogic.gdx.math.Rectangle;

public class Dispenser {
    Rectangle bounds = new Rectangle();

    public Dispenser(float x, float y) {
        bounds.x = x;
        bounds.y = y;
        bounds.width = bounds.height = 1;
    }
}
