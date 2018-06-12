import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Player extends Frame {
	Image player = Toolkit.getDefaultToolkit()
			.getImage(c.getClass().getClassLoader().getResource("myMedia/Megaman.jpg"));
	int w = 50;
	int h = 50;
	int x = 0;
	int y = 0;
	int health = 50;
	int tic;
	boolean playerpressed = false;
	public ArrayList<bullet> bullets = new ArrayList<bullet>();
	boolean hit;

	public Player() {

	}

	public boolean col() {

		for (int i = 0; i < p1.bullets.size(); i++) {// collision for all the bullets at once
			if (p1.bullets.get(i).x < x + w && x < p1.bullets.get(i).x + p1.bullets.get(i).w
					&& p1.bullets.get(i).y < y + h && y < p1.bullets.get(i).y + p1.bullets.get(i).h && health > 0) {
				p1.bullets.remove(i);// once a bullet hits remove it from array list to save on RAM
				health--;// remove health
				hit = true;
			}
		}
		return hit;

	}

	public void fire(int x1, int y1, int direct) throws InterruptedException {

		bullet b = new bullet(x1 + 50, y1 + 35, direct);
		bullets.add(b);

	}

	public void debug() {

	}

	public void update() throws InterruptedException {
		col();
		tic++;
		// System.out.println(bullets.size());
		// System.out.println(x + " " + y);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).updatebullet();

			if (bullets.get(i).col() == true) {
				bullets.remove(i);
			}
		}
		if (health > 0) {

			c.drawImage(player, x, y, 100, 100);
			int speed = 10;
			if (c.isKeyDown(68) && x < 1100) {
				x += speed;
			}
			if (c.isKeyDown(65) && x > 0) {
				x -= speed;
			}
			if (c.isKeyDown(87) && y > 0) {
				y -= speed;
			}
			if (c.isKeyDown(83) && y < 700) {
				y += speed;
			}
			if (c.isKeyDown(32)) {
				// playerpressed = true;
				if (tic % 10 == 0) {
					fire(x, y, 0);
				}

			}

		}
		if (health <= 0) {
			screen = 2;
		}
	}
}
