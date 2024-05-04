package com.example.myapplication;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.view.View.inflate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class FragmentRaspisanie extends Fragment {

    private TextView tvDate;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int currentPage = 0;
    private static final long AUTO_SWIPE_DELAY = 10000; // Задержка для автоматического перелистывания

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable autoSwipeRunnable = new Runnable() {
        @Override
        public void run() {
            if (currentPage == images.length) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(this, AUTO_SWIPE_DELAY);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.raspisanie, container, false);



    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getContext(), images);
        viewPager.setAdapter(adapter);
        handler.postDelayed(autoSwipeRunnable, AUTO_SWIPE_DELAY);
        tvDate = view.findViewById(R.id.tvDate);
        Calendar calendar = Calendar.getInstance();
        updateDate(calendar);
//        DrawerLayout myDrawerLayout =  view.findViewById(R.id.drawer_layout_id);

        ImageButton prevButton = view.findViewById(R.id.btn_prev);
        ImageButton nextButton = view.findViewById(R.id.btn_next);
        Button btnMenu = view.findViewById(R.id.btnMenu);

        // Обработчики событий для кнопок
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Перемещаемся на один день назад
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                updateDate(calendar);
            }

        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Перемещаемся на один день вперед
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                updateDate(calendar);
            }

        });



    }

//            public void onClickMenu(View view) {
//                myDrawerLayout.showContextMenu();
//            }


    private void updateDate(Calendar clr) {
        // Обновляем текст в TextView
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String formattedDate = sdf.format(clr.getTime());
        tvDate.setText(getString(R.string.selected_date, formattedDate));
    }

}