
/*Yamaan Bakir
 *ICS 3U1
 * Mrs.Medd
 * June 20, 2018
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.Timer;

import hsa_ufa.Console;

public class astriods extends Applet implements KeyListener, ActionListener, MouseListener {
	int[] x;
	int[] y;
	ArrayList<Astriod> astriodList;
	ArrayList<Bullet> bulletList;
	ArrayList<Debris> explosionList;
	ArrayList<SpaceCraft> shipList;
	SpaceCraft ship;
	Astriod rock;
	Timer timer;
	Image offScreen;
	Graphics offG;
	boolean upKey, downKey, leftKey, rightKey, spaceKey, wKey, aKey, sKey, dKey, shiftKey;
	boolean paused;
	int score;
	AudioClip winSound;
	AudioClip blast;
	AudioClip retro;
	boolean started;
	boolean survival;
	int astriodCounter;
	int timeC;
	boolean timeChallange;
	boolean timeTick;
	BufferedReader in;
	BufferedWriter out;
	File file = new File("survivalScore.txt");
	int highScore;
	int timeHS;
	File timeFile = new File("timeHS.txt");
	boolean newScore;
	boolean multiplayer;

	@Override
	public void init() {
		
		Console c = new Console(1800, 1080);
		c.setBackground(Color.BLACK);
		c.clear();
		Image player = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("myMedia/background.png"));
		c.drawImage(player, 0, 0, 1920, 1080);
		for(int i = 0; i < 1001; i++) {
			
			c.setColor(Color.green);
			c.fillRect( 300, 700, i, 50);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		c.close();
		newScore = false;
		timeC = 100000;
		timeTick = true;
		this.addMouseListener(this);
		winSound = getAudioClip(getCodeBase(), "Beat a Level.wav");// sounds for game
		retro = getAudioClip(getCodeBase(), "retro.wav");
		blast = getAudioClip(getCodeBase(), "Blast.wav");
		bulletList = new ArrayList(); // arraylist for all reused objects
		astriodList = new ArrayList();
		explosionList = new ArrayList();
		shipList = new ArrayList();
		rock = new Astriod();
		astriodList.add(rock);
		for (int i = 0; i < 8; i++) {// adds asteroids to the game
			astriodList.add(new Astriod());
		}
		shipList.add(new SpaceCraft(Color.GREEN)); // adds player with the color of green (player 2 is white)
		x = new int[3];
		x[0] = 15;
		x[1] = 0;
		x[2] = 30;
		y = new int[] { 0, 30, 30 };
		started = false;

		this.setSize(1960, 1080);// sets size of the display
		this.addKeyListener(this);// key listener for player movement
		timer = new Timer(20, this);
		offScreen = createImage(this.getWidth(), this.getHeight());
		offG = offScreen.getGraphics();

		try { // starts file reader for highscores, reads them in if there are any
			in = new BufferedReader(new FileReader(file.getAbsoluteFile())); // im pretty sure this creates a new file 
			String str = in.readLine();
			if (str != null) {
				highScore = Integer.parseInt(str);
			}
			in.close();
			in = new BufferedReader(new FileReader(timeFile.getAbsoluteFile()));
			str = in.readLine();
			if (str != null) {
				timeHS = Integer.parseInt(str);
			}
			in.close();

		}

		catch (Exception e) {

		}

	}

	@Override
	public void paint(Graphics g) { // main paint meathod

		if (started == true) {

		}

		if (!started) {
			paintMainMenu();
		} else {
			if (paused == true) {
				offG.setFont(new Font("Lucia Grande", 0, 50));
				offG.drawString("PAUSED", 910, 650);

			} else {
				paint1p();
			}
		}
		g.drawImage(offScreen, 0, 0, this);
		repaint();

	}

	public void paint1p() {
		offG.setColor(Color.BLACK);
		offG.fillRect(0, 0, 1960, 1080);
		offG.setColor(Color.GREEN);
		for (int i = 0; i < shipList.size(); i++) {
			if (shipList.get(i).active) {
				offG.setColor(shipList.get(i).color);
				shipList.get(i).paint(offG);
				offG.setColor(Color.GREEN);
			}

		}
		for (int i = 0; i < astriodList.size(); i++) {
			astriodList.get(i).paint(offG);
		}
		for (int i = 0; i < bulletList.size(); i++) {

			bulletList.get(i).paint(offG);
		}

		if (astriodList.isEmpty() && survival == false && timeChallange == false) {
			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("YAY YOU WIN!!! :)  press R to restart", 800, 450);

		}

		if (survival) {

			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("HIGHSCORE:  " + highScore, 1300, 50);
		}

		if (multiplayer) {
			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("p2 lives:  " + shipList.get(1).lives, 1300, 50);

		}

		for (int i = 0; i < shipList.size(); i++) {
			System.out.println(shipList.get(i).lives);
			if (shipList.get(i).lives == 0 && timeChallange == false) {// removed && (multiplayer &&
																		// shipList.get(1).lives == 0)
				offG.setFont(new Font("Lucia Grande", 0, 50));
				offG.drawString("ha! you suck press R", 800, 450);

				if (survival && score > highScore) {

					newhighScore(file);
				}
			}
		}
		if (timeChallange == true) {
			paintTC();
		} else {

			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("lives " + shipList.get(0).lives, 1000, 50);
		}
		offG.drawString("score:  " + score, 200, 50);

		for (int i = 0; i < explosionList.size(); i++) {

			explosionList.get(i).paint(offG);
		}

		if (newScore == true) {

			paintHS();

		}

	}

	public void paintMainMenu() { // paints the main menu for the game
		offG.setColor(Color.BLACK);
		offG.fillRect(0, 0, 1960, 1080);
		offG.setColor(Color.GREEN);

		offG.setFont(new Font("Lucia Grande", 0, 100));
		offG.drawString("ASTRIODS!", 700, 90);
		offG.setFont(new Font("Lucia Grande", 0, 80));
		offG.drawString(" 1 Player", 750, 300);

		offG.drawString(" SURVIVAL", 750, 500);

	
		offG.drawString("TIME TRIAL", 750, 700);

	
		offG.drawString("2 PLAYER!", 750, 900);
		offG.setFont(new Font("Lucia Grande",0,70));
		offG.drawString("Controls:", 20, 200);
		offG.setFont(new Font("Lucia Grande",0,60));
		offG.drawString("Accelerate: Up Arrow", 20, 300);
		offG.drawString("Deccelerate: Down Arrow", 20, 400);
		offG.drawString("Turn Right: Right Arrow", 20, 500);
		offG.drawString("Turn Left: Left Arrow", 20, 600);
	}

	public void paintTC() { // paints the ingame messages
		if (timeChallange == true) {
			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("Timer:  " + timeC, 800, 50);

			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("HIGHSCORE:  " + timeHS, 1300, 50);
		}

		if (timeC == 0) {
			offG.setFont(new Font("Lucia Grande", 0, 50));
			offG.drawString("oops! you ran out of time!", 700, 450);
		}

		if (newScore == true) {// only paint High score message if there is a new highscore 

			paintHS();

		}

	}

	public void paintHS() { // paints the new highscore message

		offG.setFont(new Font("Lucia Grande", 0, 50));
		offG.drawString("yay! new high score!", 700, 350);
	}

	public void checkCollision() { // checks if bullets or player hit an asteriod
		for (int i = 0; i < astriodList.size(); i++) {
			double rnd;
			for (int j = 0; j < bulletList.size(); j++) {
				if (collision(bulletList.get(j), astriodList.get(i))) {
					astriodList.get(i).active = false;
					bulletList.get(j).active = false;
					score += 1000;

					rnd = Math.random() * 30 + 15;
					for (int k = 0; k < rnd; k++) {

						explosionList.add(new Debris(astriodList.get(i).xPosition, astriodList.get(i).yPosition));
					}
				}
			}
			for (int j = 0; j < shipList.size(); j++) {

				if (collision(shipList.get(j), astriodList.get(i)) && shipList.get(j).active) {
					shipList.get(j).hit();
					score -= 2000;
					rnd = Math.random() * 30 + 5;
					for (int k = 0; k < rnd; k++) {

						explosionList.add(new Debris(shipList.get(j).xPosition, shipList.get(j).yPosition));
					}
				}

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) { // checks player input
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downKey = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upKey = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightKey = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			spaceKey = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_W) {
			wKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aKey = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			sKey = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			dKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftKey = false;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (started == false) {

		} else {
			if (e.getKeyCode() == KeyEvent.VK_R) {// restarts game if r is pressed

				shipList.get(0).reset();

				shipList.get(0).lives = 5;

				astriodList.clear();

				shipList.clear();

				shipList.add(new SpaceCraft(Color.GREEN));

				for (int i = 0; i < 8; i++) {
					astriodList.add(new Astriod());
				}
				multiplayer = false;
				started = false;
				paintMainMenu();
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				paused = !paused;
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downKey = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upKey = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftKey = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightKey = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spaceKey = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				wKey = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				aKey = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				sKey = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				dKey = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shiftKey = true;
			}
			
			else {
				System.out.println("press the right button");// EFFECTIVE use of error checking on user input 
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void keyCheck() { // when the keys are pressed, these are the actions
		if (upKey) {
			shipList.get(0).accelerate();// controls ships movement 
		}
		if (downKey) {
			shipList.get(0).decelerate();
		}

		if (leftKey) {
			shipList.get(0).rotateLeft();

		}

		if (rightKey) {
			shipList.get(0).rotateRight();
		}

		if (spaceKey) {
			fireBullet(0);

		}

		if (multiplayer) {// only reads player 2 input if multiplayer is selected
			if (wKey) {
				shipList.get(1).accelerate();
			}

			if (aKey) {
				shipList.get(1).rotateLeft();
			}

			if (sKey) {
				shipList.get(1).decelerate();
			}

			if (dKey) {
				shipList.get(1).rotateRight();
			}

			if (shiftKey) {
				fireBullet(1);
			}
		}
	}

	@Override
	public void update(Graphics g) {// main screen update
		paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (paused == true) {

		} else {
			keyCheck();
			respawnShip();
			shipList.get(0).updatePosition();
			for (int i = 0; i < explosionList.size(); i++) {

				explosionList.get(i).updatePosition();
				if (explosionList.get(i).counter == 30 || !explosionList.get(i).active) { // put after 100 if you want
																							// bullets to disaprear
																							// after colision, ||
																							// !bulletList.get(i).active
					explosionList.remove(i);
				}

			}
			for (int i = 0; i < astriodList.size(); i++) {
				astriodList.get(i).updatePosition();

			}
			for (int i = 0; i < bulletList.size(); i++) {
				bulletList.get(i).updatePosition();
				if (bulletList.get(i).counter == 100 || !bulletList.get(i).active) { // put after 100 if you want
																						// bsuullets to disaprear after
																						// colision, ||
																						// !bulletList.get(i).active
					bulletList.remove(i);
				}

			}
			// thsi section controls the spawning of new astroid periodically if survival or time trial mode is enabled
			if (timeChallange == true && timeTick == true) {
				timeC -= 1;
				astriodCounter += 1;
			}
			if(survival == true && score < 70000) {
				astriodCounter +=2;
			}
			else if(survival == true && score > 70000) {
				astriodCounter +=10;
			}
			if (timeC == 0) {
				// if there is a new highscore, save it 
				if (timeChallange && score > timeHS) {

					newhighScore(timeFile);
				}

				timeTick = false;
				shipList.get(0).active = false;

			}
			if (astriodCounter > 1200 && (survival == true || timeChallange == true)) {
				astriodList.add(new Astriod());

				astriodCounter =0;

			}
			checkCollision();
			checkAstriodDestruction();

			for (int i = 0; i < shipList.size(); i++) {
				shipList.get(i).updatePosition();

			}
		}
	}

	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
	}

	public boolean collision(VectorSprite thing1, VectorSprite thing2) {
		int x, y;
		for (int i = 0; i < thing1.drawShape.npoints; i++) {

			x = thing1.drawShape.xpoints[i];
			y = thing1.drawShape.ypoints[i];
			if (thing2.drawShape.contains(x, y)) {
				return true;
			}

		}

		for (int i = 0; i < thing2.drawShape.npoints; i++) {

			x = thing2.drawShape.xpoints[i];
			y = thing2.drawShape.ypoints[i];
			if (thing1.drawShape.contains(x, y)) {
				return true;
			}

		}
		return false;

	}

	public void respawnShip() {
		for (int i = 0; i < shipList.size(); i++) {

			if (!shipList.get(i).active && shipList.get(i).counter > 35 && isRespawnSafe() == true
					&& (shipList.get(i).lives > 0 || (timeChallange && timeC > 0))) {

				shipList.get(i).reset();

			}

		}

	}

	public boolean isRespawnSafe() {
		double x, y, h;

		for (int i = 0; i < astriodList.size(); i++) {

			x = 980 - astriodList.get(i).xPosition;
			y = 450 - astriodList.get(i).yPosition;
			h = Math.sqrt(x * x + y * y);
			if (h < 100) {
				return false;
			}

		}
		return true;

	}

	public void fireBullet(int i) { // fires bullet
		if (shipList.get(i).counter >= 25 && shipList.get(i).active == true) {
			bulletList.add(new Bullet(shipList.get(i).drawShape.xpoints[0], shipList.get(i).drawShape.ypoints[0],
					shipList.get(i).angle));
			shipList.get(i).counter = 0;
			blast.play();
		}

	}

	public void checkAstriodDestruction() {// when astroid is hit, spawn new astroids of a smaller size 
		for (int i = 0; i < astriodList.size(); i++) {
			if (astriodList.get(i).active == false) {

				if (astriodList.get(i).size > 1) {
					astriodList.add(new Astriod(astriodList.get(i).xPosition + 6, astriodList.get(i).yPosition + 2,
							astriodList.get(i).size - 1));
					astriodList.add(new Astriod(astriodList.get(i).xPosition - 6, astriodList.get(i).yPosition - 2,
							astriodList.get(i).size - 1));

				}
				if (astriodList.size() < 1) {// was ==1, game over not working
					winSound.play();

				}
				astriodList.remove(i);

			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {// for pressing on menu buttons
		// System.out.println(e.getX()+","+ e.getY());
		int x = e.getX();
		int y = e.getY();

		if (x > 776 && x < 1061) {

			if (y > 242 && y < 310) {
				started = true;
				retro.play();
			}
		}

		if (x > 775 && x < 1109) {
			if (y > 433 && y < 498) {
				started = true;
				survival = true;
				retro.play();

			}
		}

		if (x > 744 && x < 1190) {
			if (y > 633 && y < 702) {

				started = true;
				timeChallange = true;
				retro.play();
			}
		}

		if (x > 752 && x < 1149) {
			if (y > 840 && y < 900) {
				started = true;
				multiplayer = true;
				shipList.add(new SpaceCraft(Color.WHITE));
				retro.play();

			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void newhighScore(File writeFile) {

		newScore = true;

		try {
			out = new BufferedWriter(new FileWriter(writeFile.getAbsoluteFile()));
			out.write(Integer.toString(score));
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public void music() {

		retro.play();// plays music 
	}

}
