package bank.app.entities;

public class BankManager {

	private int bmId;
	private int branchId;
	
	public BankManager() {
		super();
	}
	
	public BankManager(int branchId) {
		super();
		this.branchId = branchId;
	}
	
	public BankManager(int bmId, int branchId) {
		super();
		this.bmId = bmId;
		this.branchId = branchId;
	}

	public int getBmId() {
		return bmId;
	}

	public void setBmId(int bmId) {
		this.bmId = bmId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "BankManager [bmId=" + bmId + ", branchId=" + branchId + "]";
	}
	
}
