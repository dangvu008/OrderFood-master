package com.example.dang.orderfood.DTO;

/**
 * Created by DANG on 8/12/2016.
 */
public class LoaiMonAn  {
    private int maLoaiMonAn;
    private String tenLoaiMonAn;
    private String hinhAnh;
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }


    public LoaiMonAn() {
    }

    public LoaiMonAn(int maLoaiMonAn, String tenLoaiMonAn) {
        this.maLoaiMonAn = maLoaiMonAn;
        this.tenLoaiMonAn = tenLoaiMonAn;
    }

    public int getMaLoaiMonAn() {
        return maLoaiMonAn;
    }

    public void setMaLoaiMonAn(int maLoaiMonAn) {
        this.maLoaiMonAn = maLoaiMonAn;
    }

    public String getTenLoaiMonAn() {
        return tenLoaiMonAn;
    }

    public void setTenLoaiMonAn(String tenLoaiMonAn) {
        this.tenLoaiMonAn = tenLoaiMonAn;
    }
}
