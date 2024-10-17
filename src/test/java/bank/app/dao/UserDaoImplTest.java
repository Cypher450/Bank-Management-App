package bank.app.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import bank.app.entities.User;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class UserDaoImplTest {

	@Autowired
	private UserDaoImpl userDaoImpl;

	private User dummyUser;
	private int generatedId;

	@BeforeEach
	public void setUp() {
		dummyUser = new User();
		dummyUser.setFirstName("Aman");
		dummyUser.setLastName("Yadav");
		dummyUser.setUsername("aman.yadav9675");
		dummyUser.setPasswordSalt("randomSalt");
		dummyUser.setPasswordHashed("hashedPassword");
		dummyUser.setDateOfBirth(Date.valueOf("2023-02-01"));
		dummyUser.setEmail("aman.yadav@gmail.com");
		dummyUser.setPhone("9001967580");
		dummyUser.setRoleId(1);
		dummyUser.setAddress("123 Street");
		dummyUser.setApprovalStatus("pending");
	}

	@Test
	@Rollback
	void testInsertUser() {
		try {
			generatedId = userDaoImpl.insertUser(dummyUser);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assertions.assertTrue(generatedId > 0);
	}
}

