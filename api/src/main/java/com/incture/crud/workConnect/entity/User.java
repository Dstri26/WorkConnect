package com.incture.crud.workConnect.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User{
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String password;
	private String phoneno;
	private String loggedIn = "false";
	
	public User() {
    }
	
    public User(String email, String password) {
	    this.email = email;
	    this.password = password;
	    this.loggedIn = "false";
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String tname) {
        this.name = tname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String em) {
        this.email = em;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String pt) {
        this.password = pt;
    }
    public String getPhoneNo() {
        return phoneno;
    }
    public void setPhoneNo(String phno) {
        this.phoneno = phno;
    }
    public String isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
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
