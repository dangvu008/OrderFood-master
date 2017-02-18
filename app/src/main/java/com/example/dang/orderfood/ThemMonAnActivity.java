package com.example.dang.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dang.orderfood.Adapter.LoaiMonAnAdapter;
import com.example.dang.orderfood.DAO.LoaiMonAnDAO;
import com.example.dang.orderfood.DAO.MonAnDAO;
import com.example.dang.orderfood.DTO.LoaiMonAn;
import com.example.dang.orderfood.DTO.MonAn;

import java.util.List;
import java.util.regex.Pattern;

public class ThemMonAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextTenMonAn,editTextGiaMonAn;
    Spinner spinnerLoaiMon;
    ImageView imageViewMonAn;
    Button btnDongY,buttonThoat;
    ImageButton imgButtonThemLoaiMonAn;
    MonAnDAO monAnDAO;
    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAn> listLoaiMonAn;
    String strDuongDanAnh = null;
    public static final int REQUEST_CODE_THEMLOAIMONAN = 0003;
    public static final int REQUEST_CODE_MOHINH = 0004;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_an);
        addWidgets();
        loadToSpinner();
        monAnDAO = new MonAnDAO(this);
        btnDongY.setOnClickListener(this);
        buttonThoat.setOnClickListener(this);
        imgButtonThemLoaiMonAn.setOnClickListener(this);
        imageViewMonAn.setOnClickListener(this);
    }

    private void loadToSpinner() {
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        listLoaiMonAn = loaiMonAnDAO.listAllLoaiMonAn();
        LoaiMonAnAdapter adapter = new LoaiMonAnAdapter(ThemMonAnActivity.this,R.layout.custom_spinner_loaimonan,R.id.textViewTenLoaiMonAn,listLoaiMonAn);
        spinnerLoaiMon.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void addWidgets() {
        editTextTenMonAn = (EditText) findViewById(R.id.editTextTenMonAn);
        editTextGiaMonAn = (EditText) findViewById(R.id.editTextGiaMonAn);
        spinnerLoaiMon = (Spinner) findViewById(R.id.spinnerLoaiMon);
        imageViewMonAn = (ImageView) findViewById(R.id.imageViewMonAn);
        btnDongY = (Button) findViewById(R.id.buttonDongY);
        buttonThoat= (Button) findViewById(R.id.buttonThoat);
        imgButtonThemLoaiMonAn = (ImageButton) findViewById(R.id.imageButtonThemLoaiMon);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_THEMLOAIMONAN && resultCode == Activity.RESULT_OK){
            loadToSpinner();
        }else if (requestCode==REQUEST_CODE_MOHINH ){
            if (resultCode == Activity.RESULT_OK &&data!=null) {
                imageViewMonAn.setImageURI(data.getData());
                strDuongDanAnh= data.getData().toString();
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonThemLoaiMon:{
                Intent intent = new Intent(ThemMonAnActivity.this,ThemLoaiMonAnActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEMLOAIMONAN);
            }
            break;
           case  R.id.imageViewMonAn :{
                Intent intent =  new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent,"Chọn hình ảnh"),REQUEST_CODE_MOHINH);
            }break;
            case R.id.buttonDongY:{
                themMonAn();
            }break;
            case R.id.buttonThoat :
                finish();
                break;
        }
    }

    private void themMonAn() {
        String tenMonAn= editTextTenMonAn.getText().toString();
        String strGiaMonAn = editTextGiaMonAn.getText().toString();
        int position_selected_spinner = spinnerLoaiMon.getSelectedItemPosition();
        int maLoaiMon=listLoaiMonAn.get(position_selected_spinner).getMaLoaiMonAn();
        if (editTextTenMonAn.getText().toString().isEmpty() || editTextGiaMonAn.getText().toString().isEmpty()){
            Toast.makeText(ThemMonAnActivity.this, "điền thông tin", Toast.LENGTH_SHORT).show();
        }else{
            if (!Pattern.matches("[0-9]{0,10}",strGiaMonAn)){
                Toast.makeText(ThemMonAnActivity.this, "Gía là số nguyên dương", Toast.LENGTH_SHORT).show();
            }else{
                int iGia = Integer.parseInt(strGiaMonAn);
                MonAn monan = new MonAn();
                monan.setTenMonAn(tenMonAn);
                monan.setGiaMonAn(iGia);
                monan.setMaLoai(maLoaiMon);
                monan.setHinhAnhMonAn(strDuongDanAnh);

                if (monAnDAO.themMonAn(monan)!=0){
                    Toast.makeText(ThemMonAnActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    setResult(Activity.RESULT_OK);
                }else {
                    Toast.makeText(ThemMonAnActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
