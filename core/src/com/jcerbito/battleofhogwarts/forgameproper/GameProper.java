package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.math.MathUtils;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;

/**
 * Created by HP on 10/01/2018.
 */

public class GameProper {

    public static final int MAX_BASEX = 11;
    public static final int MAX_BASEY = 5;

    Player player;

    public GameProper() {
        player = new Player(MathUtils.random(MAX_BASEX), MathUtils.random(MAX_BASEY));
    }

    public Player getPlayer(){
        return player;
    }

    // for movement
    public boolean ForMove(int lX, int lY){
        return (lX >= 0 && lX <= MAX_BASEX) && (lY >= 0 && lY <= MAX_BASEY);
    }

    public void PlayerPos(int lX, int lY){
        player.setLocX(lX);
        player.setLocY(lY);
    }


}
