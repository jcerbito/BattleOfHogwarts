package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.forgameproper.GameProperEasy;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeDiff;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;

/**
 * Created by HP on 30/01/2018.
 */

public class SplashScreen extends DefaultScreen {
    Stage uiStage;
    private Texture bgTexture;

    private float timeRender = 3.0f;
    private int cntTimeRender;

    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("logo.jpg"));
        Image bckgrnd = new Image(bgTexture);

        uiStage.addActor(bckgrnd);

    }

    public SplashScreen(BattleOfHogwarts g){
        super(g);

        FitViewport viewport = new FitViewport(412,350); //160,120
        uiStage = new Stage(viewport);

        Gdx.input.setInputProcessor(uiStage);
        startUI();
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cntTimeRender = (int)timeRender;
        timeRender -= delta;

        if (cntTimeRender == 0){
            game.setScreen(new StartScreen(game));
        }

        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void dispose(){
        Gdx.input.setInputProcessor(null);
        uiStage.dispose();
        super.dispose();
    }

}
