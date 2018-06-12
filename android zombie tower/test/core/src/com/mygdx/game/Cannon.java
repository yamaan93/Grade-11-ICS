package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by RP4K on 8/8/2017.
 */

public class Cannon {
    public Sprite CannonSprite;

    float xPos, yPos, width, height, angle;
    int delay, counter;

    public Cannon(float x, float y) {


        CannonSprite = new Sprite(Resources.cannonTexture);
        width = Resources.cannonTexture.getWidth();
        height = Resources.cannonTexture.getHeight();
        xPos = lock(x-width/2);
        yPos = lock(y-height/2);
        CannonSprite.setPosition(lock(xPos), lock(yPos));
        delay =     0;
        counter = 0;


    }

    public void Draw(SpriteBatch batch) {

        CannonSprite.draw(batch);
    }

    public void Fire() {
        if (!ZombieTowerYB.Zombieval.isEmpty()) {
            ZombieTowerYB.Bulletval.add(new Bullet(xPos+width/2, yPos+height/2));
        }
    }

    public void update() {
        if( counter > delay){
            Fire();
            counter = 0;
        }
        updateAngle();
        CannonSprite.setRotation(angle);
        counter ++;
    }

    public void updateAngle() {
        if (!ZombieTowerYB.Zombieval.isEmpty()) {
            float disX, disY, ZomX, ZomY, angle;
            ZomX = ZombieTowerYB.Zombieval.get(0).xPos;
            ZomY = ZombieTowerYB.Zombieval.get(0).yPos;
            disX = ZomX - xPos;
            disY = ZomY - yPos;
            angle = (float) Math.atan(disY / disX);

            this.angle = angle;
            this.angle = (float) Math.toDegrees(angle);
            if (ZomX < xPos) {
                this.angle += 180;
            }

        }
    }

    public float lock(float grid){

        return ((int)(grid+25)/50)*50;

    }
}