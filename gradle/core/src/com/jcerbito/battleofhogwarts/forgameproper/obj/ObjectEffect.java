package com.jcerbito.battleofhogwarts.forgameproper.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by HP on 18/01/2018.
 */

public class ObjectEffect extends Sprite {

    protected int lives;
    private float damageTime;
    protected float aliveTime;
    public static final float OBJ_FX_AFT_DMG = 0.25f;

    public ObjectEffect (int lvs){
        lives = lvs;
        aliveTime = 0;
        damageTime = -1;
    }

    public int getLives(){
        return lives;
    }

    public void damage(int val){
        damageTime = aliveTime;
        lives -= val;
        if (lives < 0){
            lives = 0;
        }
    }
    public  void update(float delta){
        aliveTime += delta;
    }

    public void preDraw(){
        if (aliveTime < damageTime + OBJ_FX_AFT_DMG ){
            float t = (aliveTime - damageTime) / OBJ_FX_AFT_DMG;
            t = t * t;
            setColor(1,1,1,t);
        }
    }

    public void postDraw(){
        setColor(1,1,1,1);
    }

    public float getDamageTime(){
        return damageTime;
    }

    public float getAliveTime(){
        return aliveTime;
    }
}
