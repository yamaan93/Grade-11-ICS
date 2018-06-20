import java.awt.Polygon;



public class Debris extends VectorSprite{

	
	public Debris(double xPos, double yPos){ // generates a spiral of debris when an objectis hit 
	shape = new Polygon();
		double a;
		a=Math.random()*2*Math.PI;
		shape.addPoint(1, 1); 
		shape.addPoint(-2, -2 );
		shape.addPoint(-2, 2);
		shape.addPoint(2, -1);
		
		drawShape = new Polygon();
		
		drawShape.addPoint(2, 2);
		drawShape.addPoint(-2, 2);
		drawShape.addPoint(-2, 2);
		drawShape.addPoint(2, -2);
		
		xPosition = xPos;
		yPosition = yPos;
		angle = a - Math.PI/2;
		THRUST = 11;
		xSpeed=Math.cos(a)*a;
		ySpeed=Math.sin(a)*a;
		active = true;
		
	}
}
