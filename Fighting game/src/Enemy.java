import java.awt.Image;
import java.awt.Toolkit;

public class Enemy extends Frame {
	int x = 700;
	int y = 100;
	int w = 400;
	int h = 200;
	int health;
	int type;
	Image enemy;

	public Enemy(int hel, int ty) {
		health = hel;
		type = ty;
		if (ty == 1) {
			enemy = Toolkit.getDefaultToolkit()
					.getImage(c.getClass().getClassLoader().getResource("myMedia/pixel-art-fire-monster.jpg"));
		}

	}

	public void update_en() {
		c.drawImage(enemy, x, y, w, h);
	}
}
