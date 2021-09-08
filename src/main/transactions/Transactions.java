package main.transactions;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import main.bank.Bank;
public class Transactions {
	FileReader reader;
	Scanner input;
	public void executeTransactions(Bank bk,String transactionPath)
	{
		bk.reader();
		try{
			
			//fill array
			reader = new FileReader(transactionPath);
			input = new Scanner(reader);
			int accountNumber,amount,transactionNumber=0;
			
			while(input.hasNextLine()){			
				String request = input.nextLine();
				System.out.println("request No:"+ transactionNumber);
				transactionNumber++;
				String sv= request.split(" ")[0];
				
				switch(sv)
				{
					case "Create":String owner = request.split(" ")[1];
									//System.out.println("IN CASE CREATE OWNER: "+owner);
									bk.openAccount(owner);
									break;
					case "Deposit":accountNumber = Integer.parseInt(request.split(" ")[1]);
									amount = Integer.parseInt(request.split(" ")[2]);
									//System.out.println("IN CASE deposite OWNER: "+accountNumber+" "+amount);
									bk.deposit(accountNumber, amount);
									break;
					case "Balance": accountNumber = Integer.parseInt(request.split(" ")[1]);
									bk.displayBalance(accountNumber);
									break;
					case "Withdraw":accountNumber = Integer.parseInt(request.split(" ")[1]);
									amount = Integer.parseInt(request.split(" ")[2]);
									bk.withdraw(accountNumber, amount);
									break;
					case "Transfer":
									int sender = Integer.parseInt(request.split(" ")[1]);
									int receiver = Integer.parseInt(request.split(" ")[2]);
									amount = Integer.parseInt(request.split(" ")[3]);
									bk.transfer(sender, receiver, amount);
									break;
									
				}
				
			}
			
		}
		catch(FileNotFoundException ex){
			System.out.println("File Not Found!");
		}
		
		finally
		{
			bk.writer();
			input.close();
		}
	}

}
