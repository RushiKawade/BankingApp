package main.transactions;

public class TransactionMessage {
	private boolean isSuccessful;
	private String responseMessage;
	

	public TransactionMessage(boolean isSuccessful,String responseMessage)
	{
		this.isSuccessful = isSuccessful;
		this.responseMessage = responseMessage;
	}
	public boolean isSuccessful()
	{
		return isSuccessful;
	}
	
	public String getResponseMessage()
	{
		return responseMessage;
	}
	
	
}
