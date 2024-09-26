package bank.app.entities;

import java.sql.Date;

public class User {

	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private Roles role;
	private String address;
	private Date dateOfBirth;
	private String status;
	private String passwordSalt;
	private String passwordHashed;
	
	public User() {
		super();
	}

	public User(String username, String firstName, String lastName, String email, String phoneNo, Roles role,
			String address, Date dateOfBirth, String status) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.role = role;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.status = status;
	}

	public User(int userId, String username, String firstName, String lastName, String email, String phoneNo,
			Roles role, String address, Date dateOfBirth, String status, String passwordSalt, String passwordHashed) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.role = role;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.status = status;
		this.passwordSalt = passwordSalt;
		this.passwordHashed = passwordHashed;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHashed() {
		return passwordHashed;
	}

	public void setPasswordHashed(String passwordHashed) {
		this.passwordHashed = passwordHashed;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phoneNo=" + phoneNo + ", role=" + role + ", address=" + address
				+ ", dateOfBirth=" + dateOfBirth + ", status=" + status + ", passwordSalt=" + passwordSalt
				+ ", passwordHashed=" + passwordHashed + "]";
	}

	
	
}
