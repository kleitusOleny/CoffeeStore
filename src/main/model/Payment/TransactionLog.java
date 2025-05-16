package model.Payment;

import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    private static  final List<Transaction> transactions = new ArrayList<>();
    public static void log(Transaction transaction) {
        transactions.add(transaction);
    }
    /**
     * showAll: cho ra lich su giao dich
     * @return
     */

    public static String showAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== LỊCH SỬ GIAO DỊCH =====\n");
        for (Transaction transaction : transactions) {
            sb.append(transaction).append("\n");
        }
        sb.append("=============================\n");
        return sb.toString();
    }
    public static  List<Transaction> getTransactions() {
        return transactions;
    }

    public static void addTransaction(Transaction transaction) {
    
    }
}
