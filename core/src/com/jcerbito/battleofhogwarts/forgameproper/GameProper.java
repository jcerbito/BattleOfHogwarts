package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.math.MathUtils;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.forbg.foreffects.Effect;
import com.jcerbito.battleofhogwarts.forbg.foreffects.EffectTool;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Enemy;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;

/**
 * Created by HP on 10/01/2018.
 */

public class GameProper {

    public static final int MAX_BASEX = 11;
    public static final int MAX_BASEY = 5;
    BattleOfHogwarts game;

    Player player;
    Enemy enemy;
    EffectTool effectTool;

    public GameProper(BattleOfHogwarts g) {
        game = g;
        player = new Player(MathUtils.random(MAX_BASEX), MathUtils.random(MAX_BASEY), game.res);
        enemy = new Enemy(game.res);
        effectTool = new EffectTool();
    }

    public Player getPlayer(){
        return player;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public void update(float delta){
        effectTool.update(delta);
    }


    // for movement
    public boolean ForMove(int lX, int lY){
        return (lX >= 0 && lX <= MAX_BASEX) && (lY >= 0 && lY <= MAX_BASEY);
    }

    public void PlayerPos(int lX, int lY){
        player.setLocX(lX);
        player.setLocY(lY);
    }

    public EffectTool getEffectTool(){
        return effectTool;
    }


}
