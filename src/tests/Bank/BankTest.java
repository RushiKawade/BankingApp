package tests.Bank;
import static org.junit.Assert.*;

import org.junit.Before;
import main.bank.*;
import main.transactions.*;
import org.junit.Test;

public class BankTest {
	private Bank bank;
	@Before
	public void init()
	{
		bank = new Bank("RushiBank","src\\tests\\data\\BankData.txt");
		bank.reader();
	}
	@Test
	public void shouldCreateNewAccount() {
		
		TransactionMessage result1 = bank.openAccount("Rushi");
		TransactionMessage result2 = bank.openAccount("Rushi2");
		assertEquals("2",result1.getResponseMessage());
		assertEquals("3",result2.getResponseMessage());
	
		
	}
	@Test
	public void shouldDepositeAmount()
	{
		int AccountNumber = 0;
		
		TransactionMessage result1 = bank.deposit(AccountNumber, 5000);
		TransactionMessage result2 = bank.deposit(AccountNumber, 3000);

		assertEquals(true,result1.isSuccessful());
		assertEquals("Account Balance of 0: 60000",result1.getResponseMessage());
		assertEquals(true,result2.isSuccessful());
		assertEquals("Account Balance of 0: 63000",result2.getResponseMessage());
	}
	@Test
	public void shouldFailsDeposit()
	{
		int AccountNumber = 0;
		
		TransactionMessage result1 = bank.deposit(AccountNumber, 50); 
		TransactionMessage result2 = bank.deposit(AccountNumber, 52000);  
		TransactionMessage result3 = bank.deposit(AccountNumber, 50000);	
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Minimum deposit amount is 500 for Account 0",result1.getResponseMessage());
		assertEquals(false,result2.isSuccessful());
		assertEquals("Maximum deposit amount is 50000 for Account 0",result2.getResponseMessage());
		assertEquals(false,result3.isSuccessful());
		assertEquals("Maximum account balance could be 100000 for Account 0",result3.getResponseMessage());
	}
	
	@Test
	public void checkDepositTransactionLimit()
	{
		int AccountNumber = 0;
		
		TransactionMessage result1 = bank.deposit(AccountNumber, 5000);
		result1 = bank.deposit(AccountNumber, 5000);
		result1 = bank.deposit(AccountNumber, 5000);
		TransactionMessage result2 = bank.deposit(AccountNumber, 5000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Account Balance of 0: 70000",result1.getResponseMessage());
		assertEquals(false,result2.isSuccessful());
		assertEquals("Only 3 deposits are allowed in a day for Account 0",result2.getResponseMessage());
	}
	
	
	@Test
	public void shouldWithdrawAmount()
	{
		int AccountNumber = 0;
		
		TransactionMessage result1 = bank.withdraw(AccountNumber, 5000);
	
		assertEquals(true,result1.isSuccessful());
		assertEquals("Account Balance of 0: 50000",result1.getResponseMessage());
	}
	
	@Test
	public void shouldFailsWithdraw()
	{
		int AccountNumber = 0;
		TransactionMessage result1 = bank.withdraw(AccountNumber, 500);
		TransactionMessage result2 = bank.withdraw(AccountNumber, 26000);
		TransactionMessage result3 = bank.withdraw(AccountNumber, 25000);
		result3 = bank.withdraw(AccountNumber, 25000);
		result3 = bank.withdraw(AccountNumber, 15000);
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Minimum withdraw amount is 1000 for Account 0",result1.getResponseMessage());
		assertEquals(false,result2.isSuccessful());
		assertEquals("Maximum withdraw amount is 25000 for Account 0",result2.getResponseMessage());
		assertEquals(false,result3.isSuccessful());
		assertEquals("Insufficient balance for Account 0",result3.getResponseMessage());
	}
	
	@Test
	public void checkWithdrawTransactionLimit()
	{
		int AccountNumber = 0;
		
		TransactionMessage result1 = bank.withdraw(AccountNumber, 5000);
		result1 = bank.withdraw(AccountNumber, 5000);
		result1 = bank.withdraw(AccountNumber, 5000);
		TransactionMessage result2 = bank.withdraw(0, 5000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Account Balance of 0: 40000",result1.getResponseMessage());
		assertEquals(false,result2.isSuccessful());
		assertEquals("Only 3 withdrawals are allowed in a day for Account 0",result2.getResponseMessage());
	}
	
	@Test
	public void shouldTransferAmount()
	{
		int senderAccountNumber=0;
		int receiverAccountNumber=1;
		
		TransactionMessage result1 = bank.transfer(senderAccountNumber, receiverAccountNumber, 5000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Sucessful Transaction",result1.getResponseMessage());
		
	}
	
	@Test
	public void shouldFailTransfer()
	{
		int senderAccountNumber=0;
		int receiverAccountNumber=1;
		int invalidAccountNumber = 8;
		
		TransactionMessage result1 = bank.transfer(senderAccountNumber, invalidAccountNumber, 3000);
		TransactionMessage result2 = bank.transfer(senderAccountNumber, receiverAccountNumber,  500);
		TransactionMessage result3 = bank.transfer(senderAccountNumber, receiverAccountNumber, 30000);
		TransactionMessage result4 = bank.transfer(senderAccountNumber, receiverAccountNumber, 3000);
		result4 = bank.transfer(senderAccountNumber, receiverAccountNumber, 3000);
		result4 = bank.transfer(senderAccountNumber, receiverAccountNumber, 3000);
		result4 = bank.transfer(senderAccountNumber, receiverAccountNumber, 3000);
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Invalid Account details provided",result1.getResponseMessage());
	    assertEquals(false,result2.isSuccessful());
	    assertEquals("Minimum withdraw amount is 1000 for Account 0",result2.getResponseMessage());
		assertEquals(false,result3.isSuccessful());
		assertEquals("Maximum withdraw amount is 25000 for Account 0",result3.getResponseMessage());
		assertEquals(false,result4.isSuccessful());
		assertEquals("Only 3 withdrawals are allowed in a day for Account 0",result4.getResponseMessage());
		
	}

}
