package com.jcerbito.battleofhogwarts.forgameproper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.jcerbito.battleofhogwarts.BattleOfHogwarts;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.foreffects.EffectTool;
import com.jcerbito.battleofhogwarts.forbg.foreffects.LightningBoltFx;
import com.jcerbito.battleofhogwarts.forgameproper.obj.EnemyAverage;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Equipment;
import com.jcerbito.battleofhogwarts.forgameproper.obj.Player;
import com.jcerbito.battleofhogwarts.forgameproper.obj.PlayerAverage;
import com.jcerbito.battleofhogwarts.screens.GameOverScreen;
import com.jcerbito.battleofhogwarts.screens.GameScreenAverage;
import com.jcerbito.battleofhogwarts.screens.StartScreen;
import com.jcerbito.battleofhogwarts.screens.WinScreen;
import com.jcerbito.battleofhogwarts.screens.WinScreenAverage;

import java.util.ArrayList;

/**
 * Created by HP on 10/01/2018.
 */

public class GameProperAverage implements EnemyAverage.EnemyAverageAttackedListener, LightningBoltFx.LightningBoltFxListener{

    public static final int MAX_BASEX = 11;
    public static final int MAX_BASEY = 5;
    // private static final int O_PLAYER_LIVES = 3;
    private static final float EQ_TIME_INTERVAL = 3.0f;
    private static final int MAX_EQ = 3;
    BattleOfHogwarts game;

    Sound heal;
    Sound spell;
    Sound hit;

    public interface GameEventListener{
        void OnGameEnd(boolean playerWins);
    }

    PlayerAverage player;
    EnemyAverage enemy;
    EffectTool effectTool;

    ArrayList<Equipment> equipments;
    float gTime;
    float lastEqTime;

    GameEventListener eventListener;


    public GameProperAverage(BattleOfHogwarts g, GameEventListener listener) {
        game = g;
        player = new PlayerAverage(MathUtils.random(MAX_BASEX), MathUtils.random(MAX_BASEY), game.res, GameUpgradeAverage.pLives);
        enemy = new EnemyAverage(game.res, this, MathUtils.random(Resources.DE_3)); //ipapasa yung gameproper as attacklistner
        effectTool = new EffectTool();
        equipments = new ArrayList<Equipment>();
        gTime = 0;
        lastEqTime = 0;
        eventListener = listener;
    }

    public PlayerAverage getPlayer(){
        return player;
    }

    public EnemyAverage getEnemy(){
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

        equipments.add(Equipment.Create(lx, ly, MathUtils.random(4) == 0 ? Equipment.HEART : Equipment.WAND, game.res));

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
                    playSoundHeal();
                    GameUpgradeAverage.ulock += 1;
                }else if (currEquipment.getEq() == Equipment.WAND){
                    enemy.damage(GameUpgradeAverage.pDamage);
                    playSoundSpell();
                    if (enemy.getLives() <= 0){
                        if (GameUpgradeAverage.currentLvl == 1){
                            GameUpgradeAverage.currentLvl = 2;
                            GameScreenAverage.timePast = 31;
                            GameUpgradeAverage.pLives = player.getLives();
                            eventListener.OnGameEnd(true);
                        }else if (GameUpgradeAverage.currentLvl == 2){
                            GameUpgradeAverage.currentLvl = 3;
                            GameScreenAverage.timePast = 41;
                            GameUpgradeAverage.pLives = player.getLives();
                            eventListener.OnGameEnd(true);
                        }else if (GameUpgradeAverage.currentLvl == 3){
                            GameUpgradeAverage.currentLvl = 4;
                            if (GameUpgradeAverage.currentLvl >= 4){
                                game.setScreen(new WinScreenAverage(game));
                                GameUpgradeAverage.currentLvl = 1;
                                GameScreenAverage.timePast = 21;
                                GameUpgradeAverage.ulock += 1;
                                GameUpgradeAverage.moveNextLevel = 1;
                            }
                        }
                    }

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

    public void playSoundHeal(){
        if (StartScreen.musOn == true){
            heal = Gdx.audio.newSound(Gdx.files.internal("music/healspell1.ogg"));
            heal.play();
        }
    }

    public void playSoundSpell(){
        if (StartScreen.musOn == true){
            spell = Gdx.audio.newSound(Gdx.files.internal("music/spell3.wav"));
            spell.play();
        }
    }

    public void playSoundHit(){
        if (StartScreen.musOn == true){
            hit = Gdx.audio.newSound(Gdx.files.internal("music/hit.ogg"));
            hit.play();
        }
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
            playSoundHit();
            if(player.getLives() <= 0 && GameUpgradeAverage.currentLvl == 1){
                GameUpgradeAverage.Reset();
                game.setScreen(new GameOverScreen(game));
            }else if(player.getLives() <= 0 && GameUpgradeAverage.currentLvl == 2){
                GameUpgradeAverage.ResetSecondLevel();
                game.setScreen(new GameOverScreen(game));
            }else if(player.getLives() <= 0 && GameUpgradeAverage.currentLvl == 3){
                GameUpgradeAverage.ResetThirdLevel();
                game.setScreen(new GameOverScreen(game));
            }
        }
    }

    public ArrayList<Equipment> getEquipments(){
        return equipments;
    }
}
