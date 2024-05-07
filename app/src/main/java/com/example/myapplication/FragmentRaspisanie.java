package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.view.View.inflate;

import static androidx.navigation.Navigation.findNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class FragmentRaspisanie extends Fragment {

    private TextView tvDate;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int[] images = {R.drawable.sss, R.drawable.frame_10, R.drawable.image_10};
    private int currentPage = 0;
    private static final long AUTO_SWIPE_DELAY = 10000; // Задержка для автоматического перелистывания


    private ArrayAdapter<String> adapter1;
    private List<String> listData1;
    private DatabaseReference mDatabase;
    private String LESSON_KEY = "Lesson";
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
        Calendar newDate = Calendar.getInstance();
        updateDate(calendar);


        ListView listView = view.findViewById(R.id.list_raspisanie);
        listData1 = new ArrayList<>();
        adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listData1);
        mDatabase = FirebaseDatabase.getInstance().getReference(LESSON_KEY);
        listView.setAdapter(adapter1);
        getDataFromBD();

        ImageButton prevButton = view.findViewById(R.id.btn_prev);
        ImageButton nextButton = view.findViewById(R.id.btn_next);


        // Обработчики событий для кнопок
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Перемещаемся на один день назад
                if(calendar.after(newDate)){
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                    updateDate(calendar);
                    getDataFromBD();

                }
                else {
                    Toast.makeText(getContext(), "Не смотрите в прошлое, есть только будущее", Toast.LENGTH_SHORT).show();
                }
            }

        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Перемещаемся на один день вперед
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                updateDate(calendar);
                getDataFromBD();
            }

        });




    }



    private void updateDate(Calendar clr) {
        // Обновляем текст в TextView
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String formattedDate = sdf.format(clr.getTime());
        tvDate.setText(getString(R.string.selected_date, formattedDate));


    }


    private  void getDataFromBD(){
        ValueEventListener vListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData1.size() > 0)listData1.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    Lesson lesson = ds.getValue(Lesson.class);
                    assert lesson != null;
                    if(lesson.Date.equals(tvDate.getText())){

                        listData1.add(lesson.Grup + "\n"+ lesson.Time + "\n" + lesson.Teacher1 + " " + lesson.Teacher2);

                    }


                }
                adapter1.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabase.addValueEventListener(vListener);
    }

}