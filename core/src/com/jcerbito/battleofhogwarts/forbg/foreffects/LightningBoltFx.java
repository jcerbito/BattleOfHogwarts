package com.jcerbito.battleofhogwarts.forbg.foreffects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;

/**
 * Created by HP on 11/01/2018.
 */

public class LightningBoltFx extends Effect {


    private static final float TIMES_UP = 2.0f ;
    private int locX;
    private int locY;
    private SizeEval sizeEval;
    private Resources resources;


    public static LightningBoltFx Create(int lX, int lY, EffectTool tool, SizeEval szEvl, Resources res){
        LightningBoltFx effect = lightningBoltFxPool.obtain();
        effect.init(lX,lY,tool,szEvl,res);
        return effect;
    }

    public LightningBoltFx() {

    }

    public void init(int lX, int lY, EffectTool parent, SizeEval szEvl, Resources res){
        locX = lX;
        locY = lY;
        sizeEval = szEvl;
        resources = res;
        super.init(parent);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(resources.lb, sizeEval.getBaseX(locX), sizeEval.getBaseY(locY));
        batch.end();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (timeAvailable > TIMES_UP){
            isAvailable = false;
        }
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
