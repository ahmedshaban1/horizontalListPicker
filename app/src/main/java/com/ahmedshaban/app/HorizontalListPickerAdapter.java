package com.ahmedshaban.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HorizontalListPickerAdapter extends RecyclerView.Adapter<HorizontalListPickerAdapter.HorizontalListPickerViewHolder> {

    List<String> dataList;

    public HorizontalListPickerAdapter(Context context, List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HorizontalListPickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HorizontalListPickerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_list_picker_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalListPickerViewHolder viewHolder, final int i) {
        viewHolder.title.setText(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class HorizontalListPickerViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public HorizontalListPickerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
