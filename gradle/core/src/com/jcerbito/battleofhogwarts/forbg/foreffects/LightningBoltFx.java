package com.jcerbito.battleofhogwarts.forbg.foreffects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;

/**
 * Created by HP on 11/01/2018.
 */

public class LightningBoltFx extends Effect {


    private static final float TIMES_UP = 0.75f ;
    private int locX;
    private int locY;
    private Resources resources;

    public interface LightningBoltFxListener{
      public void OnEffect(LightningBoltFx effect);
    };

    private LightningBoltFxListener listener;

    public static LightningBoltFx Create(int lX, int lY, EffectTool tool, Resources res, LightningBoltFxListener lstnr){
        LightningBoltFx effect = lightningBoltFxPool.obtain();
        effect.init(lX,lY,tool,res, lstnr);
        return effect;
    }

    public LightningBoltFx() {

    }

    public void init(int lX, int lY, EffectTool parent, Resources res, LightningBoltFxListener lstnr){
        listener = lstnr;
        locX = lX;
        locY = lY;
        resources = res;
        super.init(parent);
    }

//    public void init(int lX, int lY, EffectTool parent, Resources res){
//        locX = lX;
//        locY = lY;
//        resources = res;
//        super.init(parent);
//    }

    @Override
    public void draw(SpriteBatch batch, SizeEval sizeEval) {
        batch.begin();
        batch.draw(resources.lb, sizeEval.getBaseX(locX), sizeEval.getBaseY(locY));
        batch.end();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (timeAvailable > TIMES_UP && isAvailable){
            isAvailable = false;
            if (listener != null){
                listener.OnEffect(this);
            }
        }
    }

    //dito yung part na kapag yung locY and locX nagmatch sa effect position mababawasan ng life yung player
    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    @Override
    public void release() {
        lightningBoltFxPool.free(this);
    }

    static Pool<LightningBoltFx> lightningBoltFxPool = new Pool<LightningBoltFx>() {
        @Override
        protected LightningBoltFx newObject() {
            return new LightningBoltFx();
        }
    };
}
