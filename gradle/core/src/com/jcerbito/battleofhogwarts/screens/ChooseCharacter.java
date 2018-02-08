package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;

/**
 * Created by HP on 30/01/2018.
 */

public class ChooseCharacter extends DefaultScreen {
    Stage uiStage;

    private Texture texture;
    private TextureRegion textureRegion;
    private TextureRegionDrawable texRegionDrawable;
    private ImageButton charButton;

    private Texture bgTexture;

    private Texture textureTwo;
    private TextureRegion textureRegionTwo;
    private TextureRegionDrawable texRegionDrawableTwo;
    private ImageButton charButtonTwo;





    public static int charLockEasy = 0;




    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("charbg.jpg"));
        Image bckgrnd = new Image(bgTexture);

        texture = new Texture(Gdx.files.internal("bigharry.png"));
        textureRegion = new TextureRegion(texture);
        texRegionDrawable = new TextureRegionDrawable(textureRegion);
        charButton = new ImageButton(texRegionDrawable);

            charButton.addListener(new ClickListener(){
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    dispose();
                    charLockEasy = 1;
//                  GameUpgradeEasy.chrlckk = 0;
//                  GameUpgradeEasy.chrlck = 1;
                    game.setScreen(new GameScreenEasy(game));

                }
            });



        textureTwo = new Texture(Gdx.files.internal("bighermione.png"));
        textureRegionTwo = new TextureRegion(textureTwo);
        texRegionDrawableTwo = new TextureRegionDrawable(textureRegionTwo);
        charButtonTwo = new ImageButton(texRegionDrawableTwo);

        if ((GameUpgradeEasy.ulock + GameUpgradeAverage.ulock) >= 30){
            charButtonTwo.addListener(new ClickListener(){
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    dispose();
                    charLockEasy = 2;
                    GameUpgradeEasy.chrlck = 0;
                    GameUpgradeEasy.chrlckk = 2;
                    game.setScreen(new GameScreenEasy(game));


                }
            });
        }

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;

        TextButton aBtn = new TextButton("COLLECT MORE POTIONS", buttonStyle);
        TextButton qBtn = new TextButton("TO UNLOCK HERMIONE!", buttonStyle);
        TextButton hBtn = new TextButton("CLICK ON CHOSEN CHARACTER", buttonStyle);

        hBtn.setPosition((uiStage.getWidth() - hBtn.getWidth()) / 2, 220) ;

        TextButton ex = new TextButton("CLICK HERE TO GO BACK", buttonStyle);
        ex.setPosition((uiStage.getWidth() - ex.getWidth()) / 2, 20);

        ex.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new StartScreen(game));
            }
        });


        charButton.setPosition((uiStage.getWidth() / 3 - 60), uiStage.getHeight() / 3 + 20);
        charButtonTwo.setPosition((uiStage.getWidth() / 3 + 70), uiStage.getHeight() / 3 + 20);

        uiStage.addActor(bckgrnd);
        uiStage.addActor(charButton);
        uiStage.addActor(charButtonTwo);
        uiStage.addActor(ex);
        uiStage.addActor(hBtn);
         if ((GameUpgradeEasy.ulock + GameUpgradeAverage.ulock) <= 29){
             aBtn.setPosition((uiStage.getWidth() - aBtn.getWidth()) / 2, 70 );
             qBtn.setPosition((uiStage.getWidth() - qBtn.getWidth()) / 2, 55) ;
             uiStage.addActor(aBtn);
             uiStage.addActor(qBtn);

         }

    }

    public ChooseCharacter(BattleOfHogwarts g){
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
