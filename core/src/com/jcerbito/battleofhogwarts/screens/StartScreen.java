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

public class StartScreen extends DefaultScreen {
    Stage uiStage;
    private Texture bgTexture;
    private Texture potionTexture;

    private Texture help;
    private TextureRegion helpRegion;
    private TextureRegionDrawable helpDrawable;
    private ImageButton helpButton;

    private Texture mus;
    private TextureRegion musRegion;
    private TextureRegionDrawable musDrawable;
    private ImageButton musButton;

    private Texture musTwo;
    private TextureRegion musTwoRegion;
    private TextureRegionDrawable musTwoDrawable;
    private ImageButton musTwoButton;

    Music hptheme;

    public static int val = 0;

    public static boolean musOn;

    void startUI(){
        bgTexture = new Texture(Gdx.files.internal("bckgrnddddf.jpg"));
        Image bckgrnd = new Image(bgTexture);

        potionTexture = new Texture(Gdx.files.internal("heart.png"));
        Image potion = new Image(potionTexture);
        potion.setPosition(5, (uiStage.getHeight() / 2) + 83);

        help = new Texture(Gdx.files.internal("message.png"));
        helpRegion = new TextureRegion(help);
        helpDrawable = new TextureRegionDrawable(helpRegion);
        helpButton = new ImageButton(helpDrawable);
        helpButton.setPosition((uiStage.getWidth() - helpButton.getWidth()) / 2 + 75, (uiStage.getHeight() / 2) + 83);

        helpButton.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                helpButton.setPosition((uiStage.getWidth() - helpButton.getWidth()) / 2 + 75, (uiStage.getHeight() / 2) + 81);

                return true;
            }


            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                val = 1;
                game.setScreen(new MechanicsScreen(game));

            }
        });

        mus = new Texture(Gdx.files.internal("off.png"));
        musRegion = new TextureRegion(mus);
        musDrawable = new TextureRegionDrawable(musRegion);
        musButton = new ImageButton(musDrawable);
        musButton.setPosition((uiStage.getWidth() - musButton.getWidth()) / 2 + 130, (uiStage.getHeight() / 2) + 83);

        musTwo = new Texture(Gdx.files.internal("on.png"));
        musTwoRegion = new TextureRegion(musTwo);
        musTwoDrawable = new TextureRegionDrawable(musTwoRegion);
        musTwoButton = new ImageButton(musTwoDrawable);
        musTwoButton.setPosition((uiStage.getWidth() - musTwoButton.getWidth()) / 2 + 103, (uiStage.getHeight() / 2) + 83);

        musButton.addListener(new ClickListener(){
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
////                musButton.setPosition((uiStage.getWidth() - musButton.getWidth()) / 2 + 130, (uiStage.getHeight() / 2) + 81);
//                stopMusic();
//                return true;
//            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
//                musButton.setPosition((uiStage.getWidth() - musButton.getWidth()) / 2 + 130, (uiStage.getHeight() / 2) + 83);
                musOn = false;
                BattleOfHogwarts.stopMusic();
                super.touchUp(event,x,y,pointer,button);
            }
        });

        musTwoButton.addListener(new ClickListener(){
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
////                musTwoButton.setPosition((uiStage.getWidth() - musTwoButton.getWidth()) / 2 + 103, (uiStage.getHeight() / 2) + 81);
//                playMusic();
//                return true;
//            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
//                musTwoButton.setPosition((uiStage.getWidth() - musTwoButton.getWidth()) / 2 + 103, (uiStage.getHeight() / 2) + 83);
                musOn = true;
                BattleOfHogwarts.playMusic();
                super.touchUp(event,x,y,pointer,button);
            }


        });




        final TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;

        final TextButton.TextButtonStyle buttonStyle1 = new TextButton.TextButtonStyle();
        buttonStyle1.font = game.res.gamefont;

        final TextButton.TextButtonStyle buttonStyle2 = new TextButton.TextButtonStyle();
        buttonStyle2.font = game.res.gamefont;

        final TextButton.TextButtonStyle buttonStylee = new TextButton.TextButtonStyle();
        buttonStylee.font = game.res.gamefont;

        TextButton.TextButtonStyle buttonStyleee = new TextButton.TextButtonStyle();
        buttonStyleee.font = game.res.gamefont;




       // buttonStyle.fontColor = Color.WHITE;

        TextButton ulockBtn = new TextButton(" " + (GameUpgradeEasy.ulock + GameUpgradeAverage.ulock + GameUpgradeDiff.ulock), buttonStyleee);
        ulockBtn.setPosition(23, (uiStage.getHeight() / 2) + 83);

        final TextButton titleBtn = new TextButton("BATTLE OF HOGWARTS", buttonStylee);
        titleBtn.setPosition((uiStage.getWidth() - titleBtn.getWidth()) / 2, (uiStage.getHeight() / 2));




        final TextButton easyBtn = new TextButton("EASY", buttonStyle);
        easyBtn.setPosition((uiStage.getWidth() - easyBtn.getWidth()) / 2, (uiStage.getHeight() / 3));

        easyBtn.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                easyBtn.setPosition((uiStage.getWidth() - easyBtn.getWidth()) / 2, (uiStage.getHeight() / 3) - 2);
               buttonStyle.fontColor = Color.YELLOW;

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new ChooseCharacter(game));

            }
        });


        final TextButton aveBtn = new TextButton("AVERAGE", buttonStyle1);
        aveBtn.setPosition((uiStage.getWidth() - aveBtn.getWidth()) / 2, (uiStage.getHeight() / 4) - 5);



        if(GameUpgradeEasy.moveNextLevel == 1){
            aveBtn.addListener(new ClickListener(){
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    aveBtn.setPosition((uiStage.getWidth() - aveBtn.getWidth()) / 2, (uiStage.getHeight() / 4) - 7);
                    buttonStyle1.fontColor = Color.YELLOW;
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    dispose();
                    game.setScreen(new ChooseCharacterAverage(game));
                }
            });
        }else{
            aveBtn.addListener(new ClickListener(){
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    aveBtn.setPosition((uiStage.getWidth() - aveBtn.getWidth()) / 2, (uiStage.getHeight() / 4) - 7);
                    aveBtn.setText("COMPLETE EASY LEVEL FIRST!!!");
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    aveBtn.setText("AVERAGE");
                }
            });
        }

       final TextButton diffBtn = new TextButton("DIFFICULT", buttonStyle2);
        diffBtn.setPosition((uiStage.getWidth() - diffBtn.getWidth()) / 2, (uiStage.getHeight() / 5) - 16);

        if(GameUpgradeAverage.moveNextLevel == 1) {
            diffBtn.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    diffBtn.setPosition((uiStage.getWidth() - diffBtn.getWidth()) / 2, (uiStage.getHeight() / 5) - 18);
                    buttonStyle2.fontColor = Color.YELLOW;
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    dispose();
                    game.setScreen(new ChooseCharacterDiff(game));
                }
            });
        }else{
            diffBtn.addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    diffBtn.setPosition((uiStage.getWidth() - diffBtn.getWidth()) / 2, (uiStage.getHeight() / 5) - 18);
                    diffBtn.setText("COMPLETE AVERAGE LEVEL FIRST!!!");
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    diffBtn.setText("DIFFICULT");
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
//        uiStage.addActor(titleBtn);
        uiStage.addActor(potion);
        uiStage.addActor(helpButton);
        uiStage.addActor(musButton);
        uiStage.addActor(musTwoButton);

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
