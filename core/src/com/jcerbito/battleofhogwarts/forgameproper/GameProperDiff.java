package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.math.MathUtils;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.foreffects.EffectTool;
import com.jcerbito.battleofhogwarts.forbg.foreffects.LightningBoltFx;
import com.jcerbito.battleofhogwarts.forgameproper.obj.EnemyDiff;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Equipment;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;

import java.util.ArrayList;

/**
 * Created by HP on 10/01/2018.
 */

public class GameProperDiff implements EnemyDiff.EnemyDiffAttackedListener, LightningBoltFx.LightningBoltFxListener{

    public static final int MAX_BASEX = 11;
    public static final int MAX_BASEY = 5;
    // private static final int O_PLAYER_LIVES = 3;
    private static final float EQ_TIME_INTERVAL = 4.0f;
    private static final int MAX_EQ = 3;
    BattleOfHogwarts game;

    public interface GameEventListener{
        void OnGameEnd(boolean playerWins);
    }

    Player player;
    EnemyDiff enemy;
    EffectTool effectTool;

    ArrayList<Equipment> equipments;
    float gTime;
    float lastEqTime;

    GameEventListener eventListener;


    public GameProperDiff(BattleOfHogwarts g, GameEventListener listener) {
        game = g;
        player = new Player(MathUtils.random(MAX_BASEX), MathUtils.random(MAX_BASEY), game.res, GameUpgradeDiff.pLives);
        enemy = new EnemyDiff(game.res, this, MathUtils.random(Resources.VOLDEMORT)); //ipapasa yung gameproper as attacklistner
        effectTool = new EffectTool();
        equipments = new ArrayList<Equipment>();
        gTime = 0;
        lastEqTime = 0;
        eventListener = listener;
    }

    public Player getPlayer(){
        return player;
    }

    public EnemyDiff getEnemy(){
        return enemy;
    }

    private void randomEq(){
        int lx = 0;
        int ly = 0;
        boolean nonEmptyPos = true;

        do{
            lx = MathUtils.random(MAX_BASEX);
            ly = MathUtils.random(MAX_BASEY);
            nonEmptyPos = player.getLocX() == lx || player.getLocY() == ly;

            for(int j = 0; j < equipments.size() && (nonEmptyPos == false); j++){
                if (equipments.get(j).getLocX() == lx && equipments.get(j).getLocY() == ly){
                    nonEmptyPos = true;
                }
            }

        }while (nonEmptyPos);

        equipments.add(Equipment.Create(lx, ly, MathUtils.random(2) == 0 ? Equipment.HEART : Equipment.WAND, game.res));

        lastEqTime = gTime;
    }

    public void update(float delta){
        effectTool.update(delta);
        enemy.update(delta);
        player.update(delta);
        gTime += delta;

        if(lastEqTime + EQ_TIME_INTERVAL < gTime && equipments.size() < MAX_EQ){
            randomEq();
        }
    }


    // for movement
    public boolean ForMove(int lX, int lY){
        return (lX >= 0 && lX <= MAX_BASEX) && (lY >= 0 && lY <= MAX_BASEY);
    }

    public void PlayerPos(int lX, int lY){
        player.setLocX(lX);
        player.setLocY(lY);

        for(int ep = equipments.size() - 1; ep >= 0; ep--){
            Equipment currEquipment = equipments.get(ep);
            if (currEquipment.getLocX() == lX && currEquipment.getLocY() == lY){

                if (currEquipment.getEq() == Equipment.HEART){
                    player.addLives(1);
                }else if (currEquipment.getEq() == Equipment.WAND){
                    enemy.damage(GameUpgradeDiff.pDamage);
                    if (enemy.getLives() <= 0){
                        GameUpgradeDiff.currentLvl += 1;
                        GameUpgradeDiff.pLives = player.getLives();
                        eventListener.OnGameEnd(true);
                    }

                }else if (currEquipment.getEq() == Equipment.WAND2){
                    player.damage(2);
                }

                currEquipment.release();
                equipments.remove(ep);
                break;
            }
        }
    }

    public EffectTool getEffectTool(){
        return effectTool;
    }


    @Override
    public void OnAttack(boolean[][] tiles) {
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[x].length; y++){
                if (tiles[x][y]){
                    LightningBoltFx.Create (x, y, effectTool, game.res, this);
                }
            }
        }
    }

    @Override
    public void OnEffect(LightningBoltFx effect) {
        if(effect.getLocX() == player.getLocX() && effect.getLocY() == player.getLocY()){
            player.damage(1);
            if(player.getLives() <= 0 ){
                GameUpgradeDiff.Reset();
            }
        }
    }

    public ArrayList<Equipment> getEquipments(){
        return equipments;
    }
}
