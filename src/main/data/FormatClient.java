package data;

public class FormatClient {
    private String hoTen, soDienThoai, diemTichLuy, trangThai;
    private boolean chon;

    public FormatClient(String hoTen, String soDienThoai, String diemTichLuy, String trangThai, boolean chon) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diemTichLuy = diemTichLuy;
        this.trangThai = trangThai;
        this.chon = chon;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getDiemTichLuy() {
        return diemTichLuy;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public boolean isChon() {
        return chon;
    }
}
