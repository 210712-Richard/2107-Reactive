package com.revature.reflection;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
// implicit import java.lang.*
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ReflectionExample {
	// Reflection is an API for examining and modifying code at RUNTIME
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		//System.out.println(System.class);
		//beans();
		string();
	}

	private static void string() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String s = "Hello";
		Field[] stringFields = s.getClass().getDeclaredFields();
		for(Field f: stringFields) {
			System.out.println(f);
		}
		Field valueField = s.getClass().getDeclaredField("value");
		valueField.setAccessible(true);
		char[] c = (char[]) valueField.get(s);
		System.out.println(Arrays.toString(c));
		c[0] = 89;
		System.out.println(s);
		System.out.println("Hello");
		System.out.println("Goodbye");
		System.out.println(new String("Hello"));
		
		// So we were able to modify the contents of the array but we can't reassign the array because it is final.
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(valueField, valueField.getModifiers() & ~Modifier.FINAL);
		
		char[] chars = {'G', 'o', 'o', 'd', 'b', 'y', 'e'};
		valueField.set(s, chars);
		System.out.println("Hello");
		System.out.println(new String("Hello"));// Passing "Goodbye" into the constructor of a new string
		System.out.println("H"+"ello");
		String s1 = new String("H");
		String s2 = new String("ello");
		System.out.println(s1+s2);
		System.out.println("Hello");
		String s3 = s1+s2;
		System.out.println(s3);
		System.out.println("Hello");
		String s4 = s3.intern();
		System.out.println(s4); // Hello is now in the string pool again?
		System.out.println("Hello");
	}

	private static void beans() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Bean b = new Bean();
		System.out.println(b.getColor());
		b.setColor("red");
		System.out.println(b.getColor());
		//b.color = "green";
		
		// We can treat Classes as Objects
		Class<Bean> beanClass = Bean.class;
		System.out.println(beanClass instanceof Object);
		System.out.println(beanClass);
		System.out.println("\tDeclared Methods");
		// all methods declared ON the class, excluding inherited methods we did not override.
		for(Method m: beanClass.getDeclaredMethods()) {
			System.out.println("\t\t"+m);
		}
		System.out.println("\tPublic Methods");
		// all public methods on the class
		for(Method m : beanClass.getMethods()) {
			System.out.println("\t\t"+m);
		}
		System.out.println("\tConstructors");
		for(Constructor<Bean> c: (Constructor<Bean>[]) beanClass.getConstructors()) {
			System.out.println("\t\t"+c);
		}
		// all public fields
		System.out.println("\tFields");
		for(Field f : beanClass.getFields()) {
			System.out.println("\t\t"+f);
		}
		System.out.println("\tDeclared Fields");
		// all fields declared on the class
		for(Field f : beanClass.getDeclaredFields()) {
			System.out.println("\t\t"+f);
		}
		
		// So far we've only *looked* at things with Reflection.
		// however, Reflection is capable of modifying things as well
		
		System.out.println(b);
		Field colorField = b.getClass().getDeclaredField("color");
		System.out.println(colorField);
		colorField.setAccessible(true);
		colorField.set(b, "Dog");
		System.out.println(b);
	}
}




class Bean implements Serializable {	
	private Integer id; // only something in the same class can access this member.
	private String strain;
	private String color;
	private Integer yield;
	private Float mass;
	private Boolean tasty;
	
	public Bean() {
		super();
	}
	
	public Integer getId() { // anything that can see this class can access this member.
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStrain() {
		return strain;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getYield() {
		return yield;
	}
	public void setYield(Integer yield) {
		this.yield = yield;
	}
	public Float getMass() {
		return mass;
	}
	public void setMass(Float mass) {
		this.mass = mass;
	}
	public Boolean getTasty() {
		return tasty;
	}
	public void setTasty(Boolean tasty) {
		this.tasty = tasty;
	}
	private void method() {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mass == null) ? 0 : mass.hashCode());
		result = prime * result + ((strain == null) ? 0 : strain.hashCode());
		result = prime * result + ((tasty == null) ? 0 : tasty.hashCode());
		result = prime * result + ((yield == null) ? 0 : yield.hashCode());
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
		Bean other = (Bean) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mass == null) {
			if (other.mass != null)
				return false;
		} else if (!mass.equals(other.mass))
			return false;
		if (strain == null) {
			if (other.strain != null)
				return false;
		} else if (!strain.equals(other.strain))
			return false;
		if (tasty == null) {
			if (other.tasty != null)
				return false;
		} else if (!tasty.equals(other.tasty))
			return false;
		if (yield == null) {
			if (other.yield != null)
				return false;
		} else if (!yield.equals(other.yield))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bean [id=" + id + ", strain=" + strain + ", color=" + color + ", yield=" + yield + ", mass=" + mass
				+ ", tasty=" + tasty + "]";
	}
}