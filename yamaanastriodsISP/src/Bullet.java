import java.awt.*;

public class Bullet extends VectorSprite{
	
	public Bullet(double xPos, double yPos, double a){
		shape = new Polygon();
		
		shape.addPoint(-15, 1);// for big bullets -25,25  
		shape.addPoint(15, 1 );// -25, 25
		shape.addPoint(15, -1);// 25,-25
		shape.addPoint(-15, -1);//25,25
		
		drawShape = new Polygon();
		
		drawShape.addPoint(0, 0);
		drawShape.addPoint(0, 50);
		drawShape.addPoint(50, 0);
		drawShape.addPoint(50, 50);
		
		xPosition = xPos;
		yPosition = yPos;
		angle = a - Math.PI/2;
		THRUST = 11;
		xSpeed=Math.cos(angle)*THRUST;
		ySpeed=Math.sin(angle)*THRUST;
		active = true;
		
	}

}
