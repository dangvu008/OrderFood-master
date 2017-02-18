package com.example.dang.orderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.LoaiMonAnDAO;
import com.example.dang.orderfood.DTO.LoaiMonAn;
import com.example.dang.orderfood.R;

import java.util.List;

/**
 * Created by DANG on 8/14/2016.
 */
public class HienThiLoaiMonAnAdapter extends BaseAdapter {
    int layoutId;
    List<LoaiMonAn> listLoaiMonAn;
    Context context;
    LoaiMonAnViewHolder viewHolder;
    LoaiMonAnDAO loaiMonAnDAO;
    public HienThiLoaiMonAnAdapter(Context context, int layoutId, List<LoaiMonAn> listLoaiMonAn) {
        this.context = context;
        this.listLoaiMonAn = listLoaiMonAn;
        this.layoutId = layoutId;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return listLoaiMonAn.size();
    }

    @Override
    public Object getItem(int i) {
        return listLoaiMonAn.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listLoaiMonAn.get(i).getMaLoaiMonAn();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            viewHolder = new LoaiMonAnViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,viewGroup,false);
            viewHolder.textViewTenLoaiMonAn = (TextView) view.findViewById(R.id.tenLoaiMonAn_hienthi);
            viewHolder.imageViewHinhAnhLoaiMonAn = (ImageView) view.findViewById(R.id.imageLoaiMonAn);
            view.setTag(viewHolder);
        }else{
            viewHolder = (LoaiMonAnViewHolder) view.getTag();
        }
        LoaiMonAn loaiMonAn = listLoaiMonAn.get(i);
        //String hinhAnh =loaiMonAnDAO.LayHinhLoaiMonAn(loaiMonAn.getMaLoaiMonAn());
        viewHolder.textViewTenLoaiMonAn.setText(loaiMonAn.getTenLoaiMonAn());
        if (!loaiMonAn.getHinhAnh().equals("null")){
            Uri uriHinhAnh =  Uri.parse(loaiMonAn.getHinhAnh());
            viewHolder.imageViewHinhAnhLoaiMonAn.setImageURI(uriHinhAnh);
        }


        return view;

    }
    public class LoaiMonAnViewHolder{
        TextView textViewTenLoaiMonAn;
        ImageView imageViewHinhAnhLoaiMonAn;
    }
}
