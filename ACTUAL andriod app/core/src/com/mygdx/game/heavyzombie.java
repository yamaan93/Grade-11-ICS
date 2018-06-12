package com.mygdx.game;

/**
 * Created by Yamaan on 2017-08-18.
 */

public class heavyzombie extends Zombie{

    public heavyzombie ( float x, float y, float hp){
        super(x,y,hp);
        speed = 0.5;
        zombieType = 1;


    }
    public void setTexture(){
        ZombieTexture = Resources.heavyzombie;
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
