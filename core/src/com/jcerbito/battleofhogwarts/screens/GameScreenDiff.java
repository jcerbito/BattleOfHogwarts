package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.Background;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;
import com.jcerbito.battleofhogwarts.forbg.foreffects.LightningBoltFx;
import com.jcerbito.battleofhogwarts.forgameproper.GameProper;
import com.jcerbito.battleofhogwarts.forgameproper.GameProperAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameProperDiff;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Equipment;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;
import com.jcerbito.battleofhogwarts.forgameproper.obj.PlayerAverage;

import javax.annotation.Resource;

/**
 * Created by HP on 09/01/2018.
 */

public class GameScreenDiff extends DefaultScreen implements InputProcessor, GameProperDiff.GameEventListener{

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

    private static final float SHAKE_TIME = 0.3f;
    private static final float SHAKE_DIST = 4.0f;




    public GameScreenDiff(BattleOfHogwarts g) {
        super(g);
        batch = new SpriteBatch();

        ExtendViewport viewport = new ExtendViewport(WIDTH, HEIGHT);
        gameStage = new Stage(viewport, batch);
        bg = new Background();
        sizeEval = new SizeEval(gameStage, game.res, GameProperAverage.MAX_BASEX, GameProperAverage.MAX_BASEY);

        gmProper = new GameProperDiff(game, this);
        player = gmProper.getPlayer();

//        player.set(game.res.hp);
//        RetryMove();

        Gdx.input.setInputProcessor(this);

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

    }

    public void update(float delta){
        gameStage.act(delta);
        if (player.getLives() > 0 && gmProper.getEnemy().getLives() > 0){
            gmProper.update(delta);
        }
    }

    public void drawBase(){
        batch.begin();

        for( int x = 0; x <= GameProperAverage.MAX_BASEX; x++){
            for ( int y = 0; y <= GameProperAverage.MAX_BASEY; y++){
                batch.draw(game.res.tile3, sizeEval.getBaseX(x), sizeEval.getBaseY(y) );
            }
        }

        //      batch.draw(game.res.hp, sizeEval.getBaseX(1), sizeEval.getBaseY(1));

        batch.end();
    }



    @Override
    public void show() {

    }


    private void ForText(){
        batch.begin();
        game.res.gamefont.draw(batch, "player: " + player.getLives(), 5, gameStage.getViewport().getScreenY() + gameStage.getHeight() - 4);
        game.res.gamefont.draw(batch, "death eater: " + gmProper.getEnemy().getLives(), (gameStage.getWidth() / 2) + 25, gameStage.getViewport().getScreenY() + gameStage.getHeight() - 4);


        if (player.getLives() <= 0){
            //game.res.gamefont.setColor(Color.RED);
            game.res.gamefont.draw(batch, "OH NO! YOU LOSE!", 0, gameStage.getViewport().getScreenY() + gameStage.getHeight() / 2, gameStage.getWidth(), Align.center, false);
            //game.res.gamefont.setColor(Color.WHITE);
        }else if (gmProper.getEnemy().getLives() <= 0){
            // game.res.gamefont.setColor(Color.BLUE);
            game.res.gamefont.draw(batch, "YOU WIN!", 0, gameStage.getViewport().getScreenY() + gameStage.getHeight() / 2, gameStage.getWidth(), Align.center, false);
            //game.res.gamefont.setColor(Color.WHITE);
        }

        batch.end();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bg.draw(gameStage, game.res);
        drawBase();
//        gmProper.getEffectTool().draw(batch, sizeEval);

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
//        RetryMove();
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
    public boolean keyDown(int keycode) {
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

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
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
                                game.setScreen(new GameScreenAverage(game));
                                return true;
                            }
                        }

                )


        );
    }
}
