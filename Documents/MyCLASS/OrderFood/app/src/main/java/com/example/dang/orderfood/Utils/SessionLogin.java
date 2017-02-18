package com.example.dang.orderfood.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.dang.orderfood.LoginActivity;

import java.util.HashMap;

/**
 * Created by DANG on 8/16/2016.
 */
public class SessionLogin {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    public static final String PREF_NAME = "OrderPref";
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "TenNhanVien";
    public static final String KEY_ID = "MaNhanVien";
    public static final String KEY_ROLE = "Quyen";
    public SessionLogin(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLogin(String tenNhanVien,String maNhanVien,int quyen){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_NAME,tenNhanVien);
        editor.putString(KEY_ID,maNhanVien);
        editor.putInt(KEY_ROLE,quyen);
        editor.commit();
    }
    public void checkLogin(){
        if (!this.isLoggedIn()){
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(_context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
    public HashMap<String,String> getNhanVienDetail(){
        HashMap<String,String> nhanvien = new HashMap<>();
        nhanvien.put(KEY_ID,pref.getString(KEY_ID,""));
        nhanvien.put(KEY_NAME,pref.getString(KEY_NAME,""));
        nhanvien.put(KEY_ROLE, String.valueOf(pref.getInt(KEY_ROLE,0)));
        return nhanvien;

    }
    private boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN,false);
    }
}
