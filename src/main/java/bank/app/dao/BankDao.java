package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface BankDao {
	int getBranchIdByEmpId(int empUserId) throws SQLException, IOException;
	int getbranchIdByMgrId(int mgrUserId) throws SQLException, IOException;;
}
