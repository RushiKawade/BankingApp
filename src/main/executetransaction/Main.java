package main.executetransaction;
import main.transactions.*;
import main.bank.Bank;


public class Main {

	public static void main(String[] args) {
		
		
		System.out.println("BANKING APPLICATION");
		Bank bk =new Bank("DBK Bank","src\\main\\data\\BankData.txt");
		Transactions trns = new Transactions();
		trns.executeTransactions(bk, "src\\main\\data\\Transaction.txt");
		
	  }  
		
	

}
