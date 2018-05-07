package markbook;

import java.awt.Font;

import hsa_ufa.*;
import hsa_ufa.Console;

import java.io.*;
import java.util.Scanner;
public class marks {
	public static Console c = new Console(600, 600, "MarkBook");
	static String[] student = {"bob","tom","jed","matt","tim","cook"};
	static String [] student2 ={null,null,null,null,null,null};
	
	static String boi;
	static int[] mark = {0,0,0};
	static String selected;
	static int selectednum;
	static File inFile = new File("D:\\Yamaan\\Documents\\Grade11\\markbook\\src\\student.csv");
	public static void main(String[] args) throws IOException {
		c.enableMouse();
		c.enableMouseMotion();
		c.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		c.drawString("which student would you like to see?", 0,25);
		c.setCursor(3,1);
		Scanner f = new Scanner(inFile);	
		//selected = c.readLine();
		//	c.drawString(selected+"  Mark: "+mark[selectednum], 0,100);
/*
		for( int i = 0; i<3; i++ ) {
			student2[i]=f.nextLine();
			mark[i] = f.nextInt();
			if(student2[i] == selected) {
				selectednum= i;
			}
		}
		*/
		for(int i = 0; i<3;i++) {
		String x =f.nextLine();	
		String[]arr=x.split(",");
		student2[i] = arr[i];
		System.out.println(arr[i]);
		}
		f.close();
		
		
	}

}
