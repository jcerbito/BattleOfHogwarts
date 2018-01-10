package com.jcerbito.battleofhogwarts.forgameproper.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by HP on 10/01/2018.
 */

public class Player extends Sprite {

    private int locX;
    private int locY;



    public Player(int lX, int lY){
        locX = lX;
        locY = lY;
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

}
