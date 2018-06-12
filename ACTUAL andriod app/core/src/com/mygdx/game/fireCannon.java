package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import static java.lang.Math.abs;
import static java.lang.StrictMath.sqrt;

/**
 * Created by Yamaan on 2017-08-14.
 */

public class fireCannon extends Cannon {
    public fireCannon(float x, float y){
        super (x,y);
        delay = 20;

    }

    public void Fire() {
        if (!ZombieTowerYB.Zombieval.isEmpty()) {

            if((sqrt(Math.pow((ZombieTowerYB.Zombieval.get(0).xPos - xPos ),2)+(Math.pow((ZombieTowerYB.Zombieval.get(0).yPos-yPos),2))))<100){
                ZombieTowerYB.Bulletval.add(new Bullet(xPos+width/2, yPos+height/2));
                bang.play();
            }
        }}
    @Override
    public void setTexture(){
        CannonSprite = new Sprite(Resources.firebutton);
    }
}
