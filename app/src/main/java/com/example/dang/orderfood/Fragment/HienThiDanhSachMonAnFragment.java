package com.example.dang.orderfood.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.dang.orderfood.Adapter.MonAnAdapter;
import com.example.dang.orderfood.DAO.MonAnDAO;
import com.example.dang.orderfood.DTO.MonAn;
import com.example.dang.orderfood.R;
import com.example.dang.orderfood.Utils.SessionLogin;
import com.example.dang.orderfood.SoLuongMon;

import java.util.HashMap;
import java.util.List;

/**
 * Created by DANG on 8/16/2016.
 */
public class HienThiDanhSachMonAnFragment extends Fragment implements AdapterView.OnItemClickListener{
    MonAnDAO monAnDAO;
    List<MonAn> listMonAn;
    MonAnAdapter adapter;
    GridView gridViewMonAn;
    Bundle bundle;
    SessionLogin sessionLogin;
    int maQuyen;
    public static final int REQUEST_DATSOLUONGMON = 0005;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hienthi_monan,container,false);
        gridViewMonAn = (GridView) view.findViewById(R.id.gridViewMonAn);
        bundle = getArguments();
        loadFragmentDanhsachMonAn(bundle);
        gridViewMonAn.setOnItemClickListener(this);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()== KeyEvent.ACTION_DOWN){
                    Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        return view;
    }

    private void loadFragmentDanhsachMonAn(Bundle data) {
        monAnDAO = new MonAnDAO(getActivity());
        listMonAn= monAnDAO.listAllMonAnByLoaiMonAn(data.getInt("maLoaiMonAn"));
        adapter = new MonAnAdapter(getActivity(),R.layout.custom_gridview_monan,listMonAn);
        gridViewMonAn.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (String.valueOf(bundle.getInt("maBan"))==null){
            return;
        }else{
            sessionLogin = new SessionLogin(getActivity());
            HashMap<String,String> nhanvien = sessionLogin.getNhanVienDetail();
            int maNhanVien = Integer.parseInt(nhanvien.get(SessionLogin.KEY_ID));
            int maMonAn = listMonAn.get(position).getMaMonAn();
            bundle.putInt("maNhanVien",maNhanVien);
            bundle.putInt("maMonAn",maMonAn);
            Intent intent = new Intent(getActivity(), SoLuongMon.class);
            intent.putExtra("bundleGoiMon",bundle);
            startActivity(intent);

        }
    }
}
