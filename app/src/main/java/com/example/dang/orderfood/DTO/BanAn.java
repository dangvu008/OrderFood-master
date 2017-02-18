package com.example.dang.orderfood.DTO;

/**
 * Created by DANG on 8/9/2016.
 */
public class BanAn {
    private int maBan;
    private String tenBan;
    private String tinhTrang;

    public BanAn() {
    }

    public BanAn(String tenBan, String tinhTrang) {
        this.tenBan = tenBan;
        this.tinhTrang = tinhTrang;
    }

    public BanAn(int maBan, String tenBan, String tinhTrang) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.tinhTrang = tinhTrang;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = (tinhTrang == "")?"false":tinhTrang;
    }
}
