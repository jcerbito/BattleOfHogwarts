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

/**
 * Created by HP on 30/01/2018.
 */

public class StartScreen extends DefaultScreen {
    Stage uiStage;
    private Texture bgTexture;

    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("bckgrndd.jpg"));
        Image bckgrnd = new Image(bgTexture);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;
       // buttonStyle.fontColor = Color.WHITE;

        TextButton easyBtn = new TextButton("EASY", buttonStyle);
        easyBtn.setPosition((uiStage.getWidth() - easyBtn.getWidth()) / 2, uiStage.getHeight() / 3);
        easyBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new GameScreenEasy(game));
            }
        });

        TextButton aveBtn = new TextButton("AVERAGE", buttonStyle);
        aveBtn.setPosition((uiStage.getWidth() - aveBtn.getWidth()) / 2, uiStage.getHeight() / 4);
        aveBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new GameScreenAverage(game));
            }
        });

        TextButton diffBtn = new TextButton("DIFFICULT", buttonStyle);
        diffBtn.setPosition((uiStage.getWidth() - diffBtn.getWidth()) / 2, uiStage.getHeight() / 6);
        diffBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new GameScreenDiff(game));
            }
        });




        uiStage.addActor(bckgrnd);
        uiStage.addActor(diffBtn);
        uiStage.addActor(aveBtn);
        uiStage.addActor(easyBtn);

    }

    public StartScreen(BattleOfHogwarts g){
        super(g);

        FitViewport viewport = new FitViewport(260,220); //160,120
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
