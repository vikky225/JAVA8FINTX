package main.java;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.*;

public class FinancialTransactionMain {

    public static void main(String[] args) {

        try {
            FinancialTransactionReader reader = new FinancialTransactionReader();
            if (args.length == 1 && !args[0].isEmpty()) {
                try {
                    printResult(reader.readFrom(new File(args[0])));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Please provide input file name as program argument i.e. input.csv file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printResult(List result) throws ParseException,NumberFormatException {


        List<Transaction> transactionList = getTransactionsList(result);

        // create map transactionid , Transaction for further stream operation
        Map<String, Transaction> collect = transactionList.stream()
                .collect(toMap(transaction -> transaction.getTransactionId(), transaction->transaction));

        // summing up all the amount for given fromaccountid matched for Payment Transaction which falls in valid date
        Double fromAccountSumForPayment = collect.entrySet()
                .stream()
                .filter(map -> map.getValue().getFromAccountId().contains("ACC334455"))
                .filter(map -> map.getValue().getTransactionType().contains("PAYMENT"))
                .filter(FinancialTransactionMain::testValidCreatedDate)
                .map(map -> map.getValue())
                .mapToDouble(e -> e.getAmount())
                .sum();

     // summing up all the amount for given toAccountid matched for Payment Transaction which falls in valid date
        Double toAccountSumForPayment = collect.entrySet()
                .stream()
                .filter(map -> map.getValue().getToAccountId().contains("ACC334455"))
                .filter(map -> map.getValue().getTransactionType().contains("PAYMENT"))
                .filter(FinancialTransactionMain::testValidCreatedDate)
                .map(map -> map.getValue())
                .mapToDouble(e -> e.getAmount())
                .sum();


// summing up all the amount irrespective of Accountid  for Reversal Transaction , irrespective of date range
        Double reversalAmount = collect.entrySet()
                .stream()
                .filter(map -> map.getValue().getTransactionType().contains("REVERSAL"))
                .filter(map->!map.getValue().getRelatedTransaction().isEmpty())
                .map(map -> map.getValue())
                .mapToDouble(e -> e.getAmount())
                .sum();

// no of counts of transaction for given matched fromAccountId for Payment within valid date range
        long fromAccountCount = collect.entrySet()
                .stream()
                .filter(map -> map.getValue().getFromAccountId().contains("ACC334455"))
                .filter(map -> map.getValue().getTransactionType().contains("PAYMENT"))
                .filter(FinancialTransactionMain::testValidCreatedDate)
                .map(map -> map.getValue())
                .map(e -> e.getAmount())
                .count();

// no of counts of transaction for given matched toAccountId for Payment within valid date range
        long toAccountCount = collect.entrySet()
                .stream()
                .filter(map -> map.getValue().getToAccountId().contains("ACC334455"))
                .filter(map -> map.getValue().getTransactionType().contains("PAYMENT"))
                .filter(FinancialTransactionMain::testValidCreatedDate)
                .map(map -> map.getValue())
                .map(e -> e.getAmount())
                .count();

   // no of counts of transaction for reversal scenario irrespective of date
        long reversalTransactionCount = collect.entrySet()
                .stream()
                .filter(map -> map.getValue().getTransactionType().contains("REVERSAL"))
                .filter(map->!map.getValue().getRelatedTransaction().isEmpty())
                .map(map -> map.getValue())
                .map(e -> e.getAmount())
                .count();


        if(fromAccountSumForPayment>=toAccountSumForPayment)
        System.out.println(" Relative balance for the period is:" +(-(fromAccountSumForPayment+toAccountSumForPayment-reversalAmount)));
        else
        System.out.println(" Relative balance for the period is:" +((fromAccountSumForPayment+toAccountSumForPayment-reversalAmount)));

        System.out.println(" Number of transactions included is:" +(fromAccountCount+toAccountCount-reversalTransactionCount));
    }

    /* This method is used to create transactionlist List<Transaction> object from inputted csv file */

    public static List<Transaction> getTransactionsList(List result) throws ParseException {
        List<Transaction> transactionList = new ArrayList();
        String relatedtransaction=null;

        for (Iterator iterator = result.iterator(); iterator.hasNext(); ) {
             String[] transaction = (String[]) iterator.next();
             relatedtransaction = transaction.length > 6 ? transaction[6] : null;
             transactionList.add(new Transaction(transaction[0], transaction[1], transaction[2], new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(transaction[3]),Double.parseDouble(transaction[4].trim()),transaction[5],relatedtransaction));
         }
        return transactionList;
    }


    private static boolean testValidCreatedDate(Map.Entry<String, Transaction> map) {
        try {
            Date createdAt = map.getValue().getCreatedAt();
            Date todate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("20/10/2018 19:00:00");
            Date fromdate =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("20/10/2018 12:00:00");
            return createdAt.after(fromdate) && createdAt.before(todate);
        }catch(Exception ex){ex.getStackTrace();
        }
        return false;
    }
}

