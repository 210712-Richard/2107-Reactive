package com.revature.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataSerializer<T> {
	
	// Generic Type - Replace all instances of an object type with a variable type T

	public List<T> readObjectsFromFile(String filename) {
		List<T> objects = null;
		try(ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));){
			objects = (ArrayList<T>) o.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	public void writeObjectsToFile(List<T> objects, String filename) {
		try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));){
			o.writeObject(objects);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
