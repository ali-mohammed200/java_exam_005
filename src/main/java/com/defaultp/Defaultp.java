package com.defaultp;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;

public class Defaultp {

	public static void main(String[] args) {
		System.out.println("Enter 5 numbers seperated by a space");
		Scanner sc = new Scanner(System.in);
		
		String userNums = sc.nextLine();
		String[] nums = userNums.split(" ");
		
		try {
			if (nums.length != 5) {
				throw new NumberCountException();
			}
			
//			Checks file in the project directory
			File f = new File("NumbersFile.txt");
			if(f.exists()) { 
			    System.out.println("This file exists - File will be updated");
			} else {
				System.out.println("This file does not exist - File will be created and updated");

			}
			
//			If file exists, append, else write
			FileWriter fw = new FileWriter("NumbersFile.txt", f.exists());
			fw.write(userNums + "\n");
			// close the writer
			fw.close();
			
			System.out.println("Writing successful");
			FileReader fr = new FileReader("NumbersFile.txt");
			int ch;
			
	        // read from FileReader till the end of file 
	        while ((ch=fr.read())!=-1) 
	            System.out.print((char)ch); 
	  
	        // close the reader 
	        fr.close(); 
	        System.out.println("Reading successful");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberCountException e) {
			e.printStackTrace();
		}

	}

}
