package com.example.contohsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.contohsqlite.nav.FragCatatan;
import com.example.contohsqlite.nav.FragInfo;
import com.example.contohsqlite.nav.FragProfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    FragCatatan fragCatatan;
    FragProfil fragProfil;
    FragInfo fragInfo;

    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        setupViewPager(viewPager);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.iv_image:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.list_view:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.frag_info:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem =bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragProfil = new FragProfil();
        fragCatatan = new FragCatatan();
        fragInfo = new FragInfo();

        adapter.addFragment(fragProfil);
        adapter.addFragment(fragCatatan);
        adapter.addFragment(fragInfo);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
//Pengerjaan 5/06/2021