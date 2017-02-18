package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dang.orderfood.DTO.MonAn;
import com.example.dang.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 8/11/2016.
 */
public class MonAnDAO{
    SQLiteDatabase database ;
    Context context;
    List<MonAn> listMonAn;
    CreateDatabase createDatabase;

    public MonAnDAO(Context context) {
        this.context = context;
        createDatabase = new CreateDatabase(context);
    }
    public long themMonAn(MonAn monAn){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_MONAN_TENMONAN,monAn.getTenMonAn());
        values.put(CreateDatabase.COLUMN_MONAN_GIAMONAN,monAn.getGiaMonAn());
        values.put(CreateDatabase.COLUMN_MONAN_MALOAI,monAn.getMaLoai());
        values.put(CreateDatabase.COLUMN_MONAN_HINHANH,monAn.getHinhAnhMonAn());
        return database.insert(CreateDatabase.TABLE_MONAN,null,values);
    }
    public long suaMonAn(MonAn monAn){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_MONAN_MAMONAN,monAn.getMaMonAn());
        values.put(CreateDatabase.COLUMN_MONAN_TENMONAN,monAn.getTenMonAn());
        values.put(CreateDatabase.COLUMN_MONAN_GIAMONAN,monAn.getGiaMonAn());
        values.put(CreateDatabase.COLUMN_MONAN_MALOAI,monAn.getMaLoai());
        values.put(CreateDatabase.COLUMN_MONAN_HINHANH,monAn.getHinhAnhMonAn());
        return database.update(CreateDatabase.TABLE_MONAN,values,"MaMonAn = ?",new String[]{String.valueOf(monAn.getMaMonAn())});
    }
    public List<MonAn> listAllMonAn(){
        database = createDatabase.getReadableDatabase();
        listMonAn = new ArrayList<>();
        Cursor cursor = database.query(CreateDatabase.TABLE_MONAN,null,null,null,null,null,CreateDatabase.COLUMN_MONAN_MAMONAN);
        if (cursor.moveToFirst()){
            do {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_MAMONAN)));
                monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_TENMONAN)));
                monAn.setGiaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_GIAMONAN)));
                monAn.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_MALOAI)));
                monAn.setHinhAnhMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_HINHANH)));
                listMonAn.add(monAn);
            }while (cursor.moveToNext());
        }
        return listMonAn;
    }
    public List<MonAn> listAllMonAnByLoaiMonAn(int loaiMonAn){
        database = createDatabase.getReadableDatabase();
        listMonAn = new ArrayList<>();
        Cursor cursor = database.query(CreateDatabase.TABLE_MONAN,null,CreateDatabase.COLUMN_MONAN_MALOAI + " = ?",new String[]{String.valueOf(loaiMonAn)},null,null,null);
       // Cursor cursor = database.query(CreateDatabase.TABLE_MONAN,null,CreateDatabase.COLUMN_MONAN_MALOAI+" = ? ", new String[]{String.valueOf(loaiMonAn)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_MAMONAN)));
                monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_TENMONAN)));
                monAn.setGiaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_GIAMONAN)));
                monAn.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_MALOAI)));
                if (cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_HINHANH))==null){
                    monAn.setHinhAnhMonAn("null");
                }else{
                    monAn.setHinhAnhMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_MONAN_HINHANH)));
                }

                listMonAn.add(monAn);
            }while (cursor.moveToNext());
        }
        return listMonAn;
    }
}
