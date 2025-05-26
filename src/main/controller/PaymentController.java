package controller;

import model.customer_system.CustomerSystem;
import model.order_system.OrderSystem;
import model.reservation_system.ReservationSystem;
import model.reservation_system.Table;
import view.Staff.PaymentPanel;

import javax.swing.*;

public class PaymentController {
    private PaymentPanel view;
    private CustomerSystem customerModel;
    private OrderSystem orderModel;
    private ReservationSystem reservationModel;
    
    public PaymentController(PaymentPanel view, CustomerSystem customerModel, OrderSystem orderModel, ReservationSystem reservationModel) {
        this.view = view;
        this.customerModel = customerModel;
        this.orderModel = orderModel;
        this.reservationModel = reservationModel;
        
        // Gắn các sự kiện cho các nút
        view.getConfirmBtn().addActionListener(e -> handleConfirmPayment());
        view.getHistoryButton().addActionListener(e -> handleHistory());
    }
    
    private void handleConfirmPayment() {
        // Kiểm tra phương thức thanh toán
        if (!view.getCash().isSelected() && !view.getCard().isSelected() && !view.getBank().isSelected()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn phương thức thanh toán!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Kiểm tra dữ liệu hợp lệ
        if (orderModel.getListStoreOrder().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Chưa có món ăn nào được chọn!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        // Xử lý thanh toán
        JOptionPane.showMessageDialog(view, "Thanh toán thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        
        // Xóa dữ liệu sau khi thanh toán
        orderModel.getListStoreOrder().clear(); // Xóa danh sách đơn hàng
        reservationModel.updateTableStatus(getSelectedTableId(), false); // Cập nhật trạng thái bàn thành trống
        view.updateView(); // Cập nhật giao diện
    }
    
    private void handleHistory() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
        
        view.onHistoryButtonClicked();
    }
    
    private int getSelectedTableId() {
        for (Table table : reservationModel.getTables()) {
            if (table.isStatus()) {
                return table.getIdTable();
            }
        }
        return -1; // Không tìm thấy bàn nào được chọn
    }
}