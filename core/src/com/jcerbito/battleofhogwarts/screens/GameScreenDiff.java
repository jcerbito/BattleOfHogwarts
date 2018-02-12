package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.Background;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;
import com.jcerbito.battleofhogwarts.forgameproper.GameProperAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameProperDiff;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeDiff;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Equipment;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;

/**
 * Created by HP on 09/01/2018.
 */

public class GameScreenDiff extends DefaultScreen implements GameProperDiff.GameEventListener{

    SpriteBatch batch;

    public static final int WIDTH = 14 * Resources.TILE_SIZE; //350
    public static final int HEIGHT = 10 * Resources.TILE_SIZE; //250

    public static final float GAME_END = 0.5f;
    public static final float GAME_START= 0.2f;

    private SizeEval sizeEval;

    private Stage gameStage;
    private Background bg;

    private GameProperDiff gmProper;
    private Player player;

    public static float timePast = 26.0f;
    public static int cntTime;

    private static final float SHAKE_TIME = 0.3f;
    private static final float SHAKE_DIST = 4.0f;

    private Group ctrlGrp;


    public GameScreenDiff(BattleOfHogwarts g) {
        super(g);
        batch = new SpriteBatch();

        ExtendViewport viewport = new ExtendViewport(WIDTH, HEIGHT);
        gameStage = new Stage(viewport, batch);
        bg = new Background();
        sizeEval = new SizeEval(gameStage, game.res, GameProperAverage.MAX_BASEX, GameProperAverage.MAX_BASEY);

        gmProper = new GameProperDiff(game, this);
        player = gmProper.getPlayer();

        Gdx.input.setInputProcessor(gameStage);

        gameStage.addAction(
                new Action() {
                    float time = 0;
                    @Override
                    public boolean act(float delta) {
                        time += delta;
                        float t = time / GAME_START;
                        t *= t;
                        if (t > 1.0f){
                            t = 1.0f;
                        }
                        batch.setColor(1,1,1,t);
                        return time >= GAME_START;
                    }
                }
        );

        gameStage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.UP:
                        TryMove(0,1);
                        break;
                    case Input.Keys.DOWN:
                        TryMove(0,-1);
                        break;
                    case Input.Keys.LEFT:
                        TryMove(-1,0);
                        break;
                    case Input.Keys.RIGHT:
                        TryMove(1,0);
                        break;
                };


                return false;
            }
        });

        final TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.res.gamefont;

        final TextButton quitBtn = new TextButton("QUIT GAME", buttonStyle);
        quitBtn.setPosition((gameStage.getWidth() - quitBtn.getWidth()) / 2, 10);

        quitBtn.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                quitBtn.setPosition((gameStage.getWidth() - quitBtn.getWidth()) / 2, 8);
                buttonStyle.fontColor = Color.RED;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new StartScreen(game));
                if (GameUpgradeDiff.currentLvl == 1){
                    GameUpgradeDiff.currentLvl = 1;
                    timePast = 26;
                }else if (GameUpgradeDiff.currentLvl == 2){
                    GameUpgradeDiff.currentLvl = 2;
                    timePast = 36;
                }else if (GameUpgradeDiff.currentLvl == 3){
                    GameUpgradeDiff.currentLvl = 3;
                    timePast = 46;
                }
            }
        });

        gameStage.addActor(quitBtn);

        ctrlGrp = new Group();
        gameStage.addActor(ctrlGrp);

        if (Gdx.app.getType() == Application.ApplicationType.Android)
        {
            allDir();
        }

    }

    private void indiDir(final int tX, final int tY, TextureRegionDrawable icon, float x, float y){
        ImageButton imgBtn = new ImageButton(icon);
        imgBtn.setPosition(x,y);
        imgBtn.addListener(new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                TryMove(tX, tY);
                super.touchUp(event, x, y, pointer, button);
            }
        });

        ctrlGrp.addActor(imgBtn);

    }

    private void allDir() {
        indiDir(0, 1, game.res.up, 1, gameStage.getHeight() / 2 );
        indiDir(0, -1, game.res.down, 1, gameStage.getHeight() / 4 );

        indiDir(-1, 0, game.res.left, (gameStage.getWidth() / 2) + 170, 0 );
        indiDir(1, 0, game.res.right, (gameStage.getWidth() / 2) + 170, gameStage.getHeight() / 4 );

    }

    public void update(float delta){

        cntTime = (int)timePast;

        if (cntTime != 0){
            timePast -= delta;
        }else if(cntTime == 0){
            if (GameUpgradeDiff.currentLvl == 1){
                timePast = 26;
            }else if (GameUpgradeDiff.currentLvl == 2){
                timePast = 36;
            }else if (GameUpgradeDiff.currentLvl == 3){
                timePast = 46;
            }
            game.setScreen(new GameOverScreen(game));
            if (GameUpgradeDiff.currentLvl == 1){
                GameUpgradeDiff.currentLvl = 1;
            }else if (GameUpgradeDiff.currentLvl == 2){
                GameUpgradeDiff.currentLvl = 2;
            }else if (GameUpgradeDiff.currentLvl == 3){
                GameUpgradeDiff.currentLvl = 3;
            }
        }

        gameStage.act(delta);
        if (player.getLives() > 0 && gmProper.getEnemy().getLives() > 0){
            gmProper.update(delta);
        }
    }

    public void drawBase(){
        batch.begin();

        for( int x = 0; x <= GameProperDiff.MAX_BASEX; x++){
            for ( int y = 0; y <= GameProperDiff.MAX_BASEY; y++){
                batch.draw(game.res.tile3, sizeEval.getBaseX(x), sizeEval.getBaseY(y) );
                if (GameUpgradeDiff.currentLvl == 1){
                    batch.draw(game.res.tile5, sizeEval.getBaseX(x), sizeEval.getBaseY(y) );
                }else if (GameUpgradeDiff.currentLvl == 2){
                    batch.draw(game.res.tile2, sizeEval.getBaseX(x), sizeEval.getBaseY(y) );
                }else if (GameUpgradeDiff.currentLvl == 3){
                    batch.draw(game.res.tile3, sizeEval.getBaseX(x), sizeEval.getBaseY(y) );
                }
            }
        }

        batch.end();
    }



    @Override
    public void show() {

    }


    private void ForText(){
        batch.begin();
        game.res.gamefont.draw(batch, "player: " + player.getLives(), 5, gameStage.getViewport().getScreenY() + gameStage.getHeight() - 4);
        game.res.gamefont.draw(batch, "death eater: " + gmProper.getEnemy().getLives(), (gameStage.getWidth() / 2) + 40, gameStage.getViewport().getScreenY() + gameStage.getHeight() - 4);

        game.res.gamefont.draw(batch, "time: " + cntTime, (gameStage.getWidth() / 2) + 90, gameStage.getViewport().getScreenY() + gameStage.getHeight() - 20);

        game.res.gamefont.draw(batch, "AVERAGE Stage " + GameUpgradeDiff.getLevel(), 5, gameStage.getViewport().getScreenY() + gameStage.getHeight() - 20);

        batch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bg.draw(gameStage, game.res);
        drawBase();

        batch.begin();

        for (Equipment equipment : gmProper.getEquipments()){
            equipment.draw(batch, sizeEval);
        }

        player.draw(batch, sizeEval);
        gmProper.getEnemy().draw(batch, sizeEval);
        batch.end();

        gmProper.getEffectTool().draw(batch, sizeEval);

        gameStage.getCamera().position.set(gameStage.getWidth() / 2, gameStage.getHeight() / 2, 0);
        if (player.getLives() > 0 && player.getAliveTime() - player.getDamageTime() < SHAKE_TIME ){
            gameStage.getCamera().translate(-(SHAKE_DIST/2) + MathUtils.random(SHAKE_DIST), -(SHAKE_DIST/2) + MathUtils.random(SHAKE_DIST), 0);
        }

        gameStage.getCamera().update();

        ForText();
        gameStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.getViewport().update(width, height, true);

  }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }

    //para kapag niresize yung window mag aadjust yung coordinate location ng player
//    public void RetryMove(){
//        player.setPosition(sizeEval.getBaseX(player.getLocX()), sizeEval.getBaseY(player.getLocY()));
//    }

    // pag nag attempt gumalaw yung player
    public void TryMove(int tX, int tY){
        if (player.getLives() > 0 && gmProper.getEnemy().getLives() > 0 && gmProper.ForMove(player.getLocX() + tX, player.getLocY() + tY )){
            gmProper.PlayerPos(player.getLocX() + tX, player.getLocY() + tY );
//            RetryMove();

        }
    }

    @Override
    public void OnGameEnd(boolean playerWins) {
        gameStage.addAction(
                Actions.sequence(
                        new Action() {
                            float time = 0;
                            @Override
                            public boolean act(float delta) {
                                time += delta;

                                float t = time / GAME_END;
                                t *= t;
                                batch.setColor(1,1,1,1 - t);

                                return time >= GAME_END;
                            }
                        },
                        new Action(){
                            @Override
                            public boolean act(float delta) {
                                dispose();
                                game.setScreen(new GameScreenDiff(game));
                                return true;
                            }
                        }

                )


        );
    }
}
