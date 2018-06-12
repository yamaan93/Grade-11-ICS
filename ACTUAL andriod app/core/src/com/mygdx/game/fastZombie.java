package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Yamaan on 2017-08-11.
 */

public class fastZombie extends Zombie {

    public fastZombie(float x, float y, float hp){
        super(x,y,hp);
        speed=2;
        zombieType = 1;

    }
    public void setTexture(){
        ZombieTexture = Resources.FastZombieTexture;
    }

    public void update(){
        if(ZombieTowerYB.gameState == 1){
            xPos -=speed;
        }
        if(ZombieTowerYB.gameState == 2) {
            if (xPos > 750) {
                xPos -= speed;
            }
            if (xPos >= 750&&xPos<751) {
                yPos += speed;

            }
            if (yPos >= 475&&yPos<477) {
                xPos -= speed;
            }
            if (xPos <= 525&& xPos>=524) {
                yPos -= speed;
            }
            if (yPos >=25&&yPos <26) {
                xPos -= speed;

            }
            if (xPos >= 324&&xPos<325) {
                yPos += speed;
            }
            if (yPos >= 150&& yPos>151 &&xPos <= 324&&xPos<325) {
                xPos -= speed;
            }
        }
        if( xPos <0-width){
            active = false;
            UI.DankCash -= ZombieTowerYB.fastzombiePrice;
            ZombieTowerYB.Lives --;

        }

    }
}
