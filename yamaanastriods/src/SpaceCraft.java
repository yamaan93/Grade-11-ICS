import java.awt.*;


public class SpaceCraft extends VectorSprite {
	int lives;
	Color color;
	public SpaceCraft(Color col){
		color = col;
		shape = new Polygon();
        shape.addPoint(15, 0);
		shape.addPoint(0, 30);
		shape.addPoint(30, 30);
		drawShape = new Polygon();
        drawShape.addPoint(15, 0);
		drawShape.addPoint(0, 30);
		drawShape.addPoint(30, 30);
        xPosition = 980;
        yPosition = 450;
        ROTATION=0.2;
        THRUST=1;
        active=true;
        lives=5;
	}
	public void accelerate(){
		xSpeed+=Math.cos(angle-Math.PI/2)*THRUST;
		ySpeed+=Math.sin(angle-Math.PI/2)*THRUST;
	}
	
	public void decelerate(){
		xSpeed-=Math.cos(angle-Math.PI/2)*THRUST/2;
		ySpeed-=Math.sin(angle-Math.PI/2)*THRUST/2;
		
	}
	
	public void rotateRight(){
		angle+=ROTATION;
		
	}
	
	public void rotateLeft(){
		
		angle-=ROTATION;
	}
	
	public void hit(){
		active=false;
		counter=0;
		lives-=1;
		// :)
		
		
	}
	
	public void reset(){
		xPosition=980;
		yPosition=450;
		xSpeed=0;
	    ySpeed=0;
		angle=0;
		active=true;
		
	}


}
