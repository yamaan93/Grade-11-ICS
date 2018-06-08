
public class bullet extends Frame {
	int x;
	int y;
	int w = 20;
	int h = 20;
	boolean fire;

	public bullet(int x1, int y1) {
		x = x1;
		y = y1;
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
		if (col() == false) {
			x += 10;
			c.fillRect(x, y, w, h);
		}
	}

}
