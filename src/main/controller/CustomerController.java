package controller;

import model.Subject;
import model.customer_system.*;
import view.IView;

import java.util.List;

public class CustomerController {
    private CustomerSystem customerSystem;
    private IView view;
    private Subject promotionSubject; // Đối tượng Subject để quản lý khuyến mãi

    public CustomerController(CustomerSystem customerSystem, IView view) {
        this.customerSystem = customerSystem;
        this.view = view;
        this.promotionSubject = promotionSubject;
    }

    /**
     * Thêm khách hàng mới (Normal hoặc VIP) dựa trên thông tin từ View.
     */
    public void addCustomer(String name, String idCus, String numsPhone, boolean isVIP, boolean isRegistered, Subject subject) {
        try {
            Customer customer;
            if (isVIP) {
                customer = new VIPCustomer(name, idCus, numsPhone, subject, 0);
            } else {
                customer = new NormalCustomer(name, idCus, numsPhone, subject, isRegistered);
            }
            customerSystem.addCustomer(customer);
            view.displayMessage("Thêm khách hàng thành công: " + customer.getName());
        } catch (Exception e) {
            view.displayMessage("Lỗi khi thêm khách hàng: " + e.getMessage());
        }
    }

    /**
     * Xóa khách hàng dựa trên số điện thoại.
     */
    public void removeCustomer(String numsPhone) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null) {
            customerSystem.removeCustomer(customer);
            view.displayMessage("Xóa khách hàng thành công: " + customer.getName());
        } else {
            view.displayMessage("Không tìm thấy khách hàng với số điện thoại: " + numsPhone);
        }
    }

    /**
     * Tìm kiếm khách hàng theo số điện thoại và hiển thị thông tin.
     */
    public void findCustomerByNumPhone(String numsPhone) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null) {
            view.displayCustomer(customer);
        } else {
            view.displayMessage("Không tìm thấy khách hàng với số điện thoại: " + numsPhone);
        }
    }

    /**
     * Lấy danh sách khách hàng VIP và hiển thị trên View.
     */
    public void displayVIPCustomers() {
        List<Customer> vipCustomers = customerSystem.getVIPCustomers();
        if (vipCustomers.isEmpty()) {
            view.displayMessage("Không có khách hàng VIP.");
        } else {
            view.displayCustomerList(vipCustomers);
        }
    }

    /**
     * Lấy danh sách khách hàng thường và hiển thị trên View.
     */
    public void displayNormalCustomers() {
        List<Customer> normalCustomers = customerSystem.getNormalCustomers();
        if (normalCustomers.isEmpty()) {
            view.displayMessage("Không có khách hàng thường.");
        } else {
            view.displayCustomerList(normalCustomers);
        }
    }

    /**
     * Cập nhật thông tin khách hàng (tên, số điện thoại, trạng thái đăng ký).
     */
    public void updateCustomerInfo(String numsPhone, String newName, String newNumsPhone, boolean isRegistered) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null) {
            customer.updateInforCustomer(newName, newNumsPhone, isRegistered);
            view.displayMessage("Cập nhật thông tin khách hàng thành công: " + customer.getName());
        } else {
            view.displayMessage("Không tìm thấy khách hàng với số điện thoại: " + numsPhone);
        }
    }

    /**
     * Nâng cấp khách hàng từ Normal lên VIP.
     */
    public void upgradeToVIP(String numsPhone) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null && customer.getType().equals("Normal")) {
            ServiceCustomer service = new ServiceCustomer(customerSystem);
            service.upRank(customer);
            view.displayMessage("Nâng cấp khách hàng thành công: " + customer.getName());
        } else {
            view.displayMessage("Khách hàng không hợp lệ hoặc đã là VIP.");
        }
    }

    /**
     * Cập nhật điểm tích lũy cho khách hàng VIP.
     */
    public void updatePoints(String numsPhone, int points) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null && customer.isVIP()) {
            ServiceCustomer service = new ServiceCustomer(customerSystem);
            service.updatePoint(customer, points);
            view.displayMessage("Cập nhật điểm thành công cho khách hàng: " + customer.getName());
        } else {
            view.displayMessage("Khách hàng không phải VIP hoặc không tồn tại.");
        }
    }

    /**
     * Đổi điểm tích lũy cho khách hàng VIP.
     */
    public void exchangePoints(String numsPhone, int points) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null && customer instanceof VIPCustomer) {
            String result = ((VIPCustomer) customer).exchangePointsForRewards(points);
            view.displayMessage(result);
        } else {
            view.displayMessage("Khách hàng không phải VIP hoặc không tồn tại.");
        }
    }

    /**
     * Gửi thông báo khuyến mãi cho khách hàng VIP và khách hàng thường đã đăng ký.
     */
    public void sendPromotion(String title, String content) {
        StringBuilder notifications = new StringBuilder();
        for (List<Customer> customers : customerSystem.getListCus().values()) {
            for (Customer customer : customers) {
                if (customer.isRegister()) {
                    String notification = customer.updateNotify(title, content);
                    notifications.append(notification).append("\n\n");
                }
            }
        }
        if (notifications.length() > 0) {
            view.displayMessage(notifications.toString());
        } else {
            view.displayMessage("Không có khách hàng nào đăng ký nhận thông báo khuyến mãi.");
        }
    }

    /**
     * Đăng ký khách hàng nhận thông báo khuyến mãi
     */
    public void registerForPromotion(String numsPhone) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null) {
            if (customer.isVIP() || (customer instanceof NormalCustomer && !customer.isRegister())) {
                customer.setRegisterStatus(true);
                promotionSubject.addObserver((Observer) customer);
                view.displayMessage("Khách hàng " + customer.getName() + " đã được đăng ký nhận thông báo khuyến mãi.");
            } else {
                view.displayMessage("Khách hàng đã đăng ký hoặc không hợp lệ.");
            }
        } else {
            view.displayMessage("Không tìm thấy khách hàng với số điện thoại: " + numsPhone);
        }
    }

    /**
     * Hủy đăng ký nhận thông báo khuyến mãi
     */
    public void unregisterForPromotion(String numsPhone) {
        Customer customer = customerSystem.findCustomerByNumPhone(numsPhone);
        if (customer != null) {
            if (customer.isRegister()) {
                customer.setRegisterStatus(false);
                promotionSubject.removeObserver((Observer) customer);
                view.displayMessage("Khách hàng " + customer.getName() + " đã hủy đăng ký nhận thông báo khuyến mãi.");
            } else {
                view.displayMessage("Khách hàng chưa đăng ký nhận thông báo.");
            }
        } else {
            view.displayMessage("Không tìm thấy khách hàng với số điện thoại: " + numsPhone);
        }
    }

    /**
     * Lấy đối tượng Subject để sử dụng trong các panel
     */
    public Subject getPromotionSubject() {
        return promotionSubject;
    }
}