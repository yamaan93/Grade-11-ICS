
/*
 * Yamaan Bakir
 * 2/27/2018
 * 
 */
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import hsa_ufa.Console;

public class Invaders {
	// all the variable declarations
	static Font ocr = new Font("OCR A Extended", Font.BOLD, 30);
	static Font title = new Font("OCR A Extended", Font.BOLD, 70);
	static int Px;
	static int Py = 664;

	static int start = 0;
	static boolean down;
	static boolean win = false;
	// static int[] inv =new int[] {0,0,0,0};
	static boolean mode = false;
	static int bullx;
	static int bully;
	static boolean fire = false;
	static ArrayList<enemy> inv = new ArrayList<enemy>();
	public static int score = 0;
	static Color retro = new Color(4, 240, 20);
	static enemy in1 = new enemy();
	static enemy in2 = new enemy();
	static enemy in3 = new enemy();
	static enemy in4 = new enemy();
	static enemy in5 = new enemy();
	static enemy in6 = new enemy();
	static enemy in7 = new enemy();
	static enemy in8 = new enemy();
	static enemy in9 = new enemy();
	static enemy in10 = new enemy();
	static enemy in11 = new enemy();
	static enemy in12 = new enemy();
	static enemy in13 = new enemy();
	// my favorite scheduler
	public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	// initialises new console
	public static Console c = new Console(1020, 760, "Pirate invaders");
	public static Image player = Toolkit.getDefaultToolkit()
			.getImage(c.getClass().getClassLoader().getResource("myMedia/player.png"));
	public static Image invader = Toolkit.getDefaultToolkit()
			.getImage(c.getClass().getClassLoader().getResource("myMedia/invaider.png"));

	public static void update() {// Method for updating screen
		synchronized (c) {
			c.clear();
			for (int i = 1; i < 10; i++) {

			}
			c.drawString("score: " + score, 0, 50);
			for (int i = 0; i < inv.size(); i++) {
				inv.get(i).updateinv();
			}
			c.drawImage(player, Px, Py, 100, 100);
			if (fire == true) {
				c.fillRect(bullx, bully, 10, 10);

			}

		}
	}

	public static void player() {
		// controls player

		if (c.isKeyDown(68) || c.isKeyDown(39)) {
			Px++;

		}
		if (c.isKeyDown(65) || c.isKeyDown(37)) {
			Px--;

		}

	}

	public static void fire() throws InterruptedException { // fires the bullet

		fire = true;
		bullx = Px + 45;
		bully = Py;
		while (fire == true) {

			bully = bully - 2;
			if (bully == 0) {
				fire = false;
			}

			c.setColor(retro);

			player();

			// invader();
			update();

			Thread.sleep(1);

		}

	}

	public static void startup() {
		win = false;

		in1.x = 0;
		in2.y = 0;
		in3.y = 0;
		in4.y = 0;
		in5.y = 0;
		in6.y = 0;
		in2.x = in1.x + 100;// offset each new invader
		in3.x = in2.x + 100;
		in4.x = in3.x + 100;
		in5.x = in4.x + 100;
		in6.x = in2.x;
		in6.y = in2.y + 75;
		in7.y = in2.y + 75;
		in8.y = in2.y + 75;
		in9.y = in2.y + 75;
		in7.x = in6.x + 75;
		in8.x = in7.x + 75;
		in9.x = in8.x + 75;
		for (int i = 0; i < inv.size(); i++) {
			inv.get(i).hit = false;
			inv.get(i).switchD = false;

		}

		System.out.println("startup complete");
	}

	public static void endgame() {
		if (in2.hit == true && in3.hit == true && in3.hit == true && in4.hit == true && in5.hit == true
				&& in6.hit == true && in7.hit == true && in8.hit == true && in9.hit == true) {
			win = true;
			start = 3;
		}
	}

	public static void main(String[] args) {// main
		// inv.add(0, in1);
		inv.add(0, in2);
		inv.add(0, in3);
		inv.add(0, in4);
		inv.add(0, in5);
		inv.add(0, in6);
		inv.add(0, in7);
		inv.add(0, in8);
		inv.add(0, in9);
		c.setBackgroundColor(Color.BLACK);
		c.clear();// clears the screen to apply background

		c.setFont(new Font("OCR A Extended", Font.BOLD, 70));
		c.setColor(retro);
		startup();

		// AudioClip sound1 =
		// Applet.newAudioClip(Media.class.getResource("pirate.wav"));

		c.setFont(ocr);

		Image player;
		player = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("myMedia/player.png"));

		final Runnable draw = new Runnable() {// creates new "runnable" AKA: it
			// stars a new thread
			@Override
			public void run() {
				endgame();

				if (start == 0) {
					c.drawString("space invaders beta!", 0, 100);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// start menue instructions
					c.drawString("press shift to start", 0, 200);
					c.drawString("controls:", 500, 200);
					c.drawString("D = left", 500, 250);
					c.drawString("A = right", 500, 300);
					c.drawString("Space = fire", 500, 350);

					if (c.isKeyDown(16)) {
						start = 2;
					}
				}
				if (start == 2 && win == false) {
					// once game started
					player();

					if (c.isKeyDown(32)) {
						try {
							fire();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					update();// paint all objects

				}
				if (start == 3) {
					c.setFont(title);
					c.drawString("VICTORY", 200, 500);
					c.setFont(ocr);
					c.drawString("press Shift to continue", 200, 600);

				}
				if (c.isKeyDown(70)) {
					System.out.println("restarting");
					startup();

					start = 2;

				}
			}

		};

		final Runnable music = new Runnable() {// plays music
			@Override
			public void run() {

				// sound1.play();
			}
		};

		scheduler.scheduleWithFixedDelay(draw, 1, 1, MILLISECONDS);
		scheduler.scheduleWithFixedDelay(music, 0, 241, SECONDS);
	}

}
