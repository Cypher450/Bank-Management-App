package bank.app.entities;

public class Customer {

	private int customerId;
	private int branchId;

	public Customer() {
		super();
	}

	public Customer(int branchId) {
		super();
		this.branchId = branchId;
	}

	public Customer(int customerId, int branchId) {
		super();
		this.customerId = customerId;
		this.branchId = branchId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", branchId=" + branchId + "]";
	}

}
