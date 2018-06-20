
import java.awt.*;
public class VectorSprite {
	double xPosition;
	double yPosition;
	double xSpeed;
	double ySpeed;
	double angle;
	double ROTATION;
	double THRUST;
	boolean active;
	int counter;
	Polygon shape, drawShape;
	public VectorSprite(){
		shape = new Polygon();
        shape.addPoint(15, 0);
		shape.addPoint(0, 30);
		shape.addPoint(30, 30);
		drawShape = new Polygon();
        drawShape.addPoint(15, 0);
		drawShape.addPoint(0, 30);
		drawShape.addPoint(30, 30);
        xPosition = 450;
        yPosition = 300;
        ROTATION=0.2;
        THRUST=1;
        
	}
	
	public void paint(Graphics g){
		g.drawPolygon(drawShape);
	}
	
	public void updatePosition (){
		xPosition += xSpeed;
		yPosition += ySpeed;
		int x, y;
		counter++;
		wrapAround();
		for(int i = 0; i <shape.npoints; i++ ){ // nice eh?
			// draws shapes based off angle using MATH
			x = (int)Math.round(shape.xpoints[i] *Math.cos(angle) - shape.ypoints[i] *Math.sin(angle));
			y = (int)Math.round(shape.xpoints[i] *Math.sin(angle) + shape.ypoints[i] *Math.cos(angle));
			drawShape.xpoints[i] = x;
			drawShape.ypoints[i] = y;
		    
		}
		drawShape.invalidate();
		drawShape.translate((int)Math.round(xPosition), (int)Math.round(yPosition));
		
	}
	
	public void wrapAround(){
		if (xPosition>1960){
			xPosition=0;
		}
		if (xPosition<0){
			xPosition=1960;
		}
		if (yPosition <0){
			yPosition=1080;
		}
		if (yPosition>1080){
			yPosition=0;
		}
		
	}
	

}
