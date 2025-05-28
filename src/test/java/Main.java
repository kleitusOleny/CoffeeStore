import model.payments.BankAccount;
import model.payments.BankTransfer;
import model.payments.Invoice;
import model.payments.TransactionLog;
import model.customer_system.*;

import javax.security.auth.Subject;

public class Main {
    public static void main(String[] args) {
        System.out.println(TransactionLog.getTransactions());
        System.out.println(TransactionLog.stateIncome());
    }
}
