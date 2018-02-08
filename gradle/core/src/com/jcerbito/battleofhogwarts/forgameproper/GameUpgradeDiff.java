package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.jcerbito.battleofhogwarts.screens.GameScreenDiff;


/**
 * Created by HP on 25/01/2018.
 */

public class GameUpgradeDiff {
    public static int pLives = 8;
    public static int maxPLives = 8;
    public static int pDamage = 1;
    public static int currentLvl = 1;

    public static int ulock;

    public static int checker;

    private static final String UPDT_NAME = "update3";

    private static final String SAVE_LIVES = "lives";
    private static final String SAVE_MAX_LIVES = "livesmax";
    private static final String SAVE_CURRENT_LEVEL = "currentlevel";
    private static final String SAVE_DAMAGE = "playerdamage";

    private static final String UNLOCK_KEY = "unlocklevel";
    private static final String CHCK_KEY = "checker";

    public static int getEnemyLives(){
        return 4 + currentLvl * 2;
    }

    public static int getLevel(){
        return currentLvl;
    }

    public static void Save(){
        Preferences pref  = Gdx.app.getPreferences(UPDT_NAME);
        pref.putInteger(SAVE_LIVES, pLives);
        pref.putInteger(SAVE_MAX_LIVES, maxPLives);
        pref.putInteger(SAVE_CURRENT_LEVEL, currentLvl);
        pref.putInteger(SAVE_DAMAGE, pDamage);
        pref.putInteger(UNLOCK_KEY, ulock);
        pref.putInteger(CHCK_KEY, checker);
        pref.flush();
    }

    public static void Load(){
        Preferences pref = Gdx.app.getPreferences(UPDT_NAME);
        pLives = pref.getInteger(SAVE_LIVES, 8);
        maxPLives = pref.getInteger(SAVE_MAX_LIVES, 8);
        currentLvl = pref.getInteger(SAVE_CURRENT_LEVEL, 1);
        pDamage = pref.getInteger(SAVE_DAMAGE, 1);
        ulock = pref.getInteger(UNLOCK_KEY);
        checker = pref.getInteger(CHCK_KEY);
    }

    public static void Reset() {
        pLives = 8;
        maxPLives = 8;
        currentLvl  = 1;
        pDamage = 1;
        checker = 1;
        GameScreenDiff.timePast = 26;
    }

    public static void ResetSecondLevel() {
        pLives = 8;
        maxPLives = 8;
        currentLvl  = 2;
        pDamage = 1;
        GameScreenDiff.timePast = 36;
    }

    public static void ResetThirdLevel() {
        pLives = 8;
        maxPLives = 8;
        currentLvl  = 3;
        pDamage = 1;
        GameScreenDiff.timePast = 46;
    }
}


