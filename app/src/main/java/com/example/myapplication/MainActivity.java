package com.example.myapplication;

import static android.view.View.inflate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import androidx.navigation.NavController;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentRaspisanie raspisanieFragment = new FragmentRaspisanie();
        setNewFragment(raspisanieFragment);
        DrawerLayout myDrawerLayout = findViewById(R.id.drawer_layout_id);
        NavigationView myNavigationView = findViewById(R.id.navigation_view_id);


        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Toast.makeText(MainActivity.this, "Расписание", Toast.LENGTH_SHORT).show();
                    setNewFragment(raspisanieFragment);
                    myDrawerLayout.close();
                    return true;

                } else if (id == R.id.nav_gallery) {
                    Toast.makeText(MainActivity.this, "Преподаватели", Toast.LENGTH_SHORT).show();
                    FragmentPrepodavateli prepodavateliFragment = new FragmentPrepodavateli();
                    setNewFragment(prepodavateliFragment);
                    myDrawerLayout.close();
                    return true;
                } else if (id == R.id.nav_abonementi) {
//                    Toast.makeText(MainActivity.this, "Абонементы", Toast.LENGTH_SHORT).show();
                    FragmentAbonementi abonementiFragment = new FragmentAbonementi();
                    setNewFragment(abonementiFragment);
                    myDrawerLayout.close();
                    return true;

                } else if (id == R.id.nav_slideshow) {
                    Toast.makeText(MainActivity.this, "Контакты", Toast.LENGTH_SHORT).show();
                    FragmentKontakti kontaktiFragment = new FragmentKontakti();
                    setNewFragment(kontaktiFragment);
                    myDrawerLayout.close();
                    return true;
                }
                else if (id == R.id.nav_avtorizaciya) {
                    Toast.makeText(MainActivity.this, "Авторизация", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Avtorizaciya.class);
                    startActivity(intent);
                    myDrawerLayout.closeDrawers();;
                    return true;
                }


                return false;
            }


        });


    }


    private  void setNewFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.frameL_id, fragment);
        ft.commit();

    }
}