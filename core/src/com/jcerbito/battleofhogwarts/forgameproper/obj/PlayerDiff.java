package com.jcerbito.battleofhogwarts.forgameproper.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgrade;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeDiff;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;
import com.jcerbito.battleofhogwarts.screens.ChooseCharacter;
import com.jcerbito.battleofhogwarts.screens.ChooseCharacterAverage;
import com.jcerbito.battleofhogwarts.screens.ChooseCharacterDiff;

/**
 * Created by HP on 10/01/2018.
 */

public class PlayerDiff extends ObjectEffect {

    private int locX;
    private int locY;


    private final int maxLives;


    public PlayerDiff(int lX, int lY, Resources res, int lvs){
        super(lvs);
        locX = lX;
        locY = lY;

        if (ChooseCharacterDiff.charLockDiff == 1){
            set(res.hp);
        }else if (ChooseCharacterDiff.charLockDiff == 2){
            set(res.hg);
        }else if (ChooseCharacterDiff.charLockDiff == 3){
            set(res.hag);
        }else if (ChooseCharacterDiff.charLockDiff == 4){
            set(res.rw);
        }

        maxLives = GameUpgradeDiff.maxPLives;


    }

    public int getLocX(){
        return locX;
    }

    public void setLocX(int lX){
        locX = lX;
    }

    public int getLocY(){
        return locY;
    }

    public void setLocY(int lY){
        locY = lY;
    }

    public void draw(SpriteBatch batch, SizeEval sizeEval){
        preDraw();
        setPosition(sizeEval.getBaseX(locX), sizeEval.getBaseY(locY));
        super.draw(batch);
        postDraw();
    }


    public void addLives(int numLives) {
        lives += numLives;
        if (lives > maxLives){
            lives = maxLives;
        }
    }
}
