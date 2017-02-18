package com.example.dang.orderfood;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.NhanVienDAO;
import com.example.dang.orderfood.DAO.QuyenDAO;
import com.example.dang.orderfood.DTO.NhanVien;
import com.example.dang.orderfood.Fragment.DialogFragmentDatePicker;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTenNhanVien, editTenDangNhap,editMatKhau,editNgaySinh,editCmnd;
    Button buttonDongY,buttonThoat;
    RadioGroup rbGroupSex;
    RadioButton rbNam,rbNu;
    TextInputLayout validTenNhanVien,validTenDangNhap,validMatkhau,valiNgaySinh,validCmnd;
    TextView textViewDangKi;
    Spinner spinnerQuyen;
    RelativeLayout layoutRegister;
    String gioitinh;
    NhanVienDAO nhanVienDAO;
    List<String> listQuyen;
    Animation animation;
    ArrayAdapter<String> adapterSpinner;
    QuyenDAO quyenDAO;
    int maNhanVien = 0;
    String tennhanVien = "";
    NhanVien nhanvienEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWidgets();
        nhanVienDAO = new NhanVienDAO(this);
        quyenDAO = new QuyenDAO(this);
        Intent intent = getIntent();
       if (intent.getExtras()!=null){
           maNhanVien = intent.getIntExtra("maNhanVien",0);
           nhanvienEdit = nhanVienDAO.getNhanVienByMa(maNhanVien);
           tennhanVien = nhanvienEdit.getTenNhanVien();
           if(tennhanVien!=""){
               loadData();
               editTenDangNhap.setEnabled(false);
               textViewDangKi.setText("Cập nhật");
           }
       }

        listQuyen = quyenDAO.listQuyen();
        adapterSpinner =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listQuyen);
        spinnerQuyen.setAdapter(adapterSpinner);
        if (nhanvienEdit.getMaQuyen()==1){
            spinnerQuyen.setSelection(0);
        }else{
            spinnerQuyen.setSelection(1);
        }
        buttonDongY.setOnClickListener(this);
        buttonThoat.setOnClickListener(this);
        editTenNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTenNhanVien.setHint("");
            }
        });

    }

    private void loadData() {

        editTenNhanVien.setText(nhanvienEdit.getTenNhanVien());
        editTenDangNhap.setText(nhanvienEdit.getTenDangNhap());
        editMatKhau.setText(String.valueOf(nhanvienEdit.getMatkhau()));
        if (nhanvienEdit.getGioiTinh().equals("Nam")){
            rbNam.setChecked(true);
        }else{
            rbNu.setChecked(true);
        }


        editNgaySinh.setText(nhanvienEdit.getNgaySinh());
        editCmnd.setText(String.valueOf(nhanvienEdit.getCmnd()));
    }

    public void getWidgets(){
        editTenNhanVien = (EditText) findViewById(R.id.editTenNhanVien);
        editTenDangNhap = (EditText) findViewById(R.id.editTenDangNhap);
        editMatKhau = (EditText) findViewById(R.id.editMatKhau);
        editNgaySinh = (EditText) findViewById(R.id.editNgaySinh);
        editCmnd = (EditText) findViewById(R.id.editCmnd);
        buttonDongY = (Button) findViewById(R.id.buttonDongY);
        buttonThoat = (Button) findViewById(R.id.buttonThoat);
        rbGroupSex = (RadioGroup) findViewById(R.id.rbGroupSex);
        rbNam = (RadioButton) findViewById(R.id.radioButtonMale);
        rbNu = (RadioButton) findViewById(R.id.radioButtonFemale);
        validTenNhanVien = (TextInputLayout) findViewById(R.id.validTenNhanVien);
        validTenDangNhap = (TextInputLayout) findViewById(R.id.validTenDangNhap);
        validMatkhau = (TextInputLayout) findViewById(R.id.validMatKhau);
        valiNgaySinh = (TextInputLayout) findViewById(R.id.validNgaySinh);
        validCmnd = (TextInputLayout) findViewById(R.id.validCmnd);
        textViewDangKi = (TextView) findViewById(R.id.textViewDangKi);
        spinnerQuyen = (Spinner) findViewById(R.id.spinnerQuyen);
        layoutRegister = (RelativeLayout) findViewById(R.id.layoutRegister);
        editTenNhanVien.addTextChangedListener(new RegisterFormTextWatcher(editTenNhanVien));
        editTenDangNhap.addTextChangedListener(new RegisterFormTextWatcher(editTenDangNhap));
        editNgaySinh.addTextChangedListener(new RegisterFormTextWatcher(editNgaySinh));
        editMatKhau.addTextChangedListener(new RegisterFormTextWatcher(editMatKhau));
        editCmnd.addTextChangedListener(new RegisterFormTextWatcher(editCmnd));
        editNgaySinh.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    DialogFragmentDatePicker fragment = new DialogFragmentDatePicker();
                    fragment.show(getFragmentManager(),"Năm sinh");
                }
            }
        });
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonDongY :
                dangKyNhanVien();
                break;
            case R.id.buttonThoat:
                finish();
                break;
        }
    }
    private boolean validateTenNhanVien(String tenNhanVien){
        if (tenNhanVien.isEmpty()){
            validTenNhanVien.setError(getString(R.string.valid_nhanvien));
            editTenNhanVien.requestFocus();
            return false;
        }else{
            validTenNhanVien.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateTenDangNhap(String tenDangNhap){
        if (tenDangNhap.isEmpty()){
            validTenDangNhap.setError(getString(R.string.valid_tendangnhap));
            editTenDangNhap.requestFocus();
            return false;
        }else if (nhanvienEdit ==null && nhanVienDAO.kiemTraTonTaiTenDangNhap(tenDangNhap)){
            validTenDangNhap.setError("tên đăng nhập đã tồn tại");
            editTenDangNhap.requestFocus();
            return false;
        }else{
            validTenDangNhap.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMatkhau(String matkhau){
        if (matkhau.isEmpty()){
            validMatkhau.setError(getString(R.string.valid_matkhau));
            editMatKhau.requestFocus();
            return false;
        }else{
            validMatkhau.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateNgaySinh(String ngaysinh){
        if (ngaysinh.isEmpty()){
            valiNgaySinh.setError(getString(R.string.valid_ngaysinh));
            editNgaySinh.requestFocus();
            return false;
        }/*else if(!ngaysinh.matches("^((0?[13578]|10|12)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1}))|(0?[2469]|11)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1})))$"))
        {
            valiNgaySinh.setError(getString(R.string.validate_ngaysinh_dinhdang));
            editNgaySinh.requestFocus();
            return false;
        }*/else{
            valiNgaySinh.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateCmnd(String cmnd){
        if (cmnd.isEmpty()){
            validCmnd.setError(getString(R.string.cmnd));
            editCmnd.requestFocus();
            return false;
        }
        else if (cmnd.matches("^[0-9]$")){
            validCmnd.setError(getString(R.string.validate_cmnd_dinhdang));
            editCmnd.requestFocus();
            return false;
        }
        else if (nhanvienEdit==null && nhanVienDAO.kiemTraTonTaiCmnd(Integer.parseInt(cmnd))){
            validCmnd.setError("số chứng minh thư đã tồn tại");
            editCmnd.requestFocus();
            return false;
        }else{
            validCmnd.setErrorEnabled(false);
        }
        return true;
    }
    private void dangKyNhanVien() {

        if (rbGroupSex.getCheckedRadioButtonId()== R.id.radioButtonMale) {
            gioitinh = "Nam";
        }
        else if (rbGroupSex.getCheckedRadioButtonId()==R.id.radioButtonFemale) {
            gioitinh = "Nữ";
        }else{
            Toast.makeText(RegisterActivity.this, R.string.toast_gioitinh, Toast.LENGTH_SHORT).show();
        }

            String tenNhanVien = editTenNhanVien.getText().toString();
            String tenDangNhap = editTenDangNhap.getText().toString();
        String matKhau = null;
        try {
            matKhau = AESCrypt.encrypt("order",editMatKhau.getText().toString());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        String ngaySinh = editNgaySinh.getText().toString();
            String cmnd = editCmnd.getText().toString();
            if (!validateTenNhanVien(tenNhanVien)){
                return;
            }
            if (!validateTenDangNhap(tenDangNhap)){
                    return;
            }
            if (!validateMatkhau(matKhau)){
                return;
             }
            if (!validateNgaySinh(ngaySinh)){
               return;
            }
            if (!validateCmnd(cmnd)){
                    return;
             }
            else{
                int iCmnd = Integer.parseInt(cmnd);
               NhanVien nhanvien = new NhanVien();
                nhanvien.setTenNhanVien(tenNhanVien);
                nhanvien.setTenDangNhap(tenDangNhap);
                nhanvien.setMatkhau(matKhau);
                nhanvien.setGioiTinh(gioitinh);
                nhanvien.setNgaySinh(ngaySinh);
                nhanvien.setCmnd(iCmnd);
                nhanvien.setMaQuyen((spinnerQuyen.getSelectedItemPosition()+1));
              if (maNhanVien !=0){
                  nhanvien.setMaNhanVien(maNhanVien);
                  if (nhanVienDAO.updateNhanVien(nhanvien)>0){
                      Toast.makeText(RegisterActivity.this, "update Thành Công", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                      startActivity(intent);

                  }else {
                  Toast.makeText(RegisterActivity.this, "update Thất bại", Toast.LENGTH_SHORT).show();
                }
              }else{
                  if (nhanVienDAO.themNhanVien(nhanvien)>0) {
                      Toast.makeText(RegisterActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                      startActivity(intent);

                  }else{
                      Toast.makeText(RegisterActivity.this, "Thêm Thất bại", Toast.LENGTH_SHORT).show();
                  }
              }
            }



    }



    private class RegisterFormTextWatcher implements TextWatcher{

        private View view;

        public RegisterFormTextWatcher(View view) {
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
                case R.id.editTenDangNhap :
                    validateTenDangNhap(editTenDangNhap.getText().toString());
                    break;
                case R.id.editTenNhanVien :
                    validateTenNhanVien(editTenNhanVien.getText().toString());
                    break;
                case R.id.editMatKhau :
                    validateMatkhau(editMatKhau.getText().toString());
                    break;
                case R.id.editNgaySinh :
                    validateNgaySinh(editNgaySinh.getText().toString());
                    break;
                case R.id.editCmnd :
                    validateCmnd(editCmnd.getText().toString());
                    break;
            }
        }
    }
}

