package com.example.dang.orderfood;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.BanAnDAO;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText ediTenBanAn;
    Button btnThemBanAn;
    BanAnDAO banAnDAO;
    TextView textViewThemBanAn;
    int maBanAn = 0 ;
    Intent intent = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban_an);
        ediTenBanAn = (EditText) findViewById(R.id.ediTenBanAn);
        btnThemBanAn = (Button) findViewById(R.id.buttonThembanAn);
        textViewThemBanAn = (TextView) findViewById(R.id.textviewThemBan);
        banAnDAO = new BanAnDAO(this);
        intent = getIntent();
        if (intent.getExtras()!=null){
            maBanAn = intent.getIntExtra("maBan",0);
            textViewThemBanAn.setText("Sửa tên bàn ăn");
            btnThemBanAn.setText("sửa");
            String tenBanAn = banAnDAO.getBanAnById(maBanAn).getTenBan();
            ediTenBanAn.setText(tenBanAn);


        }
        btnThemBanAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.buttonThembanAn :
               themBanAn();
               break;
       }
    }

    private void themBanAn() {
        String tenBanAn = ediTenBanAn.getText().toString();
        if (tenBanAn!=null){
            if (intent.getExtras()==null){
                long ketqua = banAnDAO.themBanAn(tenBanAn);
                if (ketqua!=0){
                    Toast.makeText(ThemBanAnActivity.this, R.string.themBanAnThanhCong, Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(ThemBanAnActivity.this, R.string.themBanAnThatBai, Toast.LENGTH_SHORT).show();
                }
            }else{
                if (banAnDAO.updateTenBan(maBanAn,tenBanAn)!=0){
                    Toast.makeText(ThemBanAnActivity.this, "cập nhật thành công", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(ThemBanAnActivity.this,"cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
