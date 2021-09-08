package tests.Bank;
import main.account.*;
import main.transactions.TransactionMessage;
import main.bank.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.bank.TransactionValidator;

public class TransactionValidatorUnit {
	
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
	public void testValidWithdrawTransaction()
	{
		TransactionMessage result1 = TransactionValidator.validateWithdrawTransaction(account, 2000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Valid Transaction",result1.getResponseMessage());
	}
	
	@Test
	public void testInvalidWithdrawTransaction()
	{
		TransactionMessage result1 = TransactionValidator.validateWithdrawTransaction(account, 26000);
		TransactionMessage result2 = TransactionValidator.validateWithdrawTransaction(account, 200);
		TransactionMessage result3 = TransactionValidator.validateWithdrawTransaction(account, 6000);
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Maximum withdraw amount is 25000 for Account 1001",result1.getResponseMessage());
		assertEquals(false,result2.isSuccessful());
		assertEquals("Minimum withdraw amount is 1000 for Account 1001",result2.getResponseMessage());
		assertEquals(false,result3.isSuccessful());
		assertEquals("Insufficient balance for Account 1001",result3.getResponseMessage());

	}
	@Test
	public void testValidDepositTransaction()
	{
		TransactionMessage result1 = TransactionValidator.validateDepositTransaction(account, 2000);
		
		assertEquals(true,result1.isSuccessful());
		assertEquals("Valid Transaction",result1.getResponseMessage());
	}
	
	@Test
	public void testInvalidDepositTransaction()
	{
		TransactionMessage result1 = TransactionValidator.validateDepositTransaction(account, 400);
		TransactionMessage result2 = TransactionValidator.validateDepositTransaction(account, 51000);
		
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Minimum deposit amount is 500 for Account 1001",result1.getResponseMessage());
		assertEquals(false,result2.isSuccessful());
		assertEquals("Maximum deposit amount is 50000 for Account 1001",result2.getResponseMessage());
		

	}
	//@Test
	public void testDepositeTransactionLimit()
	{
		account.deposit(1000);
		account.deposit(1000);
		account.deposit(1000);
		
		TransactionMessage result1 = TransactionValidator.validateDepositTransaction(account, 4000);
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Only 3 deposits are allowed in a day for Account 1001",result1.getResponseMessage());
	}
	//@Test
	public void testWithdrawTransactionLimit()
	{
		account.withdraw(1000);
		account.withdraw(1000);
		account.withdraw(1000);
		
		TransactionMessage result1 = TransactionValidator.validateWithdrawTransaction(account, 4000);
		
		assertEquals(false,result1.isSuccessful());
		assertEquals("Only 3 withdrawals are allowed in a day for Account 1001",result1.getResponseMessage());
	}

}
