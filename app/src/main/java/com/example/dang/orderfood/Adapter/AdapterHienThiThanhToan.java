package com.example.dang.orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dang.orderfood.DTO.GoiMon;
import com.example.dang.orderfood.DTO.ThanhToan;
import com.example.dang.orderfood.R;

import java.util.List;

/**
 * Created by DANG on 8/21/2016.
 */
public class AdapterHienThiThanhToan extends BaseAdapter {
    int layoutId;
    Context context;
    List<ThanhToan> listThanhToan;
    ThanhToanViewHolder viewHolder;
    public AdapterHienThiThanhToan(Context context,int resource,List<ThanhToan> listThanhToan) {
        this.context = context;
        this.layoutId=resource;
        this.listThanhToan=listThanhToan;
    }

    @Override
    public int getCount() {
        return listThanhToan.size();
    }

    @Override
    public Object getItem(int i) {
        return listThanhToan.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        if (view==null){
            viewHolder = new ThanhToanViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,viewGroup,false);
            viewHolder.textViewTenMonAn = (TextView) view.findViewById(R.id.txtTenMonAnThanhToan);
            viewHolder.textViewSoLuong = (TextView) view.findViewById(R.id.txtSoLuongThanhToan);
            viewHolder.textViewGiaTien = (TextView) view.findViewById(R.id.txtGiaTienThanhToan);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ThanhToanViewHolder) view.getTag();
        }
        ThanhToan thanhToan = listThanhToan.get(i);
        viewHolder.textViewTenMonAn.setText(thanhToan.getTenMonAn());
        viewHolder.textViewSoLuong.setText(String.valueOf(thanhToan.getSoluong()));
        viewHolder.textViewGiaTien.setText(String.valueOf(thanhToan.getGiatien()));
        return view;
    }
    public class ThanhToanViewHolder{
        TextView textViewTenMonAn,textViewSoLuong,textViewGiaTien;
    }
}
