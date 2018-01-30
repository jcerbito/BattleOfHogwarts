package com.jcerbito.battleofhogwarts.forbg;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jcerbito.battleofhogwarts.Resources;

/**
 * Created by HP on 09/01/2018.
 */

public class Background {


    public Background(){

    }

    public void draw(Stage stage, Resources res){
        stage.getBatch().begin();

        for (int y = 0; y <= stage.getHeight(); y += Resources.TILE_SIZE){
            for (int x = 0; x <= stage.getWidth(); x += Resources.TILE_SIZE) {
                stage.getBatch().draw(res.tile2, x, y, 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.01f, 1.01f, 0);
            }
        }

        for (int y = 0; y <= stage.getHeight(); y += Resources.TILE_SIZE) {
            for (int x = 0; x <= stage.getWidth(); x += Resources.TILE_SIZE) {
                stage.getBatch().draw(res.tile1, x, y + ((stage.getHeight() / 2) + 90), 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.00f, 1.00f, 0 ); //+ (Resources.TILE_SIZE + (stage.getHeight() / 2) + 68)
            }
        }



        stage.getBatch().end();
    }
}
