import java.awt.Image;
import java.awt.Toolkit;

public class Player extends Frame {
	Image player = Toolkit.getDefaultToolkit()
			.getImage(c.getClass().getClassLoader().getResource("myMedia/Megaman.jpg"));
	int w = 50;
	int h = 50;
	int x = 0;
	int y = 0;

	public Player() {

	}

	public void update() {

		int speed = 1;
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

		c.drawImage(player, x, y, w, h);

	}
}
