package com.rizn.wonderland;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public class DevMode {

    public static boolean isDevMode = false;

    public static boolean isDevMode() {
        return isDevMode;
    }

    public static void setIsDevMode(boolean isDevMode) {
        DevMode.isDevMode = isDevMode;

        if (isDevMode) {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        }

    }

    public static boolean checkIfDevPressed() {
        float x0 = (Gdx.input.getX(0) / (float) Gdx.graphics.getWidth()) * 480;
        float x1 = (Gdx.input.getX(1) / (float) Gdx.graphics.getWidth()) * 480;
        float y0 = 320 - (Gdx.input.getY(0) / (float) Gdx.graphics.getHeight()) * 320;

        return (Gdx.input.isTouched(0) && x0 > 416 && x0 < 480 && y0 > 260);
    }
}
