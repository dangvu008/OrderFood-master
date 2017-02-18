package com.example.dang.orderfood.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dang.orderfood.DTO.LoaiMonAn;
import com.example.dang.orderfood.R;

import java.util.List;

/**
 * Created by DANG on 8/13/2016.
 */
public class LoaiMonAnAdapter extends ArrayAdapter<LoaiMonAn> {
    int layoutId;
    Context context;
    List<LoaiMonAn>lisLoaiMonAn;
    int textViewResourceId;
    ViewHolder viewHolder;

    public LoaiMonAnAdapter(Context context, int resource, int textViewResourceId, List<LoaiMonAn> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.layoutId=resource;
        this.textViewResourceId = textViewResourceId;
        this.lisLoaiMonAn = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        viewHolder = new ViewHolder();
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(layoutId,parent,false);
            viewHolder.textViewTenLoaiMonAn = (TextView) view.findViewById(textViewResourceId);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonAn loaiMonAn = lisLoaiMonAn.get(position);
        Log.d("LoaiMon",loaiMonAn.getTenLoaiMonAn());
        viewHolder.textViewTenLoaiMonAn.setText(loaiMonAn.getTenLoaiMonAn());
        return view;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        viewHolder = new ViewHolder();
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(layoutId,parent,false);
            viewHolder.textViewTenLoaiMonAn = (TextView) view.findViewById(textViewResourceId);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonAn loaiMonAn = lisLoaiMonAn.get(position);
        viewHolder.textViewTenLoaiMonAn.setText(loaiMonAn.getTenLoaiMonAn());
        return view;
    }
    public class ViewHolder{
        TextView textViewTenLoaiMonAn;
    }
}
