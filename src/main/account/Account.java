package main.account;
import main.transactions.*;
public class Account {
	private String owner;
	private int balance;
	private int accountNumber;
	private int depositeCount;
	private int withdrawCount;
	
	
	public Account(String owner,int balance, int accountNumber){
		this.owner = owner ;
		this.balance = balance ;
		this.accountNumber = accountNumber ;
		this.withdrawCount = 0;
		this.depositeCount = 0;
		
	}
	public TransactionMessage deposit(int amount){
		
		balance += amount;
		String responseMessage = "Account Balance of "+ accountNumber + ": " + balance;
		System.out.println(responseMessage);
		depositeCount++;
		
		return new TransactionMessage(true,responseMessage);
	}
	public TransactionMessage withdraw(int amount){
		
			balance -= amount;
			String responseMessage = "Account Balance of "+ accountNumber + ": " + balance;
			System.out.println(responseMessage);
			withdrawCount++;
			
			return new TransactionMessage(true,responseMessage);
	}
	
	
	public int getWithdrawCount()
	{
		return withdrawCount;
	}
	public int getDepositeCount()
	{
		return depositeCount;
	}
	public void reduceDepositeCount()
	{
		depositeCount--;
	}
	public void reduceWithdrawCount()
	{
		withdrawCount--;
	}
	public void displayBalance()
	{
		System.out.println("Balance: "+balance);
	}
	public void resetTransactionLimit()
	{
		depositeCount = 0;
		withdrawCount = 0;
	}
	
	public String getOwner(){
		return owner;
		
	}
	public int getAccountNumber()
	{
		return accountNumber;
		
	}
	public int getBalance(){
		
		return balance;
		
	}
}
