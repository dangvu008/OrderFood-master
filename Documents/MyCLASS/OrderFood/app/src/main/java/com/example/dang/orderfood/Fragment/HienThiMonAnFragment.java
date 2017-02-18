package com.example.dang.orderfood.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dang.orderfood.Adapter.HienThiLoaiMonAnAdapter;
import com.example.dang.orderfood.Adapter.LoaiMonAnAdapter;
import com.example.dang.orderfood.Adapter.MonAnAdapter;
import com.example.dang.orderfood.DAO.LoaiMonAnDAO;
import com.example.dang.orderfood.DAO.MonAnDAO;
import com.example.dang.orderfood.DTO.LoaiMonAn;
import com.example.dang.orderfood.DTO.MonAn;
import com.example.dang.orderfood.MainActivity;
import com.example.dang.orderfood.R;
import com.example.dang.orderfood.ThemMonAnActivity;
import com.example.dang.orderfood.Utils.SessionLogin;

import java.util.HashMap;
import java.util.List;

/**
 * Created by DANG on 8/11/2016.
 */
public class HienThiMonAnFragment extends Fragment implements AdapterView.OnItemClickListener{
    GridView gridViewLoaiMonAn;
    MonAnAdapter monAnAdapter;
    HienThiLoaiMonAnAdapter hienThiLoaiMonAnAdapter;
    List<LoaiMonAn> listLoaiMonAn;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;
    Bundle goiMonBundle;
    public static final int REQUEST_CODE_THEMMON= 0002;
    private SessionLogin sessionLogin;
    int maQuyen ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hienthi_monan,container,false);
        setHasOptionsMenu(true);
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        gridViewLoaiMonAn = (GridView) view.findViewById(R.id.gridViewMonAn);
        gridViewLoaiMonAn.setOnItemClickListener(this);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Thực đơn");
       // loadListMonAn();
        loadLoaiMonAn();
        sessionLogin = new SessionLogin(getActivity());
        HashMap<String,String> nhanvien = sessionLogin.getNhanVienDetail();
        maQuyen = Integer.parseInt(nhanvien.get(SessionLogin.KEY_ROLE));

        return view;
    }


    public void loadLoaiMonAn(){
        listLoaiMonAn = loaiMonAnDAO.listAllLoaiMonAnCoHinh();
        hienThiLoaiMonAnAdapter = new HienThiLoaiMonAnAdapter(getActivity(),R.layout.custom_layout_hienthi_loaimonan,listLoaiMonAn);
        gridViewLoaiMonAn.setAdapter(hienThiLoaiMonAnAdapter);
        hienThiLoaiMonAnAdapter.notifyDataSetChanged();

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maQuyen==1){
            MenuItem menuItem = menu.add(1,R.id.item_them_monan,1, R.string.themMonAn);
            menuItem.setIcon(R.drawable.logodangnhap);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK)
            loadLoaiMonAn();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_them_monan:{
                Intent intent = new Intent(getActivity(), ThemMonAnActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEMMON);
            }break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        int maLoaiMonAn = listLoaiMonAn.get(position).getMaLoaiMonAn();
        goiMonBundle = getArguments();
        if (goiMonBundle==null){
            goiMonBundle = new Bundle();
            goiMonBundle.putInt("maLoaiMonAn",maLoaiMonAn);
            loadFragmentDanhSachMonAn(position,goiMonBundle);
        }else{
            goiMonBundle.putInt("maLoaiMonAn",maLoaiMonAn);
            loadFragmentDanhSachMonAn(position,goiMonBundle);
        }

    }

    private void loadFragmentDanhSachMonAn(int position,Bundle bundle) {
        fragmentManager = getActivity().getSupportFragmentManager();
        HienThiDanhSachMonAnFragment danhSachMonAnFragment = new HienThiDanhSachMonAnFragment();
        danhSachMonAnFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_content,danhSachMonAnFragment).addToBackStack("hienthiloai");
        transaction.commit();
    }

}
