package com.revature.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.revature.beans.User;
import com.revature.beans.UserType;

@Table("user")
public class UserDTO {
	@PrimaryKey
	@Column("username")
	private String username;
	@Column
	private String email;
	// all fields in the class get "@Column" implicitly
	private LocalDate birthday;
	private UserType type;
	private Long currency;
	private LocalDate lastCheckIn;
	private List<UUID> inventory;
	
	public UserDTO() { }
	
	public UserDTO(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
		this.currency = user.getCurrency();
		this.type = user.getType();
		this.lastCheckIn = user.getLastCheckIn();
		this.inventory = new ArrayList<UUID>();
		user.getInventory().stream().forEach((gacha)->{
			this.inventory.add(gacha.getId());
		});
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
	public List<UUID> getInventory() {
		if(inventory ==  null) {
			inventory = new ArrayList<UUID>();
		}
		return inventory;
	}
	public void setInventory(List<UUID> inventory) {
		this.inventory = inventory;
	}
	
	public User getUser() {
		User u = new User(this.username, this.email, this.birthday, this.currency);
		u.setType(this.type);
		u.setLastCheckIn(this.lastCheckIn);
		return u;
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
		UserDTO other = (UserDTO) obj;
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
