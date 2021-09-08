package main.bank;
import main.transactions.*;
import main.account.*;
public class TransactionValidator {
	public static int withdrawLowerLimit = 1000;
	public static int withdrawUpperLimit = 25000;
	public static int depositLowerLimit = 500;
	public static int depositUpperLimit = 50000;
	
	public static TransactionMessage validateWithdrawTransaction(Account account, int amount)
	{
		
		if( amount < withdrawLowerLimit)
		{
			String responseMessage = "Minimum withdraw amount is 1000 for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
			
		}
		if(amount>withdrawUpperLimit)
		{
			String responseMessage = "Maximum withdraw amount is 25000 for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
			
			
		}
		if(account.getBalance() - amount < 0)
		{
			String responseMessage = "Insufficient balance for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
			
		
		}
		if(account.getWithdrawCount() + 1 > 3)
		{
			String responseMessage = "Only 3 withdrawals are allowed in a day for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
		
			
		}
		
			return new TransactionMessage(true,"Valid Transaction");
		
	}
	public static TransactionMessage validateDepositTransaction(Account account, int amount)
	{
		if(amount < depositLowerLimit)
		{
			String responseMessage = "Minimum deposit amount is 500 for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
			
		}
		if(amount > depositUpperLimit)
		{
			String responseMessage = "Maximum deposit amount is 50000 for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
			
		}
		if(account.getBalance() + amount >100000)
		{
			String responseMessage = "Maximum account balance could be 100000 for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
		}
		if(account.getDepositeCount() + 1 > 3)
		{
			String responseMessage = "Only 3 deposits are allowed in a day for Account " + account.getAccountNumber();
			System.out.println(responseMessage);
			return new TransactionMessage(false,responseMessage);
		}
		
			return new TransactionMessage(true,"Valid Transaction");
			
		
	}

}
