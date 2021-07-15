package com.revature.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileExample {
	public static void main(String[] args) {
		writeCharactersToFile("sampleFile.txt", "Hello guys.");
		readCharactersFromFile("sampleFile.txt");
		System.out.println();
		readScanner("sampleFile.txt");
	}

	private static void readScanner(String filename) {
		// The Scanner class allows you to read data from an input stream (such as the console)
		// If we open a file as an input stream, we can use the Scanner to parse it.
		try(
				FileInputStream stream = new FileInputStream(filename);
				Scanner scan = new Scanner(stream);
			) {
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readCharactersFromFile(String filename) {
		try(FileReader reader = new FileReader(filename);) {
			int i;
			//i = reader.read(); // why does read return an int and not a char?
			// Reads the end of the stream as -1
			while((i=reader.read()) != -1) {
				System.out.print((char)i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeCharactersToFile(String filename, String message) {
		try (FileWriter writer = new FileWriter(filename, true);) {
			writer.write(message+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
