package com.revature.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Serialization: The process of writing a Java Object to an external source.
// Serialization has fallen out of favor and in current editions of Java is deprecated.
// Alternatives to Serialization are data marshalling in easy to parse formats (XML or JSON)
public class SerializableExample {
	private static List<Object> cats = new ArrayList<Object>();
	
	public static void main(String[] args) {
		//populateArrayList();
		cats = readCatsFromFile("cats.dat");
		System.out.println(cats);
		//cats.add(new Cat("Fred", "Calico"));
		writeCatsToFile(cats, "cats.dat");
	}
	
	private static List<Object> readCatsFromFile(String filename) {
		List<Object> cats = null;
		try(ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));){
			cats = (ArrayList<Object>) o.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cats;
	}

	public static void writeCatsToFile(List<Object> cats, String filename) {
		try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));){
			o.writeObject(cats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void populateArrayList() {
		cats.add(new Object("Sprankles", "Calico"));
		cats.add(new Object("Susan", "White"));
		cats.add(new Object("Paul", "Gray"));
		cats.add(new Object("Aang", "Black"));
		cats.add(new Object("George", "Orange"));
	}
}


class Object implements Serializable {
	public String name;
	public String color;
	
	public Object() {
		super();
	}
	public Object(String name, String color) {
		this.name = name;
		this.color=color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Cat [name=" + name + ", color=" + color + "]";
	}
}