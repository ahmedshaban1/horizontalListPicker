package com.ahmedshaban.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ahmedshaban.horizontalpicker.HorizontalListPicker;
import com.ahmedshaban.horizontalpicker.PickerLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HorizontalListPicker horizontal_picker;
    TextView selectedItemTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontal_picker = findViewById(R.id.horizontal_picker);
        selectedItemTv = findViewById(R.id.selectedItemTv);


        List<String> dummyList = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            dummyList.add(i + "");
        }
        horizontal_picker.setUp(this, new HorizontalListPickerAdapter(this, dummyList), 0);
        selectedItemTv.setText(horizontal_picker.getSelectedIndex()+"");

        horizontal_picker.setOnItemSelectedListener(new PickerLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                selectedItemTv.setText(horizontal_picker.getSelectedIndex()+"");
            }
        });

        //horizontal_picker.setUnselectedAlpha(.2f);

        horizontal_picker.setScrollType(HorizontalListPicker.SCALETYPE);


        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontal_picker.goNexT();
            }
        });

        findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontal_picker.goPrevious();
            }
        });




    }
}
