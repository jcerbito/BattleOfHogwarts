package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;

/**
 * Created by HP on 30/01/2018.
 */

public class GameOverScreen extends DefaultScreen {
    Stage uiStage;
    private Texture bgTexture;
    private Texture goTexture;



    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("gameoverbggg.jpg"));
        Image bckgrnd = new Image(bgTexture);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;
        // buttonStyle.fontColor = Color.WHITE;

        TextButton easyBtn = new TextButton("Click here to go back", buttonStyle);
        TextButton gameOverBtn = new TextButton("YOU LOSE!", buttonStyle);


        gameOverBtn.setPosition((uiStage.getWidth() - easyBtn.getWidth()) / 2 + 50, uiStage.getHeight() / 2);
        gameOverBtn.getLabel().setFontScale(2,2);

        easyBtn.setPosition((uiStage.getWidth() - easyBtn.getWidth()) / 2, uiStage.getHeight() / 3);
        easyBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new StartScreen(game));
                if (GameUpgradeEasy.currentLvl == 1){
                    GameScreenEasy.cntTime = 16;
                }else if (GameUpgradeAverage.currentLvl == 1){
                    GameScreenAverage.cntTime = 21;
                }
            }
        });


        uiStage.addActor(bckgrnd);
        uiStage.addActor(gameOverBtn);
        uiStage.addActor(easyBtn);

    }

    public GameOverScreen(BattleOfHogwarts g){
        super(g);
        FitViewport viewport = new FitViewport(340,250); //160,120
        uiStage = new Stage(viewport);
        Gdx.input.setInputProcessor(uiStage);
        startUI();
    }



    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
