package tests.Account;
import main.account.*;
import main.transactions.TransactionMessage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account account;
	
	@Before
	public void init()
	{
		String owner="Rushi";
		int balance = 5000;
		int accountNumber = 1001;
		
		account = new Account(owner,balance,accountNumber);
	}
	
	@Test
	
	public void shouldIncreaseAccountBalanceOnDeposite()
	{
		TransactionMessage result1 = account.deposit(3000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Account Balance of 1001: 8000",result1.getResponseMessage());
	}
	
	@Test
	
	public void shouldDecreaseAccountBalanceOnWithdraw()
	{
		TransactionMessage result1 = account.withdraw(3000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Account Balance of 1001: 2000",result1.getResponseMessage());
	}
	

}
