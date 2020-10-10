package com.teamx.pregads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomSIQAdapter extends RecyclerView.Adapter<CustomSIQAdapter.CustomViewHolder> {

    List<SkillIQ> skillIQList;
    Context context;

    public CustomSIQAdapter(List<SkillIQ> skillIQList, Context context){
        this.skillIQList = skillIQList;
        this.context = context;
    }
    @NonNull
    @Override
    public CustomSIQAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomSIQAdapter.CustomViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.show_leaders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomSIQAdapter.CustomViewHolder holder, int position) {

        Picasso.with(context)
                .load(skillIQList.get(position).getBadgeUrl())
                .placeholder(R.drawable.gads)
                .error(R.drawable.gads)
                .into(holder.badge);
        SkillIQ post = skillIQList.get(position);
        holder.name.setText(post.getName());
        holder.country.setText(post.getCountry());
        holder.score.setText(post.getScore());
    }

    @Override
    public int getItemCount() {
        return skillIQList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView badge;
        TextView name, score, country;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.u_fullname);
            score = itemView.findViewById(R.id.u_hours);
            country = itemView.findViewById(R.id.u_country);
            badge = itemView.findViewById(R.id.u_pic);
        }
    }
}
