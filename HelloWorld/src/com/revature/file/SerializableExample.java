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
	private static List<Cat> cats = new ArrayList<Cat>();
	
	public static void main(String[] args) {
		//populateArrayList();
		cats = readCatsFromFile("cats.dat");
		System.out.println(cats);
		//cats.add(new Cat("Fred", "Calico"));
		writeCatsToFile(cats, "cats.dat");
	}
	
	private static List<Cat> readCatsFromFile(String filename) {
		List<Cat> cats = null;
		try(ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));){
			cats = (ArrayList<Cat>) o.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cats;
	}

	public static void writeCatsToFile(List<Cat> cats, String filename) {
		try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));){
			o.writeObject(cats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void populateArrayList() {
		cats.add(new Cat("Sprankles", "Calico"));
		cats.add(new Cat("Susan", "White"));
		cats.add(new Cat("Paul", "Gray"));
		cats.add(new Cat("Aang", "Black"));
		cats.add(new Cat("George", "Orange"));
	}
}


class Cat implements Serializable {
	public String name;
	public String color;
	
	public Cat() {
		super();
	}
	public Cat(String name, String color) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cat [name=" + name + ", color=" + color + "]";
	}
}