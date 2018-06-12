package com.mygdx.game;

/**
 * Created by RP4K on 8/9/2017.
 */



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

import static java.lang.StrictMath.sqrt;

public class Bullet {
    float xPos, yPos, myAngle, speed, height, width, angle;
    int counter;
    boolean active;
    public Texture BulletTexture;
    int closeZombie;


    public Bullet (float x, float y){

        xPos = x;
        yPos = y;
        height = Resources.BulletTexture.getHeight();
        width = Resources.BulletTexture.getWidth();
        speed = 39;
        active = true;
        BulletTexture = Resources.BulletTexture;
        counter = 0;
        updateAngle();



    }


    public void Draw(SpriteBatch batch){
        batch.draw(BulletTexture,xPos-width/2,yPos-height/2);

    }
    public void update(){
        xPos+= (float) Math.cos(myAngle)*speed;
        yPos+= (float) Math.sin(myAngle)*speed;
        counter ++;
        if(counter > 400){
         active = false;
         }
    }
    public void updateAngle(){
        if(!ZombieTowerYB.Zombieval.isEmpty()){

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
            disX =  ZomX - xPos;
            disY =  ZomY - yPos;
            angle = (float) Math.atan(disY/disX);
            this.myAngle = angle;
            if(ZomX < xPos){
                myAngle += Math.PI;
            }

        }
    }

    public Circle getCircle(){
        return new Circle(xPos, yPos,width/2);
    }

}
