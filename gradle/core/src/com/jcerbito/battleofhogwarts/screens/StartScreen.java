package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;

/**
 * Created by HP on 30/01/2018.
 */

public class StartScreen extends DefaultScreen {
    Stage uiStage;
    private Texture bgTexture;
    private Texture potionTexture;

    private Texture help;
    private TextureRegion helpRegion;
    private TextureRegionDrawable helpDrawable;
    private ImageButton helpButton;

    public static int val = 0;

    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("bckgrnddd.jpg"));
        Image bckgrnd = new Image(bgTexture);

        potionTexture = new Texture(Gdx.files.internal("heart.png"));
        Image potion = new Image(potionTexture);
        potion.setPosition(5, (uiStage.getHeight() / 2) + 83);

        help = new Texture(Gdx.files.internal("message.png"));
        helpRegion = new TextureRegion(help);
        helpDrawable = new TextureRegionDrawable(helpRegion);
        helpButton = new ImageButton(helpDrawable);
        helpButton.setPosition((uiStage.getWidth() - helpButton.getWidth()) / 2 + 130, (uiStage.getHeight() / 2) + 83);

        helpButton.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                val = 1;
                game.setScreen(new MechanicsScreen(game));

            }
        });

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;

       // buttonStyle.fontColor = Color.WHITE;

        TextButton ulockBtn = new TextButton(" " + (GameUpgradeEasy.ulock + GameUpgradeAverage.ulock), buttonStyle);
        ulockBtn.setPosition(23, (uiStage.getHeight() / 2) + 83);


        TextButton easyBtn = new TextButton("EASY", buttonStyle);
        easyBtn.setPosition((uiStage.getWidth() - easyBtn.getWidth()) / 2, (uiStage.getHeight() / 3));

        easyBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new ChooseCharacter(game));
            }
        });

        TextButton aveBtn = new TextButton("AVERAGE", buttonStyle);
        aveBtn.setPosition((uiStage.getWidth() - aveBtn.getWidth()) / 2, (uiStage.getHeight() / 4) - 5);

        if(GameUpgradeEasy.ulock >= 1){
            aveBtn.addListener(new ClickListener(){
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    dispose();
                    game.setScreen(new ChooseCharacterAverage(game));
                }
            });
        }

        TextButton diffBtn = new TextButton("DIFFICULT", buttonStyle);
        diffBtn.setPosition((uiStage.getWidth() - diffBtn.getWidth()) / 2, (uiStage.getHeight() / 5) - 16);
        if(GameUpgradeAverage.ulock >= 1) {
            diffBtn.addListener(new ClickListener() {
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    dispose();
                    game.setScreen(new ChooseCharacterDiff(game));
                }
            });
        }

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
        uiStage.addActor(ulockBtn);
        uiStage.addActor(diffBtn);
        uiStage.addActor(aveBtn);
        uiStage.addActor(easyBtn);
        uiStage.addActor(potion);
        uiStage.addActor(helpButton);

    }

    public StartScreen(BattleOfHogwarts g){
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
