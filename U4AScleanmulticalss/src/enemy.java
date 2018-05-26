
public class enemy extends Invaders {
	int x = 0;
	int y = 0;
	int w = 50;
	int h = 50;
	static int speed = 1;
	boolean switchD = false;
	boolean hit = false;

	public enemy() {
		switchD = false;
		hit = false;
		x = 0;
		y = 0;
	}

	public void col() { // checking colision of invader
		// old collision bullx > x - 1 && bullx < x + 101 && bully == y || bully > y &&
		// bully < y + h && bullx == x - w + 10
		if (bullx < x + w - 1 && bully < y + h - 1 && x < bullx + 10 && y < bully + 10) {
			hit = true;
			score = score + 100;
			fire = false;
			bully = Py;

		}
	}

	public void updateinv() { // draw invader

		if (hit == false) {

			c.drawImage(invader, x, y, w, h);

			// System.out.println(x);
			col();

			if (x < -1 || x > 800) {
				switchD = !switchD;
				down = true;

			}
			if (switchD == false) {
				x += speed;
			}
			if (switchD == true) {
				x -= speed;
			}

			if (down == true && y < 700) {
				down = false;

				y = y + 75;

			}
		}

	}

}
