package com.teamx.pregads;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomLLAdapter extends RecyclerView.Adapter<CustomLLAdapter.CustomViewHolder> {
    List<LearningLeaders> learningLeadersList;
    Context context;

    public CustomLLAdapter (Context context, List<LearningLeaders> learningLeadersList){
        this.context = context;
        this.learningLeadersList = learningLeadersList;
    }
    @NonNull
    @Override
    public CustomLLAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomLLAdapter.CustomViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.show_leaders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomLLAdapter.CustomViewHolder holder, int position) {
        Picasso.with(context)
                .load(learningLeadersList.get(position).getBadgeUrl())
                .placeholder(R.drawable.gads)
                .error(R.drawable.gads)
                .into(holder.badge);
        LearningLeaders post = learningLeadersList.get(position);
        holder.name.setText(post.getName());
        holder.country.setText(post.getCountry());
        holder.hours.setText(post.getHours());

    }

    @Override
    public int getItemCount() {
        return learningLeadersList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView badge;
        TextView name, hours, country;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.u_fullname);
            hours = itemView.findViewById(R.id.u_hours);
            country = itemView.findViewById(R.id.u_country);
            badge = itemView.findViewById(R.id.u_pic);
        }
    }
}
