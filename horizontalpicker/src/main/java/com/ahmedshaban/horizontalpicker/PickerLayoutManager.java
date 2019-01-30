package com.ahmedshaban.horizontalpicker;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by adityagohad on 06/06/17.
 */

public class PickerLayoutManager extends LinearLayoutManager {

    private float scaleDownBy = 0.66f;
    private float scaleDownDistance = 0.9f;
    private boolean changeAlpha = true;
    private RecyclerView recyclerView;
    private float unSelectedAlpha = .5f;
    private OnItemSelectedListener callback = null;
    private String scrollType;

    public PickerLayoutManager(Context context, int orientation, boolean reverseLayout, RecyclerView recyclerView, OnItemSelectedListener callback) {
        super(context, orientation, reverseLayout);
        this.recyclerView = recyclerView;
        this.callback = callback;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scaleDownView();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int orientation = getOrientation();
        if (orientation == HORIZONTAL) {
            int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
            scaleDownView();
            return scrolled;
        } else return 0;
    }

    private void scaleDownView() {
        float mid = getWidth() / 2.0f;
        float unitScaleDownDist = scaleDownDistance * mid;
        for (int i = 0; i < getChildCount(); i++) {
            validateType(false);
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        validateType(true);

    }

    private void validateType(boolean b) {
        if(scrollType.equals(HorizontalListPicker.ALPHATYPE))
            highLightCenter(true);
        else
            scaleCenter(true);
    }

    private void scaleCenter(boolean isNeedUpdated) {
        float mid = getWidth() / 2.0f;
        // Find the closest child to the recyclerView center --> this is the selected item.
        int recyclerViewCenterX = getRecyclerViewCenterX();
        int minDistance = recyclerView.getWidth();
        int position = -1;
        View lastViewPosition = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = recyclerView.getChildAt(i);
            int childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2;
            int childDistanceFromCenter = Math.abs(childCenterX - recyclerViewCenterX);
            if (childDistanceFromCenter < minDistance) {
                minDistance = childDistanceFromCenter;
                position = recyclerView.getChildLayoutPosition(child);
                lastViewPosition = child;
            }
            float childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;
            //float scale = 1.0f + (-1 * scaleDownBy) * (Math.min(unitScaleDownDist, Math.abs(mid - childMid))) / unitScaleDownDist;
            float distanceFromCenter = Math.abs(mid - childMid);
            float scale = 1 - distanceFromCenter / recyclerView.getWidth();
            child.setScaleX(scale);
            child.setScaleY(scale);
            if (changeAlpha) {
            }
        }

        // Notify on the selected item
        if (isNeedUpdated) {
            callback.onItemSelected(position);
        }


        if (lastViewPosition != null)
            lastViewPosition.setAlpha(1f);
    }


    private int getRecyclerViewCenterX() {
        return (recyclerView.getRight() - recyclerView.getLeft()) / 2 + recyclerView.getLeft();
    }

    public void setScrollType(String scrollType) {
        this.scrollType=scrollType;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }


    public void highLightCenter(boolean isNeedUpdated) {
        // Find the closest child to the recyclerView center --> this is the selected item.
        int recyclerViewCenterX = getRecyclerViewCenterX();
        int minDistance = recyclerView.getWidth();
        int position = -1;
        View lastViewPosition = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = recyclerView.getChildAt(i);
            int childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2;
            int childDistanceFromCenter = Math.abs(childCenterX - recyclerViewCenterX);
            if (childDistanceFromCenter < minDistance) {
                minDistance = childDistanceFromCenter;
                position = recyclerView.getChildLayoutPosition(child);
                lastViewPosition = child;
            }
            child.setAlpha(unSelectedAlpha);
        }

        // Notify on the selected item
        if (isNeedUpdated) {
            callback.onItemSelected(position);
        }


        if (lastViewPosition != null)
            lastViewPosition.setAlpha(1f);
    }


    public void setAlpha(float unSelectedAlpha) {
        this.unSelectedAlpha = unSelectedAlpha;
    }


}