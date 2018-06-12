package com.mygdx.game;

/**
 * Created by RP4K on 8/9/2017.
 */



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class Bullet {
    float xPos, yPos, myAngle, speed, height, width, angle;
    int counter;
    boolean active;
    public Texture BulletTexture;


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
        //if(counter > 400){
           // active = false;
       // }
    }
    public void updateAngle(){
        if(!ZombieTowerYB.Zombieval.isEmpty()){
            float disX, disY, ZomX, ZomY, angle;
            ZomX = ZombieTowerYB.Zombieval.get(0).xPos;
            ZomY = ZombieTowerYB.Zombieval.get(0).yPos;
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
