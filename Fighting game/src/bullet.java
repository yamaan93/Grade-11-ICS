
public class bullet extends Frame {
	int x;
	int y;
	int w = 20;
	int h = 20;
	boolean fire;
	int direction;

	public bullet(int x1, int y1, int mode) {
		x = x1;
		y = y1;
		direction = mode;
	}

	public boolean col() {
		boolean hit;
		if (x > 1300) {
			hit = true;
		} else {
			hit = false;
		}

		return hit;
	}

	public void updatebullet() {

		col();
		if (col() == false && direction == 0) {
			x += 10;
			c.fillRect(x, y, w, h);
		}
		if (col() == false && direction == 1) {
			System.out.println("fired");
			x -= 10;
			c.fillRect(x, y, w, h);
		}
		if (col() == false && direction == 2) {
			x -= 10;
			y += 10;
			c.fillRect(x, y, w, h);
		}
		if (col() == false && direction == 3) {
			x -= 10;
			y += 10;
			c.fillRect(x, y, w, h);
		}
	}

}
