package com.example.dang.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.LoaiMonAnDAO;
import com.example.dang.orderfood.DTO.LoaiMonAn;

public class ThemLoaiMonAnActivity extends AppCompatActivity {

    LoaiMonAnDAO loaiMonAnDAO;
    EditText editTextTenLoaiMonAn;
    Button buttonThemLoaiMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_mon_an);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        editTextTenLoaiMonAn = (EditText) findViewById(R.id.ediTenLoaiMonAn);
        buttonThemLoaiMonAn= (Button) findViewById(R.id.buttonThemLoaiMonAn);
        buttonThemLoaiMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themLoaiMonAn();
            }
        });
    }

    private void themLoaiMonAn() {
        String tenloaiMonAn = editTextTenLoaiMonAn.getText().toString();
        if (tenloaiMonAn!=null){
            LoaiMonAn loaiMonAn= new LoaiMonAn();
            loaiMonAn.setTenLoaiMonAn(tenloaiMonAn);
            if (loaiMonAnDAO.themLoaiMonAn(loaiMonAn)!=0){
                Toast.makeText(ThemLoaiMonAnActivity.this, R.string.themloaiMonAnThanhCong, Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }else{
                Toast.makeText(ThemLoaiMonAnActivity.this, "Thêm loại món ăn thất bại", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ThemLoaiMonAnActivity.this, "Điền tên loại món ăn", Toast.LENGTH_SHORT).show();
        }
    }
}
