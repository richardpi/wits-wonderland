package com.rizn.witswonderland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class Sfx {

    public static Music currentMusic;

    public static Music intro;
    public static Music music;
    public static Music music2;

    public static Sound sound;
    public static Sound jump;
    public static Sound die;

    public static boolean init = false;

    public static void init() {

        sound = Gdx.audio.newSound(Gdx.files.internal("data/button-37.ogg"));
        jump = Gdx.audio.newSound(Gdx.files.internal("data/jump2.ogg"));
        die = Gdx.audio.newSound(Gdx.files.internal("data/die.ogg"));

        intro = Gdx.audio.newMusic(Gdx.files.internal("data/welcome_screen.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("data/jungle_run.ogg"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("data/little_swans.ogg"));

    }

    public static void setCurrentMusic(int level) {
        switch (level) {
            case 0:
                currentMusic = intro;
                currentMusic.setVolume(0.7f);
                break;

            case 1:
            case 2:
            case 3:
                currentMusic = music;
                currentMusic.setVolume(0.7f);
                break;

            case 4:
            case 5:
            case 6:
                currentMusic = music2;
                currentMusic.setVolume(1f);
                break;

            default:
                throw new IllegalArgumentException("sfx: level doesn't exist");
        }
    }

    public static void playMusic() {
        currentMusic.play();
        currentMusic.setLooping(true);
    }

    public static void disposeMusic() {
        if (null != currentMusic) {
            currentMusic.stop();
            currentMusic.dispose();
        }
    }

    public static boolean isPlaying() {

        if (null == currentMusic) {
            return  false;
        }

        return currentMusic.isPlaying();
    }
}
