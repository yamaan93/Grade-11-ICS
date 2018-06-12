import java.awt.Font;

import hsa_ufa.Console;

public class Frame {
	public static Console c = new Console(1200, 800, "FIGHT");
	public static int screen = 0;
	public static int Sx = 0;
	public static int Sy = 250;
	public static boolean selected = false;
	static Player p1 = new Player();
	static Enemy en = new Enemy(100, 1);

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
		synchronized (c) {
			c.clear();
			p1.update();
			en.update_en();

		}
	}

	public static void main(String[] args) throws InterruptedException {

		/////////////////////////////////
		c.enableMouse();
		c.enableMouseMotion();
		c.enableMouseWheel();

		////////////////////////////////

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
