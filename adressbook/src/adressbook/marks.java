package adressbook;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.omg.CORBA.SystemException;

public class marks {
	static String studentinfo[][] = { { "", "", "" }, { "", "", "" } };
	static int mark[];
	static File info = new File("D:\\info.txt");

	static String input;
	
	static int selected;

	public static void numnam () throws  IOException{
		
		Scanner n = new Scanner(System.in);
		System.out.println("name or number?");
		input = n.nextLine();
		n.close();
	}

	public static void select() throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("who would you like to change?");
		for (int i = 0; i < 3; i++) {
			System.out.println(i + 1 + "." + studentinfo[0][i]);
		}
		input = scanner.nextLine();

		for (int i = 0; i < 3; i++) {
			if (input == studentinfo[0][i]) {
				selected = i;
			}
		}
		scanner.close();
		numnam();

	}

	public static void scan() throws IOException {
		Scanner s = new Scanner(info);
		for (int i = 0; i < 3; i++) {

			studentinfo[0][i] = s.nextLine();
			System.out.println(studentinfo[0][i]);
			studentinfo[1][i] = s.nextLine();
			System.out.println(studentinfo[1][i]);

		}
		s.close();
	}

	public static void main(String[] args) throws IOException {
		scan();
		Scanner scanner = new Scanner(System.in);
		System.out.println("change info? Type yes or no");
		input = scanner.nextLine();
		System.out.println(input);

		if (input.equals("yes")) {
			select();

		}
		scanner.close();

	}
}
