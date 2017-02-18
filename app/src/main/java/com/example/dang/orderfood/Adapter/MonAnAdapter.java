package com.example.dang.orderfood.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dang.orderfood.DTO.MonAn;
import com.example.dang.orderfood.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by DANG on 8/11/2016.
 */
public class MonAnAdapter extends ArrayAdapter<MonAn> {
    int layoutId;
    Context context;
    List<MonAn> listMonAn;
    MonAnViewHolder viewHolder;
    public MonAnAdapter(Context context, int resource,List<MonAn> list) {
        super(context, resource,list);
        this.context= context;
        this.layoutId = resource;
        this.listMonAn = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view==null){
            viewHolder = new MonAnViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,parent,false);
            viewHolder.textViewTenMonAn = (TextView) view.findViewById(R.id.textView_hienthi_tenMonAn);
            viewHolder.textViewGiaMonAn = (TextView) view.findViewById(R.id.textView_hienthi_giaMonAn);
            viewHolder.imageViewHinhAnhMonan = (ImageView) view.findViewById(R.id.imageView_hienthi_monan);
            view.setTag(viewHolder);

        }else{
            viewHolder = (MonAnViewHolder) view.getTag();
        }
        MonAn monAn = listMonAn.get(position);
        viewHolder.textViewTenMonAn.setText(monAn.getTenMonAn());
        viewHolder.textViewGiaMonAn.setText(String.valueOf(monAn.getGiaMonAn()));
       if (!monAn.getHinhAnhMonAn().equals("null")){
           viewHolder.imageViewHinhAnhMonan.setImageURI(Uri.parse(monAn.getHinhAnhMonAn()));
       }

        return view;
    }
    public class MonAnViewHolder{
        TextView textViewTenMonAn;
        TextView textViewGiaMonAn;
        ImageView imageViewHinhAnhMonan;
    }

}
