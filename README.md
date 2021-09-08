# BankingApp
Banking Application using Java
Banks have a maximum limit of ₹1,00,000 on the account balance. The balance cannot exceed this limit. The bank wants to put in some conditions for withdrawals and deposits to an account. Below are the conditions:
1. Account balance cannot exceed ₹1,00,000
2. Account balance cannot be less than ₹0
3. The minimum deposit amount is ₹500 per transaction
4. The maximum deposit amount is ₹50,000 per transaction
5. The minimum withdrawal amount is ₹1,000 per transaction
6. The maximum withdrawal amount is ₹25,000 per transaction
7. No more than 3 deposits are allowed in a day
8. Account number entered during deposit or withdrawal should be valid
9. Account has sufficient balance during withdrawals
10.  No more than 3 withdrawals are allowed in a day


Appliaction Support following operations

1.Create - Takes 1 parameter that is the full name of the holder. Creates a new account
and returns the account number
2. Deposit - Takes 2 parameters as input. First is the account number and the second is the
deposit amount. Returns the balance post deposit.
3. Withdraw - Takes 2 parameters as input. First is the account number and the second is
the withdrawal amount. Returns the balance post withdrawal.
4. Balance - Takes 1 parameter that is the account number. Returns current balance.
5. Transfer - Takes 3 parameters. First is the source account number, second is the target
account number and the last one is the amount to transfer. Returns status as successful
or failure.
6. All the deposit and withdrawal rules are applicable for transfer operation as well.


Instruction to execute
1. go to BankingApp\src\main\data
2. write transaction command in Transaction.txt file
3. run bankingApp java appliaction
