package bank.app.entities;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Account {
// `account_number`, `ifsc_code`, `customer_id`, `balance`, `opened_date`, `account_type_id`
	private String accountNumber;
	private String ifscCode;
	private int customerId;
	private double balance;
	private Date openedDate;
	private int accountTypeId;
	private MultipartFile idProof;
	private String password;

	public Account() {
		super();
	}

	public Account(String accountNumber, String ifscCode, int customerId, double balance, Date openedDate,
			int accountTypeId, MultipartFile idProof) {
		super();
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.customerId = customerId;
		this.balance = balance;
		this.openedDate = openedDate;
		this.accountTypeId = accountTypeId;
		this.idProof = idProof;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public MultipartFile getIdProof() {
		return idProof;
	}

	public void setIdProof(MultipartFile idProof) {
		this.idProof = idProof;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", ifscCode=" + ifscCode + ", customerId=" + customerId
				+ ", balance=" + balance + ", openedDate=" + openedDate + ", accountTypeId=" + accountTypeId
				+ ", idProof=" + idProof + "]";
	}

}
