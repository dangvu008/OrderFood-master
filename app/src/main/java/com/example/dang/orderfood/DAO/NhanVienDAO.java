package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dang.orderfood.DTO.NhanVien;
import com.example.dang.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 8/6/2016.
 */
public class NhanVienDAO {
    SQLiteDatabase database;
    CreateDatabase createDatabase;
    public NhanVienDAO(Context context) {
        createDatabase = new CreateDatabase(context);

    }

    public long themNhanVien(NhanVien nhanVien) {
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_NHANVIEN_TENNHANVIEN, nhanVien.getTenNhanVien());
        values.put(CreateDatabase.COLUMN_NHANVIEN_TENDANGNHAP, nhanVien.getTenDangNhap());
        values.put(CreateDatabase.COLUMN_NHANVIEN_MATKHAU, nhanVien.getMatkhau());
        values.put(CreateDatabase.COLUMN_NHANVIEN_GIOITINH, nhanVien.getGioiTinh());
        values.put(CreateDatabase.COLUMN_NHANVIEN_NGAYSINH, nhanVien.getNgaySinh());
        values.put(CreateDatabase.COLUMN_NHANVIEN_CMND, nhanVien.getCmnd());
        values.put(CreateDatabase.COLUMN_NHANVIEN_MAQUYEN, nhanVien.getMaQuyen());

        long kiemtra = database.insert(CreateDatabase.TABLE_NHANVIEN, null, values);
        return kiemtra;
    }

    public NhanVien dangNhap(String tenDangNhap) {
        NhanVien nhanVien = null;
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_NHANVIEN,null,
                CreateDatabase.COLUMN_NHANVIEN_TENDANGNHAP + " = ? "
                , new String[]{tenDangNhap}, null, null, null);
        if (cursor.moveToFirst()) {
            nhanVien = new NhanVien();
            int maNhanVien = cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN));
            String tenNhanVien = cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_TENNHANVIEN));
            String matkhau = cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MATKHAU));
            int maQuyen = cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MAQUYEN));
            nhanVien.setMaNhanVien(maNhanVien);
            nhanVien.setTenNhanVien(tenNhanVien);
            nhanVien.setMatkhau(matkhau);
            nhanVien.setMaQuyen(maQuyen);
        }

        return nhanVien;
    }

    public List<NhanVien> listNhanVien() {
        database = createDatabase.getReadableDatabase();
        List<NhanVien> list = new ArrayList<>();
        Cursor cursor = database.query(CreateDatabase.TABLE_NHANVIEN, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN)));
                nhanVien.setTenNhanVien(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_TENNHANVIEN)));
                nhanVien.setTenDangNhap(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_TENDANGNHAP)));
                nhanVien.setGioiTinh(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_GIOITINH)));
                nhanVien.setNgaySinh(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_NGAYSINH)));
                nhanVien.setCmnd(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_CMND)));
                nhanVien.setMaQuyen(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MAQUYEN)));
                list.add(nhanVien);
            } while (cursor.moveToNext());
            cursor.close();
            database.close();
        }
        return list;
    }

    public NhanVien getNhanVienByMa(int maNhanVien) {
        database = createDatabase.getReadableDatabase();
        String query = "SELECT * FROM "+CreateDatabase.TABLE_NHANVIEN+" WHERE "+CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN+" = ?";
        Cursor cursor = database.rawQuery(query,new String[]{String.valueOf(maNhanVien)});
        if (cursor!=null)
            cursor.moveToFirst();
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN)));
        nhanVien.setTenNhanVien(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_TENNHANVIEN)));
        nhanVien.setTenDangNhap(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_TENDANGNHAP)));
        nhanVien.setMatkhau(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MATKHAU)));
        nhanVien.setGioiTinh(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_GIOITINH)));
        nhanVien.setNgaySinh(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_NGAYSINH)));
        nhanVien.setCmnd(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_CMND)));
        nhanVien.setMaQuyen(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_NHANVIEN_MAQUYEN)));
        cursor.close();
        database.close();
        return nhanVien;
    }
    public boolean kiemTraTonTaiTenDangNhap(String tenDangNhap) {
        boolean flag = false;
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_NHANVIEN,null,CreateDatabase.COLUMN_NHANVIEN_TENDANGNHAP + " = ?",new String[]{tenDangNhap},null,null,null);
        if (cursor.moveToFirst()) {
            flag = true;
        }
        cursor.close();
        database.close();
        return flag;
    }
    public boolean kiemTraTonTaiCmnd(int cmnd){
        boolean flag = false;
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_NHANVIEN,new String[]{CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN},
                CreateDatabase.COLUMN_NHANVIEN_CMND + " = ? ",new String[]{String.valueOf(cmnd)},null,null,null);
        if (cursor!=null && cursor.moveToFirst())
            flag =true;
        cursor.close();
        database.close();
        return flag;
    }
    public long xoaNhanVien(int maNhanVien) {
        database = createDatabase.getWritableDatabase();
        return database.delete(CreateDatabase.TABLE_NHANVIEN, CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN + " = ? ", new String[]{String.valueOf(maNhanVien)});
    }

    public long updateNhanVien(NhanVien nhanVien) {
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_NHANVIEN_TENNHANVIEN, nhanVien.getTenNhanVien());
        values.put(CreateDatabase.COLUMN_NHANVIEN_TENDANGNHAP, nhanVien.getTenDangNhap());
        values.put(CreateDatabase.COLUMN_NHANVIEN_MATKHAU, nhanVien.getMatkhau());
        values.put(CreateDatabase.COLUMN_NHANVIEN_GIOITINH, nhanVien.getGioiTinh());
        values.put(CreateDatabase.COLUMN_NHANVIEN_NGAYSINH, nhanVien.getNgaySinh());
        values.put(CreateDatabase.COLUMN_NHANVIEN_CMND, nhanVien.getCmnd());
        values.put(CreateDatabase.COLUMN_NHANVIEN_MAQUYEN,nhanVien.getMaQuyen());
        long kiemtra = database.update(CreateDatabase.TABLE_NHANVIEN, values, CreateDatabase.COLUMN_NHANVIEN_MANHANVIEN +" = ? ", new String[]{String.valueOf(nhanVien.getMaNhanVien())});
        return kiemtra;
    }


}
