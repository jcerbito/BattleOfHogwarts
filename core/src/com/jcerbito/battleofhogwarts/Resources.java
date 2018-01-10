package com.jcerbito.battleofhogwarts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by HP on 09/01/2018.
 */

public class Resources {
    TextureAtlas gameSprite;

    public TextureRegion tile1;
    public TextureRegion tile2;
    public TextureRegion tile3;
    public Sprite hp;


    public static final int TILE_SIZE = 25;

    public Resources(){
        gameSprite = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        tile1 = gameSprite.findRegion("tile1");
        tile2 = gameSprite.findRegion("tile2");
        tile3 = gameSprite.findRegion("tile3");
        hp = new Sprite(gameSprite.findRegion("hp"));
    }

    public void dispose(){
        gameSprite.dispose();
    }
}
