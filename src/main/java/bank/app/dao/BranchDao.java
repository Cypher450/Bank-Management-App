package bank.app.dao;

public interface BranchDao {
    boolean addNewBranch(String branchName, String address, String phone, String pinCode, String regionId);
}
