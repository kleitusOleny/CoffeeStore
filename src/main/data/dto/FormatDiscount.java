package data.dto;

public class FormatDiscount {
    private String maKM, tenKM, noiDung, ngayBatDau, ngayKetThuc;
    private boolean chon;

    public FormatDiscount(String maKM, String tenKM, String noiDung, String ngayBatDau, String ngayKetThuc, boolean chon) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.noiDung = noiDung;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.chon = chon;
    }

    public String getMaKM() {
        return maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public boolean isChon() {
        return chon;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }
}
