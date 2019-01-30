package com.ahmedshaban.horizontalpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

public class HorizontalListPicker extends ConstraintLayout {
    private ConstraintLayout constraintLayout;
    private RecyclerView rvHorizontalPicker;
    int currentIndex = 0;
    private PickerLayoutManager layoutManagerCenter;
    private PickerLayoutManager.OnItemSelectedListener onItemSelectedListener;
    public static String ALPHATYPE = "alpha";
    public static String SCALETYPE = "scale";


    public HorizontalListPicker(Context context) {
        super(context);
        init(context, null);
    }

    public HorizontalListPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    void init(Context context, AttributeSet attributeSet) {
        constraintLayout = (ConstraintLayout) inflate(context, R.layout.horizontal_list_picker_view, this);
        rvHorizontalPicker = findViewById(R.id.rv_horizontal_picker);


    }


    public void setUp(final Context context, @android.support.annotation.Nullable android.support.v7.widget.RecyclerView.Adapter adapter, final int requiredPosition) {


        layoutManagerCenter
                = new PickerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false, rvHorizontalPicker, new PickerLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                currentIndex = position;
                if (onItemSelectedListener != null)
                    onItemSelectedListener.onItemSelected(position);
            }
        });

        layoutManagerCenter.setScrollType(ALPHATYPE);
        rvHorizontalPicker.setLayoutManager(layoutManagerCenter);


        rvHorizontalPicker.setAdapter(adapter);
        SnapHelper snapHelperCenter = new LinearSnapHelper();
        snapHelperCenter.attachToRecyclerView(rvHorizontalPicker);


        rvHorizontalPicker.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                View view = rvHorizontalPicker.getLayoutManager().findViewByPosition(currentIndex);
                if (view != null) {
                    rvHorizontalPicker.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    float width = displayMetrics.widthPixels;
                    // float padding = width / 2  - dpToPx(MainActivity.this, view.getMeasuredWidth()/2);
                    float padding = width / 2;
                    float extraPadding = view.getMeasuredWidth() / 2;
                    float finalPadding = padding - extraPadding;
                    rvHorizontalPicker.setPadding((int) finalPadding, 0, (int) finalPadding, 0);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentIndex = requiredPosition;
                            rvHorizontalPicker.smoothScrollToPosition(requiredPosition);

                        }
                    }, 2000);


                }


            }
        });


    }


    public int getSelectedIndex() {
        return currentIndex;
    }


    public void setOnItemSelectedListener(PickerLayoutManager.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setUnselectedalpha(float unselectedalpha) {
        layoutManagerCenter.setAlpha(unselectedalpha);
    }

    public void setScrollType(String scaletype) {
        layoutManagerCenter.setScrollType(scaletype);
    }

    public void goNexT() {
        if (rvHorizontalPicker.getAdapter().getItemCount() - 1 > currentIndex) {
            currentIndex++;
            rvHorizontalPicker.smoothScrollToPosition(currentIndex);

        }
    }

    public void goPrevious() {
        if(currentIndex !=0){
            currentIndex--;
            rvHorizontalPicker.smoothScrollToPosition(currentIndex);
        }
    }
}
