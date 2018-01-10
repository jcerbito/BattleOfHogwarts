package com.jcerbito.battleofhogwarts.forbg;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jcerbito.battleofhogwarts.Resources;

/**
 * Created by HP on 10/01/2018.
 */

public class SizeEval {
    private Stage StageMeasure;
    private Resources resources;
    //for stage
    private final int maxTileBaseY;
    private final int maxTileBaseX;

    public SizeEval(Stage stg, Resources res, int maxBaseX, int maxBaseY){
        StageMeasure = stg;
        resources = res;
        maxTileBaseX = maxBaseX;
        maxTileBaseY = maxBaseY;
    }

    public float getBaseX(int baseX){
        return StageMeasure.getWidth() / 2 - resources.TILE_SIZE * ((maxTileBaseX + 2) / 2 - baseX) ; // - resources.TILE_SIZE * (maxTileBaseX + 1 - baseX)
    }

    public float getBaseY(int baseY){
        return StageMeasure.getHeight() / 2 - ((resources.TILE_SIZE) * 4 / 5) * ((maxTileBaseY + 3) / 2 - baseY);
    }


}
