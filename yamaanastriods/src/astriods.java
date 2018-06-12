
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.Timer;
import java.util.ArrayList;
import java.applet.AudioClip;
public class astriods extends Applet implements KeyListener,ActionListener, MouseListener{
	int [] x;
	int [] y;
	ArrayList <Astriod> astriodList;
	ArrayList <Bullet>  bulletList;
	ArrayList <Debris> explosionList;
	ArrayList <SpaceCraft> shipList;
	SpaceCraft ship;
	Astriod rock;
	Timer timer;
	Image offScreen;
	Graphics offG;
	boolean upKey, downKey, leftKey, rightKey, spaceKey , wKey , aKey, sKey, dKey, shiftKey;
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
	File file = new File ("survivalScore.txt");
	int highScore;
	int timeHS;
	File timeFile = new File ("timeHS.txt");
	boolean newScore;
	boolean multiplayer;
	
	public void init(){
		newScore = false;
		timeC=100000;
		timeTick = true;
		this.addMouseListener(this);
		winSound =getAudioClip(getCodeBase(), "Beat a Level.wav");
		retro =getAudioClip(getCodeBase(), "retro.wav");
		blast = getAudioClip (getCodeBase(), "Blast.wav");
		bulletList= new ArrayList();
		astriodList= new ArrayList();
		explosionList=new ArrayList();
		shipList = new ArrayList();
		rock = new  Astriod();
		astriodList.add(rock);
		for (int i=0; i<8;i++){
			astriodList.add(new Astriod());
		}
		shipList.add(new SpaceCraft(Color.GREEN));
		x = new int [3];
		x[0]=15;
		x[1]=0;
		x[2]=30;
		y = new int [] { 0,30,30};
		started = false; 
		
		
			this.setSize(1960, 1080);
			this.addKeyListener(this);
			timer = new Timer (20,this);
			offScreen = createImage(this.getWidth(), this.getHeight());
			offG=offScreen.getGraphics();
					
			try{
				in = new BufferedReader (new FileReader ( file.getAbsoluteFile()));
				String str = in.readLine();
				if (str != null){
					highScore = Integer.parseInt(str);
				}
				in.close();
				in = new BufferedReader (new FileReader ( timeFile.getAbsoluteFile()));
				 str = in.readLine();
				if (str != null){
					timeHS = Integer.parseInt(str);
				}
				in.close();
				
			}
			
			catch(Exception e){
				
				
			}
			
	}
	public void paint(Graphics g){
		
		if (started == true){
			
			
		}
		
		if(!started){
			paintMainMenu();
		}
		else{
			if (paused==true){
				offG.setFont(new Font("Lucia Grande",0,50));
				offG.drawString("PAUSED", 910, 650);
		
			}
			else{
			paint1p();	
			}
		}
		g.drawImage(offScreen,0,0,this);
		repaint();
		
		
	}
	public void paint1p(){
		offG.setColor(Color.BLACK);
		offG.fillRect(0, 0, 1960, 1080);
		offG.setColor(Color.GREEN);
		for(int i=0; i<shipList.size();i++){
			if(shipList.get(i).active){
				offG.setColor(shipList.get(i).color);
				shipList.get(i).paint(offG);
				offG.setColor(Color.GREEN);
			}
		
		}
		for (int i=0; i < astriodList.size(); i++){
			astriodList.get(i).paint(offG);
		}
		for (int i=0; i < bulletList.size(); i++){
			
			bulletList.get(i).paint(offG);
		}
		if(astriodList.isEmpty()&& survival==false && timeChallange == false){
			offG.setFont(new Font("Lucia Grande",0,50));
			offG.drawString("YAY YOU WIN!!! :)", 800, 450);
			
		}
		
		if(survival){
			
			
			offG.setFont(new Font("Lucia Grande",0,50));
	        offG.drawString("HIGHSCORE:  "+highScore, 1300, 50);
		}
		
		if (multiplayer){
			offG.setFont(new Font("Lucia Grande",0,50));
	        offG.drawString("p2 lives:  "+shipList.get(1).lives, 1300, 50);
			
		}
		
		if (shipList.get(0).lives <0 && timeChallange == false&& (multiplayer && shipList.get(1).lives <0)){
			offG.setFont(new Font("Lucia Grande",0,50));
			offG.drawString("ha! you suck press R", 800, 450);
			
			if (survival && score > highScore){
				
				newhighScore(file);
			}
		}
		if (timeChallange == true){
			paintTC();
		}
		else{
			
			offG.setFont(new Font("Lucia Grande",0,50));
	        offG.drawString("lives "+shipList.get(0).lives, 1000, 50);
		}
        offG.drawString("score:  "+score, 200, 50);
        
        for(int i=0; i<explosionList.size(); i++){
        	
        	explosionList.get(i).paint(offG);
        }
        
if (newScore == true){
			
			paintHS();
			
		}
		
	}
	public void paintMainMenu(){
		offG.setColor(Color.BLACK);
		offG.fillRect(0, 0, 1960, 1080);
		offG.setColor(Color.GREEN);
		
		offG.setFont(new Font("Lucia Grande",0,100));
		offG.drawString("ASTRIODS!", 700, 90);
		offG.setFont(new Font("Lucia Grande",0,80));
		offG.drawString(" 1 Player", 750, 300);
		
		offG.setFont(new Font("Lucia Grande",0,80));
		offG.drawString(" SURVIVAL", 750, 500);
		
		offG.setFont(new Font("Lucia Grande",0,80));
		offG.drawString("TIME TRIAL", 750, 700);
		
		offG.setFont(new Font("Lucia Grande",0,80));
		offG.drawString("2 PLAYER!", 750, 900);
		
		
	}
	public void paintTC(){
		if (timeChallange == true){
			offG.setFont(new Font("Lucia Grande",0,50));
	        offG.drawString("Timer:  "+timeC, 800, 50);
	        
	        offG.setFont(new Font("Lucia Grande",0,50));
	        offG.drawString("HIGHSCORE:  "+timeHS, 1300, 50);
		}
		
		if (timeC == 0){
			offG.setFont(new Font("Lucia Grande",0,50));
			offG.drawString("oops! you ran out of time!", 700, 450);
		}
		
		if (newScore == true){
			
			paintHS();
			
		}
		
	}
	

	
	public void paintHS(){
		
		offG.setFont(new Font("Lucia Grande",0,50));
		offG.drawString("yay! new high score!", 700, 350);
	}
	
	public void checkCollision(){
			for(int i=0; i<astriodList.size(); i++){
				double rnd;
				for(int j=0; j<bulletList.size(); j++){
					if(collision(bulletList.get(j),astriodList.get(i))){
						astriodList.get(i).active = false;
						bulletList.get(j).active = false;
						score+=1000; 
						
						rnd = Math.random()*30+15;
						for(int k=0; k<rnd; k++){
							
						
						explosionList.add (new Debris (astriodList.get(i).xPosition, astriodList.get(i).yPosition)); 
					}
					}
				}
				for(int j=0; j<shipList.size();j++){
					

				if(collision(shipList.get(j),astriodList.get(i))&&shipList.get(j).active){
					shipList.get(j).hit();
					score-=2000;
					rnd = Math.random()*30+5;
					for(int k=0; k<rnd; k++){
							
						
						explosionList.add (new Debris (shipList.get(j).xPosition, shipList.get(j).yPosition));
						}
					}
					
				}
			}
			
			
	}
	
	public void keyReleased (KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			downKey=false;
		}
		
		if (e.getKeyCode()	== KeyEvent.VK_UP){
			upKey=false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			leftKey=false;
		}
		if (e.getKeyCode()	== KeyEvent.VK_RIGHT){
			rightKey=false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			
			spaceKey=false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W){
			wKey=false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A){
			aKey=false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S){
			sKey=false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D){
			dKey=false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT){
			shiftKey=false;
		}
		
	}
	
	public void keyPressed (KeyEvent e){
		
		if (started == false){
			
		}
		else{
		if (e.getKeyCode() == KeyEvent.VK_R){
			
			shipList.get(0).reset();
			
			shipList.get(0).lives = 5;
			
			astriodList.clear();
			
			shipList.clear();
			
			shipList.add(new SpaceCraft(Color.GREEN));
			
			for (int i=0; i<8;i++){
				astriodList.add(new Astriod());
			}
			multiplayer=false;
			started = false;
			 paintMainMenu();
		}
			
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			paused=!paused;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			downKey=true;
		}
		
		if (e.getKeyCode()	== KeyEvent.VK_UP){
			upKey=true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			leftKey=true;
		}
		if (e.getKeyCode()	== KeyEvent.VK_RIGHT){
			rightKey=true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			spaceKey=true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W){
			wKey=true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A){
			aKey=true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S){
			sKey=true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_D){
			dKey=true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT){
			shiftKey=true;
		}
		}
	}
	
	public void keyTyped (KeyEvent e){
		
	}
	public void keyCheck(){
		if (upKey){
			shipList.get(0).accelerate();
		}
		if (downKey){
			shipList.get(0).decelerate();
		}
		
		if (leftKey){
			shipList.get(0).rotateLeft();
			
		}
		
		if (rightKey){
			shipList.get(0).rotateRight();
		}
		
		if(spaceKey){
			fireBullet(0);
			
		}
		
		if(multiplayer){
			if(wKey){
				shipList.get(1).accelerate();
			}
			
			if(aKey){
				shipList.get(1).rotateLeft();
			}
			
			if(sKey){
				shipList.get(1).decelerate();
			}
			
			if(dKey){
				shipList.get(1).rotateRight();
			}
			
			if(shiftKey){
				fireBullet(1);
			}
		}
	}
	
	public void update(Graphics g){
		paint(g);
	}
	public void actionPerformed (ActionEvent e){
		if (paused==true){
			
			
		}
		else{
		keyCheck();
		respawnShip();
		shipList.get(0).updatePosition();
		for(int i=0; i<explosionList.size(); i++){
			
			explosionList.get(i).updatePosition();
			if (explosionList.get(i).counter == 30 || !explosionList.get(i).active ){ //put after 100 if you want bullets to disaprear after colision, || !bulletList.get(i).active
				explosionList.remove(i);
			}
			
			
		}
		for(int i=0; i<astriodList.size(); i++){
			astriodList.get(i).updatePosition();
			
		}
		for (int i=0; i < bulletList.size(); i++){
			bulletList.get(i).updatePosition();
			if	(bulletList.get(i).counter == 100 || !bulletList.get(i).active ){ //put after 100 if you want bsuullets to disaprear after colision, || !bulletList.get(i).active
				bulletList.remove(i);
			}
			
			

		}
		if (timeChallange ==true && timeTick == true){
		timeC-=1;
		astriodCounter+=1;
		}
		if (timeC == 0){
			
				if (timeChallange && score > timeHS){
				
				newhighScore(timeFile);
			}
			
			timeTick = false;
			shipList.get(0).active = false;
			
		}
		if (astriodCounter>1200&& (survival==true || timeChallange== true)){
			astriodList.add(new Astriod());
			
			astriodCounter=0;
			
		}
		checkCollision();
		checkAstriodDestruction();
		
		for(int i=0; i<shipList.size();i++){
			shipList.get(i).updatePosition();

		}
	}
	}
	public void start (){
		timer.start();
	}
	public void stop (){
		timer.stop();
	}
	public boolean collision(VectorSprite thing1, VectorSprite thing2){
		int x,y;
		for(int i = 0; i <thing1.drawShape.npoints; i++ ){

			
			x=thing1.drawShape.xpoints[i];
			y=thing1.drawShape.ypoints[i];
			if (thing2.drawShape.contains(x,y)){
				return true;
			}
			
		
			
			
		}
		
		for(int i = 0; i <thing2.drawShape.npoints; i++ ){

			
			x=thing2.drawShape.xpoints[i];
			y=thing2.drawShape.ypoints[i];
			if (thing1.drawShape.contains(x,y)){
				return true;
			}
		
			
	}
		return false;
		
		
	}
	
	public void respawnShip(){
		for(int i=0; i<shipList.size();i++){
			

		if(!shipList.get(i).active&&shipList.get(i).counter>35&&isRespawnSafe()==true&&(shipList.get(i).lives>-1||(timeChallange&&timeC>0))){
			
			
			shipList.get(i).reset();
			
		}
		
		
			
		}
		
	}
	
	public boolean  isRespawnSafe(){
		double x,y,h;
		
			
		
		for (int i=0; i<astriodList.size(); i++){
			
			x= 980 - astriodList.get(i).xPosition;
			y= 450 - astriodList.get(i).yPosition;
			h= Math.sqrt(x*x+y*y);
			if(h<100){
				return false;
			}
			
		}
		return true;
		
	}
	
	public void fireBullet(int i){
		if(shipList.get(i).counter >= 25 && shipList.get(i).active == true){
			bulletList.add(new Bullet(shipList.get(i).drawShape.xpoints[0], shipList.get(i).drawShape.ypoints[0], shipList.get(i).angle));
			shipList.get(i).counter=0;
			blast.play();
		}
		
	}
	
	public void checkAstriodDestruction(){
		for(int i=0; i<astriodList.size(); i++){
			if (astriodList.get(i).active == false){
				
				
				
				if (astriodList.get(i).size>1){
					astriodList.add(new Astriod(astriodList.get(i).xPosition+6, astriodList.get(i).yPosition+2,astriodList.get(i).size - 1));
					astriodList.add(new Astriod(astriodList.get(i).xPosition-6, astriodList.get(i).yPosition-2,astriodList.get(i).size - 1));
					
				}
				if (astriodList.size()==1){
					winSound.play();
					
				}
				astriodList.remove(i);

			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println(e.getX()+","+ e.getY());
		int x = e.getX();
		int y = e.getY();
		
		if (x>776 && x<1061){
			
			
		
			if (y>242 && y<310){
				started = true;
				retro.play();
			}
		}
		
		if (x>775 && x<1109 ){
		    if (y>433 && y<498){
		    	started = true;
		    	survival = true;
		    	retro.play();
		    	
		    }
		}
		
		if (x>744 && x<1190){
			if (y>633 && y<702){
				
				started = true;
				timeChallange = true;
				retro.play();
			}
		}
		
		if (x>752 && x<1149 ){
			if (y> 840 && y<900){
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
	
	public void newhighScore(File writeFile){
		
		newScore=true;
		
		try{
			out = new BufferedWriter (new FileWriter(writeFile.getAbsoluteFile()));
			out.write(Integer.toString(score));
			out.close();
		}
			catch (Exception e){
				System.out.println(e.getMessage());
				
			}
			
			
		
	}
	
	public void music(){
		
		retro.play();
	}
	
	
}
	
