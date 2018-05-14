/* Yamaan Bakir 
 * 5-13-2018 
 * ICS 3U1
 * Mrs.Medd
 */

package adressbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class marks {

	// variable and scanner declaration
	static Scanner scanner = new Scanner(System.in);
	static String studentinfo[][] = new String[4][3];
	static int mark[];
	static File info = new File("D:\\info.txt");
	static int linecount;
	static String input;
	static int selected;

	public static void select() throws IOException {// responsible for selecting the contact you would like to edit

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

	public static void scan() throws IOException { // scans the file for information and gets line count

		FileReader input = new FileReader(info);
		LineNumberReader count = new LineNumberReader(input);
		String lineRead = "";
		while ((lineRead = count.readLine()) != null) {
			linecount = count.getLineNumber();
		}
		Scanner s = new Scanner(info);
		String[][] resizer = new String[4][linecount / 4];
		studentinfo = resizer;
		for (int i = 0; i < studentinfo[0].length; i++) {

			studentinfo[0][i] = s.nextLine();
			System.out.println(studentinfo[0][i]);
			studentinfo[1][i] = s.nextLine();
			System.out.println(studentinfo[1][i]);
			studentinfo[2][i] = s.nextLine();
			System.out.println(studentinfo[2][i]);
			studentinfo[3][i] = s.nextLine();
			System.out.println(studentinfo[3][i]);
			System.out.println("");

		}

		s.close();
	}

	public static void printout() {// prints everything out
		for (int i = 0; i < studentinfo[0].length; i++) {
			System.out.println(studentinfo[0][i]);
			System.out.println(studentinfo[1][i]);
			System.out.println(studentinfo[2][i]);
			System.out.println(studentinfo[3][i]);

		}
	}

	public static void save() throws IOException { // saves edited information to the file
		scanner.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(info)); // creates a writer that purges the file
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

	public static void addnew() throws IOException { // responsible for adding new contact
		System.out.println("enter new name:");
		String newname = scanner.next();
		System.out.println("enter new number:");
		String newnum = scanner.next();
		System.out.println("enter new address:");
		String newaddr = scanner.next();
		System.out.println("enter new email:");
		String newemail = scanner.next();
		String temp[][] = new String[4][studentinfo[0].length + 1]; // temporary array for resizing original array

		for (int i = 0; i < studentinfo[0].length; i++) { // copies old array into temporary array
			temp[0][i] = studentinfo[0][i];
			temp[1][i] = studentinfo[1][i];
			temp[2][i] = studentinfo[2][i];
			temp[3][i] = studentinfo[3][i];

		}
		// adds new information
		temp[0][studentinfo[0].length] = newname;
		temp[1][studentinfo[0].length] = newnum;
		temp[2][studentinfo[0].length] = newaddr;
		temp[3][studentinfo[0].length] = newemail;
		studentinfo = temp; // resizes orginal array
		System.out.println("new contact added!");
		System.out.println("save changes?");
		System.out.println("");

		// printout();
		input = scanner.next();
		if (input.equals("yes")) {

			save();
		}

		else {

		}

		// printout();
	}

	public static void main(String[] args) throws IOException { // main section of the program that detemins weither or
																// not you want to change the information of the program
		scan();// reads in from file

		System.out.println("change info? Type yes or no");
		input = scanner.nextLine();// gets user input

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

			printout(); // prints out newest version of the info array
			System.out.println("save changes?");
			input = scanner.nextLine();
			if (input.equals("yes")) {

				save();
			}

			else {

			}

		}

		else if (input.equals("no")) { // if user doesnt want contact list doesnt want to be edited, asks user if they
										// want to add a new contact
			System.out.println("add new contact? type yes or no");
			input = scanner.next();
			if (input.equals("yes")) {

				addnew();

			}
		}

	}

}
