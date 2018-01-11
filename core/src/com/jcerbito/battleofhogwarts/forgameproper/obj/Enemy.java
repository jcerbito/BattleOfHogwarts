package com.jcerbito.battleofhogwarts.forgameproper.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;

/**
 * Created by HP on 11/01/2018.
 */

public class Enemy extends Sprite {
    public Enemy(Resources res){
        set(res.voldemort);
    }

    public void draw(SpriteBatch batch, SizeEval sizeEval){
        setPosition(sizeEval.getPosEnemyX(this), sizeEval.getPosEnemyY(this));
        super.draw(batch);
    }
}
