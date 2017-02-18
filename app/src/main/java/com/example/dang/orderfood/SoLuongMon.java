package com.example.dang.orderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.ChiTietGoiMonDAO;
import com.example.dang.orderfood.DAO.GoiMonDAO;
import com.example.dang.orderfood.DTO.ChiTietGoiMon;
import com.example.dang.orderfood.DTO.GoiMon;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SoLuongMon extends AppCompatActivity implements View.OnClickListener{

    Button buttonThem,buttonThoat;
    EditText editSoLuong;
    GoiMonDAO goiMonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_luong_mon);
        buttonThem = (Button) findViewById(R.id.buttonThemSoLuongMon);
        buttonThoat = (Button) findViewById(R.id.buttonThoatSoLuongMon);
        editSoLuong = (EditText) findViewById(R.id.editSoLuongMon);
        goiMonDAO = new GoiMonDAO(this);
        buttonThem.setOnClickListener(this);
        buttonThoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonThemSoLuongMon:{
                themMon();
            }break;
            case R.id.buttonThoatSoLuongMon:
                finish();
                break;
        }
    }

    private void themMon() {
        ChiTietGoiMonDAO chitietMonAnDao = new ChiTietGoiMonDAO(this);
        ChiTietGoiMon chiTietGoiMon;
        Intent intent = getIntent();
        Bundle bundleGoiMon = intent.getBundleExtra("bundleGoiMon");
        int maBan = bundleGoiMon.getInt("maBan");
        int maNhanVien = bundleGoiMon.getInt("maNhanVien");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        String time = sdf.format(calendar.getTime());
        int maMonAn = bundleGoiMon.getInt("maMonAn");

        String strSoLuong = editSoLuong.getText().toString();
        if (strSoLuong==""){
            Toast.makeText(SoLuongMon.this, "dien so luong", Toast.LENGTH_SHORT).show();
        }else{
            int soluong = Integer.parseInt(strSoLuong);
            GoiMon kiemTraGoiMon = goiMonDAO.kiemTraGoiMon(maBan,"false");
            if (kiemTraGoiMon.getNgayGoi()!=null){
                int kiemTraSoLuong = chitietMonAnDao.kiemTraChitietGoiMon(kiemTraGoiMon.getMaGoiMon(),maMonAn);
                if (kiemTraSoLuong!=0){
                    int soluongthem = kiemTraSoLuong +soluong;
                    chiTietGoiMon = new ChiTietGoiMon(kiemTraGoiMon.getMaGoiMon(),maMonAn,soluongthem);
                    if (chitietMonAnDao.updateSoLuongMon(chiTietGoiMon)>0)
                        Toast.makeText(SoLuongMon.this, "Thêm món thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SoLuongMon.this, "Thêm món thất bại", Toast.LENGTH_SHORT).show();
                }else{
                    chiTietGoiMon = new ChiTietGoiMon(kiemTraGoiMon.getMaGoiMon(),maMonAn,soluong);
                    if (chitietMonAnDao.themChiTietGoiMon(chiTietGoiMon)>0)
                        Toast.makeText(SoLuongMon.this, "Thêm món thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SoLuongMon.this, "Thêm món thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                GoiMon goiMon = new GoiMon();
                goiMon.setMaNhanVien(maNhanVien);
                goiMon.setMaBan(maBan);
                goiMon.setNgayGoi(time);
                goiMon.setTinhTrang("false");
                int iThemGoiMon = (int) goiMonDAO.themGoiMon(goiMon);
                if (iThemGoiMon>0){
                    chiTietGoiMon = new ChiTietGoiMon(iThemGoiMon,maMonAn,soluong);
                    if (chitietMonAnDao.themChiTietGoiMon(chiTietGoiMon)>0)
                        Toast.makeText(SoLuongMon.this, "Thêm món thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SoLuongMon.this, "Thêm món thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        }
        finish();


    }
}
