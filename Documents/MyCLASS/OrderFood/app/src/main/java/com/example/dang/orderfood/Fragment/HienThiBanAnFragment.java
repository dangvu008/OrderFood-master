package com.example.dang.orderfood.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.dang.orderfood.Adapter.BanAnAdapter;
import com.example.dang.orderfood.DAO.BanAnDAO;
import com.example.dang.orderfood.DTO.BanAn;
import com.example.dang.orderfood.R;
import com.example.dang.orderfood.Utils.SessionLogin;
import com.example.dang.orderfood.ThemBanAnActivity;

import java.util.HashMap;
import java.util.List;


public class HienThiBanAnFragment extends Fragment implements AdapterView.OnItemClickListener{

    public static final int REQUEST_CODE_THEMBANAN = 0001;
    public static final int RESULT_CODE_THANHTOAN_THANHCONG = 0006;
    public static final int RESULT_CODE_SUA_BANAN = 9;
    BanAnDAO banAnDAO;
    List<BanAn> listBanAn;
    BanAnAdapter adapter;
    GridView gridViewBanAn;
    SessionLogin sessionLogin;
    int maQuyen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hien_thi_ban_an, container, false);
        setHasOptionsMenu(true);
        gridViewBanAn = (GridView) view.findViewById(R.id.gridViewBanAn);
        banAnDAO = new BanAnDAO(getActivity());
        loadGridview();
        sessionLogin = new SessionLogin(getActivity());
        HashMap<String,String> nhanvien = sessionLogin.getNhanVienDetail();
        maQuyen = Integer.parseInt(nhanvien.get(SessionLogin.KEY_ROLE));
        gridViewBanAn.setOnItemClickListener(this);
        if (maQuyen==1){
            registerForContextMenu(gridViewBanAn);
        }
        return view;

    }
    public void loadGridview(){
        listBanAn = banAnDAO.listAllBanAn();
        adapter = new BanAnAdapter(R.layout.custom_gridview_hienthibanan,getActivity(),listBanAn);
        gridViewBanAn.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
        int maBan = banAnDAO.listAllBanAn().get(position).getMaBan();
        switch (item.getItemId()){
            case R.id.item_xoa:{
                if (banAnDAO.xoaBanAn(maBan)!=0){
                    Toast.makeText(getActivity(), "Xóa thành công !", Toast.LENGTH_SHORT).show();
                    loadGridview();
                }
            }break;
            case R.id.item_sua :{
                Intent intent = new Intent(getActivity(),ThemBanAnActivity.class);
                intent.putExtra("maBan",maBan);
                startActivityForResult(intent,RESULT_CODE_SUA_BANAN);
            }break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maQuyen==1){
            MenuItem menuItem = menu.add(1,R.id.item_them_banan,1,R.string.thembanan);
            menuItem.setIcon(R.drawable.thembanan);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_THEMBANAN && resultCode== Activity.RESULT_OK){
            loadGridview();
        }else if (resultCode==RESULT_CODE_THANHTOAN_THANHCONG){
            loadGridview();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_them_banan :{
                Intent intent = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEMBANAN);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        int maBan = listBanAn.get(position).getMaBan();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadGridview();
    }
}
