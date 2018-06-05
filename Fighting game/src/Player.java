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
	public ArrayList<bullet> bullets = new ArrayList<bullet>();

	public Player() {

	}

	public void fire() throws InterruptedException {
		bullet b = new bullet(x + 20, y);
		bullets.add(b);

	}

	public void debug() {

	}

	public void update() throws InterruptedException {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).updatebullet();

			if (bullets.get(i).col() == true) {
				bullets.remove(i);
			}
		}
		c.drawImage(player, x, y, 100, 100);
		int speed = 10;
		if (c.isKeyDown(68)) {
			x += speed;
		}
		if (c.isKeyDown(65)) {
			x -= speed;
		}
		if (c.isKeyDown(87)) {
			y -= speed;
		}
		if (c.isKeyDown(83)) {
			y += speed;
		}
		if (c.isKeyDown(32)) {
			fire();
		}

	}
}
