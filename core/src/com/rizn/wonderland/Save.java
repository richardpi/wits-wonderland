package com.rizn.wonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Save {

    public static int getLevel(int slot) {
        return Save.getLevelPref(slot).getInteger("level");
    }

    public static void setLevel(int slot, int level) {
        Preferences pref = getLevelPref(slot);
        pref.putInteger("level", level);
        pref.flush();
    }

    private static Preferences getLevelPref(int slot) {
        return getPrefs("slot_" + slot);
    }

    private static Preferences getPrefs(String name) {
        Preferences preferences = Gdx.app.getPreferences(name);

        return preferences;
    }

}
