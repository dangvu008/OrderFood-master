package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dang.orderfood.DTO.GoiMon;
import com.example.dang.orderfood.DTO.ThanhToan;
import com.example.dang.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 8/16/2016.
 */
public class GoiMonDAO {
    CreateDatabase createDatabase;
    SQLiteDatabase database;
    Context context;

    public GoiMonDAO(Context context) {
        this.context = context;
        createDatabase = new CreateDatabase(context);
    }
    public long themGoiMon(GoiMon goiMon){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_GOIMON_MANHANVIEN,goiMon.getMaNhanVien());
        values.put(CreateDatabase.COLUMN_GOIMON_NGAYGOI,goiMon.getNgayGoi());
        values.put(CreateDatabase.COLUMN_GOIMON_MABAN,goiMon.getMaBan());
        values.put(CreateDatabase.COLUMN_GOIMON_TINHTRANG,"false");
        return database.insert(CreateDatabase.TABLE_GOIMON,null,values);
    }
    public GoiMon kiemTraGoiMon(int maBan,String tinhTrang){
        GoiMon goiMon = null;
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_GOIMON,null,
                CreateDatabase.COLUMN_GOIMON_MABAN + " = ? AND " +CreateDatabase.COLUMN_BANAN_TINHTRANG +" = ? "
                ,new String[]{String.valueOf(maBan),tinhTrang},null,null,null);
        if (cursor.moveToFirst()){
           goiMon = new GoiMon();
            goiMon.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_GOIMON_MABAN)));
            goiMon.setMaNhanVien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_GOIMON_MANHANVIEN)));
            goiMon.setMaGoiMon(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_GOIMON_MAGOIMON)));
            goiMon.setNgayGoi(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_GOIMON_NGAYGOI)));
            goiMon.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_GOIMON_TINHTRANG)));

        }
        return goiMon;

    }
    public long updateGoiMonChuyenBan(int MagoiMon,int maBan){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_GOIMON_MANHANVIEN,maBan);
        return database.update(CreateDatabase.TABLE_GOIMON,values,CreateDatabase.COLUMN_GOIMON_MABAN +" = ?",new String[]{String.valueOf(maBan)});
    }
    public long updatetinhTrangGoiMon(int magoiMon,String tinhTrang){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_GOIMON_TINHTRANG,tinhTrang);
        return database.update(CreateDatabase.TABLE_GOIMON,values,CreateDatabase.COLUMN_GOIMON_MAGOIMON +" = ?",new String[]{String.valueOf(magoiMon)});
    }
    public List<ThanhToan> listThanhToanGoiMon(int maGoiMon){
        database = createDatabase.getReadableDatabase();
        String truyvan = "SELECT * FROM " + CreateDatabase.TABLE_CHITIET_GOIMON + " ct," + CreateDatabase.TABLE_MONAN + " ma WHERE "
                + "ct." + CreateDatabase.COLUMN_CHITETGOIMON_MAMONAN + " = ma." + CreateDatabase.COLUMN_MONAN_MAMONAN
                + " AND " + CreateDatabase.COLUMN_CHITETGOIMON_MAGOIMON + " = '" + maGoiMon + "'";

        List<ThanhToan> thanhToanDTOs = new ArrayList<ThanhToan>();
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ThanhToan thanhToanDTO = new ThanhToan();
            thanhToanDTO.setSoluong(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_CHITETGOIMON_SOLUONG)));
            thanhToanDTO.setGiatien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_GIAMONAN)));
            thanhToanDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_TENMONAN)));

            thanhToanDTOs.add(thanhToanDTO);

            cursor.moveToNext();
        }

        return thanhToanDTOs;
    }
}
