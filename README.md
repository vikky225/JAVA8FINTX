# JavaLambdaAssignment

This Program is simple java based application witout maven/gradle used heavily java8 lambda and stream to facilitate solution

# Instruction to run program: 

# Prerequisuite : 
Please make sure you add given input file as program argument (i.e in intellij you can go to edit configuration and give program argument 
to the pate where input.csv file is from your class path ii.e src\resource\input.csv

# How to Run: 
Please import java project in your IDE as java project and run the main application FinancialTransactionMain.java 
simply by right click and run as java application.
or 
can be run via command prompt as well but give path to csv file as an program argument while running.

# input.csv is as below as given example 
TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT
TX10002, ACC334455, ACC998877, 20/10/2018 17:33:43, 10.50, PAYMENT
TX10003, ACC998877, ACC778899, 20/10/2018 18:00:00, 5.00, PAYMENT
TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL,TX10002
TX10005, ACC334455, ACC778899, 21/10/2018 09:30:00, 7.25,PAYMENT

# Output 
  Once you run Main application i.e. FinancialTransactionMain , it should give answer below
  Relative balance for the period is:-25.0
  Number of transactions included is:1

# Solution Approach :
  First we have mapped all records from input.csv to our internal data structure i.e List<Transaction> 
  than we use java8 to convert it to map <transaction id , Transaction> so to make it easier for further 
  filter operaton and calculation using java 8 steam and lambda expressions. 
  
  inputting test is from date , to date, account id which is fixed in our secnaior for testing
  ACC334455 
  from:20/10/2018 12:00:00
  to: 20/10/2018 19:00:00
  
  Relative Balance Calculation :
  first get all the accounts for fromaccountid from map and sum amount for payment which falls in valid date range .
  Second get all the accounts for toaccountid from map and sum amount for payment which falls in valid date range. 
  Since reversal has to be omitted whatever the case we calculate that amount as well. 
  Than amount is calculated by using fromaccountamount , toaccountamount and reversal amount
  
  No of transaction calculation:
  first we get the count for fromaccountid matched rows for payment from map for valid date range
  Second we get the count for toaccountid matched rows for payment from map for valid date range
  Get the count of reversal which has to omitted irrespective of date range.
  Than count is calculated by summing firstcount and second count and substracting reversal count
  
    
 # Given Problem Statement :
The goal of the challenge is to implement a system that analyses
financial transaction records.
A transaction record describes transferring money from one account to
another account. As such, each transaction record will have the
following fields:
• transactionId – The id of the transaction
• fromAccountId – The id of the account to transfer money from
• toAccountId – The id of the account to transfer money to
• createAt – the date and time the transaction was created (in the
format of
“DD/MM/YYYY hh:mm:ss”)
• amount – The amount that was transferred including dollars and
cents
• transactionType – The type of the transaction which could be
either PAYMENT or REVERSAL.
• relatedTransaction – In case of a REVERSAL transaction, this
will contain the id of the transaction it is reversing. In case of a
PAYMENT transaction this field would be empty.
The system will be initialised with an input file in CSV format containing
a list of transaction records.
Once initialised it should be able to print the relative account balance
(positive or negative) in a given time frame.
The relative account balance is the sum of funds that were transferred
to / from an account in a given time frame, it does not account for funds
that were in that account prior to the timeframe.
Another requirement is that, if a transaction has a reversing transaction,
this transaction should be omitted from the calculation, even if the
reversing transaction is outside the given time frame.

2.1 Example Data

ME Bank D&I - API Team Coding Challenge
The following data is an example of an input CSV file transactionId,
fromAccountId, toAccountId, createdAt, amount, transactionType,
relatedTransaction
TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT
TX10002, ACC334455, ACC998877, 20/10/2018 17:33:43, 10.50, PAYMENT
TX10003, ACC998877, ACC778899, 20/10/2018 18:00:00, 5.00, PAYMENT
TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL,
TX10002 TX10005, ACC334455, ACC778899, 21/10/2018 09:30:00, 7.25,
PAYMENT

2.1 Example Input and Output
Given the above input CSV file, entering the following input arguments:
accountId:
ACC334455 from:
20/10/2018 12:00:00
to: 20/10/2018
19:00:00

The output should be:
Relative balance for the period is: -$25.00
Number of transactions included is: 1

2.2 Assumptions
For the sake of simplicity, it is safe to assume that
• Input file and records are all in a valid format
• Transaction are recorded in order

2.3 Deliverables
Please implement your solution using Java (version 8 or higher) or Kotlin.

ME Bank D&I - API Team Coding Challenge
Make sure you include tests that demonstrate that your solution is working.
Don’t forget to add a README file describing your design and how to build
/ run it.
Please submit your solution by sending us a link to a git repository
containing the source code.
  
  
 We could have written few Junits for various tests and could have been used maven build to package and run application 
 and could have used flatmap as well for intersection to use stream and lambda more appropriately.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  


Deliverable:
