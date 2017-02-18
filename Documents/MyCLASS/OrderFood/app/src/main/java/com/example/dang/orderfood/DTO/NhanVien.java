package com.example.dang.orderfood.DTO;

import java.io.Serializable;

/**
 * Created by DANG on 8/6/2016.
 */
public class NhanVien implements Serializable {
    private int maNhanVien;
    private String tenNhanVien;
    private String tenDangNhap;
    private String matkhau;
    private String maDangNhap ;
    private String GioiTinh ;
    private String ngaySinh ;
    private int cmnd;
    private int maQuyen;

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String tenNhanVien, String tenDangNhap, String matkhau, String maDangNhap, String gioiTinh, String ngaySinh, int cmnd) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matkhau;
        this.maDangNhap = maDangNhap;
        GioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMaDangNhap() {
        return maDangNhap;
    }

    public void setMaDangNhap(String maDangNhap) {
        this.maDangNhap = maDangNhap;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
