package com.revature.beans;

import java.time.LocalDate;

public class Associate implements Comparable<Associate>{
	private String name;
	private Integer score;
	private LocalDate birthday;
	public Associate() {
		super();
	}
	public Associate(String name, Integer score, LocalDate birthday) {
		super();
		this.name = name;
		this.score = score;
		this.birthday = birthday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
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
		Associate other = (Associate) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Associates [name=" + name + ", score=" + score + ", birthday=" + birthday + "]";
	}
	@Override
	public int compareTo(Associate o) {
		// The "natural" or default ordering of this object is defined by the compareTo method.
		if(!this.name.equals(o.name)) {
			return this.name.compareTo(o.name);
		}
		if(!this.score.equals(o.score)) {
			return this.score.compareTo(o.score);
		}
		if(!this.birthday.equals(o.birthday)) {
			return this.birthday.compareTo(o.birthday);
		}
		return 0;
	}
	
}
