package com.malhar.mycovidtracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.activities.BaseActivity;
import com.malhar.mycovidtracker.dataclasses.Statewise;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    List<Statewise> list;
    Context context;

    public StateAdapter(Context context, List<Statewise> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new StateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1.setText(list.get(position).getState());
        holder.tv2.setText(list.get(position).getConfirmed());
        holder.tv3.setText(list.get(position).getActive());
        holder.tv4.setText(list.get(position).getDeaths());
        holder.tv5.setText(list.get(position).getRecovered());

        holder.itemView.setOnClickListener(v ->
                context.startActivity(new Intent(context, BaseActivity.class)
                        .putExtra("state", list.get(position).getState()))
        );
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
            tv5 = itemView.findViewById(R.id.curedTV);
        }
    }

}
