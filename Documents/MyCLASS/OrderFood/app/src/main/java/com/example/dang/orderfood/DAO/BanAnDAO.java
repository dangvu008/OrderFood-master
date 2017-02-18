package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dang.orderfood.DTO.BanAn;
import com.example.dang.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 8/9/2016.
 */
public class BanAnDAO {
    SQLiteDatabase database;
    CreateDatabase createDatabase;
    List<BanAn> listBanAn;
    public BanAnDAO(Context context) {
        createDatabase = new CreateDatabase(context);
    }
    public long themBanAn(String banan){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenBan",banan);
        values.put("TinhTrang","false");
        return  database.insert(CreateDatabase.TABLE_BANAN,CreateDatabase.COLUMN_BANAN_TENBAN,values);

    }
    public long xoaBanAn(int maBan){
        database = createDatabase.getWritableDatabase();
        return  database.delete(CreateDatabase.TABLE_BANAN,CreateDatabase.COLUMN_BANAN_MABAN +" = ?",new String[]{String.valueOf(maBan)});

    }
    public long updateTrangThaiBanAn(int maBanAn,String tinhTrang){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TinhTrang",tinhTrang);
        return  database.update(CreateDatabase.TABLE_BANAN,values,CreateDatabase.COLUMN_BANAN_MABAN + " = ?",new String[]{String.valueOf(maBanAn)});

    }
    public long updateTenBan(int maBanAn,String tenBan){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenBan",tenBan);
        return  database.update(CreateDatabase.TABLE_BANAN,values,CreateDatabase.COLUMN_BANAN_MABAN + " = ?",new String[]{String.valueOf(maBanAn)});

    }
    public List<BanAn> listAllBanAn(){
        database = createDatabase.getReadableDatabase();
        listBanAn = new ArrayList<>();
        Cursor cursor = database.query(CreateDatabase.TABLE_BANAN,new String[]{CreateDatabase.COLUMN_BANAN_MABAN,CreateDatabase.COLUMN_BANAN_TENBAN,CreateDatabase.COLUMN_BANAN_TINHTRANG},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                BanAn banAn = cursorToBanAn(cursor);
                banAn.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_MABAN)));
                banAn.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TENBAN)));
                banAn.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TINHTRANG)));
                listBanAn.add(banAn);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return listBanAn;
    }
    public String getTrangThaiBanAnById(int id){
        database = createDatabase.getReadableDatabase();
        String tinhTrang = "";
        Cursor cursor = database.query(CreateDatabase.TABLE_BANAN,new String[]{CreateDatabase.COLUMN_BANAN_TINHTRANG}
                , "MaBan = ?",new String[]{String.valueOf(id)},null,null,null);
        if (cursor.moveToFirst()){
            if (cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TINHTRANG))==null){
                tinhTrang = "false";
            }else{
                tinhTrang = cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TINHTRANG));
            }
            cursor.close();
        }
        return tinhTrang;
    }
    public BanAn getBanAnById(int id){
        database = createDatabase.getReadableDatabase();
        BanAn banAn = null;
        Cursor cursor = database.query(CreateDatabase.TABLE_BANAN,null,"MaBan = ?",new String[]{String.valueOf(id)},null,null,null);
        if (cursor.moveToFirst()){
            banAn = new BanAn();
            banAn.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TENBAN)));
            if (cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TINHTRANG))==null){
                banAn.setTinhTrang("false");
            }else{
               banAn.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TINHTRANG)));
            }
            cursor.close();
        }
        return banAn;
    }
    public BanAn cursorToBanAn(Cursor cursor){
        BanAn banAn = new BanAn();
        banAn.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_MABAN)));
        banAn.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TENBAN)));
        banAn.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_BANAN_TINHTRANG)));
        return banAn;
    }

}
