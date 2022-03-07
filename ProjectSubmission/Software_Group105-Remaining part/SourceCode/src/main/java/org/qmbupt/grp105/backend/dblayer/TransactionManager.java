package org.qmbupt.grp105.backend.dblayer;

import org.qmbupt.grp105.backend.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * manage transactions
 * @author Lingsong Feng
 * @version 5.3
 */
public class TransactionManager {

    /**
     * get income group by month
     * @param month number of month (from 1 to 12)
     * @return the income of that month
     * @throws IOException
     */
    public static int getMonthlyIncome(int month) throws IOException {
        int ret = 0;
        ArrayList<Transaction> transactions = DataManager.getInstance().transactions;
        for (Transaction transaction : transactions) {;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(transaction.time);

            if (calendar.get(Calendar.MONTH) + 1 == month) {
                ret += transaction.mount;
            }
        }
        return ret;
    }

    /**
     * write a transaction into file. The number of transaction is automatically allocated
     * @param transaction
     * @throws IOException
     */
    public static void writeTransaction(Transaction transaction) throws IOException {
        transaction.transactionId = getNextTransactionId();
        DataManager.getInstance().transactions.add(transaction);
        DataManager.getInstance().commit();
    }

    /**
     * generate the next number of transaction
     * @return next number of transaction
     * @throws IOException
     */
    public static String getNextTransactionId() throws IOException {
        ArrayList<Transaction> transactions = DataManager.getInstance().transactions;

        int currentMax = 0;
        for (Transaction transaction : transactions) {
            int currentIdInt = Integer.parseInt(transaction.transactionId);
            if (currentIdInt > currentMax) {
                currentMax = currentIdInt;
            }
        }

        int tIdInt = currentMax + 1;
        return "" + tIdInt;
    }
}
