package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;


import java.util.ArrayList;

public class ZombieTowerYB extends ApplicationAdapter {
	SpriteBatch batch;
	public static ArrayList<Cannon> Cannonval = new ArrayList<Cannon>();
	public static ArrayList<Zombie> Zombieval = new ArrayList<Zombie>();
	public static ArrayList<Bullet> Bulletval = new ArrayList<Bullet>();
	//Texture backgroundTexture;

	@Override
	public void create () {
		batch=new SpriteBatch();
		spawn();

	//	backgroundTexture = new Texture(Gdx.files.internal("Grassybackground.png"));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		batch.begin();
		batch.draw(Resources.backgroundTexture,0,0);
		for(int i = 0; i < Cannonval.size();i ++){
			Cannonval.get(i).Draw(batch);

		}
		for(int i = 0; i < Zombieval.size();i++) {
			Zombieval.get(i).Draw(batch);
		}
		for(int i = 0; i < Bulletval.size();i++){
			Bulletval.get(i).Draw(batch);
		}
		UI.Draw(batch);

		batch.end();


	}
	public void Controls(){
		if( Gdx.input.justTouched()){
			int x;
			int y;
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight()- Gdx.input.getY();
			if((y<500&&y>300||y<200)&&UI.DankCash>=10 ){
				Cannonval.add(new Cannon(x, y));
				UI.DankCash-=10;
			}
			//Zombieval.add(new Zombie (x,y));
		}

	}
	public void spawn(){
		for(int i = 0; i<3; i++){
			Zombieval.add(new Zombie(1024+i*50,275));


		}

	}
	public void update(){
		Collision();
		Controls();
		deleteSprite();
		for(int i = 0; i<Zombieval.size(); i++){
			Zombieval.get(i).update();
		}
		for(int i = 0; i<Bulletval.size(); i++){
			Bulletval.get(i).update();
		}
		for(int i = 0; i<Cannonval.size(); i++){
			Cannonval.get(i).update();
		}
		if(Zombieval.isEmpty()){
			spawn();
			UI.Wave++;
		}


	}
	@Override
	public void dispose () {
	}
	public void deleteSprite(){
		for(int i = 0;i<Bulletval.size();i++){
			if(!Bulletval.get(i).active){
				Bulletval.remove(i);
			}
		}
		for(int i = 0; i<Zombieval.size(); i++){
			if (!Zombieval.get(i).active){
				Zombieval.remove(i);
				UI.DankCash+=20;
			}

		}
	}
	public void Collision (){
		if(! Zombieval.isEmpty()){
			for(int j = 0; j<Bulletval.size(); j++){
				for(int i = 0; i<Zombieval.size(); i++){
					if(Intersector.overlaps(Bulletval.get(j).getCircle(),Zombieval.get(i).getRectangle())){
						Bulletval.get(j).active = false;
						Zombieval.get(i).Health();
					}
				}
			}
		}
	}

}
