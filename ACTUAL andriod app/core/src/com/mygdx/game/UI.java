package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/10/2017.
 */

public class UI {
    public static int DankCash = 600;
    public static int Wave = 0;
    public static BitmapFont font = new BitmapFont();

    public static void Draw(SpriteBatch batch){
        font.setColor(Color.WHITE);
        font.draw(batch," DANK CASH: "+DankCash, 500,600);
        font.draw(batch,"WAVE: " +Wave, 300,600);
        font.draw(batch, "LIVES: "+ZombieTowerYB.Lives, 100,600);
        if(ZombieTowerYB.Lives<=0){
            font.getData().setScale(3);
            font.draw(batch, "GAME OVER, YOU'RE A FAILURE", 300,300);
            font.getData().setScale(1);
            ZombieTowerYB.gameState= 3;
        }
    }

}
