
package com.rizn.wonderland;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MovingVulture {

    public enum Start {
        LEFT, RIGHT
    }

    static final int FORWARD = 1;
    static final int BACKWARD = -1;
    static final float FORWARD_VEL = 4;
    static final float BACKWARD_VEL = 4;

    int state = FORWARD;

    Map map;
    Rectangle bounds = new Rectangle();
    Vector2 vel = new Vector2();
    Vector2 pos = new Vector2();
    float angle = 0;

    public MovingVulture(Map map, float x, float y) {
        this.map = map;
        pos.x = x;
        pos.y = y;
        bounds.x = x;
        bounds.y = y;
        bounds.width = bounds.height = 1;
    }

    public void init(Start start) {
        if (Start.RIGHT == start) {
            vel.x = FORWARD_VEL;
            state = BACKWARD;
        }

        if (Start.LEFT == start) {
            vel.x = -FORWARD_VEL;
            state = FORWARD;
        }
    }

    public void update(float deltaTime) {
        pos.add(vel.x * deltaTime, vel.y * deltaTime);
        boolean change = false;
        if (state == FORWARD) {
            int c = map.tiles[(int) pos.x][map.tiles[0].length - (int) pos.y - 1];
            change = c == Map.MOVING_FLY_WALL;
        } else {
            int c = map.tiles[(int) pos.x + 1][map.tiles[0].length - (int) pos.y - 1];
            change = c == Map.MOVING_FLY_WALL;
        }
        if (change) {
            pos.x -= vel.x * deltaTime;
            pos.y -= vel.y * deltaTime;
            state = -state;
            vel.scl(-1);
            if (state == FORWARD) vel.nor().scl(FORWARD_VEL);
            if (state == BACKWARD) vel.nor().scl(BACKWARD_VEL);
        }

        bounds.x = pos.x;
        bounds.y = pos.y;

        if (map.bob.bounds.overlaps(bounds)) {
            if (map.bob.state != Bob.DYING) {
                map.bob.state = Bob.DYING;
                map.bob.stateTime = 0;
            }
        }
    }
}
