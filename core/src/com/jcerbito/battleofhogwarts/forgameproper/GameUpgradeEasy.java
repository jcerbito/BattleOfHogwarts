package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.jcerbito.battleofhogwarts.screens.GameScreenEasy;
import com.jcerbito.battleofhogwarts.screens.StartScreen;

//import static com.jcerbito.battleofhogwarts.forgameproper.GameProperEasy.gameTime;


/**
 * Created by HP on 25/01/2018.
 */

public class GameUpgradeEasy {
    public static int pLives = 3;
    public static int maxPLives = 3;
    public static int pDamage = 1;
    public static int currentLvl = 1;

    public static int ulock;

    public static int checker;

    public static int chrlck = 0;
    public static int chrlckk = 1;

    public static int moveNextLevel = 0;

    private static final String UPDT_NAME = "update2";

    private static final String SAVE_LIVES = "lives";
    private static final String SAVE_MAX_LIVES = "livesmax";
    private static final String SAVE_CURRENT_LEVEL = "currentlevel";
    private static final String SAVE_DAMAGE = "playerdamage";

    private static final String UNLOCK_KEY = "unlocklevel";
    private static final String CHCK_KEY = "checker";

    private static final String MOVE_NEXT = "movenextlevel";




//    private static final String CHARLOCK_KEY = "charlock";
//    private static final String CHARLOCK_KEYY = "charlockk";


    public static int getEnemyLives(){
        return 1 + currentLvl * 2;

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
        pref.putInteger(MOVE_NEXT, moveNextLevel);
//        pref.putInteger(CHARLOCK_KEY, chrlck);
//        pref.putInteger(CHARLOCK_KEYY, chrlckk);
        pref.flush();
    }

    public static void Load(){
        Preferences pref = Gdx.app.getPreferences(UPDT_NAME);
        pLives = pref.getInteger(SAVE_LIVES, 3);
        maxPLives = pref.getInteger(SAVE_MAX_LIVES, 3);
        currentLvl = pref.getInteger(SAVE_CURRENT_LEVEL, 1);
        pDamage = pref.getInteger(SAVE_DAMAGE, 1);
        ulock = pref.getInteger(UNLOCK_KEY);
        checker = pref.getInteger(CHCK_KEY);
        moveNextLevel = pref.getInteger(MOVE_NEXT);
//        chrlck = pref.getInteger(CHARLOCK_KEY);
//        chrlckk = pref.getInteger(CHARLOCK_KEYY);
    }

    public static void Reset() {
        pLives = 3;
        maxPLives = 3;
        currentLvl  = 1;
        pDamage = 1;
        checker = 1;
        GameScreenEasy.timePast = 16;
    }

    public static void ResetSecondLevel() {
        pLives = 3;
        maxPLives = 3;
        currentLvl  = 2;
        pDamage = 1;
        GameScreenEasy.timePast = 21;
    }

    public static void ResetThirdLevel() {
        pLives = 3;
        maxPLives = 3;
        currentLvl  = 3;
        pDamage = 1;
        GameScreenEasy.timePast = 31;
    }



}


