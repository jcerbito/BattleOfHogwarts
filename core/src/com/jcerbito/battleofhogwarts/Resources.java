package com.jcerbito.battleofhogwarts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;

import java.util.HashMap;

/**
 * Created by HP on 09/01/2018.
 */

public class Resources {
    TextureAtlas gameSprite;

    public TextureRegion tile1;
    public TextureRegion tile2;
    public TextureRegion lb;
    public TextureRegion tile3;
    public Sprite hp;
  //  public Sprite voldemort;
    public BitmapFont gamefont;
    public Sprite wand;
    public Sprite wandy;
    public Sprite heart;

    public static final int TILE_SIZE = 25;
    public static final int DE_1 = 0;
    public static final int DE_2 = 1;
    public static final int DE_3 = 2;
    public static final int VOLDEMORT = 3;

    public HashMap<Integer, Sprite> deathEater;
    public HashMap<Integer, Sprite> deathEaterEasy;
    public HashMap<Integer, Sprite> deathEaterAverage;

    public Resources(){
        gameSprite = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        tile1 = gameSprite.findRegion("tile1");
        tile2 = gameSprite.findRegion("tile2");
        tile3 = gameSprite.findRegion("tile3");
        hp = new Sprite(gameSprite.findRegion("hp"));
        //voldemort = new Sprite(gameSprite.findRegion("voldemort"));
        lb = gameSprite.findRegion("lb");
        gamefont = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);
        wand  = new Sprite(gameSprite.findRegion("wand"));
        heart = new Sprite(gameSprite.findRegion("heart"));
        wandy = new Sprite(gameSprite.findRegion("wandy"));
        deathEater = new HashMap<Integer, Sprite>();
        deathEaterEasy = new HashMap<Integer, Sprite>();
        deathEaterAverage = new HashMap<Integer, Sprite>();
        deathEaterEasy.put(DE_1, gameSprite.createSprite("de1"));
        deathEaterEasy.put(DE_2, gameSprite.createSprite("de2"));
        deathEaterAverage.put(DE_1, gameSprite.createSprite("de1"));
        deathEaterAverage.put(DE_2, gameSprite.createSprite("de2"));
        deathEaterAverage.put(DE_3, gameSprite.createSprite("de3"));
        deathEater.put(DE_1, gameSprite.createSprite("de1"));
        deathEater.put(DE_2, gameSprite.createSprite("de2"));
        deathEater.put(DE_3, gameSprite.createSprite("de3"));
        deathEater.put(VOLDEMORT, gameSprite.createSprite("voldemort"));
    }

    public void dispose(){
        gameSprite.dispose();
    }
}
