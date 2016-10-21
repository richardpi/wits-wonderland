package com.rizn.wonderland;

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

    public static void init() {
        sound = Gdx.audio.newSound(Gdx.files.internal("data/button-37.mp3"));
        jump = Gdx.audio.newSound(Gdx.files.internal("data/jump2.wav"));
        die = Gdx.audio.newSound(Gdx.files.internal("data/die.wav"));

        intro = Gdx.audio.newMusic(Gdx.files.internal("data/welcome_screen.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("data/jungle-run-01.mp3"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("data/little_swans.ogg"));
    }

    public static void setCurrentMusic(int level) {
        switch (level) {
            case 0:
                currentMusic = intro;
                break;

            case 1:
            case 2:
            case 3:
                currentMusic = music;
                break;

            case 4:
                currentMusic = music2;
                break;

            default:
                throw new IllegalArgumentException("sfx: level doesn't exist");
        }
    }

    public static void playMusic() {
        currentMusic.play();
        currentMusic.setVolume(0.7f);
        currentMusic.setLooping(true);
    }

    public static void disposeMusic() {
        currentMusic.stop();
    }
}
