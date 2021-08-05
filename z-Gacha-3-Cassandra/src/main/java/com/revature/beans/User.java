package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	// literally only created the UID to get rid of the warning, and it doesn't matter at all.
	private static final long serialVersionUID = -6426075925303078798L;
	private String username;
	private String email;
	private LocalDate birthday;
	private UserType type;
	private Long currency;
	private LocalDate lastCheckIn;
	private List<GachaObject> inventory;
	
	public User() {
		super();
		this.type = UserType.PLAYER;
		this.lastCheckIn = LocalDate.of(2021, 1, 1);
		this.currency = 0l;
		this.inventory = new ArrayList<GachaObject>();
	}
	
	public User(String username, String email, LocalDate birthday, Long currency) {
		this();
		this.username = username;
		this.email = email;
		this.birthday = birthday;
		this.currency = currency;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public Long getCurrency() {
		return currency;
	}
	public void setCurrency(Long currency) {
		this.currency = currency;
	}
	public List<GachaObject> getInventory() {
		if(inventory ==  null) {
			inventory = new ArrayList<GachaObject>();
		}
		return inventory;
	}
	public void setInventory(List<GachaObject> inventory) {
		this.inventory = inventory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((lastCheckIn == null) ? 0 : lastCheckIn.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (lastCheckIn == null) {
			if (other.lastCheckIn != null)
				return false;
		} else if (!lastCheckIn.equals(other.lastCheckIn))
			return false;
		if (type != other.type)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", birthday=" + birthday + ", type="
				+ type + ", currency=" + currency + ", lastCheckIn=" + lastCheckIn + ", inventory=" + inventory + "]";
	}

	public LocalDate getLastCheckIn() {
		return lastCheckIn;
	}

	public void setLastCheckIn(LocalDate lastCheckIn) {
		this.lastCheckIn = lastCheckIn;
	}
}
