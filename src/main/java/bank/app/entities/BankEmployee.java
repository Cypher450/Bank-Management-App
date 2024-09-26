package bank.app.entities;

public class BankEmployee {

	private int beId;
	private int branchId;
	
	public BankEmployee() {
		super();
	}
	
	public BankEmployee(int beId, int branchId) {
		super();
		this.beId = beId;
		this.branchId = branchId;
	}
	
	public BankEmployee(int branchId) {
		super();
		this.branchId = branchId;
	}
	
	public int getBeId() {
		return beId;
	}
	public void setBeId(int beId) {
		this.beId = beId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "BankEmployee [beId=" + beId + ", branchId=" + branchId + "]";
	}
	
	
	
}
