package com.revature.beans;

public class AnimalDriver {
	public static void main(String[] args) {
		//animal();
		dog();
	}

	private static void dog() {
		Chihuahua c = new Chihuahua();
		c.quiver();
		c.speak();
		Dog d = c;
		//d.quiver();
		((Chihuahua)d).quiver();
		d.speak();
		
		Animal a = d;
		//a.quiver();
		a.speak();
		
		System.out.println(c);
		
		Breathable b = new Chihuahua();
		b.breathes();
		b.hold();
	}

	private static void animal() {
		Animal a = /*new Animal();*/ new Dog();
		System.out.println(a);
		a.speak();
		a.setName("Francisco");
		a.speak();
		System.out.println(a);
		a.setName("Milton");
		System.out.println(a);
		a.name = "Milton";
		System.out.println(a);
		a.speak();
		a.setAdorable(true);
		System.out.println(a);
		
		Animal[] arr = new Animal[7];
		arr[0] = new Dog();
		Dog d = (Dog) arr[0];
	}
}
