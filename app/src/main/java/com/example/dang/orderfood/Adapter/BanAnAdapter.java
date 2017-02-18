package com.example.dang.orderfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dang.orderfood.DAO.BanAnDAO;
import com.example.dang.orderfood.DAO.GoiMonDAO;
import com.example.dang.orderfood.DTO.BanAn;
import com.example.dang.orderfood.DTO.GoiMon;
import com.example.dang.orderfood.Fragment.HienThiMonAnFragment;
import com.example.dang.orderfood.MainActivity;
import com.example.dang.orderfood.R;
import com.example.dang.orderfood.Utils.SessionLogin;
import com.example.dang.orderfood.ThanhToanActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DANG on 8/9/2016.
 */
public class BanAnAdapter extends BaseAdapter implements View.OnClickListener{
    int layoutId;
    Context context;
    List<BanAn> listBanAn;
    LayoutInflater layoutInflater;
    BanAnViewHolder viewHolder;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO ;
    SessionLogin sessionLogin;
    FragmentManager fragmentManager ;
    public static final int REQUEST_CODE_THANHTOAN=0006;
    public BanAnAdapter(int layoutId, Context context, List<BanAn> listBanAn) {
        this.layoutId = layoutId;
        this.context = context;
        this.listBanAn = listBanAn;
        fragmentManager= ((MainActivity)context).getSupportFragmentManager();
        banAnDAO = new BanAnDAO(context);
        goiMonDAO = new GoiMonDAO(context);
        sessionLogin = new SessionLogin(context);
    }

    @Override
    public int getCount() {
        return listBanAn.size();
    }

    @Override
    public Object getItem(int i) {
        return listBanAn.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listBanAn.get(i).getMaBan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new BanAnViewHolder();
            view = layoutInflater.inflate(R.layout.custom_gridview_hienthibanan,viewGroup,false);

            viewHolder.textViewTenBanAn = (TextView) view.findViewById(R.id.textViewTenBanAn);
            viewHolder.imageViewAnhBanAn = (ImageView) view.findViewById(R.id.imageViewBanAn);
            viewHolder.imageButtonAn = (ImageButton) view.findViewById(R.id.imageButtonAn);
            viewHolder.imageButtongoiMon = (ImageButton) view.findViewById(R.id.imageButtonGoiMon);
            viewHolder.imageButtonThanhToan = (ImageButton) view.findViewById(R.id.imageButtonThanhToan);
            view.setTag(viewHolder);

        }else{
           viewHolder = (BanAnViewHolder) view.getTag();
        }
        BanAn banAn = listBanAn.get(position);
        viewHolder.imageButtongoiMon.setTag(position);
        if (banAnDAO.getTrangThaiBanAnById(banAn.getMaBan()).equals("false"))
           viewHolder.imageViewAnhBanAn.setImageResource(R.drawable.banan);
        else
            viewHolder.imageViewAnhBanAn.setImageResource(R.drawable.banantrue);
        viewHolder.textViewTenBanAn.setText(banAn.getTenBan());
        viewHolder.imageViewAnhBanAn.setOnClickListener(this);
        viewHolder.imageButtongoiMon.setOnClickListener(this);
        viewHolder.imageButtonThanhToan.setOnClickListener(this);
        if (view.isSelected())
            hienThiButtonImage(viewHolder);
        else{
            anButtonImage(viewHolder);
        }
        return view;
    }
    public void hienThiButtonImage(BanAnViewHolder viewHolder){
        viewHolder.imageButtonThanhToan.setVisibility(View.VISIBLE);
        viewHolder.imageButtongoiMon.setVisibility(View.VISIBLE);
        viewHolder.imageButtonAn.setVisibility(View.VISIBLE);

    }
    public void anButtonImage(BanAnViewHolder viewHolder){
        viewHolder.imageButtonThanhToan.setVisibility(View.INVISIBLE);
        viewHolder.imageButtongoiMon.setVisibility(View.INVISIBLE);
        viewHolder.imageButtonAn.setVisibility(View.INVISIBLE);

    }



    @Override
    public void onClick(View view) {
       int  id = view.getId();
        viewHolder = (BanAnViewHolder) ((View)view.getParent()).getTag();
        int position = (int) viewHolder.imageButtongoiMon.getTag();

        int maBanAn = listBanAn.get(position).getMaBan();
        String tinhTrangBan = banAnDAO.getTrangThaiBanAnById(maBanAn);
        switch (id){
            case R.id.imageViewBanAn:{
                hienThiButtonImage(viewHolder);
                view.isSelected();
            }break;
            case R.id.imageButtonGoiMon:{

                if (tinhTrangBan.equals("false")||tinhTrangBan.equals("")){
                    banAnDAO.updateTrangThaiBanAn(maBanAn,"true");
                    goiMon(maBanAn);
                }else{
                    goiMon(maBanAn);
                }

            }break;
            case R.id.imageButtonThanhToan:{
                thanhToan(maBanAn);
            }break;
        }

    }

    private void thanhToan(int maBan) {
        int maGoiMon = goiMonDAO.kiemTraGoiMon(maBan,"false").getMaGoiMon();
        Intent intent = new Intent(context, ThanhToanActivity.class);
        intent.putExtra("maGoiMon",maGoiMon);
        intent.putExtra("maBan",maBan);
        context.startActivity(intent);
    }

    private void goiMon(int maBan) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String ngaygoi= df.format(cal.getTime());

        HashMap<String,String> nhanvien = sessionLogin.getNhanVienDetail();
        int maNhanVien = Integer.parseInt(nhanvien.get(SessionLogin.KEY_ID));
        GoiMon goiMon = new GoiMon();
        goiMon.setMaBan(maBan);
        goiMon.setMaNhanVien(maNhanVien);
        goiMon.setNgayGoi(ngaygoi);
        goiMon.setTinhTrang("false");
        long kiemtra = goiMonDAO.themGoiMon(goiMon);
        banAnDAO.updateTrangThaiBanAn(maBan,"true");
        if (kiemtra==0){
            Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HienThiMonAnFragment hienThiMonAnFragment = new HienThiMonAnFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("maBan",maBan);
        hienThiMonAnFragment.setArguments(bundle);
        transaction.replace(R.id.frame_content,hienThiMonAnFragment).addToBackStack("hienthibanan");
        transaction.commit();


    }

    public class BanAnViewHolder{
        TextView textViewTenBanAn;
        ImageView imageViewAnhBanAn;
        ImageButton imageButtongoiMon,imageButtonThanhToan,imageButtonAn;
    }
}
