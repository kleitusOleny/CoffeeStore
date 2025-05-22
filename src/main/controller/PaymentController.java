package controller;

import model.customer_system.Customer;
import model.payments.*;
import model.reservation_system.Table;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PaymentController {
    private PaymentPanel paymentPanel;
    private Customer customer;
    private double totalAmount;
    private DefaultTableModel tableModel;
    private Table table;


    public PaymentController(PaymentPanel paymentPanel, Customer customer, double totalAmount, DefaultTableModel tableModel, Table table) {
        this.paymentPanel = paymentPanel;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.tableModel = tableModel;
        this.table = table;
        initializeView();
        setupListener();
    }

    private void setupListener() {
        // History button listener
        paymentPanel.getHistoryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory();
            }
        });
        paymentPanel.getConfirmBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }

        });
        paymentPanel.getInvoiceBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInvoice();
            }
        });
    }

    private void showTransactionHistory() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(paymentPanel);
        TransactionHistoryDialog dialog = new CustomTransactionHistoryDialog(parentFrame);
        dialog.setVisible(true);
    }

    private void showInvoice() {
        PaymentStrategy paymentStrategy = new Cash();//mac dinh se la tra tien mat
        if (paymentPanel.getCard().isSelected()){
            BankAccount bankAccount = new BankAccount();
            paymentStrategy = new CreditCard("1234-5678-9012-3456", customer.getName(), new model.employee_system.Date(), 123, bankAccount);
        }else if (paymentPanel.getBank().isSelected()){
            BankAccount bankAccount = new BankAccount();
            paymentStrategy = new BankTransfer("987654321", customer.getName(), bankAccount);
        }
        Invoice invoice = new Invoice(totalAmount,paymentStrategy,customer);
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(paymentPanel);
        InvoiceDialog dialog = new InvoiceDialog(parentFrame);
        dialog.setVisible(true);

    }
    private void processPayment() {
        PaymentStrategy paymentStrategy = null;
        if (paymentPanel.getCash().isSelected()) {
            paymentStrategy = new Cash();
        } else if (paymentPanel.getCard().isSelected()) {
            // Assuming a default BankAccount and CreditCard details for demo
            // In a real system, these should come from user input or a database
            BankAccount bankAccount = new BankAccount(); // Placeholder
            paymentStrategy = new CreditCard("1234-5678-9012-3456", customer.getName(), new model.employee_system.Date(), 123, bankAccount);
        } else if (paymentPanel.getBank().isSelected()) {
            // Assuming a default BankAccount and BankTransfer details for demo
            BankAccount bankAccount = new BankAccount(); // Placeholder
            paymentStrategy = new BankTransfer("987654321", customer.getName(), bankAccount);
        }

        if (paymentStrategy == null) {
            JOptionPane.showMessageDialog(paymentPanel, "Vui lòng chọn phương thức thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create and process invoice
        Invoice invoice = new Invoice(totalAmount, paymentStrategy, customer);
        double paidAmount = invoice.pay();

        if (paidAmount > 0) {
            JOptionPane.showMessageDialog(paymentPanel, "Thanh toán thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Clear the content panel except for the history button
            JPanel contentPanel = (JPanel) paymentPanel.getConfirmBtn().getParent().getParent();
            Component[] components = contentPanel.getComponents();
            JPanel topLeftPanel = null;
            for (Component c : components) {
                if (c instanceof JPanel && ((JPanel) c).getComponentCount() > 0) {
                    Component first = ((JPanel) c).getComponent(0);
                    if (first == paymentPanel.getHistoryButton()) {
                        topLeftPanel = (JPanel) c;
                        break;
                    }
                }
            }

            contentPanel.removeAll();
            if (topLeftPanel != null) {
                contentPanel.add(topLeftPanel);
                contentPanel.add(Box.createVerticalStrut(20));
            }
            contentPanel.setBackground(new Color(255, 248, 220));
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private void initializeView() {
        // Update PaymentPanel labels with customer and amount data
        paymentPanel.getTenLabel().setText(customer.getName());
        paymentPanel.getSdtLabel().setText(customer.getNumsPhone());
        paymentPanel.getTrangThaiLabel().setText(customer.isVIP()?"VIP":"Thường");
        paymentPanel.getBanLabel().setText(table.getIdTable()+"");// Could be dynamic if table info is available
        paymentPanel.getGiamGiaLabel().setText(String.format("%,.0fđ", totalAmount));

    }
    private  class CustomTransactionHistoryDialog extends TransactionHistoryDialog {
        public CustomTransactionHistoryDialog(JFrame parent) {
            super(parent);
            updateTransactionHistoryContent();
        }

        private void updateTransactionHistoryContent() {
            getContentPane().removeAll();
            setLayout(new BorderLayout());
            getContentPane().setBackground(new Color(255, 248, 220));

            String[] columns = {"STT", "Tên", "SĐT", "Số Tiền", "Phương Thức Thanh Toán", "Ngày Thanh Toán"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            // Populate table with transaction data
            List<Transaction> transactions = TransactionLog.getTransactions();
            for (int i = 0; i < transactions.size(); i++) {
                Transaction t = transactions.get(i);
                model.addRow(new Object[]{
                        i + 1,
                        t.toString().contains("[VIP]") ? t.toString().split(" \\[")[0].split("Khách: ")[1] : t.toString().split("Khách: ")[1],
                        customer.getNumsPhone(), // Assuming phone is available
                        String.format("%,.0fđ", t.getAmount()),
                        t.getPaymentMethod(),
                        t.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                });
            }

            CustomTable table = new CustomTable();
            table.setModel(model);
            table.setFont(new Font("SansSerif", Font.PLAIN, 16));
            table.setRowHeight(35);
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
            table.getTableHeader().setPreferredSize(new Dimension(100, 40));
            table.getTableHeader().setBackground(new Color(255, 224, 178));
            table.getTableHeader().setForeground(Color.BLACK);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(scrollPane, BorderLayout.CENTER);

            revalidate();
            repaint();

        }
    }


}
