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

	public boolean col() {

		if (bullets.size() > 1) {
			System.out.println(p1.bullets.get(0).x);
		}
		for (int i = 0; i < p1.bullets.size(); i++) {// collision for all the bullets at once
			if (p1.bullets.get(i).x < x + w && x < p1.bullets.get(i).x + p1.bullets.get(i).w
					&& p1.bullets.get(i).y < y + h && y < p1.bullets.get(i).y + p1.bullets.get(i).h) {
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

	public void update_en() {

		tic++;
		if (tic % 200 == 0) {

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
