package com.revature.beans;

import java.io.Serializable;

public class Bean implements Serializable{
	// A bean?
	/*
	 * The classical definition of a Bean in Java is an object with:
	 * 1) private fields with public getters and setters
	 * 2) a no-arguments constructor
	 * 3) serializable
	 * 
	 * This was an EJB (Enterprise Java Beans) standard and isn't used much anymore
	 * 
	 * Serializable has been deprecated.
	 * 
	 * POJO - Plain Old Java Object
	 * An object with encapsulated fields
	 */
	
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
