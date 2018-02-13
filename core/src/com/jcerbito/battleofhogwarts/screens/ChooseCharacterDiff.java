package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeDiff;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeEasy;

/**
 * Created by HP on 30/01/2018.
 */

public class ChooseCharacterDiff extends DefaultScreen {
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

    private Texture textureThree;
    private TextureRegion textureRegionThree;
    private TextureRegionDrawable texRegionDrawableThree;
    private ImageButton charButtonThree;

    private Texture textureFour;
    private TextureRegion textureRegionFour;
    private TextureRegionDrawable texRegionDrawableFour;
    private ImageButton charButtonFour;

    public static int charLockDiff = 0;




    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("charbg.jpg"));
        Image bckgrnd = new Image(bgTexture);

        texture = new Texture(Gdx.files.internal("bigharry.png"));
        textureRegion = new TextureRegion(texture);
        texRegionDrawable = new TextureRegionDrawable(textureRegion);
        charButton = new ImageButton(texRegionDrawable);

        charButton.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                charLockDiff = 1;
                dispose();
                game.setScreen(new GameScreenDiff(game));

            }
        });



        textureTwo = new Texture(Gdx.files.internal("bighermione.png"));
        textureRegionTwo = new TextureRegion(textureTwo);
        texRegionDrawableTwo = new TextureRegionDrawable(textureRegionTwo);
        charButtonTwo = new ImageButton(texRegionDrawableTwo);

        if ((GameUpgradeEasy.ulock + GameUpgradeAverage.ulock) >= 30){
            charButtonTwo.addListener(new ClickListener(){
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    charLockDiff = 2;
                    dispose();
                    game.setScreen(new GameScreenDiff(game));


                }
            });
        }

        textureThree = new Texture(Gdx.files.internal("bighagrid.png"));
        textureRegionThree = new TextureRegion(textureThree);
        texRegionDrawableThree = new TextureRegionDrawable(textureRegionThree);
        charButtonThree = new ImageButton(texRegionDrawableThree);

        if ((GameUpgradeEasy.ulock + GameUpgradeAverage.ulock)  >= 50 ){
            charButtonThree.addListener(new ClickListener(){
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    charLockDiff = 3;
                    dispose();
                    game.setScreen(new GameScreenDiff(game));
                }
            });
        }

        textureFour = new Texture(Gdx.files.internal("bigron.png"));
        textureRegionFour = new TextureRegion(textureFour);
        texRegionDrawableFour = new TextureRegionDrawable(textureRegionFour);
        charButtonFour = new ImageButton(texRegionDrawableFour);

        if ((GameUpgradeEasy.ulock + GameUpgradeAverage.ulock + GameUpgradeDiff.ulock)  >= 70 ){
            charButtonFour.addListener(new ClickListener(){
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    charLockDiff = 4;
                    dispose();
                    game.setScreen(new GameScreenDiff(game));
                }
            });
        }

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;

        final TextButton.TextButtonStyle buttonStyle1 = new TextButton.TextButtonStyle();
        buttonStyle1.font = game.res.gamefont;

        TextButton aBtn = new TextButton("COLLECT MORE POTIONS", buttonStyle);
        TextButton qBtn = new TextButton("TO UNLOCK RON!", buttonStyle);
        TextButton hBtn = new TextButton("CLICK ON CHOSEN CHARACTER", buttonStyle);

        hBtn.setPosition((uiStage.getWidth() - hBtn.getWidth()) / 2, 220) ;

        final TextButton ex = new TextButton("CLICK HERE TO GO BACK", buttonStyle1);
        ex.setPosition((uiStage.getWidth() - ex.getWidth()) / 2, 5);

        ex.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                ex.setPosition((uiStage.getWidth() - ex.getWidth()) / 2, 5 - 2);
                buttonStyle1.fontColor = Color.CYAN;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new StartScreen(game));
            }
        });


        charButton.setPosition((uiStage.getWidth() / 3 - 80 ), uiStage.getHeight() / 2);
        charButtonTwo.setPosition((uiStage.getWidth() / 3 + 40), uiStage.getHeight() / 2);
        charButtonThree.setPosition((uiStage.getWidth() / 3 - 20), uiStage.getHeight() / 4);
        charButtonFour.setPosition((uiStage.getWidth() / 2 + 50), uiStage.getHeight() / 4);

        uiStage.addActor(bckgrnd);
        uiStage.addActor(charButton);
        uiStage.addActor(charButtonTwo);
        uiStage.addActor(charButtonThree);
        uiStage.addActor(charButtonFour);
        uiStage.addActor(ex);
        uiStage.addActor(hBtn);
        if ((GameUpgradeEasy.ulock + GameUpgradeAverage.ulock + GameUpgradeDiff.ulock) <= 69){
            aBtn.setPosition((uiStage.getWidth() - aBtn.getWidth()) / 2, 45 );
            qBtn.setPosition((uiStage.getWidth() - qBtn.getWidth()) / 2, 33) ;
            uiStage.addActor(aBtn);
            uiStage.addActor(qBtn);
        }


    }

    public ChooseCharacterDiff(BattleOfHogwarts g){
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
