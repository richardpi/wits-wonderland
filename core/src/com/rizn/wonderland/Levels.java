package com.rizn.wonderland;

public class Levels {

    public static int MAX_LEVELS = 6;

    public static String LEVEL_NAME_START = "New Game";

    public static String LEVEL_TILES_WORLD_1 = "data/world1.png";
    public static String LEVEL_TILES_WORLD_2 = "data/world2.png";

    public static String LEVEL_START_SCREEN_WORLD_1 = "data/start_1.png";
    public static String LEVEL_START_SCREEN_WORLD_2 = "data/start_2.png";

    public static String LEVEL_BACKGROUND_WORLD_1 = "data/background.png";
    public static String LEVEL_BACKGROUND_WORLD_2 = "data/backgroundDesert.png";

    public static String LEVEL_MAP_1 = "data/levels.png";
    public static String LEVEL_NAME_1 = "World 1 - 1";
    public static int LEVEL_BACKGROUND_1_X = 0;
    public static int LEVEL_BACKGROUND_1_Y = 11;
    public static int LEVEL_BACKGROUND_1_WIDTH = 100;
    public static int LEVEL_BACKGROUND_1_HEIGHT = 80;

    public static String LEVEL_MAP_2 = "data/levels2.png";
    public static String LEVEL_NAME_2 = "World 1 - 2";
    public static int LEVEL_BACKGROUND_2_X = 0;
    public static int LEVEL_BACKGROUND_2_Y = 21;
    public static int LEVEL_BACKGROUND_2_WIDTH = 100;
    public static int LEVEL_BACKGROUND_2_HEIGHT = 80;

    public static String LEVEL_MAP_3 = "data/levels3.png";
    public static String LEVEL_NAME_3 = "World 1 - 3";
    public static int LEVEL_BACKGROUND_3_X = 0;
    public static int LEVEL_BACKGROUND_3_Y = 31;
    public static int LEVEL_BACKGROUND_3_WIDTH = 100;
    public static int LEVEL_BACKGROUND_3_HEIGHT = 80;

    //

    public static String LEVEL_MAP_4 = "data/levels4.png";
    public static String LEVEL_NAME_4 = "World 2 - 1";
    public static int LEVEL_BACKGROUND_4_X = 0;
    public static int LEVEL_BACKGROUND_4_Y = 20;
    public static int LEVEL_BACKGROUND_4_WIDTH = 100;
    public static int LEVEL_BACKGROUND_4_HEIGHT = 80;

    public static String LEVEL_MAP_5 = "data/levels5.png";
    public static String LEVEL_NAME_5 = "World 2 - 2";
    public static int LEVEL_BACKGROUND_5_X = 0;
    public static int LEVEL_BACKGROUND_5_Y = 30;
    public static int LEVEL_BACKGROUND_5_WIDTH = 100;
    public static int LEVEL_BACKGROUND_5_HEIGHT = 80;

    public static String LEVEL_MAP_6 = "data/levels6.png";
    public static String LEVEL_NAME_6 = "World 2 - 3";
    public static int LEVEL_BACKGROUND_6_X = 0;
    public static int LEVEL_BACKGROUND_6_Y = 40;
    public static int LEVEL_BACKGROUND_6_WIDTH = 100;
    public static int LEVEL_BACKGROUND_6_HEIGHT = 80;

    public static String getLevelTiles(int level) {

        switch (level) {
            case 1:
            case 2:
            case 3:
                return LEVEL_TILES_WORLD_1;

            case 4:
            case 5:
            case 6:
                return LEVEL_TILES_WORLD_2;

            default:
                throw new IllegalArgumentException("level doesn't exist");
        }

    }

    public static String getLevelStartScreen(int level) {

        switch (level) {
            case 1:
            case 2:
            case 3:
                return LEVEL_START_SCREEN_WORLD_1;

            case 4:
            case 5:
            case 6:
                return LEVEL_START_SCREEN_WORLD_2;

            default:
                throw new IllegalArgumentException("level doesn't exist");
        }

    }

    public static String getLevelMap(int level) {

        switch (level) {
            case 1:
                return LEVEL_MAP_1;

            case 2:
                return LEVEL_MAP_2;

            case 3:
                return LEVEL_MAP_3;

            case 4:
                return LEVEL_MAP_4;

            case 5:
                return LEVEL_MAP_5;

            case 6:
                return LEVEL_MAP_6;

            default:
                throw new IllegalArgumentException("level doesn't exist");
        }

    }

    public static String getLevelName(int level) {

        switch (level) {
            case 0:
                return LEVEL_NAME_START;

            case 1:
                return LEVEL_NAME_1;

            case 2:
                return LEVEL_NAME_2;

            case 3:
                return LEVEL_NAME_3;

            case 4:
                return LEVEL_NAME_4;

            case 5:
                return LEVEL_NAME_5;

            case 6:
                return LEVEL_NAME_6;

            default:
                throw new IllegalArgumentException("level doesn't exist");
        }

    }

    public static String getLevelBackground(int level) {

        switch (level) {
            case 1:
            case 2:
            case 3:
                return LEVEL_BACKGROUND_WORLD_1;

            case 4:
            case 5:
            case 6:
                return LEVEL_BACKGROUND_WORLD_2;

            default:
                throw new IllegalArgumentException("background doesn't exist");
        }

    }

    public static int[] getLevelBackgroundCoord(int level) {

        switch (level) {
            case 1:
                return new int[]{LEVEL_BACKGROUND_1_X, LEVEL_BACKGROUND_1_Y, LEVEL_BACKGROUND_1_WIDTH, LEVEL_BACKGROUND_1_HEIGHT};

            case 2:
                return new int[]{LEVEL_BACKGROUND_2_X, LEVEL_BACKGROUND_2_Y, LEVEL_BACKGROUND_2_WIDTH, LEVEL_BACKGROUND_2_HEIGHT};

            case 3:
                return new int[]{LEVEL_BACKGROUND_3_X, LEVEL_BACKGROUND_3_Y, LEVEL_BACKGROUND_3_WIDTH, LEVEL_BACKGROUND_3_HEIGHT};

            case 4:
                return new int[]{LEVEL_BACKGROUND_4_X, LEVEL_BACKGROUND_4_Y, LEVEL_BACKGROUND_4_WIDTH, LEVEL_BACKGROUND_4_HEIGHT};

            case 5:
                return new int[]{LEVEL_BACKGROUND_5_X, LEVEL_BACKGROUND_5_Y, LEVEL_BACKGROUND_5_WIDTH, LEVEL_BACKGROUND_5_HEIGHT};

            case 6:
                return new int[]{LEVEL_BACKGROUND_6_X, LEVEL_BACKGROUND_6_Y, LEVEL_BACKGROUND_6_WIDTH, LEVEL_BACKGROUND_6_HEIGHT};

            default:
                throw new IllegalArgumentException("background coord doesn't exist");
        }

    }

}
