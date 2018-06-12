package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by RP4K on 8/8/2017.
 */

public class Explosion {
    public Texture ExplosionTexture;
    float xPos,yPos, height, width, speed;
    float health;
    boolean active;
    int numbCol, numRow;
    Animation walkAnimation;
    TextureRegion[] walkfarms;
    float farmtime;
    TextureRegion currentframe;
    int counter;

    public Explosion(float x, float y){
        ExplosionTexture = Resources.ExplosionTexture;
        xPos = x;
        yPos = y;
        height = Resources.ExplosionTexture.getHeight();
        width = Resources.ExplosionTexture.getWidth()/6;
        animateExplosion();
        speed = 1;
        active = true;
        counter= 0;
        health = 100;
    }
    public void Draw(SpriteBatch batch){
        farmtime += Gdx.graphics.getDeltaTime();
        currentframe = (TextureRegion)walkAnimation.getKeyFrame(farmtime, true);
        batch.draw(currentframe,xPos-width/2,yPos-height/2);

    }
     public void update(){
         counter++;
         if(counter > 60){
             active = false;
         }
         xPos-=speed;
         if( xPos <0-width){
             active = false;

         }
     }



    public void animateExplosion(){
        numRow = 1;
        numbCol = 6;
        TextureRegion[][] temp = TextureRegion.split(ExplosionTexture, ExplosionTexture.getWidth()/numbCol,ExplosionTexture.getHeight()/numRow);
        walkfarms = new TextureRegion[numRow*numbCol];
        int frameIndex = 0;
        for(int i=0; i<numRow; i++){
            for(int j = 0; j<numbCol; j++){
                walkfarms[frameIndex ++] = temp[i][j];
            }
        }
        walkAnimation = new Animation(0.2f, walkfarms);
    }

}
