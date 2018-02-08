package com.jcerbito.battleofhogwarts.forgameproper.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;

/**
 * Created by HP on 17/01/2018.
 */

public class Equipment extends Sprite implements Pool.Poolable {

    public static byte WAND = 0;
    public static byte WAND2 = 2;
    public static byte HEART = 1;

    private int locY;
    private int locX;

    private byte Eq;




    public Equipment(){

    }

    public void setup(int lx, int ly, byte eq , Resources res){
        locX = lx;
        locY = ly;
        Eq = eq;

        if (Eq == WAND){
            set(res.wand);
        }else if (Eq == HEART){
            set(res.heart);
        }else if(Eq == WAND2){
            set(res.wandy);
        }

    }

    public static Equipment Create(int lx, int ly, byte eq, Resources res){
        Equipment equipment = equipmentPool.obtain();
        equipment.setup(lx, ly, eq, res);
        return equipment;
    }

    public void draw(SpriteBatch batch, SizeEval sizeEval){
        setPosition(sizeEval.getBaseX(locX), sizeEval.getBaseY(locY));
        super.draw(batch);
    }

    public int getLocX(){
        return locX;
    }

    public int getLocY(){
        return locY;
    }

    public byte getEq(){
        return Eq;
    }


    @Override
    public void reset() {

    }

    public void release(){
        equipmentPool.free(this);
    }

    static final Pool<Equipment> equipmentPool = new Pool<Equipment>() {
        @Override
        protected Equipment newObject() {
            return new Equipment();
        }
    };

}
