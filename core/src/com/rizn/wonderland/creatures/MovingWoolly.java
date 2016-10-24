
package com.rizn.wonderland.creatures;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rizn.wonderland.Bob;
import com.rizn.wonderland.Map;

public class MovingWoolly {

    public enum Start {
        LEFT, RIGHT
    }

    public static final int FORWARD = 1;
    public static final int BACKWARD = -1;
    public static final float FORWARD_VEL = 5;
    public static final float BACKWARD_VEL = 5;

    public int state = FORWARD;

    public Map map;
    public Rectangle bounds = new Rectangle();
    public Vector2 vel = new Vector2();
    public Vector2 pos = new Vector2();
    public float angle = 0;

    public MovingWoolly(Map map, float x, float y) {
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
            int c = map.tiles[(int) pos.x][map.tiles[0].length - (int) pos.y];
            change = c == Map.EMPTY || c == Map.SPIKES;
        } else {
            int c = map.tiles[(int) pos.x + 1][map.tiles[0].length - (int) pos.y];
            change = c == Map.EMPTY || c == Map.SPIKES;
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
