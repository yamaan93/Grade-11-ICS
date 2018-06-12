package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * Created by RP4K on 8/8/2017.
 */

public class Resources {
    public static Texture backgroundTexture = new Texture(Gdx.files.internal("Grassybackground.png"));
    public static Texture cannonTexture = new Texture(Gdx.files.internal("Cannon.png"));
    public static Texture cannonButton = new Texture(Gdx.files.internal("Cannon.png"));
    public static Texture cannonPressed = new Texture (Gdx.files.internal("CannonPressed.png"));
    public static Texture firecannonPressed = new Texture (Gdx.files.internal("firecannonPressed.png"));
    public static Texture ZombieTexture = new Texture (Gdx.files.internal("Zombies.png"));
    public static Texture BulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
    public static Texture ExplosionTexture = new Texture(Gdx.files.internal("Explosion.png"));
    public static Texture FastZombieTexture = new Texture ( Gdx.files.internal ("Fastzombies.png"));
    public static Texture playButton = new Texture (Gdx.files.internal("begin.png"));
    public static Texture firebutton = new Texture (Gdx.files.internal("Firecannon.png"));
    public static Texture map2 = new Texture (Gdx.files.internal("alternateRoute.png"));
    public static Texture begin2 = new Texture(Gdx.files.internal("begin2.png"));
    public static Texture restart = new Texture(Gdx.files.internal("restart.png"));
    public static Texture heavyzombie = new Texture(Gdx.files.internal("heavyzombies.png"));
    public static Texture startPressed = new Texture(Gdx.files.internal("beginPressed.png"));
}
