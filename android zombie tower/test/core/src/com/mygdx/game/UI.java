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
    public static int DankCash = 60;
    public static int Wave = 0;
   public static BitmapFont font = new BitmapFont();

    public static void Draw(SpriteBatch batch){
        font.setColor(Color.WHITE);
        font.draw(batch," DANK CASH: "+DankCash, 500,600);
        font.draw(batch,"WAVE: " +Wave, 300,600);

    }

}
