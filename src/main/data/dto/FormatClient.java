package data.dto;

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

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setDiemTichLuy(String diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setChon(boolean chon) {
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
