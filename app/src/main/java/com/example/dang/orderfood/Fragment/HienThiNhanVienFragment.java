package com.example.dang.orderfood.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dang.orderfood.Adapter.AdapterNhanVien;
import com.example.dang.orderfood.DAO.NhanVienDAO;
import com.example.dang.orderfood.DTO.NhanVien;
import com.example.dang.orderfood.R;
import com.example.dang.orderfood.RegisterActivity;

import java.util.List;

/**
 * Created by DANG on 8/23/2016.
 */
public class HienThiNhanVienFragment extends Fragment {

    List<NhanVien> listNhanVien;
    NhanVienDAO nhanVienDAO;
    Context context;
    AdapterNhanVien adapterNhanVien;
    ListView listViewNhanVien;
    public static final int REQUEST_CODE_THEM_NHANVIEN = 0007;
    public static final int REQUEST_CODE_SUA_NHANVIEN = 8;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hien_thi_nhanvien,container,false);
        listViewNhanVien = (ListView) view.findViewById(R.id.listViewNhanVien);
        nhanVienDAO = new NhanVienDAO(getActivity());
        setHasOptionsMenu(true);
        loadDanhSachNhanVien();
        registerForContextMenu(listViewNhanVien);
        return view;
    }

    private void loadDanhSachNhanVien() {
        listNhanVien = nhanVienDAO.listNhanVien();
        adapterNhanVien = new AdapterNhanVien(getActivity(),R.layout.custom_listview_nhanvien,listNhanVien);
        listViewNhanVien.setAdapter(adapterNhanVien);
        adapterNhanVien.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_nhanvien,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        int maNhanVien = nhanVienDAO.listNhanVien().get(position).getMaNhanVien();
        switch (item.getItemId()){
            case R.id.item_xoa:{
                if (nhanVienDAO.xoaNhanVien(maNhanVien)>0){
                    Toast.makeText(getActivity(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    loadDanhSachNhanVien();
                }else {
                    Toast.makeText(getActivity(), "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }break;
            case R.id.item_sua:{
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                intent.putExtra("maNhanVien",maNhanVien);
                startActivity(intent);
            }break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.add(1,R.id.item_them_nhanvien,1,"Thêm nhân viên");
        menuItem.setIcon(R.drawable.nhanvien);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_them_nhanvien){
            Intent intent = new Intent(getActivity(), RegisterActivity.class);
            startActivityForResult(intent,REQUEST_CODE_THEM_NHANVIEN);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK &&REQUEST_CODE_THEM_NHANVIEN==requestCode){
            loadDanhSachNhanVien();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDanhSachNhanVien();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadDanhSachNhanVien();
    }
}
