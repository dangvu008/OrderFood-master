package com.example.dang.orderfood.DTO;

/**
 * Created by DANG on 8/21/2016.
 */
public class ThanhToan {
    String tenMonAn;
    int soluong,giatien;

    public ThanhToan() {
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
}
