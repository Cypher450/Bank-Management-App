package bank.app.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BranchDaoTest {

	@Autowired
	private BranchDaoImpl branchDaoImpl;

	@Test
	public void testAddNewBranch_Success() {
		String branchName = "XYZ Hyderabad";
		String address = "Hyderabad";
		String phone = "9001967584";
		String pinCode = "567891";
		String regionId = "1";

		boolean result = branchDaoImpl.addNewBranch(branchName, address, phone, pinCode, regionId);

		assertTrue(result, "Branch should be added successfully");
	}
}