package com.jcerbito.battleofhogwarts.forbg.foreffects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;

/**
 * Created by HP on 11/01/2018.
 */

//yung pool collection ng objects for effects magkakaroon ng properties; for memory reuse
public abstract class Effect implements Pool.Poolable{
    
    protected boolean isAvailable;
    protected float timeAvailable;
    
    public Effect() {
        isAvailable = false;
        timeAvailable = 0;
    }

    @Override
    public void reset() {
        
    }
    
    //reassignment of variables
    public void init(EffectTool parent){
        isAvailable = true;
        timeAvailable = 0;
        parent.add(this);
    }
    
    public void update(float delta){
        timeAvailable += delta;
    }
    
    public abstract void draw(SpriteBatch batch, SizeEval sizeEval);
    
    public boolean isAvailable(){
        return isAvailable;
    }

    public abstract void release();
}
