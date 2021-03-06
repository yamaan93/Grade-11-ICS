import java.awt.Image;
import java.awt.Toolkit;

public class Enemy extends Player {
	int x = 700;
	int y = 100;
	int w = 400;
	int h = 200;
	int health;
	int type;
	int tic;
	boolean switchD = false;
	Image enemy;
	boolean hit = false;

	@Override
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

	public Enemy(int hel, int ty) {

		health = hel;
		type = ty;
		if (ty == 1) {
			enemy = Toolkit.getDefaultToolkit()
					.getImage(c.getClass().getClassLoader().getResource("myMedia/pixel-art-fire-monster.png"));
		}

	}

	public void update_en() throws InterruptedException {

		c.drawString("health:", 700, 100);
		c.fillRect(800, 80, health * 3, 20);
		boolean straight = true;
		System.out.println(p1.y);
		tic++;
		if (tic % 20 == 0 && health > 0) {
			if (p1.y > y + h) {
				straight = false;
				p1.fire(x - 110, y, 2);
			}
			if (p1.y < y + p1.h) {
				straight = false;
				p1.fire(x - 110, y, 3);
			}
			if (p1.y < y && p1.y > y + h && straight == true) {
				p1.fire(x - 110, y, 1);
			}

		}
		if (y == 700) {
			switchD = true;
		}
		if (y == 0) {
			switchD = false;
		}

		if (switchD == false) {
			y++;
		}
		if (switchD == true) {
			y--;
		}

		col();
		if (health > 0) {
			c.drawImage(enemy, x, y, w, h);
		}
	}
}
