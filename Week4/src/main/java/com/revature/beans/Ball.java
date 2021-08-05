package com.revature.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Ball {
	
	private String type;
	private Boolean inflated;
	
	public Ball() {
		super();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getInflated() {
		return inflated;
	}
	public void setInflated(Boolean inflated) {
		this.inflated = inflated;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inflated == null) ? 0 : inflated.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Ball other = (Ball) obj;
		if (inflated == null) {
			if (other.inflated != null)
				return false;
		} else if (!inflated.equals(other.inflated))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ball [type=" + type + ", inflated=" + inflated + "]";
	}
}
