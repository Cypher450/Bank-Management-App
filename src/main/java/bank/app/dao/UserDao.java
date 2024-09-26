package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import bank.app.entities.User;

public interface UserDao {

	int insertUser(User user) throws SQLException, IOException;
	
}
