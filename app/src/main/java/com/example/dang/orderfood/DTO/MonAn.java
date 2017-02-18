package com.example.dang.orderfood.DTO;

/**
 * Created by DANG on 8/11/2016.
 */
public class MonAn {
    private int maMonAn;
    private String tenMonAn;
    private int giaMonAn;
    private int maLoai;
    private String hinhAnhMonAn;
    public MonAn() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getGiaMonAn() {
        return giaMonAn;
    }

    public void setGiaMonAn(int giaMonAn) {
        this.giaMonAn = giaMonAn;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getHinhAnhMonAn() {
        return hinhAnhMonAn;
    }

    public void setHinhAnhMonAn(String hinhAnhMonAn) {
        this.hinhAnhMonAn = hinhAnhMonAn;
    }
}
