

import java.awt.Font;

import hsa_ufa.*;
import hsa_ufa.Console;

import java.io.*;
import java.util.Scanner;
public class marks {
	public static Console c = new Console(600, 600, "MarkBook");
	static String[] student = {"bob","tom","jed","matt","tim","cook"};
	static String [] student2;
	static int[] mark = {22,53,65,90,87,2};
	static String selected;
	static int selectednum;
	static File inFile = new File("files/student.txt");
	public static void main(String[] args) throws IOException {
		c.enableMouse();
		c.enableMouseMotion();
		c.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		c.drawString("which student would you like to see?", 0,25);
		c.setCursor(3,1);
		selected = c.readLine();
		for( int i = 0; i<6; i++) {
			if( selected  == student[i]) {
				selectednum = i;
			}
		}
		c.drawString(selected+"  Mark: "+mark[selectednum], 0,100);
		Scanner f = new Scanner(inFile);	
		
		for( int i= 0; i <3; i++) {
			student2[i] = f.nextLine();
			
		}
		f.close();
		System.out.println(student2[1]);
		
	}

}
