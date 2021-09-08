package main.bank;
import main.transactions.*;
import main.account.*;
import java.util.*;
import java.io.*;

public class Bank {
	private String bankName;
	private int accountCount;
	private String bankDataPath;
	private ArrayList<Account> accounts= new ArrayList<Account>(accountCount);
	
	
	
	public Bank(String name,String bankDataPath)
	{
		this.accountCount = 0;
		this.bankName = name;
		this.bankDataPath = bankDataPath;
	}
	public void reader(){
		//this.resetTransactionCount();
		try{
			
			
			FileReader reader = new FileReader(bankDataPath);
			Scanner input = new Scanner(reader);
			
			while(input.hasNextLine()){			
				String owner = input.nextLine();
				System.out.println(owner);
				String balance = input.nextLine();
				String accountNumber = input.nextLine();
				Account acc = new Account(owner,Integer.parseInt(balance),Integer.parseInt(accountNumber));
				accounts.add(acc);
				accountCount++;
				
			}
			System.out.println(accountCount);
			
			input.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("File Not Found!2");
		}
	}
	public TransactionMessage openAccount(String accountHolderName){	
		int initialBalance=0;
		Account acc = new Account(accountHolderName,initialBalance,accountCount++);
		accounts.add(acc);
		String responseMessage = "" + acc.getAccountNumber();
		System.out.println(responseMessage);
		
		return new TransactionMessage(true,responseMessage);
	}

	private Optional<Account>  getAccount(int AccountNumber)
	{
		Optional<Account> account = accounts.stream().filter(s->s.getAccountNumber()==AccountNumber).findFirst();
		
		return account;
	}
	
	public TransactionMessage deposit(int accountNumber,int amount)
	{
		
		Optional<Account> account = getAccount(accountNumber);
		
		if(account.isEmpty())
		{
			System.out.println("Invalid Account Number");
			return new TransactionMessage(false,"Invalid Account Number");
		}
		TransactionMessage response = TransactionValidator.validateDepositTransaction(account.get(), amount);
		
		if(!response.isSuccessful())
		{
			return response;
		}
		
		return account.get().deposit(amount);
		
	}
	
	
	public TransactionMessage withdraw(int accountNumber,int amount){
		
		
		Optional<Account> account = getAccount(accountNumber);
		
		if(account.isEmpty())
		{
			System.out.println("Invalid Account Number");
			return new TransactionMessage(false,"Invalid Account Number");
		}
		TransactionMessage response = TransactionValidator.validateWithdrawTransaction(account.get(), amount);	
		
		if(!response.isSuccessful())
		{
			return response;
		}
		
		return account.get().withdraw(amount);
	}	
	
public TransactionMessage transfer(int sender, int receiver,int amount){
		
		Optional<Account> senderAccount = getAccount(sender);
		Optional<Account> receiverAccount = getAccount(receiver);
		
		
		if(senderAccount.isEmpty()  || receiverAccount.isEmpty())
		{
			System.out.println("Invalid Account details provided");
			return new TransactionMessage(false,"Invalid Account details provided");
			
		}
		
		TransactionMessage withdrawResponse = withdraw(senderAccount.get().getAccountNumber(),amount);
		
		if(!withdrawResponse.isSuccessful())
		{
			return withdrawResponse;
		}
		
		TransactionMessage depositeResponse = deposit(receiverAccount.get().getAccountNumber(),amount);
			
		if(!depositeResponse.isSuccessful())
		{
				rollback(senderAccount.get(),amount);
				return depositeResponse;
		}
		
		return new TransactionMessage(true,"Sucessful Transaction");
		
	
}
	private void rollback(Account account,int amount)
	{
		account.reduceDepositeCount();
		deposit(account.getAccountNumber(),amount);
	}
	public void displayBalance(int accountNumber)
	{
		Optional<Account> account = getAccount(accountNumber);
		if(account.isEmpty())
		{
			System.out.println("Invalid Account Number");
			return;
		}
		account.get().displayBalance();
	}
	private void resetTransactionCount()
	{
		Timer timer = new Timer();
		TimerTask task = new TimerTask()
		{

			@Override
			public void run() {
				
				for (int i=0 ; i < accountCount ; i++){
					accounts.get(i).resetTransactionLimit();
				}
				System.out.println("Transaction limit has been restored");
				
			}
			
		};
		timer.scheduleAtFixedRate(task, 0, 10000);
	}
	public void writer(){ 
		try{
			PrintWriter output = new PrintWriter(bankDataPath);
			for (int i=0 ; i < accountCount ; i++){
				output.println(accounts.get(i).getOwner());
				output.println(accounts.get(i).getBalance());
				output.println(accounts.get(i).getAccountNumber());
				//each information of person write in one line
			}
			output.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("File Not Found!3");
		}
	}
}
