package com.rizn.witswonderland;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class InputTransform {
    private static int appWidth = 480;
    private static int appHeight = 320;

    public static float getCursorToModelX(int screenX, int cursorX) {
        return (((float) cursorX) * appWidth) / ((float) screenX);
    }

    public static float getCursorToModelY(int screenY, int cursorY) {
        return ((float) (screenY - cursorY)) * appHeight / ((float) screenY);
    }

    public static boolean checkIfClicked(Sprite skin, float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                return true;
            }
        }
        return false;
    }
}