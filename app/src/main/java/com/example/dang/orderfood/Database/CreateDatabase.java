package com.example.dang.orderfood.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DANG on 8/6/2016.
 */
public class CreateDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "OrderFood";
    //TABLE
    public static final String TABLE_NHANVIEN = "NhanVien ";
    public static final String TABLE_MONAN = "MonAn ";
    public static final String TABLE_LOAIMONAN = "LoaiMonAn ";
    public static final String TABLE_BANAN = "BanAn ";
    public static final String TABLE_GOIMON = "GoiMon ";
    public static final String TABLE_CHITIET_GOIMON = "ChiTietGoiMon ";
    public static final String TABLE_PHANQUYEN = "PhanQuyen";
    //colums table nhanvien
    public static final String COLUMN_NHANVIEN_MANHANVIEN ="MaNhanVien";
    public static final String COLUMN_NHANVIEN_TENNHANVIEN ="TenNhanVien";
    public static final String COLUMN_NHANVIEN_TENDANGNHAP ="TenDangNhap";
    public static final String COLUMN_NHANVIEN_MAQUYEN ="MaQuyen";
    public static final String COLUMN_NHANVIEN_MATKHAU ="MatKhau";
    public static final String COLUMN_NHANVIEN_GIOITINH ="GioiTInh";
    public static final String COLUMN_NHANVIEN_NGAYSINH ="NgaySinh";
    public static final String COLUMN_NHANVIEN_CMND ="Cmnd";
    // colums table monan
    public static final String COLUMN_MONAN_MAMONAN ="MaMonAn";
    public static final String COLUMN_MONAN_TENMONAN ="TenMonAn";
    public static final String COLUMN_MONAN_GIAMONAN ="GiaMonAn";
    public static final String COLUMN_MONAN_MALOAI ="MaLoai";
    public static final String COLUMN_MONAN_HINHANH ="HinhAnhMonAn";
    //colums table loaimonan
    public static final String COLUMN_LOAIMONAN_MALOAI = "MaLoaiMonAn";
    public static final String COLUMN_LOAIMONAN_TENLOAI = "TenLoaiMonAn";
    //colums table banan
    public static final String COLUMN_BANAN_MABAN ="MaBan";
    public static final String COLUMN_BANAN_TENBAN ="TenBan";
    public static final String COLUMN_BANAN_TINHTRANG="TinhTrang";
    //columns table goimon
    public static final String COLUMN_GOIMON_MAGOIMON = "MaGoiMon";
    public static final String COLUMN_GOIMON_MANHANVIEN = "MaNhanVien";
    public static final String COLUMN_GOIMON_NGAYGOI = "NgayGoi";
    public static final String COLUMN_GOIMON_TINHTRANG = "TinhTrang";
    public static final String COLUMN_GOIMON_MABAN = "MaBan";
   //colums table chitietgoimon
    public static final String COLUMN_CHITETGOIMON_MAGOIMON = "MaGoiMon";
    public static final String COLUMN_CHITETGOIMON_MAMONAN = "MaMonAn";
    public static final String COLUMN_CHITETGOIMON_SOLUONG= "SoLuong";
    // columns table phanquyen
    public static final String COLUMN_PHANQUYEN_MAQUYEN = "MaQuyen";
    public static final String COLUMN_PHANQUYEN_TENQUYEN ="TenQuyen";


    public CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_NHANVIEN ="CREATE TABLE  "+TABLE_NHANVIEN +"(" +
                  COLUMN_NHANVIEN_MANHANVIEN+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_NHANVIEN_TENNHANVIEN+" TEXT , " +
               COLUMN_NHANVIEN_TENDANGNHAP+ " TEXT , " +
                COLUMN_NHANVIEN_MATKHAU+" TEXT , " +
                COLUMN_NHANVIEN_GIOITINH + " TEXT ," +
                COLUMN_NHANVIEN_NGAYSINH + " TEXT ," +
                COLUMN_NHANVIEN_CMND + " INTEGER , " +
               COLUMN_NHANVIEN_MAQUYEN+ " INTEGER NOT NULL ) ";

        String CREATE_TABLE_MONAN ="CREATE TABLE  "+TABLE_MONAN +"(" +
                COLUMN_MONAN_MAMONAN+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_MONAN_TENMONAN+" TEXT , " +
                COLUMN_MONAN_GIAMONAN+ " INTEGER , " +
                COLUMN_MONAN_MALOAI+" INTEGER , " +
                COLUMN_MONAN_HINHANH+" TEXT ) " ;
        String CREATE_TABLE_LOAIMONAN ="CREATE TABLE  "+TABLE_LOAIMONAN +"(" +
                COLUMN_LOAIMONAN_MALOAI+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_LOAIMONAN_TENLOAI+" TEXT ) ";
        String CREATE_TABLE_BANAN ="CREATE TABLE  "+TABLE_BANAN +"(" +
                COLUMN_BANAN_MABAN+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_BANAN_TENBAN+" TEXT , " +
                COLUMN_BANAN_TINHTRANG+ " TEXT  DEFAULT false)";
        String CREATE_TABLE_GOIMON ="CREATE TABLE  "+TABLE_GOIMON +"(" +
                COLUMN_GOIMON_MAGOIMON+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_GOIMON_MANHANVIEN+" INTEGER , " +
                COLUMN_GOIMON_NGAYGOI+ " TEXT , " +
                COLUMN_GOIMON_MABAN+" INTEGER , " +
                COLUMN_GOIMON_TINHTRANG + " TEXT )";
        String CREATE_TABLE_CHITIET_GOIMON ="CREATE TABLE  "+TABLE_CHITIET_GOIMON +"(" +
                COLUMN_CHITETGOIMON_MAGOIMON+" INTEGER , " +
                COLUMN_CHITETGOIMON_MAMONAN+" INTEGER, " +
                COLUMN_CHITETGOIMON_SOLUONG+ " INTEGER, " +
                "PRIMARY KEY("+COLUMN_CHITETGOIMON_MAGOIMON+","+COLUMN_CHITETGOIMON_MAMONAN+"))" ;
        String CREATE_TABLE_PHANQUYEN ="CREATE TABLE  "+TABLE_PHANQUYEN +"(" +
                COLUMN_PHANQUYEN_MAQUYEN+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PHANQUYEN_TENQUYEN +" TEXT )" ;

        sqLiteDatabase.execSQL(CREATE_TABLE_NHANVIEN);
        sqLiteDatabase.execSQL(CREATE_TABLE_MONAN);
        sqLiteDatabase.execSQL(CREATE_TABLE_LOAIMONAN);
        sqLiteDatabase.execSQL(CREATE_TABLE_BANAN);
        sqLiteDatabase.execSQL(CREATE_TABLE_GOIMON);
        sqLiteDatabase.execSQL(CREATE_TABLE_CHITIET_GOIMON);
        sqLiteDatabase.execSQL(CREATE_TABLE_PHANQUYEN);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}
