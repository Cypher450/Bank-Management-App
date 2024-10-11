package bank.app.controllers;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bank.app.dao.AccountDaoImpl;
import bank.app.dao.TransactionDaoImpl;
import bank.app.entities.Account;
import bank.app.entities.Transaction;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {

	@Autowired
	private AccountDaoImpl accountDaoImpl;

	@Autowired
	private TransactionDaoImpl transactionDaoImpl;

	@Autowired
	private HttpSession session;

	private List<Account> accounts;
	private int transactionTypeId;
	private double updatedBalance;
	private String resultMessage;

	@GetMapping("/transaction-history")
	public String showTransactions(@RequestParam String accountNo, Model model) throws SQLException, IOException {

		System.out.println("transaction history called");
		System.out.println("account no : " + accountNo);

		List<Transaction> transactions = transactionDaoImpl.getLastTenTransaction(accountNo);
		
		model.addAttribute("transactions", transactions);

		return "customer/transactionHistory";

	}

	@GetMapping("/deposit")
	public String showDepositForm() {
		User user = (User) session.getAttribute("userDetails");
		try {
			accounts = accountDaoImpl.getAccountsByCustomerId(user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		session.setAttribute("accounts", accounts);

		return "customer/deposit";
	}

	@GetMapping("/withdraw")
	public String showWithdrawForm() {

		User user = (User) session.getAttribute("userDetails");
		try {
			accounts = accountDaoImpl.getAccountsByCustomerId(user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		session.setAttribute("accounts", accounts);

		return "customer/withdraw";
	}

	@PostMapping("/transaction")
	public String processTransaction(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("transactionTime") String transactionTime,
			@RequestParam("transactionType") String transactionType, @RequestParam("amount") Double amount,
			@RequestParam("password") String password, RedirectAttributes attributes) {

		if (transactionType.equalsIgnoreCase("Deposit")) {
			transactionTypeId = 1;
		} else {
			transactionTypeId = 2;
		}

		User user = (User) session.getAttribute("userDetails");

		String pwdSalt = user.getPasswordSalt();
		String oldPwdHash = user.getPasswordHashed();

		// check password
		String newPassword = pwdSalt + password;
		String newPasswordHash = Password.generatePwdHash(newPassword);

		if (newPasswordHash.equals(oldPwdHash)) {
			// Perform the deposit or withdraw operation
			// Get updated balance

			try {
				resultMessage = transactionDaoImpl.saveTransaction(accountNumber, amount, transactionTypeId);
				updatedBalance = transactionDaoImpl.getAccountBalance(accountNumber);

				List<Account> accounts = accountDaoImpl.getAccountsByCustomerId(user.getUserId());

				for (Account account : accounts) {
					if (account.getAccountNumber().equals(accountNumber)) {

						if (account.getAccountTypeId() == 1) {
							session.setAttribute("savingsAcc", account);
						} else {
							session.setAttribute("currentAcc", account);
						}
					}
				}

				if (resultMessage.equalsIgnoreCase("Transaction successful.")) {
					session.setAttribute("updatedBalance", updatedBalance);
				}

				attributes.addFlashAttribute("message", resultMessage);
				return resultMessage.equalsIgnoreCase("Transaction successful.") ? "redirect:/transactionSuccess"
						: (transactionTypeId == 1 ? "redirect:/deposit" : "redirect:/withdraw");
			} catch (Exception e) {
				attributes.addFlashAttribute("message", "An error occured while processing transaction.");
				return transactionTypeId == 1 ? "redirect:/deposit" : "redirect:/withdraw";
			}
		} else {
			attributes.addFlashAttribute("message", "Password is incorrect");
			return transactionTypeId == 1 ? "redirect:/deposit" : "redirect:/withdraw";
		}

	}

	@GetMapping("/transactionSuccess")
	public String showTransactionPage() {
		return "customer/transactionSuccess";
	}
}
