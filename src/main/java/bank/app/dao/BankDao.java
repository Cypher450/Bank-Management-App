package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface BankDao {
	int getBranchIdByUserId(int empUserId) throws SQLException, IOException;
}
