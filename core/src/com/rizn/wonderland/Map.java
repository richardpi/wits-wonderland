
package com.rizn.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.rizn.wonderland.colours.*;

public class Map {
    static int EMPTY = 0;
    static int TILE = 0xffffff;
    static int COLOUR_BLUE = 0x00ccaa;
    static int COLOUR_GREEN = 0xbbee88;
    static int COLOUR_BROWN = 0xdd7700;
    static int COLOUR_PURPLE = 0xdd33dd;
    static int START = 0xff0000;
    static int SPIKES = 0x00ff00;
    static int MOVING_SPIKES = 0xffff00;
    static int MOVING_FROG = 0x443300;
    static int MOVING_WOOLLY = 0xff5500;
    static int MOVING_BEE = 0x773333;
    static int MOVING_VULTURE = 0xddff66;
    static int MOVING_FLY_WALL = 0x553333;
    static int OUTER = 0xaaaaaa;
    static int LEFT_TILE = 0xeeeeee;
    static int RIGHT_TILE = 0xdddddd;
    static int WATER_DEEP = 0xaaffaa;
    static int GROUND_DEEP = 0x888888;

    int[][] tiles;
    public Bob bob;
    Array<Colour> colours = new Array<Colour>();
    Dispenser dispenser = null;
    Array<MovingSpikes> movingSpikes = new Array<MovingSpikes>();
    Array<MovingFrog> movingFrogs = new Array<MovingFrog>();
    Array<MovingWoolly> movingWoollies = new Array<MovingWoolly>();
    Array<MovingBee> movingBees = new Array<MovingBee>();
    Array<MovingVulture> movingVultures = new Array<MovingVulture>();

    public Map() {
        loadBinary();
    }

    private void loadBinary() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(Levels.getLevelMap(Save.getLevel(Status.getSlot()))));
        tiles = new int[pixmap.getWidth()][pixmap.getHeight()];
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 100; x++) {
                int pix = (pixmap.getPixel(x, y) >>> 8) & 0xffffff;
                if (match(pix, START)) {
                    dispenser = new Dispenser(x, pixmap.getHeight() - 1 - y);
                    bob = new Bob(this, dispenser.bounds.x, dispenser.bounds.y);
                } else if (match(pix, MOVING_SPIKES)) {
                    movingSpikes.add(new MovingSpikes(this, x, pixmap.getHeight() - 1 - y));
                } else if (match(pix, MOVING_FROG)) {
                    movingFrogs.add(new MovingFrog(this, x, pixmap.getHeight() - 1 - y));
                } else if (match(pix, MOVING_BEE)) {
                    movingBees.add(new MovingBee(this, x, pixmap.getHeight() - 1 - y));
                } else if (match(pix, MOVING_VULTURE)) {
                    movingVultures.add(new MovingVulture(this, x, pixmap.getHeight() - 1 - y));
                } else if (match(pix, MOVING_WOOLLY)) {
                    movingWoollies.add(new MovingWoolly(this, x, pixmap.getHeight() - 1 - y));
                } else if (match(pix, COLOUR_BLUE)) {
                    Colour colour = new ColourBlue(this, x, pixmap.getHeight() - 1 - y);
                    colours.add(colour);
                    tiles[x][y] = pix;  //register also as tile, so stworki can move
                } else if (match(pix, COLOUR_GREEN)) {
                    Colour colour = new ColourGreen(this, x, pixmap.getHeight() - 1 - y);
                    colours.add(colour);
                    tiles[x][y] = pix;  //register also as tile, so stworki can move
                } else if (match(pix, COLOUR_BROWN)) {
                    Colour colour = new ColourBrown(this, x, pixmap.getHeight() - 1 - y);
                    colours.add(colour);
                    tiles[x][y] = pix;  //register also as tile, so stworki can move
                } else if (match(pix, COLOUR_PURPLE)) {
                    Colour colour = new ColourPurple(this, x, pixmap.getHeight() - 1 - y);
                    colours.add(colour);
                    tiles[x][y] = pix;  //register also as tile, so stworki can move
                } else {
                    tiles[x][y] = pix;
                }
            }
        }

        for (int i = 0; i < movingSpikes.size; i++) {
            movingSpikes.get(i).init();
        }
        for (int i = 0; i < movingFrogs.size; i++) {
            if (i % 2 == 0) {
                movingFrogs.get(i).init(MovingFrog.Start.RIGHT);
            } else {
                movingFrogs.get(i).init(MovingFrog.Start.LEFT);
            }
        }
        for (int i = 0; i < movingWoollies.size; i++) {
            if (i % 2 == 0) {
                movingWoollies.get(i).init(MovingWoolly.Start.RIGHT);
            } else {
                movingWoollies.get(i).init(MovingWoolly.Start.LEFT);
            }
        }
        for (int i = 0; i < movingBees.size; i++) {
            if (i % 2 == 0) {
                movingBees.get(i).init(MovingBee.Start.RIGHT);
            } else {
                movingBees.get(i).init(MovingBee.Start.LEFT);
            }
        }
        for (int i = 0; i < movingVultures.size; i++) {
            if (i % 2 == 0) {
                movingVultures.get(i).init(MovingVulture.Start.RIGHT);
            } else {
                movingVultures.get(i).init(MovingVulture.Start.LEFT);
            }
        }

        //set counter total
        Counter.total = colours.size;
        System.out.println("Counter.total: " + Counter.total);
    }

    boolean match(int src, int dst) {
        return src == dst;
    }

    public void update(float deltaTime) {
        bob.update(deltaTime);
        if (bob.state == Bob.DEAD) bob = new Bob(this, dispenser.bounds.x, dispenser.bounds.y);

        for (int i = 0; i < movingSpikes.size; i++) {
            MovingSpikes spikes = movingSpikes.get(i);
            spikes.update(deltaTime);
        }
        for (int i = 0; i < movingFrogs.size; i++) {
            MovingFrog frog = movingFrogs.get(i);
            frog.update(deltaTime);
        }
        for (int i = 0; i < movingWoollies.size; i++) {
            MovingWoolly woolly = movingWoollies.get(i);
            woolly.update(deltaTime);
        }
        for (int i = 0; i < movingBees.size; i++) {
            MovingBee bee = movingBees.get(i);
            bee.update(deltaTime);
        }
        for (int i = 0; i < movingVultures.size; i++) {
            MovingVulture vulture = movingVultures.get(i);
            vulture.update(deltaTime);
        }
    }

    public boolean isDeadly(int tileId) {
        if (tileId == SPIKES) {
            return true;
        } else {
            return false;
        }
    }
}
