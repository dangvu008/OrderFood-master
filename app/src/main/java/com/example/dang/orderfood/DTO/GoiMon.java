package com.example.dang.orderfood.DTO;

/**
 * Created by DANG on 8/16/2016.
 */
public class GoiMon {
    private int maGoiMon;
    private int maNhanVien;
    private String ngayGoi;
    private int maBan;
    private String tinhTrang ;

    public GoiMon() {
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public String getNgayGoi() {
        return ngayGoi;
    }

    public void setNgayGoi(String ngayGoi) {
        this.ngayGoi = ngayGoi;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
