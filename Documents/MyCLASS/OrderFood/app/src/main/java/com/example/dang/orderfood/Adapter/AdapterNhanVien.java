package com.example.dang.orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dang.orderfood.DAO.NhanVienDAO;
import com.example.dang.orderfood.DTO.NhanVien;
import com.example.dang.orderfood.R;

import java.util.List;

/**
 * Created by DANG on 8/23/2016.
 */
public class AdapterNhanVien extends BaseAdapter{
    Context context;
    int layoutId ;
    List<NhanVien> listNhanVien;
    NhanVienDAO nhanVienDAO;
    NhanVienViewHolder viewHolder;
    public AdapterNhanVien(Context context, int layoutId, List<NhanVien> listNhanVien) {
        this.context = context;
        this.layoutId = layoutId;
        this.listNhanVien = listNhanVien;
        nhanVienDAO = new NhanVienDAO(context);
    }

    @Override
    public int getCount() {
        return listNhanVien.size();
    }

    @Override
    public Object getItem(int i) {
        return listNhanVien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        if (view==null){
            viewHolder = new NhanVienViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,viewGroup,false);
            viewHolder.textViewtenNhanVien = (TextView) view.findViewById(R.id.textviewTenNhanVien);
            viewHolder.textViewCmnd = (TextView) view.findViewById(R.id.textViewCmnd);
            view.setTag(viewHolder);
        }else{
            viewHolder = (NhanVienViewHolder) view.getTag();
        }
        NhanVien nhanVien = listNhanVien.get(position);
        viewHolder.textViewtenNhanVien.setText(nhanVien.getTenNhanVien());
        viewHolder.textViewCmnd.setText(String.valueOf(nhanVien.getCmnd()));
        return view;
    }
    public class NhanVienViewHolder{
        TextView textViewtenNhanVien,textViewCmnd;
        ImageView imageViewHinhNhanVien;
    }
}
