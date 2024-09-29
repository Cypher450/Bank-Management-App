package bank.app.entities;

import java.sql.Date;

public class User {

	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private int roleId;
	private String address;
	private Date dateOfBirth;
	private String approvalStatus;
	private String passwordSalt;
	private String passwordHashed;
	private String password;
	private int branchId;

	public User() {
		super();
	}

	public User(String username, String firstName, String lastName, String email, String phone, int roleId,
			String address, Date dateOfBirth, String approvalStatus, int branchId) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.approvalStatus = approvalStatus;
		this.branchId = branchId;
	}

	public User(int userId, String username, String firstName, String lastName, String email, String phone, int roleId,
			String address, Date dateOfBirth, String approvalStatus, String passwordSalt, String passwordHashed,
			String password, int branchId) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.approvalStatus = approvalStatus;
		this.passwordSalt = passwordSalt;
		this.passwordHashed = passwordHashed;
		this.password = password;
		this.branchId = branchId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + ", roleId=" + roleId + ", address=" + address
				+ ", dateOfBirth=" + dateOfBirth + ", approvalStatus=" + approvalStatus + ", passwordSalt="
				+ passwordSalt + ", passwordHashed=" + passwordHashed + ", password=" + password + ", branchId="
				+ branchId + "]";
	}
}
