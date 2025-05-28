package data.dto;

public class PayDTO {
    private String tenMon, topping;
    private int soLuong;
    private String gia;

    public PayDTO(String tenMon, int soLuong, String gia, String topping) {
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.gia = gia;
        this.topping = topping;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getGia() {
        return gia;
    }

    public String getTopping() {
        return topping;
    }
}
