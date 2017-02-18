package com.example.dang.orderfood;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dang.orderfood.Database.CreateDatabase;
import com.example.dang.orderfood.Fragment.HienThiBanAnFragment;
import com.example.dang.orderfood.Fragment.HienThiMonAnFragment;
import com.example.dang.orderfood.Fragment.HienThiNhanVienFragment;
import com.example.dang.orderfood.Utils.SessionLogin;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView tvTenNhanVien;
    NavigationView  navigationView;
    FragmentManager fragmentManager;
    SessionLogin session;
    int maquyen = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateDatabase database = new CreateDatabase(this);
        database.open();
        addWidgets();

        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        defaultHome();
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        overridePendingTransition(R.anim.ani_hieuung_ra,R.anim.activity_animation);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        session = new SessionLogin(getApplicationContext());
        session.checkLogin();
        HashMap<String,String> nhanVienDetail = session.getNhanVienDetail();
        String tenNhanVien = nhanVienDetail.get(SessionLogin.KEY_NAME);
        maquyen = Integer.parseInt(nhanVienDetail.get(SessionLogin.KEY_ROLE));
        tvTenNhanVien.setText(tenNhanVien);
        if (maquyen==2){
            hideItem();
        }
        View view = navigationView.getHeaderView(0);
        ImageView imgLogout = (ImageView) view.findViewById(R.id.imageViewLogout);
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                session.logout();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addWidgets() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        navigationView= (NavigationView) findViewById(R.id.navigation_main);

        fragmentManager = getSupportFragmentManager();
        View view = navigationView.inflateHeaderView(R.layout.header_navigation);
        tvTenNhanVien = (TextView) view.findViewById(R.id.textViewTenNhanVien);
    }



    private void defaultHome(){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
        transaction.replace(R.id.frame_content,hienThiBanAnFragment);
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_home :{
               defaultHome();
               item.setChecked(true);
                drawerLayout.closeDrawers();
            }
            break;
            case R.id.item_ThucDon :{
                hienthiThucDon();
                item.setChecked(true);
                drawerLayout.closeDrawers();
            }break;
            case R.id.item_NhanVien:{
                hienThiNhanVien();
                item.setChecked(true);
                drawerLayout.closeDrawers();
            }break;
        }
        return false;
    }
    public void hideItem(){
        Menu nav_menu =navigationView.getMenu();
        nav_menu.findItem(R.id.item_NhanVien).setVisible(false);
        nav_menu.findItem(R.id.item_thongke).setVisible(false);
        nav_menu.findItem(R.id.item_caidat).setVisible(false);

    }
    private void hienThiNhanVien() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HienThiNhanVienFragment hienThiNhanVienFragment = new HienThiNhanVienFragment();
        transaction.replace(R.id.frame_content,hienThiNhanVienFragment);
        transaction.commit();

    }

    private void hienthiThucDon() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HienThiMonAnFragment hienThiMonAnFragment = new HienThiMonAnFragment();
        transaction.replace(R.id.frame_content,hienThiMonAnFragment);
        transaction.commit();
    }
}
