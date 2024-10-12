package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bank.app.entities.Branch;

public interface BankDao {
	int getBranchIdByEmpId(int empUserId) throws SQLException, IOException;

	int getbranchIdByMgrId(int mgrUserId) throws SQLException, IOException;

	int getRegionIdByMgrId(int regionalMgrUserId) throws SQLException, IOException;

	List<Branch> getBranchesByRegionId(int regionId) throws SQLException, IOException;
}
