package com.example.dang.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dang.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANG on 8/31/2016.
 */
public class QuyenDAO {
    Context context;
    SQLiteDatabase database ;
    CreateDatabase createDatabase;

    public QuyenDAO(Context context) {
        this.context = context;
        createDatabase = new CreateDatabase(context);
    }
    public long themQuyen(String tenQuyen){
        database = createDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COLUMN_PHANQUYEN_TENQUYEN,tenQuyen);
        return database.insert(CreateDatabase.TABLE_PHANQUYEN,null,values);
    }
    public List<String> listQuyen(){
        List<String> list = new ArrayList<>();
        database = createDatabase.getReadableDatabase();
        Cursor cursor = database.query(CreateDatabase.TABLE_PHANQUYEN,new String[]{CreateDatabase.COLUMN_PHANQUYEN_TENQUYEN},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String tenQuyen = cursor.getString(cursor.getColumnIndex(CreateDatabase.COLUMN_PHANQUYEN_TENQUYEN));
                list.add(tenQuyen);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }
}
