package com.example.dang.orderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dang.orderfood.Adapter.AdapterHienThiThanhToan;
import com.example.dang.orderfood.DAO.BanAnDAO;
import com.example.dang.orderfood.DAO.GoiMonDAO;
import com.example.dang.orderfood.DTO.ThanhToan;
import com.example.dang.orderfood.Fragment.HienThiBanAnFragment;

import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener{

    GridView gridViewThanhToan;
    Button buttonThanhToan,button_thanhtoan_thoat;
    TextView textViewTongTien;
    GoiMonDAO goiMonDAO;
    List<ThanhToan> listThanhToan;
    AdapterHienThiThanhToan adapter;
    int maGoiMon,maBan;
    BanAnDAO banAnDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        gridViewThanhToan = (GridView) findViewById(R.id.gridViewThanhToan);
        buttonThanhToan = (Button) findViewById(R.id.button_thanhToan);
        button_thanhtoan_thoat = (Button) findViewById(R.id.button_thanhtoan_thoat);
        textViewTongTien = (TextView) findViewById(R.id.textViewTongTien);
        goiMonDAO = new GoiMonDAO(this);
         banAnDAO= new BanAnDAO(this);
        Intent intent = getIntent();
        maGoiMon = intent.getIntExtra("maGoiMon",0);
        maBan = intent.getIntExtra("maBan",0);
        loadThanhtoan();
        button_thanhtoan_thoat.setOnClickListener(this);
        buttonThanhToan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_thanhToan:{
                thanhtoan();
            }break;
            case R.id.button_thanhtoan_thoat:{
                finish();
            }break;
        }
    }

    private void thanhtoan() {
        if (goiMonDAO.updatetinhTrangGoiMon(maGoiMon,"true")>0) {
            banAnDAO.updateTrangThaiBanAn(maBan, "false");

            Toast.makeText(ThanhToanActivity.this, "Thanh toan thanh cong", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void loadThanhtoan() {


        listThanhToan =goiMonDAO.listThanhToanGoiMon(maGoiMon);
        adapter = new AdapterHienThiThanhToan(this,R.layout.custom_gridview_thanhtoan,listThanhToan);
        gridViewThanhToan.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        String strTongTien= "tổng tiền : "+ String.valueOf(tongTien());
        textViewTongTien.setText(strTongTien);


    }
    private int tongTien(){
        int tongtien =0 ;
        for (int i = 0; i < listThanhToan.size(); i++) {
            tongtien += (listThanhToan.get(i).getGiatien()*listThanhToan.get(i).getSoluong());
        }
        return tongtien;
    }
}
