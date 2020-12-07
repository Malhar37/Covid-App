package com.malhar.mycovidtracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.dataclasses.DistrictDatum;

import java.util.List;

public class DistrictsAdapter extends RecyclerView.Adapter<DistrictsAdapter.ViewHolder> {

    List<DistrictDatum> list;
    Context context;

    public DistrictsAdapter(Context context, List<DistrictDatum> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1.setText(list.get(position).getDistrict());
        holder.tv2.setText(String.valueOf(list.get(position).getConfirmed()));
        holder.tv3.setText(String.valueOf(list.get(position).getActive()));
        holder.tv4.setText(String.valueOf(list.get(position).getDeceased()));
        holder.tv5.setText(String.valueOf(list.get(position).getRecovered()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv1, tv2, tv3, tv4, tv5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.stateTV);
            tv2 = itemView.findViewById(R.id.confirmedTV);
            tv3 = itemView.findViewById(R.id.activeTV);
            tv4 = itemView.findViewById(R.id.deathTV);
            tv5=itemView.findViewById(R.id.curedTV);
        }
    }

}

