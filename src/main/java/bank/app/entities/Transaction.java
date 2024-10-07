package bank.app.entities;

import java.sql.Timestamp;

public class Transaction {

	private int transactionId;
	private String accountNo;
	private int transactionType;
	private double amount;
	private Timestamp transactionTime;
	public Transaction(String accountNo, int transactionType, double amount, Timestamp transactionTime) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionTime = transactionTime;
	}
	public Transaction(int transactionId, String accountNo, int transactionType, double amount, Timestamp transactionTime) {
		super();
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionTime = transactionTime;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNo=" + accountNo + ", transactionType="
				+ transactionType + ", amount=" + amount + ", transactionTime=" + transactionTime + "]";
	}
	
	
	
	
}
