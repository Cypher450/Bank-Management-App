package bank.app.entities;

public class BankEmployee {

	private int beId;
	private int branchId;
	private int bmId;
	
	public BankEmployee() {
		super();
	}
	
	public BankEmployee(int beId, int branchId, int bmId) {
		super();
		this.beId = beId;
		this.branchId = branchId;
		this.bmId = bmId;
	}
	
	public BankEmployee(int branchId, int bmId) {
		super();
		this.branchId = branchId;
		this.bmId = bmId;
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
	public int getBmId() {
		return bmId;
	}
	public void setBmId(int bmId) {
		this.bmId = bmId;
	}

	@Override
	public String toString() {
		return "BankEmployee [beId=" + beId + ", branchId=" + branchId + ", bmId=" + bmId + "]";
	}
	
	
	
}
