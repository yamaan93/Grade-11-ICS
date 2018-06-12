package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

import static java.lang.Math.abs;
import static java.lang.StrictMath.sqrt;

/**
 * Created by RP4K on 8/8/2017.
 */

public class Cannon {

    int closeZombie;
    public Sprite CannonSprite;

    float xPos, yPos, width, height, angle;
    int delay, counter;
    public Sound bang;

    public Cannon(float x, float y) {


        setTexture();
        width = Resources.cannonTexture.getWidth();
        height = Resources.cannonTexture.getHeight();
        xPos = lock(x-width/2);
        yPos = lock(y-height/2);
        CannonSprite.setPosition(lock(xPos), lock(yPos));
        delay =     100;
        counter = 0;
        bang = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));



    }

    public void setTexture(){
        CannonSprite = new Sprite(Resources.cannonTexture);
    }

    public void Draw(SpriteBatch batch) {

        CannonSprite.draw(batch);
    }

    public void Fire() {
        if (!ZombieTowerYB.Zombieval.isEmpty()) {


    if(closeZombie< ZombieTowerYB.Zombieval.size()){
            if((sqrt(Math.pow((ZombieTowerYB.Zombieval.get(closeZombie).xPos - xPos ),2)+(Math.pow((ZombieTowerYB.Zombieval.get(closeZombie).yPos-yPos),2))))<300){
                ZombieTowerYB.Bulletval.add(new Bullet(xPos+width/2, yPos+height/2));
                bang.play();
            }
        }}


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
            double temp;
            for (int i = 0; i < ZombieTowerYB.Zombieval.size(); i++) {
                if(!(closeZombie<ZombieTowerYB.Zombieval.size())) {
                    closeZombie = 0;
                }
                temp = (sqrt(Math.pow((ZombieTowerYB.Zombieval.get(i).xPos - xPos), 2) + (Math.pow((ZombieTowerYB.Zombieval.get(i).yPos - yPos), 2))));
                if (temp < (sqrt(Math.pow((ZombieTowerYB.Zombieval.get(closeZombie).xPos - xPos), 2) + (Math.pow((ZombieTowerYB.Zombieval.get(closeZombie).yPos - yPos), 2))))) {
                    closeZombie = i;
                }
            }
            float disX, disY, ZomX, ZomY, angle;
            ZomX = ZombieTowerYB.Zombieval.get(closeZombie).xPos;
            ZomY = ZombieTowerYB.Zombieval.get(closeZombie).yPos;
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