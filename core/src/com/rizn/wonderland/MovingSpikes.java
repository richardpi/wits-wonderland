
package com.rizn.wonderland;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MovingSpikes {
    static final int FORWARD = 1;
    static final int BACKWARD = -1;
    static final float FORWARD_VEL = 10;
    static final float BACKWARD_VEL = 4;

    int state = FORWARD;

    Map map;
    Rectangle bounds = new Rectangle();
    Vector2 vel = new Vector2();
    Vector2 pos = new Vector2();
    float angle = 0;
    int fx = 0;
    int fy = 0;
    int bx = 0;
    int by = 0;

    public MovingSpikes(Map map, float x, float y) {
        this.map = map;
        pos.x = x;
        pos.y = y;
        bounds.x = x;
        bounds.y = y;
        bounds.width = bounds.height = 1;
    }

    public void init() {
        int ix = (int) pos.x;
        int iy = (int) pos.y;

        int left = map.tiles[ix - 1][map.tiles[0].length - 1 - iy];
        int right = map.tiles[ix + 1][map.tiles[0].length - 1 - iy];
        int top = map.tiles[ix][map.tiles[0].length - 1 - iy - 1];
        int bottom = map.tiles[ix][map.tiles[0].length - 1 - iy + 1];

		/*
        if (left == Map.TILE || left == Map.COLOUR_BLUE || left == Map.COLOUR_GREEN || left == Map.COLOUR_BROWN) {
			vel.x = FORWARD_VEL;
			angle = -90;
			fx = 1;
		}
		if (right == Map.TILE || right == Map.COLOUR_BLUE || right == Map.COLOUR_GREEN || right == Map.COLOUR_BROWN) {
			vel.x = -FORWARD_VEL;
			angle = 90;
			bx = 1;
		}
		*/
        if (top == Map.TILE || top == Map.COLOUR_BLUE || top == Map.COLOUR_GREEN || top == Map.COLOUR_BROWN) {
            vel.y = -FORWARD_VEL;
            angle = 0;
            by = -1;
        }
        if (bottom == Map.TILE || bottom == Map.COLOUR_BLUE || bottom == Map.COLOUR_GREEN || bottom == Map.COLOUR_BROWN) {
            vel.y = FORWARD_VEL;
            angle = 0;
            fy = -1;
        }
    }

    public void update(float deltaTime) {
        pos.add(vel.x * deltaTime, vel.y * deltaTime);
        boolean change = false;
        if (state == FORWARD) {
            int c = map.tiles[(int) pos.x + fx][map.tiles[0].length - 1 - (int) pos.y + fy];
            change = c == Map.TILE || c == Map.COLOUR_BLUE || c == Map.COLOUR_GREEN || c == Map.COLOUR_BROWN;
        } else {
            int c = map.tiles[(int) pos.x + bx][map.tiles[0].length - 1 - (int) pos.y + by];
            change = c == Map.TILE || c == Map.COLOUR_BLUE || c == Map.COLOUR_GREEN || c == Map.COLOUR_BROWN;
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
