package com.jcerbito.battleofhogwarts.forgameproper.obj;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.jcerbito.battleofhogwarts.Resources;
import com.jcerbito.battleofhogwarts.forbg.SizeEval;
import com.jcerbito.battleofhogwarts.forgameproper.GameProper;
import com.jcerbito.battleofhogwarts.forgameproper.GameProperAverage;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgrade;
import com.jcerbito.battleofhogwarts.forgameproper.GameUpgradeAverage;

/**
 * Created by HP on 11/01/2018.
 */

public class EnemyAverage extends ObjectEffect {

    private  static final float ATTACK_TIME = 0.5f; //1.0f before
    //private static final int O_ENEMY_LIVES = 10;

    private float timeStartAttack;
    private float timeNextAttack;
    // private int lives;

    private boolean targetTiles[][];

    private int type;

    public static final float stillTime = 1.0f; //di dapat maattack yung player during 2 seconds

   /* public void damage(int numLives) {
        lives -= numLives;
        if (lives < 0){
            lives = 0;
        }
    }*/

    public interface EnemyAverageAttackedListener{
        void OnAttack(boolean[][] tiles);
    }

    private EnemyAverageAttackedListener attackedListener;


    public EnemyAverage(Resources res, EnemyAverageAttackedListener listener, int _type){
        super(GameUpgradeAverage.getEnemyLives());
        type = _type;
        set(res.deathEaterAverage.get(type));
        resetTimeAttack();
        attackedListener = listener;


        //lives = O_ENEMY_LIVES;

        targetTiles = new boolean[GameProperAverage.MAX_BASEX + 1][];

        for (int i = 0; i <= GameProperAverage.MAX_BASEX; i++){
            targetTiles[i] = new boolean[GameProperAverage.MAX_BASEY + 1];
        }
    }

    public void draw(SpriteBatch batch, SizeEval sizeEval){
        preDraw();
        setPosition(sizeEval.getPosEnemyX(this), sizeEval.getPosEnemyY(this));
        super.draw(batch);
        postDraw();
    }

    public void resetTimeAttack(){
        timeStartAttack = 0;
        timeNextAttack = ATTACK_TIME + MathUtils.random(2.0f); //attack will be 3 and 5 seconds
    }

    @Override
    public void update(float delta){
        super.update(delta);
        timeStartAttack += delta;
        if (aliveTime > stillTime && timeStartAttack > timeNextAttack){
            //deathEater3Attack();
            switch (type){
                case Resources.DE_1:
                    firstAttack();
                    break;
                case Resources.DE_2:
                    thirdAttack();
                    break;
                case Resources.DE_3:
                    fourthAttack();
                    break;

            }
            attackedListener.OnAttack(targetTiles);
            resetTimeAttack();
        }
    }
    //vertical
    private void firstAttack(){
        int colA = MathUtils.random(GameProperAverage.MAX_BASEX);
        int colB = 0;
        do{
            colB = MathUtils.random(GameProperAverage.MAX_BASEX);
        }while (colA == colB);

        for (int x = 0; x <= GameProperAverage.MAX_BASEX; x++){
            for (int y = 0 ; y <= GameProperAverage.MAX_BASEY; y++){
                targetTiles[x][y] = (x == colA || x == colB);
            }
        }
    }
    //horizontal
    private void secondAttack(){
        int rowA = MathUtils.random(GameProperAverage.MAX_BASEY);
        int rowB = 0;
        do{
            rowB = MathUtils.random(GameProperAverage.MAX_BASEY);
        }while (rowA == rowB);

        for (int x = 0; x <= GameProperAverage.MAX_BASEX; x++){
            for (int y = 0 ; y <= GameProperAverage.MAX_BASEY; y++){
                targetTiles[x][y] = (y == rowA || y == rowB); //pag yung y equals dun sa rowA or rowB ready for attack na siya
            }
        }
    }
    //diagonal
    private void thirdAttackFill(int mstart, int dm){
        for(int d = 0; d <= GameProperAverage.MAX_BASEY; d++){
            int nm = mstart + d * dm;
            if (nm > GameProperAverage.MAX_BASEX){
                nm = nm - GameProperAverage.MAX_BASEX - 1;
            }
            if (nm < 0) {
                nm = nm + GameProperAverage.MAX_BASEX + 1;
            }

            targetTiles[nm][d] = true;
        }
    }
    //diagonal
    private void thirdAttack(){
        int dm1 = -1 + MathUtils.random(1) * 2;
        int dm2 = -1 + MathUtils.random(1) * 2;

        int colA = MathUtils.random(GameProperAverage.MAX_BASEX);
        int colB = 0;
        do{
            colB = MathUtils.random(GameProperAverage.MAX_BASEX);
        }while (colA == colB);

        for (int x = 0; x <= GameProperAverage.MAX_BASEX; x++){
            for (int y = 0 ; y <= GameProperAverage.MAX_BASEY; y++){
                targetTiles[x][y] = false;
            }
        }

        thirdAttackFill(colA, dm1);
        thirdAttackFill(colB, dm2);
    }
    //random
    private void fourthAttack(){
        for (int x = 0; x <= GameProperAverage.MAX_BASEX; x++){
            for (int y = 0 ; y <= GameProperAverage.MAX_BASEY; y++){
                targetTiles[x][y] = false;
            }
        }

        for (int j = 0; j < 10; j++){
            int vx = MathUtils.random(GameProperAverage.MAX_BASEX);
            int vy = MathUtils.random(GameProperAverage.MAX_BASEY);

            targetTiles[vx][vy] = true;
        }

    }
//
//    private void finalAttack(){
//        int randAttck = MathUtils.random(3);
//        switch (randAttck){
//            case 0:
//                firstAttack();
//                break;
//            case 1:
//                secondAttack();
//                break;
//            case 2:
//                thirdAttack();
//                break;
//            default:
//                fourthAttack();
//                break;
//        }
//    }


    /*public int getLives(){
        return lives;
    }*/
}
