package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class Read {
	public static void readTxt(String path, int count, Scanner sc) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
		System.out.println("Enter the word that you want find in the file: ");
		String word = sc.nextLine();
		String line = br.readLine();

		while (line != null) {
			String[] vect = line.split("[\\W&&[^wÀ-ú]]");

			for (String x : vect) {
				if (removeAccent(word).equalsIgnoreCase(removeAccent(x))) {
					count++;
				}
			}
			line = br.readLine();
		}
		System.out.println("The word '" + word + "' was found " + count + " time(s).");
		br.close();
	}

	public static void readDocx(String path, int count, Scanner sc) throws IOException {

		FileInputStream fis = new FileInputStream(new File(path));
		XWPFDocument document = new XWPFDocument(fis);
		List<XWPFParagraph> list = document.getParagraphs();

		System.out.println("Enter the word that you want find in the file: ");
		String word = sc.nextLine();

		for (XWPFParagraph x : list) {
			String[] vect = x.getText().split("[\\W&&[^wÀ-ú]]");
			for (String y : vect) {
				if (removeAccent(word).equalsIgnoreCase(removeAccent(y))) {
					count++;
				}
			}
		}
		System.out.println("The word '" + word + "' was found " + count + " time(s).");

		document.close();
	}

	private static String removeAccent(String word) {
		return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}
