package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FinancialTransactionReader {

    private List transactions = new ArrayList();
    private Iterator transactionIterator = null;


    public List readFrom(File file)
            throws IOException {
        if (file == null) {
            throw new IOException("File not found");
        }
        if (!file.exists()) {
            throw new IOException("File not exist");
        }
        return read(file);
    }


    private List read(File file)
             {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {

                String strSplit[] = currentLine.split(",");
                transactions.add(strSplit);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return transactions;
    }
}


