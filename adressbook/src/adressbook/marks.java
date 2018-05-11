package adressbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.omg.CORBA.SystemException;

public class marks {
	static Scanner scanner = new Scanner(System.in);
	static String studentinfo[][] = { { "", "", "" }, { "", "", "" } };
	static int mark[];
	static File info = new File("D:\\info.txt");

	static String input;

	static int selected;

	public static void select() throws IOException {

		System.out.println("who would you like to change?");
		for (int i = 0; i < 3; i++) {
			System.out.println(i + 1 + "." + studentinfo[0][i]);
		}
		input = scanner.nextLine();

		for (int i = 0; i < 3; i++) {
			// System.out.println(selected);
			if (input.equals(studentinfo[0][i])) {
				selected = i;

			}
		}

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
	public static void printout() {
		for (int i = 0; i < 3; i++) {
			System.out.println(studentinfo[0][i]);
			System.out.println(studentinfo[1][i]);
		}
	}

	public static void main(String[] args) throws IOException {
		scan();

		System.out.println("change info? Type yes or no");
		input = scanner.nextLine();
		System.out.println(input);

		if (input.equals("yes")) {
			select();
			System.out.println("name or number?");
			input = scanner.nextLine();
			if (input.equals("name")) {
				System.out.println("enter new name");
				input = scanner.nextLine();
				studentinfo[0][selected] = input;

			} else if (input.equals("number")) {
				System.out.println("enter new number");
				input = scanner.nextLine();
				studentinfo[1][selected] = input;

			}

			printout();
			System.out.println("save changes?");
			input = scanner.nextLine();
			if (input.equals("yes")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(info));
				for (int i = 0; i < 3; i++) {
					writer.write(studentinfo[0][i]);
					writer.newLine();
					writer.write(studentinfo[1][i]);
					writer.newLine();
					
				}
				writer.close();
			}
			
			else {

			}

		}

	}
}
