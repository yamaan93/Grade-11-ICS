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
 * Created by RP4K on 8/8/2017.
 */

public class Zombie {
    public Texture ZombieTexture;
    float xPos,yPos, height, width, speed;
    float health;
    boolean active;
    int numbCol, numRow;
    Animation walkAnimation;
    TextureRegion[] walkfarms;
    float farmtime;
    TextureRegion currentframe;

    public Zombie(float x, float y){
        ZombieTexture = Resources.ZombieTexture;
        xPos = x;
        yPos = y;
        height = Resources.ZombieTexture.getHeight();
        width = Resources.ZombieTexture.getWidth()/4;
        animateZom();
        speed = 1;
        active = true;
        health = 100;
    }
    public void Draw(SpriteBatch batch){
        farmtime += Gdx.graphics.getDeltaTime();
        currentframe = (TextureRegion)walkAnimation.getKeyFrame(farmtime, true);
      batch.draw(currentframe,xPos-width/2,yPos-height/2);

    }
    public void update(){
        xPos-=speed;
        if( xPos <0-width){
            active = false;

        }

    }
    public Rectangle getRectangle(){
        return new Rectangle(xPos, yPos, height,width);
    }
    public void Health(){
        health -= 20;
        if(health< 1){
            active = false;
        }
    }
    public void animateZom(){
        numRow = 1;
        numbCol = 4;
        TextureRegion[][] temp = TextureRegion.split(ZombieTexture, ZombieTexture.getWidth()/numbCol,ZombieTexture.getHeight()/numRow);
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
