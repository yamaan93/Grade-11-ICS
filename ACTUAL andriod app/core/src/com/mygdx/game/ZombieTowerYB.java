package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;


import java.util.ArrayList;

import static com.mygdx.game.Resources.*;

public class ZombieTowerYB extends ApplicationAdapter {
	SpriteBatch batch;
	public OrthographicCamera camera;
	public static ArrayList<Cannon> Cannonval = new ArrayList<Cannon>();
	public static ArrayList<Zombie> Zombieval = new ArrayList<Zombie>();
	public static ArrayList<Bullet> Bulletval = new ArrayList<Bullet>();
	public static ArrayList<Explosion> ExplosionVal = new ArrayList<Explosion>();public static Button startButton;
	public static Button cannonButton;
	public static Button fireButton;
	public static Button mapchoice;
	public static Button reset;
	public static int zombiePrice = 5;
	public static int fastzombiePrice = 20;
	public static int cannonPrice=30;
	public static int firecannonPrice=100;




	public enum towerType{
		cannon,
		superCannon;
	}
	public static towerType currentCannon;


	public static int Lives = 10;
	public static int gameState;
	@Override
	public void create () {
		gameState =0;
		batch=new SpriteBatch();
		spawn();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1024,600);
		startButton = new Button(1024/2-Resources.playButton.getWidth()/2,300, Resources.playButton);
		cannonButton = new Button (400,500,Resources.cannonButton);
		fireButton = new Button ( 700,500, Resources.firebutton);
		mapchoice = new Button ( 1024/2-Resources.begin2.getWidth()/2,200,Resources.begin2);
		reset = new Button ( 1024/2-Resources.restart.getWidth()/2,200,Resources.restart);




	}






	@Override
	public void render () {
			if(gameState ==1||gameState==2) {
				Gdx.gl.glClearColor(1, 0, 0, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				update();
				camera.update();
				batch.setProjectionMatrix(camera.combined);
				batch.begin();
				batch.draw(backgroundTexture, 0, 0);
				for (int i = 0; i < Cannonval.size(); i++) {
					Cannonval.get(i).Draw(batch);

				}

				for (int i = 0; i < Zombieval.size(); i++) {
					Zombieval.get(i).Draw(batch);
				}
				for (int i = 0; i < Bulletval.size(); i++) {
					Bulletval.get(i).Draw(batch);
				}
				for (int i = 0; i < ExplosionVal.size(); i++) {
					ExplosionVal.get(i).Draw(batch);
				}

				cannonButton.Draw(batch);
				fireButton.Draw(batch);
				UI.Draw(batch);


				batch.end();


			}
			else if (gameState == 0) {
					Gdx.gl.glClearColor(0, 0, 0, 1);
					Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
					camera.update();
					batch.setProjectionMatrix(camera.combined);

					batch.begin();
					//font.getData().setScale(3);
					//font.setColor(com.badlogic.gdx.graphics.Color.WHITE);
					//font.draw(batch,"Begin",1024/2,600/2 );
					startButton.Draw(batch);
					mapchoice.Draw(batch);
					int x,y;
					x = Gdx.input.getX();
					y = Gdx.graphics.getHeight() - Gdx.input.getY();
					Vector3 touchedPostion = new Vector3();
					touchedPostion.set(Gdx.input.getX(),Gdx.input.getY(),0);
					camera.unproject(touchedPostion);


					if(Gdx.input.justTouched()) {
						x= Gdx.input.getX();
						y= Gdx.graphics.getHeight()-Gdx.input.getY();
						if (startButton.isClicked(touchedPostion.x, touchedPostion.y)) {
							startButton = new Button(1024/2-Resources.playButton.getWidth()/2,300, Resources.startPressed);
							startButton.Draw(batch);
							Gdx.gl.glClearColor(0, 0, 0, 1);
							Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
							camera.update();
							batch.setProjectionMatrix(camera.combined);
							try{
								Thread.sleep(1000);
							}catch(Exception e){

							}
							gameState = 1;
						}
						else if(mapchoice.isClicked(touchedPostion.x,touchedPostion.y)){
							gameState = 2;
							backgroundTexture  = new Texture (Gdx.files.internal("alternateRoute.png"));
						}
						}
							batch.end();
					}

					if( gameState == 3){
						Gdx.gl.glClearColor(0, 0, 0, 1);
						Gdx.gl.glClearColor(0, 0, 0, 1);
						Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
						camera.update();
						batch.setProjectionMatrix(camera.combined);

						batch.begin();
						//font.getData().setScale(3);
						//font.setColor(com.badlogic.gdx.graphics.Color.WHITE);
						//font.draw(batch,"Begin",1024/2,600/2 );
						reset.Draw(batch);

						int x,y;
						x = Gdx.input.getX();
						y = Gdx.graphics.getHeight() - Gdx.input.getY();
						Vector3 touchedPostion = new Vector3();
						touchedPostion.set(Gdx.input.getX(),Gdx.input.getY(),0);
						camera.unproject(touchedPostion);


						if(Gdx.input.justTouched()) {
							x= Gdx.input.getX();
							y= Gdx.graphics.getHeight()-Gdx.input.getY();
							if (reset.isClicked(touchedPostion.x, touchedPostion.y)) {
								Lives = 10;
								gameState = 0;
							}

							}
							batch.end();
						}
					}









	public void Controls(){

				if (Gdx.input.justTouched()) {
					int x;
					int y;
					x = Gdx.input.getX();
					y = Gdx.graphics.getHeight() - Gdx.input.getY();
					Vector3 touchedPostion = new Vector3();
					touchedPostion.set(Gdx.input.getX(),Gdx.input.getY(),0);
					camera.unproject(touchedPostion);

					if (gameState == 1||gameState==2) {
						if (cannonButton.isClicked(touchedPostion.x, touchedPostion.y)) {
							currentCannon = towerType.cannon;
							batch.begin();
							cannonButton = new Button (400,500,Resources.cannonPressed);
							cannonButton.Draw(batch);
							fireButton = new Button(700,500,Resources.firebutton);
							fireButton.Draw(batch);
							batch.end();

						} else if (fireButton.isClicked(touchedPostion.x, touchedPostion.y)) {

							currentCannon = towerType.superCannon;
							batch.begin();
							fireButton = new Button(700,500,Resources.firecannonPressed);
							fireButton.Draw(batch);
							cannonButton = new Button (400,500,Resources.cannonButton);
							cannonButton.Draw(batch);
							batch.end();
						}
						if (gameState == 1) {

							if (currentCannon == towerType.cannon) {
								if (((int) touchedPostion.y < 500 && (int) touchedPostion.y > 300 || (int) touchedPostion.y < 200) && UI.DankCash >= cannonPrice) {

									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();

									//Zombieval.add(new Zombie (x,y));
								}
							} else if (currentCannon == towerType.superCannon) {
								if (((int) touchedPostion.y < 500 && (int) touchedPostion.y > 300 || (int) touchedPostion.y < 200) && UI.DankCash >= firecannonPrice) {

									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								}
							}

						} else if (gameState == 2) {
						if(currentCannon == towerType.cannon){
							if (currentCannon == towerType.cannon) {
								if (((int) touchedPostion.y < 500 && (int) touchedPostion.y > 150 && (int) touchedPostion.x < 500) && UI.DankCash >= cannonPrice) {
									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();
								} else if (((int) touchedPostion.y > 50 && (int) touchedPostion.y < 500 && (int) touchedPostion.x >= 350 && (int) touchedPostion.x < 500) && UI.DankCash >= cannonPrice) {
									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();
								} else if (((int) touchedPostion.y < 100 && (int) touchedPostion.x < 300) && UI.DankCash >= cannonPrice) {
									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();
								} else if (((int) touchedPostion.y < 450 && (int) touchedPostion.x > 550 && (int) touchedPostion.x < 700) && UI.DankCash >= cannonPrice) {
									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();
								} else if (((int) touchedPostion.y < 200 && (int) touchedPostion.x > 550) && UI.DankCash >= cannonPrice) {
									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();
								} else if (((int) touchedPostion.x > 800 && (int) touchedPostion.y < 500 && (int) touchedPostion.y > 300) && UI.DankCash >= cannonPrice) {

									Cannonval.add(new Cannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= cannonPrice;
									Stack();
								}
							}


							}
							if (currentCannon == towerType.superCannon) {
								if (((int) touchedPostion.y < 500 && (int) touchedPostion.y > 150 && (int) touchedPostion.x < 500) && UI.DankCash >= firecannonPrice) {
									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								} else if (((int) touchedPostion.y > 50 && (int) touchedPostion.y < 500 && (int) touchedPostion.x >= 350 && (int) touchedPostion.x < 500) && UI.DankCash >= firecannonPrice) {
									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								} else if (((int) touchedPostion.y < 100 && (int) touchedPostion.x < 300) && UI.DankCash >= firecannonPrice) {
									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								} else if (((int) touchedPostion.y < 450 && (int) touchedPostion.x > 550 && (int) touchedPostion.x < 700) && UI.DankCash >= firecannonPrice) {
									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								} else if (((int) touchedPostion.y < 200 && (int) touchedPostion.x > 550) && UI.DankCash >= firecannonPrice) {
									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								} else if (((int) touchedPostion.x > 800 && (int) touchedPostion.y < 500 && (int) touchedPostion.y > 300) && UI.DankCash >= firecannonPrice) {

									Cannonval.add(new fireCannon((int) touchedPostion.x, (int) touchedPostion.y));
									UI.DankCash -= firecannonPrice;
									Stack();
								}
							}
						}
					}

			}
	}
	public void spawn(){
		for(int i = 0; i<3; i++){
			Zombieval.add(new Zombie(1024+i*50,275, 120));
			//Zombieval.add(new fastZombie(1024+i*50,275,120));


		}

	}
	public void update(){

			if(gameState==1||gameState==2){
			Collision();
			Controls();
			deleteSprite();
			if (Lives > 0) {
				for (int i = 0; i < Zombieval.size(); i++) {
					Zombieval.get(i).update();
				}
				for (int i = 0; i < Bulletval.size(); i++) {
					Bulletval.get(i).update();
				}
				for (int i = 0; i < Cannonval.size(); i++) {
					Cannonval.get(i).update();
				}
				for (int i = 0; i < ExplosionVal.size(); i++) {
					ExplosionVal.get(i).update();
				}
				if (Zombieval.isEmpty()) {
					UI.Wave++;
					for (int i = 0; i < 3 + UI.Wave / 2; i++) {
						Zombieval.add(new Zombie(1024 + i * 50, 275, 100 + 15 * UI.Wave));

					}
					if (UI.Wave % 4 == 0) {
						for (int i = 1; i < 3 + UI.Wave / 4; i++) {
							Zombieval.add(new fastZombie(1024 + i * 50, 275, 90 + 10 * UI.Wave));
						}
					}
					//health
					if(UI.Wave % 4 == 00){
						for(int i = 1; i<3+ UI.Wave/4; i++){
							Zombieval.add(new heavyzombie(1024 + i * 50, 275, 250 + 10 * UI.Wave));

						}
					}

				}
			}
			}
		}



	@Override
	public void dispose () {
	}
	public void deleteSprite() {
		for (int i = 0; i < Bulletval.size(); i++) {
			if (!Bulletval.get(i).active) {
				Bulletval.remove(i);
			}
		}
		for (int i = 0; i < Zombieval.size(); i++) {
			if (!Zombieval.get(i).active) {

				if(Zombieval.get(i).zombieType ==0) {
					UI.DankCash += zombiePrice;
				}
				else if( Zombieval.get(i).zombieType ==1){
					UI.DankCash+= fastzombiePrice;
				}
				Zombieval.remove(i);
			}

		}
		for (int i = 0; i < ExplosionVal.size(); i++) {
			if (!ExplosionVal.get(i).active) {
				ExplosionVal.remove(i);
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
						ExplosionVal.add(new Explosion(Zombieval.get(i).xPos,Zombieval.get(i).yPos));
					}
				}
			}
		}
	}

	public void Stack() {
		if (Cannonval.size() > 1) {
			for (int i = 0; i < Cannonval.size()-1; i++) {
				if (Cannonval.get(Cannonval.size() - 1).xPos		 == (Cannonval.get(i).xPos) && Cannonval.get(Cannonval.size() - 1).yPos == (Cannonval.get(i).yPos)) {
					Cannonval.remove(Cannonval.size() - 1);
					if (currentCannon == towerType.cannon) {
						UI.DankCash += cannonPrice;
					} else if (currentCannon == towerType.superCannon) {
						UI.DankCash += firecannonPrice;
					}
				}
			}
		}
	}


}
