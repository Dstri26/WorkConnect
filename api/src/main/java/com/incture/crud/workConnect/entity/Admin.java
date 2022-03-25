package com.incture.crud.workConnect.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ADMIN", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class Admin {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String password;
	private String phoneno;
	private String loggedIn = "false";
	
	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
		this.loggedIn = "false";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(email, admin.email) && Objects.equals(password, admin.password);
    }
    
    public String toString() {
        return "User{" +
                "id :" + id +
                ", email :'" + email + '\'' +
                ", password :'" + password + '\'' +
                ", loggedIn :" + loggedIn +
                '}';
    }


}















































