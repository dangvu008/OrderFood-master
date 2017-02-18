package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dang.orderfood.DTO.LoaiMonAn;
import com.example.dang.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 8/12/2016.
 */
public class LoaiMonAnDAO {
    SQLiteDatabase database;
    CreateDatabase createDatabase;
    List<LoaiMonAn> listLoaiMonAn;
    LoaiMonAn loaiMonAn;
    public LoaiMonAnDAO(Context context) {
        createDatabase = new CreateDatabase(context);
    }
    public long themLoaiMonAn(LoaiMonAn loaiMonAn){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_LOAIMONAN_TENLOAI,loaiMonAn.getTenLoaiMonAn());
        return database.insert(CreateDatabase.TABLE_LOAIMONAN,null,values);
    }
    public List<LoaiMonAn> listAllLoaiMonAn(){
        database = createDatabase.getReadableDatabase();
        listLoaiMonAn = new ArrayList<>();
        Cursor cursor = database.query(CreateDatabase.TABLE_LOAIMONAN,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                loaiMonAn = new LoaiMonAn();
                loaiMonAn.setMaLoaiMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_LOAIMONAN_MALOAI)));
                loaiMonAn.setTenLoaiMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_LOAIMONAN_TENLOAI)));
                listLoaiMonAn.add(loaiMonAn);
            }while (cursor.moveToNext());
            cursor.close();
            database.close();
        }
        return listLoaiMonAn;
    }
    public List<LoaiMonAn> listAllLoaiMonAnCoHinh(){
        database = createDatabase.getReadableDatabase();
        listLoaiMonAn = new ArrayList<>();
        String strSQL = "SELECT DISTINCT  lma.MaLoaiMonAn,lma.TenLoaiMonAn,ma.HinhAnhMonAn  FROM  LoaiMonAn lma,MonAn ma " +
                " WHERE lma.MaLoaiMonAn= ma.MaLoai GROUP BY lma.MaLoaiMonAn";
        Cursor cursor = database.rawQuery(strSQL,null);
        if (cursor.moveToFirst()){
            do {
                loaiMonAn = new LoaiMonAn();
                loaiMonAn.setMaLoaiMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_LOAIMONAN_MALOAI)));
                loaiMonAn.setTenLoaiMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_LOAIMONAN_TENLOAI)));
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_HINHANH))==null){
                    loaiMonAn.setHinhAnh("null");
                }else{
                    loaiMonAn.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_HINHANH)));
                }

                listLoaiMonAn.add(loaiMonAn);
            }while (cursor.moveToNext());
            cursor.close();
            database.close();
        }
        return listLoaiMonAn;
    }
    public LoaiMonAn getLoaiMonAnById(int id){
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_LOAIMONAN,null,"MaLoaiMonAn = ?",new String[]{String.valueOf(id)},null,null,null);
        loaiMonAn = new LoaiMonAn();
        loaiMonAn.setMaLoaiMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_LOAIMONAN_MALOAI)));
        loaiMonAn.setTenLoaiMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_LOAIMONAN_TENLOAI)));
        cursor.close();
        database.close();
        return loaiMonAn;
    }
    public String LayHinhLoaiMonAn(int maloai){
        String hinhanh = "";
        database = createDatabase.getReadableDatabase();
        String truyvan = "SELECT * FROM " + CreateDatabase.TABLE_MONAN + " WHERE " + CreateDatabase.COLUMN_MONAN_MALOAI + " = '" + maloai + "' "
                + " AND " + CreateDatabase.COLUMN_MONAN_HINHANH + " IS NOT NULL ORDER BY " + CreateDatabase.COLUMN_MONAN_MAMONAN + " LIMIT 1";
        Cursor cursor = database.rawQuery(truyvan,null);
        Log.d("truyvan",truyvan);
        cursor.moveToFirst();
            hinhanh = cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_HINHANH));
        return hinhanh;
    }

    public long updateLoaiMonAn(LoaiMonAn loaiMonAn) {
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_LOAIMONAN_TENLOAI, loaiMonAn.getTenLoaiMonAn());
        return database.update(CreateDatabase.TABLE_LOAIMONAN, values, "MaLoaiMonAn", new String[]{String.valueOf(loaiMonAn.getMaLoaiMonAn())});
    }
}
