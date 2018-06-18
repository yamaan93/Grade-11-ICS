import java.awt.Polygon;


public class Astriod extends VectorSprite{
	int size;
	

	public Astriod(){
		
		size = 3;
		
		initializeAstriod();
		
		
	}
	
	public Astriod (double x, double y,int s){
		size = s;
		initializeAstriod();
		xPosition=x;
		yPosition=y;
	}
	public void initializeAstriod(){
		double a,h;
		shape = new Polygon();
        shape.addPoint(20*size, 2*size);
		shape.addPoint(3*size, 23*size);
		shape.addPoint(-18*size, 7*size);
		shape.addPoint(-11*size, -10*size);
		shape.addPoint(16*size, -18*size);
		drawShape = new Polygon();
        drawShape.addPoint(20*size, 2*size);
		drawShape.addPoint(3*size, 23*size);
		drawShape.addPoint(-18*size, 7*size);
		drawShape.addPoint(-11*size, -10*size);
		drawShape.addPoint(16*size, -18*size);
        ROTATION=0.2/2;
        THRUST=1;
        active=true;
        a=Math.random()*Math.PI*2;
        
        h=Math.random ()*400+100;
        xPosition = Math.cos(a)*h +980;
        yPosition = Math.sin(a)*h+1*450;
        h=Math.random()+1*5;
        xSpeed=Math.cos(a)*h/size;
        ySpeed=Math.sin(a)*h/size;
	}
	
	public void updatePosition(){
		angle += ROTATION;
		super.updatePosition();
	}
	
}
