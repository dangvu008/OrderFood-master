package com.example.dang.orderfood;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.NhanVienDAO;
import com.example.dang.orderfood.DAO.QuyenDAO;
import com.example.dang.orderfood.DTO.NhanVien;
import com.example.dang.orderfood.Utils.SessionLogin;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class LoginActivity extends AppCompatActivity {

    EditText editTenDangNhap,editMatKhau;
    TextInputLayout validate_login_tendangnhap,validate_login_matkhau;
    Button button_login_DangNhap,button_login_dangki;
    SessionLogin session;
    NhanVienDAO nhanVienDAO ;

    QuyenDAO quyenDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addWidgets();
        nhanVienDAO = new NhanVienDAO(this);
        quyenDAO = new QuyenDAO(this);
        overridePendingTransition(R.anim.anim_hieuung_x_vao,R.anim.ani_hieuung_x_ra);
        if (quyenDAO.listQuyen().size()==0){
            quyenDAO.themQuyen("Quản lý");
            quyenDAO.themQuyen("nhân viên");
        }
        if (nhanVienDAO.listNhanVien().size()==0){
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
        session = new SessionLogin(getApplicationContext());

        button_login_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {
        if (!validateFormTenDangNhap()|| !validateFormMatKhau()){
            return;
        }else{
            String tendangnhap = editTenDangNhap.getText().toString();
            String matkhau= editMatKhau.getText().toString();
            NhanVienDAO nhanVienDAO = new NhanVienDAO(this);
            NhanVien nhanVien = nhanVienDAO.dangNhap(tendangnhap);

            try {
                if (matkhau.equals(AESCrypt.decrypt("order",nhanVien.getMatkhau()))){
                    Toast.makeText(LoginActivity.this, R.string.dangnhapthanhcong, Toast.LENGTH_SHORT).show();
                    session.createLogin(nhanVien.getTenNhanVien(), String.valueOf(nhanVien.getMaNhanVien()),nhanVien.getMaQuyen());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this, R.string.dangnhapthatbai, Toast.LENGTH_SHORT).show();
                }
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public void  addWidgets(){
       editTenDangNhap = (EditText) findViewById(R.id.edit_login_TenDangNhap);
       editMatKhau = (EditText) findViewById(R.id.edit_login_MatKhau);
       validate_login_tendangnhap = (TextInputLayout) findViewById(R.id.valid_login_tenDangNhap);
       validate_login_matkhau= (TextInputLayout) findViewById(R.id.valid_login_matkhau);
        button_login_DangNhap= (Button) findViewById(R.id.button_login_DangNhap);

    }
    private boolean validateFormTenDangNhap(){
        String tendangnhap = editTenDangNhap.getText().toString();
        if (tendangnhap.isEmpty()){
            validate_login_tendangnhap.setError(getResources().getString(R.string.valid_tendangnhap));
            editTenDangNhap.requestFocus();
            return false;
        }else{
            validate_login_tendangnhap.setErrorEnabled(false);

        }
        return true;

    }
    private boolean validateFormMatKhau(){
        String matkhau= editMatKhau.getText().toString();

        if (matkhau.isEmpty()){
            validate_login_matkhau.setError(getResources().getString(R.string.valid_matkhau));
            editMatKhau.requestFocus();
            return  false;
        }else{
            validate_login_matkhau.setErrorEnabled(false);

        }
        return true;

    }
    private class  LoginTextWatcher implements TextWatcher{

        private View view;

        public LoginTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.edit_login_TenDangNhap :
                    validateFormTenDangNhap();
                    break;
                case  R.id.edit_login_MatKhau :
                    validateFormMatKhau();
                    break;
            }
        }
    }
}
