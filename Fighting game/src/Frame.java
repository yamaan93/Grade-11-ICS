import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import hsa_ufa.Console;

public class Frame {
	public static Console c = new Console(1200, 800, "FIGHT");
	public static int screen = 0;
	public static int Sx = 0;
	public static int Sy = 250;
	public static boolean selected = false;
	static Player p1 = new Player();
	static Enemy en = new Enemy(100, 1);
	static Font UI = new Font("Arial", Font.BOLD, 20);

	public static void menu() {
		Font title = new Font("Arial", Font.BOLD, 70);
		c.setFont(title);

		synchronized (c) {
			c.clear();
			c.drawRect(Sx, Sy, 400, 50);
			c.drawString("Bit Blaster", 300, 100);
			c.drawString("Start Game", Sx, 300);
			c.drawString("Controls", 0, 400);
			// System.out.println(c.getMouseX());
			int Mx = c.getMouseX();
			int My = c.getMouseY();

			if (Mx > Sx && Mx < 400 && My > Sy && My < 300) {
				Sx = 100;
				selected = true;
			} else {
				Sx = 0;
			}
			if (selected == true && c.getMouseButton(0)) {
				screen = 1;
				System.out.println("hola");
			}

		}
	}

	public static void game() throws InterruptedException {
		c.setFont(UI);
		synchronized (c) {
			c.clear();
			p1.update();
			en.update_en();

		}
	}

	public static void main(String[] args) throws InterruptedException {
		int tic = 0;
		p1.x = 200;
		p1.y = 200;
		Image background1 = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("myMedia/background.jpg"));
		/////////////////////////////////
		c.enableMouse();
		c.enableMouseMotion();
		c.enableMouseWheel();

		////////////////////////////////
		while (tic < 10) {
			tic++;
			synchronized (c) {
				System.out.println(tic);
				c.clear();
				c.drawImage(background1, 0, 0);
				p1.x++;
				p1.update();
			}

		}
		p1.x = 0;
		while (true) {

			if (screen == 0) {
				menu();
			}

			if (screen == 1) {
				game();
			}
			if (screen == 2) {
				// game over
				synchronized (c) {
					c.drawString("game over", 300, 200);
				}
			}

			Thread.sleep(10);
		}
	}

}
