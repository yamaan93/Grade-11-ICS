package adressbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class marks {
	static Scanner scanner = new Scanner(System.in);
	// static String studentinfo[][] = { { "", "", "" }, { "", "", "" }, { "", "",
	// "" }, { "", "", "" } };
	static String studentinfo[][] = new String[4][3];
	static int mark[];
	static File info = new File("D:\\info.txt");
	static int linecount;
	static String input;

	static int selected;

	public static void select() throws IOException {

		System.out.println("who would you like to change?");
		for (int i = 0; i < studentinfo[0].length; i++) {
			System.out.println(i + 1 + "." + studentinfo[0][i]);
		}
		input = scanner.nextLine();

		for (int i = 0; i < studentinfo[0].length; i++) {
			// System.out.println(selected);
			if (input.equals(studentinfo[0][i])) {
				selected = i;

			}
		}

	}

	public static void scan() throws IOException {

		FileReader input = new FileReader(info);
		LineNumberReader count = new LineNumberReader(input);
		String lineRead = "";
		while ((lineRead = count.readLine()) != null) {
			linecount = count.getLineNumber();
		}
		Scanner s = new Scanner(info);
		String[][] resizer = new String[4][linecount / 4];
		studentinfo = resizer;
		for (int i = 0; i < linecount / 4; i++) {

			studentinfo[0][i] = s.nextLine();
			System.out.println(studentinfo[0][i]);
			studentinfo[1][i] = s.nextLine();
			System.out.println(studentinfo[1][i]);
			studentinfo[2][i] = s.nextLine();
			System.out.println(studentinfo[2][i]);
			studentinfo[3][i] = s.nextLine();
			System.out.println(studentinfo[3][i]);

		}

		s.close();
	}

	public static void printout() {
		for (int i = 0; i < studentinfo[0].length; i++) {
			System.out.println(studentinfo[0][i]);
			System.out.println(studentinfo[1][i]);
			System.out.println(studentinfo[2][i]);
			System.out.println(studentinfo[3][i]);

		}
	}

	public static void save() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(info));
		for (int i = 0; i < studentinfo[0].length; i++) {
			writer.write(studentinfo[0][i]);
			writer.newLine();
			writer.write(studentinfo[1][i]);
			writer.newLine();
			writer.write(studentinfo[2][i]);
			writer.newLine();
			writer.write(studentinfo[3][i]);
			writer.newLine();

		}
		writer.close();
	}

	public static void addnew() throws IOException {
		System.out.println("enter new name:");
		String newname = scanner.next();
		System.out.println("enter new number:");
		String newnum = scanner.next();
		System.out.println("enter new address:");
		String newaddr = scanner.next();
		System.out.println("enter new email:");
		String newemail = scanner.next();
		String temp[][] = new String[4][studentinfo.length + 1];

		for (int i = 0; i == studentinfo.length; i++) {
			temp[0][i] = studentinfo[0][i];
			temp[1][i] = studentinfo[1][i];
			temp[2][i] = studentinfo[2][i];
			temp[3][i] = studentinfo[3][i];

		}
		studentinfo = temp;
		studentinfo[0][studentinfo.length] = newname;
		studentinfo[1][studentinfo.length] = newnum;
		studentinfo[2][studentinfo.length] = newaddr;
		studentinfo[3][studentinfo.length] = newemail;
		save();
		System.out.println("new contact added!");

		// printout();
	}

	public static void main(String[] args) throws IOException {
		scan();// reads in from file
		System.out.println(linecount);
		System.out.println("change info? Type yes or no");
		input = scanner.nextLine();// gets user input
		System.out.println(input);

		if (input.equals("yes")) {
			select();
			System.out.println("name, number, address or email?");
			input = scanner.nextLine();

			if (input.equals("name")) {
				System.out.println("enter new name");
				input = scanner.nextLine();
				studentinfo[0][selected] = input;

			} else if (input.equals("number")) {
				System.out.println("enter new number");
				input = scanner.nextLine();
				studentinfo[1][selected] = input;

			} else if (input.equals("address")) {
				System.out.println("enter new address");
				input = scanner.nextLine();
				studentinfo[2][selected] = input;
			} else if (input.equals("email")) {
				System.out.println("enter new email");
				input = scanner.nextLine();
				studentinfo[3][selected] = input;
			}

			printout();
			System.out.println("save changes?");
			input = scanner.nextLine();
			if (input.equals("yes")) {
				save();
			}

			else {

			}

		}

		else if (input.equals("no")) {
			System.out.println("add new contact? type yes or no");
			input = scanner.next();
			if (input.equals("yes")) {

				addnew();

			}
		}

	}

}
