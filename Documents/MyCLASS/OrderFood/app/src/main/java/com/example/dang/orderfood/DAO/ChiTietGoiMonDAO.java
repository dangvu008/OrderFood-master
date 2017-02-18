package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dang.orderfood.DTO.ChiTietGoiMon;
import com.example.dang.orderfood.Database.CreateDatabase;

/**
 * Created by DANG on 8/20/2016.
 */
public class ChiTietGoiMonDAO {
    Context context;
    SQLiteDatabase database;
    CreateDatabase createDatabase;

    public ChiTietGoiMonDAO(Context context) {
        this.context = context;
        createDatabase = new CreateDatabase(context);
    }
    public long themChiTietGoiMon(ChiTietGoiMon chiTietGoiMon){
        database =createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maGoiMon",chiTietGoiMon.getMaGoiMon());
        values.put("maMonAn",chiTietGoiMon.getMaMonAn());
        values.put("soLuong",chiTietGoiMon.getSoLuong());
        return database.insert(CreateDatabase.TABLE_CHITIET_GOIMON,null,values);
    }
    public long updateSoLuongMon(ChiTietGoiMon chiTietGoiMon){
        database =createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soLuong",chiTietGoiMon.getSoLuong());
        return database.update(CreateDatabase.TABLE_CHITIET_GOIMON,values,CreateDatabase.COLUMN_GOIMON_MAGOIMON +" = ? AND " +
                                CreateDatabase.COLUMN_CHITETGOIMON_MAMONAN+ " = ? ",
                new String[]{String.valueOf(chiTietGoiMon.getMaGoiMon()), String.valueOf(chiTietGoiMon.getMaMonAn())});
    }
    public int kiemTraChitietGoiMon(int maGoiMon,int maMonAn){
        int soluong  = 0;
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_CHITIET_GOIMON,new String[]{CreateDatabase.COLUMN_CHITETGOIMON_SOLUONG},CreateDatabase.COLUMN_CHITETGOIMON_MAGOIMON +" = ? AND "
                        +CreateDatabase.COLUMN_CHITETGOIMON_MAMONAN + " = ? ",
                new String[]{String.valueOf(maGoiMon), String.valueOf(maMonAn)},null,null,null);
        if (cursor.moveToFirst())
            soluong = cursor.getInt(cursor.getColumnIndex(CreateDatabase.COLUMN_CHITETGOIMON_SOLUONG));
        return soluong ;
    }
}
