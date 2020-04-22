package model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class User {
	@Id
    private String id;
	private String firstname;	
	private String lastname;
	private Date birthDate;
	private String email;
	private String password;
	private String phone;
	private String role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User(String id, String firstname, String lastname, Date birthDate, String email, String password,
			String phone, String role) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}
	public User() {
		super();
	}
	
	

}
