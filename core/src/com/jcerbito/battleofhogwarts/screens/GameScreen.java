package com.jcerbito.battleofhogwarts.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.Background;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;
import com.jcerbito.battleofhogwarts.forgameproper.GameProper;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;

import javax.annotation.Resource;

/**
 * Created by HP on 09/01/2018.
 */

public class GameScreen extends DefaultScreen implements InputProcessor{

    SpriteBatch batch;

    public static final int WIDTH = 14 * Resources.TILE_SIZE; //350
    public static final int HEIGHT = 10 * Resources.TILE_SIZE; //250


    private SizeEval sizeEval;

    private Stage gameStage;
    private Background bg;

    private GameProper gmProper;
    private Player player;



    public GameScreen(BattleOfHogwarts g) {
        super(g);
        batch = new SpriteBatch();

        ExtendViewport viewport = new ExtendViewport(WIDTH, HEIGHT);
        gameStage = new Stage(viewport, batch);
        bg = new Background();
        sizeEval = new SizeEval(gameStage, game.res, GameProper.MAX_BASEX, GameProper.MAX_BASEY);

        gmProper = new GameProper();
        player = gmProper.getPlayer();

        player.set(game.res.hp);
        RetryMove();

        Gdx.input.setInputProcessor(this);
    }

    public void update(float delta){
        gameStage.act(delta);
    }

    public void drawBase(){
        batch.begin();

        for( int x = 0; x <= GameProper.MAX_BASEX; x++){
            for ( int y = 0; y <= GameProper.MAX_BASEY; y++){
                batch.draw(game.res.tile3, sizeEval.getBaseX(x), sizeEval.getBaseY(y) );
            }
        }

  //      batch.draw(game.res.hp, sizeEval.getBaseX(1), sizeEval.getBaseY(1));

        batch.end();
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bg.draw(gameStage, game.res);
        drawBase();

        batch.begin();
        player.draw(batch);
        batch.end();

        gameStage.draw();




    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.getViewport().update(width, height, true);
        RetryMove();
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
    public void RetryMove(){
        player.setPosition(sizeEval.getBaseX(player.getLocX()), sizeEval.getBaseY(player.getLocY()));
    }

    // pag nag attempt gumalaw yung player
    public void TryMove(int tX, int tY){
        if (gmProper.ForMove(player.getLocX() + tX, player.getLocY() + tY )){
            gmProper.PlayerPos(player.getLocX() + tX, player.getLocY() + tY );
            RetryMove();

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
}
