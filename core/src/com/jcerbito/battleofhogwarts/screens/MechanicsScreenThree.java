package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

/**
 * Created by HP on 30/01/2018.
 */

public class MechanicsScreenThree extends DefaultScreen {
    Stage uiStage;
    private Texture bgTexture;
    private Texture potionTexture;
    private Texture helpOne;
    private Texture helpTwo;

    private Texture help;
    private TextureRegion helpRegion;
    private TextureRegionDrawable helpDrawable;
    private ImageButton helpButton;



    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("bckgrnddd.jpg"));
        Image bckgrnd = new Image(bgTexture);

        helpOne = new Texture(Gdx.files.internal("help3.png"));
        Image hOne = new Image(helpOne);
        hOne.setPosition(0, uiStage.getHeight() / 5);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;

        TextButton homeBtn = new TextButton("PREVIOUS", buttonStyle);
        homeBtn.setPosition((uiStage.getWidth() - homeBtn.getWidth()) / 2 - 80, 25);

        homeBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new MechanicsScreenTwo(game));
            }
        });

        TextButton nextBtn = new TextButton("NEXT", buttonStyle);
        nextBtn.setPosition((uiStage.getWidth() - nextBtn.getWidth()) / 2 + 80, 25);

        nextBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new MechanicsScreenFour(game));
            }
        });



        /*

        HELP IN SAVING HOGWARTS FROM DEATH EATERS' WRATH! DEFEAT THEM BY COLLECTING WANDS AND
        AVOIDING SET OF LIGHTNING BOLTS COMING YOUR WAY. THE FARTHER THE LEVEL, THE HARDER IT IS TO DEFEAT THEM.
        COLLECT MORE POTIONS TO INCREASE LIFE. WHEN MAXIMUM LIFE POINTS REACHED, ANY POTIONS THAT WILL BE COLLECTED
        SINCE THEN WILL BE ADDED TO POINTS TO UNLOCK OTHER CHARACTERS.

        REQUIRED POINTS TO UNLOCK:
        HERMIONE GRANGER - 30
        HAGRID - 50
        RON - 70




         */

        uiStage.addActor(bckgrnd);
        uiStage.addActor(hOne);
        uiStage.addActor(homeBtn);
        uiStage.addActor(nextBtn);


    }



    public MechanicsScreenThree(BattleOfHogwarts g){
        super(g);

        FitViewport viewport = new FitViewport(300,220); //160,120
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
