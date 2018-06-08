import java.awt.Image;
import java.awt.Toolkit;

public class Enemy extends Player {
	int x = 700;
	int y = 100;
	int w = 400;
	int h = 200;
	int health;
	int type;
	Image enemy;
	boolean hit = false;

	public boolean col() {
		System.out.println(bullets.get(0).x);
		for (int i = 0; i < bullets.size(); i++) {
			// if (bullets.get(i).x < x + w && x < bullets.get(i).x + bullets.get(i).w &&
			// bullets.get(i).y < y + h
			// && y < bullets.get(i).y + bullets.get(i).h) {
			if (bullets.get(i).x < x) {
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
		col();
		if (col() == false) {
			c.drawImage(enemy, x, y, w, h);
		}
	}
}
