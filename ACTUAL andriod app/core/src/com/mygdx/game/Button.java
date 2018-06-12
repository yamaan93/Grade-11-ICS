package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Yamaan on 2017-08-14.
 */

public class Button {

    public Texture button;
    public float x,y,width,height;
    public Rectangle play;
    public Button(int xPos,int yPos,Texture texture){
        button = texture;
        x = xPos;
        y = yPos;
        width = button.getWidth();
        height = button.getHeight();
        play = new Rectangle(x,y,width,height);
}
    public void Draw(SpriteBatch batch){

        batch.draw(button, x, y );


    }
     public boolean isClicked(float x , float  y){
         return play.contains(x,y) ;


     }
}


