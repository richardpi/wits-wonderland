
package com.rizn.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.rizn.wonderland.colours.*;

public class MapRenderer {
    Map map;
    OrthographicCamera cam;
    SpriteCache cache;
    SpriteBatch batch = new SpriteBatch(5460);
    int[][] blocks;
    TextureRegion tile;
    TextureRegion tileLeft;
    TextureRegion tileRight;
    Animation bobLeft;
    Animation bobRight;
    Animation bobJumpLeft;
    Animation bobJumpRight;
    Animation bobIdleLeft;
    Animation bobIdleRight;
    Animation bobDead;
    TextureRegion dispenser;
    Animation dying;
    TextureRegion spikes;
    TextureRegion movingSpikes;
    TextureRegion roof;

    //colours
    TextureRegion colour;
    TextureRegion colourBlueFill;
    TextureRegion colourGreenFill;
    TextureRegion colourBrownFill;
    TextureRegion colourPurpleFill;

    //deep
    TextureRegion deepGround;
    TextureRegion deepWater;

    //
    Animation frogTexture;
    TextureRegion currentFrogFrame;
    Animation woollyTexture;
    TextureRegion currentWoollyFrame;
    Animation beeTextureLeft;
    Animation beeTextureRight;
    TextureRegion currentBeeFrame;
    Animation vultureTextureLeft;
    Animation vultureTextureRight;
    TextureRegion currentVultureFrame;

    FPSLogger fps = new FPSLogger();

    Texture background;

    ShapeRenderer shapeRenderer;

    public MapRenderer(Map map) {
        shapeRenderer = new ShapeRenderer();
        this.map = map;
        this.cam = new OrthographicCamera(24, 16);
        this.cam.position.set(map.bob.pos.x, map.bob.pos.y, 0);
        this.cache = new SpriteCache(this.map.tiles.length * this.map.tiles[0].length, false);
        this.blocks = new int[(int) Math.ceil(this.map.tiles.length / 24.0f)][(int) Math.ceil(this.map.tiles[0].length / 16.0f)];

        createBackground();
        createAnimations();
        createBlocks();

        //set textures for colour (roof for now)
        for (int i = 0; i < map.colours.size; i++) {
            Colour col = map.colours.get(i);

            TextureRegion colourFill = colourBlueFill;  //default colour
            if (col instanceof ColourBlue) {
                colourFill = colourBlueFill;
            }
            if (col instanceof ColourGreen) {
                colourFill = colourGreenFill;
            }
            if (col instanceof ColourBrown) {
                colourFill = colourBrownFill;
            }
            if (col instanceof ColourPurple) {
                colourFill = colourPurpleFill;
            }

            col.setTextures(colour, colourFill);
        }
    }

    private void createBackground() {
        this.background = new Texture(Gdx.files.internal(Levels.getLevelBackground(Save.getLevel(Status.getSlot()))));
    }

    private void createBlocks() {
        int width = map.tiles.length;
        int height = map.tiles[0].length;
        for (int blockY = 0; blockY < blocks[0].length; blockY++) {
            for (int blockX = 0; blockX < blocks.length; blockX++) {
                cache.beginCache();
                for (int y = blockY * 16; y < blockY * 16 + 16; y++) {
                    for (int x = blockX * 24; x < blockX * 24 + 24; x++) {
                        if (x > width) continue;
                        if (y > height) continue;
                        int posX = x;
                        int posY = height - y - 1;
                        if (map.match(map.tiles[x][y], Map.TILE)) cache.add(tile, posX, posY, 1, 1);
                        if (map.match(map.tiles[x][y], Map.SPIKES)) cache.add(spikes, posX, posY, 1, 1);
                        if (map.match(map.tiles[x][y], Map.LEFT_TILE)) cache.add(tileLeft, posX, posY, 1, 1);
                        if (map.match(map.tiles[x][y], Map.RIGHT_TILE)) cache.add(tileRight, posX, posY, 1, 1);
                        if (map.match(map.tiles[x][y], Map.GROUND_DEEP)) cache.add(deepGround, posX, posY, 1, 1);
                        if (map.match(map.tiles[x][y], Map.WATER_DEEP)) cache.add(deepWater, posX, posY, 1, 1);
                    }
                }
                blocks[blockX][blockY] = cache.endCache();
            }
        }
    }

    private void createAnimations() {
        Texture tileTexture = new Texture(Gdx.files.internal(Levels.getLevelTiles(Save.getLevel(Status.getSlot()))));
        TextureRegion[] tileSplit = new TextureRegion(tileTexture).split(20, 20)[2];
        this.tile = tileSplit[0];
        this.tileLeft = tileSplit[1];
        this.tileRight = tileSplit[2];

        //bob
        Texture bobTexture = new Texture(Gdx.files.internal("data/character.png"));
        TextureRegion[] split = new TextureRegion(bobTexture).split(20, 20)[0];
        TextureRegion[] mirror = new TextureRegion(bobTexture).split(20, 20)[0];
        for (TextureRegion region : mirror)
            region.flip(true, false);
        bobRight = new Animation(0.1f, split[0], split[1]);
        bobLeft = new Animation(0.1f, mirror[0], mirror[1]);
        bobJumpRight = new Animation(0.1f, split[2], split[3]);
        bobJumpLeft = new Animation(0.1f, mirror[2], mirror[3]);
        bobIdleRight = new Animation(0.5f, split[0], split[4]);
        bobIdleLeft = new Animation(0.5f, mirror[0], mirror[4]);
        bobDead = new Animation(0.2f, split[0]);

        split = new TextureRegion(bobTexture).split(20, 20)[1];
        dying = new Animation(0.1f, split[1], split[2], split[3], split[4]);
        dispenser = split[5];

        split = new TextureRegion(bobTexture).split(20, 20)[2];
        colour = split[0];
        colourBlueFill = split[1];
        colourGreenFill = split[2];
        colourBrownFill = split[3];
        colourPurpleFill = split[4];

        //

        split = new TextureRegion(tileTexture).split(20, 20)[0];
        spikes = split[1];  //water top
        movingSpikes = split[0];
        roof = split[3];

        //deep
        split = new TextureRegion(tileTexture).split(20, 20)[1];
        deepGround = split[0];
        deepWater = split[1];

        //creatures
        Texture creaturesTexture = new Texture(Gdx.files.internal("data/creatures.png"));

        split = new TextureRegion(creaturesTexture).split(20, 20)[0];
        frogTexture = new Animation(0.5f, split[0], split[1]);

        //bee
        split = new TextureRegion(creaturesTexture).split(20, 20)[1];
        TextureRegion[] mirrorBee = new TextureRegion(creaturesTexture).split(20, 20)[1];
        for (TextureRegion region : mirrorBee)
            region.flip(true, false);
        beeTextureLeft = new Animation(0.5f, split[0], split[1]);
        beeTextureRight = new Animation(0.5f, mirrorBee[0], mirrorBee[1]);

        //vulture
        split = new TextureRegion(creaturesTexture).split(20, 20)[3];
        TextureRegion[] mirrorVulture = new TextureRegion(creaturesTexture).split(20, 20)[3];
        for (TextureRegion region : mirrorVulture)
            region.flip(true, false);
        vultureTextureLeft = new Animation(0.5f, split[0], split[1]);
        vultureTextureRight = new Animation(0.5f, mirrorVulture[0], mirrorVulture[1]);

        split = new TextureRegion(creaturesTexture).split(20, 20)[2];
        woollyTexture = new Animation(0.5f, split[0], split[1]);
    }

    float stateTime = 0;
    Vector3 lerpTarget = new Vector3();

    public void render(float deltaTime) {
        cam.position.lerp(lerpTarget.set(map.bob.pos.x, map.bob.pos.y, 0), 2f * deltaTime);
        cam.update();

        stateTime += deltaTime;
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        renderBackground();
        renderDispensers();
        renderMovingSpikes();
        renderColours();
        renderBob();
        renderMovingBees(stateTime);
        renderMovingVultures(stateTime);
        renderMovingFrogs(stateTime);
        renderMovingWoollies(stateTime);
        batch.end();

        cache.setProjectionMatrix(cam.combined);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        cache.begin();
        for (int blockY = 0; blockY < 5; blockY++) {
            for (int blockX = 0; blockX < 5; blockX++) {
                cache.draw(blocks[blockX][blockY]);
            }
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
        cache.end();

        //fps.log();
    }

    private void renderBob() {
        Animation anim = null;
        boolean loop = true;
        if (map.bob.state == Bob.RUN) {
            if (map.bob.dir == Bob.LEFT)
                anim = bobLeft;
            else
                anim = bobRight;
        }
        if (map.bob.state == Bob.IDLE) {
            if (map.bob.dir == Bob.LEFT)
                anim = bobIdleLeft;
            else
                anim = bobIdleRight;
        }
        if (map.bob.state == Bob.JUMP) {
            if (map.bob.dir == Bob.LEFT)
                anim = bobJumpLeft;
            else
                anim = bobJumpRight;
        }
        if (map.bob.state == Bob.DYING) {
            anim = dying;
            loop = false;
        }
        batch.draw(anim.getKeyFrame(map.bob.stateTime, loop), map.bob.pos.x, map.bob.pos.y, 1, 1);
    }

    private void renderBackground() {
        int[] coord = Levels.getLevelBackgroundCoord(Save.getLevel(Status.getSlot()));
        batch.draw(this.background, coord[0], coord[1], coord[2], coord[3]);
    }

    private void renderDispensers() {
        batch.draw(this.dispenser, map.dispenser.bounds.x, map.dispenser.bounds.y, 1, 1);
    }

    private void renderColours() {
        for (int i = 0; i < map.colours.size; i++) {
            Colour colour = map.colours.get(i);
            batch.draw(colour.getTexture(), colour.bounds.x, colour.bounds.y, 1, 1);
        }
    }

    private void renderMovingFrogs(float stateTime) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrogFrame = frogTexture.getKeyFrame(stateTime, true);

        for (int i = 0; i < map.movingFrogs.size; i++) {
            MovingFrog frog = map.movingFrogs.get(i);
            batch.draw(currentFrogFrame, frog.pos.x, frog.pos.y, 0.5f, 0.5f, 1, 1, 1, 1, frog.angle);
        }
    }

    private void renderMovingWoollies(float stateTime) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentWoollyFrame = woollyTexture.getKeyFrame(stateTime, true);

        for (int i = 0; i < map.movingWoollies.size; i++) {
            MovingWoolly woolly = map.movingWoollies.get(i);
            batch.draw(currentWoollyFrame, woolly.pos.x, woolly.pos.y, 0.5f, 0.5f, 1, 1, 1, 1, woolly.angle);
        }
    }

    private void renderMovingBees(float stateTime) {
        stateTime += Gdx.graphics.getDeltaTime();

        for (int i = 0; i < map.movingBees.size; i++) {
            MovingBee bee = map.movingBees.get(i);

            if (MovingBee.FORWARD == bee.state) {
                currentBeeFrame = beeTextureLeft.getKeyFrame(stateTime, true);
            } else {
                currentBeeFrame = beeTextureRight.getKeyFrame(stateTime, true);
            }

            batch.draw(currentBeeFrame, bee.pos.x, bee.pos.y, 0.5f, 0.5f, 1, 1, 1, 1, bee.angle);
        }
    }

    private void renderMovingVultures(float stateTime) {
        stateTime += Gdx.graphics.getDeltaTime();

        for (int i = 0; i < map.movingVultures.size; i++) {
            MovingVulture vulture = map.movingVultures.get(i);

            if (MovingVulture.FORWARD == vulture.state) {
                currentVultureFrame = vultureTextureLeft.getKeyFrame(stateTime, true);
            } else {
                currentVultureFrame = vultureTextureRight.getKeyFrame(stateTime, true);
            }

            batch.draw(currentVultureFrame, vulture.pos.x, vulture.pos.y, 0.5f, 0.5f, 1, 1, 1, 1, vulture.angle);
        }
    }

    private void renderMovingSpikes() {
        for (int i = 0; i < map.movingSpikes.size; i++) {
            MovingSpikes spikes = map.movingSpikes.get(i);
            batch.draw(movingSpikes, spikes.pos.x, spikes.pos.y, 0.5f, 0.5f, 1, 1, 1, 1, spikes.angle);
        }
    }

    public void dispose() {
        cache.dispose();
        batch.dispose();
        tile.getTexture().dispose();
    }
}
