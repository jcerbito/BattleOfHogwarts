package com.jcerbito.battleofhogwarts.forbg;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgrade;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;

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
                if (GameUpgradeEasy.currentLvl == 1 || GameUpgradeAverage.currentLvl == 2 ){
                    stage.getBatch().draw(res.tile2, x, y, 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.01f, 1.01f, 0);
                }else if (GameUpgradeEasy.currentLvl == 2 || GameUpgradeAverage.currentLvl == 1  ){
                    stage.getBatch().draw(res.tile4, x, y, 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.01f, 1.01f, 0);
                }else if (GameUpgradeEasy.currentLvl == 3 || GameUpgradeAverage.currentLvl == 3  ){
                    stage.getBatch().draw(res.tile11, x, y, 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.01f, 1.01f, 0);
                }

            }
        }

        for (int y = 0; y <= stage.getHeight(); y += Resources.TILE_SIZE) {
            for (int x = 0; x <= stage.getWidth(); x += Resources.TILE_SIZE) {
                if (GameUpgradeEasy.currentLvl == 1 || GameUpgradeAverage.currentLvl == 2 ){
                    stage.getBatch().draw(res.tile1, x, y + ((stage.getHeight() / 2) + 90), 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.00f, 1.00f, 0 ); //+ (Resources.TILE_SIZE + (stage.getHeight() / 2) + 68)
                }else if (GameUpgradeEasy.currentLvl == 2 || GameUpgradeAverage.currentLvl == 1 ){
                    stage.getBatch().draw(res.tile2, x, y + ((stage.getHeight() / 2) + 90), 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.00f, 1.00f, 0 ); //+ (Resources.TILE_SIZE + (stage.getHeight() / 2) + 68)
                }else if (GameUpgradeEasy.currentLvl == 3  || GameUpgradeAverage.currentLvl == 3){
                    stage.getBatch().draw(res.tile4, x, y + ((stage.getHeight() / 2) + 90), 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.00f, 1.00f, 0 ); //+ (Resources.TILE_SIZE + (stage.getHeight() / 2) + 68)
                }
            }
        }


        stage.getBatch().end();
    }

 //this start//
//    public void drawOne(Stage stage, Resources res){
//        stage.getBatch().begin();
//
//        for (int y = 0; y <= stage.getHeight(); y += Resources.TILE_SIZE){
//            for (int x = 0; x <= stage.getWidth(); x += Resources.TILE_SIZE) {
//                stage.getBatch().draw(res.tile4, x, y, 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.01f, 1.01f, 0);
//            }
//        }
//
//        for (int y = 0; y <= stage.getHeight(); y += Resources.TILE_SIZE) {
//            for (int x = 0; x <= stage.getWidth(); x += Resources.TILE_SIZE) {
//                stage.getBatch().draw(res.tile2, x, y + ((stage.getHeight() / 2) + 90), 0, 0, Resources.TILE_SIZE, Resources.TILE_SIZE, 1.00f, 1.00f, 0 ); //+ (Resources.TILE_SIZE + (stage.getHeight() / 2) + 68)
//            }
//        }
//
//
//        stage.getBatch().end();
//    }

}
