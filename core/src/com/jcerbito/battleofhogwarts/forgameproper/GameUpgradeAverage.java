package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * Created by HP on 25/01/2018.
 */

public class GameUpgradeAverage {
    public static int pLives = 5;
    public static int maxPLives = 5;
    public static int pDamage = 1;
    public static int currentLvl = 0;

    private static final String UPDT_NAME = "update";

    private static final String SAVE_LIVES = "lives";
    private static final String SAVE_MAX_LIVES = "livesmax";
    private static final String SAVE_CURRENT_LEVEL = "currentlevel";
    private static final String SAVE_DAMAGE = "playerdamage";


    public static int getEnemyLives(){
        return 5 + currentLvl * 2;
    }

    public static void Save(){
        Preferences pref  = Gdx.app.getPreferences(UPDT_NAME);
        pref.putInteger(SAVE_LIVES, pLives);
        pref.putInteger(SAVE_MAX_LIVES, maxPLives);
        pref.putInteger(SAVE_CURRENT_LEVEL, currentLvl);
        pref.putInteger(SAVE_DAMAGE, pDamage);
        pref.flush();
    }

    public static void Load(){
        Preferences pref = Gdx.app.getPreferences(UPDT_NAME);
        pLives = pref.getInteger(SAVE_LIVES, 5);
        maxPLives = pref.getInteger(SAVE_MAX_LIVES, 5);
        currentLvl = pref.getInteger(SAVE_CURRENT_LEVEL, 0);
        pDamage = pref.getInteger(SAVE_DAMAGE, 1);
    }

    public static void Reset() {
        pLives = 5;
        maxPLives = 5;
        currentLvl  = 0;
        pDamage = 1;
    }
}


