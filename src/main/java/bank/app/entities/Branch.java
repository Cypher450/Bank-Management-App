package bank.app.entities;

public class Branch {

	private int branchId;
	private String branchName;
	private String address;
	private String phone;
	private String pinCode;
	private int regionId;
	private int bankManagerId;
	private int bankTypeId;
	
	public Branch(String branchName, String address, String phone, String pinCode, int regionId, int bankManagerId,
			int bankTypeId) {
		super();
		this.branchName = branchName;
		this.address = address;
		this.phone = phone;
		this.pinCode = pinCode;
		this.regionId = regionId;
		this.bankManagerId = bankManagerId;
		this.bankTypeId = bankTypeId;
	}
	public Branch(int branchId, String branchName, String address, String phone, String pinCode, int regionId,
			int bankManagerId, int bankTypeId) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.address = address;
		this.phone = phone;
		this.pinCode = pinCode;
		this.regionId = regionId;
		this.bankManagerId = bankManagerId;
		this.bankTypeId = bankTypeId;
	}
	public Branch(int branchId, String branchName) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getBankManagerId() {
		return bankManagerId;
	}
	public void setBankManagerId(int bankManagerId) {
		this.bankManagerId = bankManagerId;
	}
	public int getBankTypeId() {
		return bankTypeId;
	}
	public void setBankTypeId(int bankTypeId) {
		this.bankTypeId = bankTypeId;
	}
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", address=" + address + ", phone="
				+ phone + ", pinCode=" + pinCode + ", regionId=" + regionId + ", bankManagerId=" + bankManagerId
				+ ", bankTypeId=" + bankTypeId + "]";
	}
	
}
