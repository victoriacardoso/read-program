package application;

import java.io.IOException;
import java.util.Scanner;

import util.Read;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			int count = 0;

			System.out.println("What the type of the file?");
			System.out.println("1- TXT");
			System.out.println("2- DOC/DOCX");
			int type = sc.nextInt();

			System.out.println();
			System.out.println("Enter the file path: ");
			sc.nextLine();
			String path = sc.nextLine();

			switch (type) {
			case 1:
				Read.readTxt(path, count, sc);
				break;
			case 2:
				Read.readDocx(path, count, sc);
				break;
			default:
				throw new IllegalArgumentException("Invalid option");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (RuntimeException e) {
			System.out.println("Unexpected error");
		} finally {
			sc.close();
		}
	}
}
