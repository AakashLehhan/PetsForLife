package com.aakash.petsforlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {

    List<Entity> list;
    Context context;
    String type;

    public UserRecyclerViewAdapter(Context context, List<Entity> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public UserRecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view;
        if(type.equals("posts")) {
            view = LayoutInflater.from(context).inflate(R.layout.tip_card, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.user_pets_card, parent, false);
        }
        return new UserViewHolder(view, type);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewAdapter.UserViewHolder holder, int position) {
        if(type.equals("posts")) {
            holder.title.setText(list.get(position).getPostTitle());
            holder.description.setText(list.get(position).getPostDescription());
        } else {
            holder.petName.setText(list.get(position).getAnimalName());
            holder.petDesc.setText(list.get(position).getAnimalDesc());
            holder.petDOB.setText(list.get(position).getAnimalDOB());
            holder.petToken.setText(list.get(position).getAnimalToken());
            holder.petType.setText(list.get(position).getAnimalType());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        TextView petName, petDOB, petDesc, petToken, petType;
        View view;

        public UserViewHolder(@NonNull View itemView, String type) {
            super(itemView);

            if(type.equals("posts")) {
                title = (TextView) itemView.findViewById(R.id.postTitle);
                description = (TextView) itemView.findViewById(R.id.postDescription);
                view = itemView;
            } else {
                petName = (TextView) itemView.findViewById(R.id.petName);
                petDesc = (TextView) itemView.findViewById(R.id.petDesc);
                petDOB = (TextView) itemView.findViewById(R.id.petDOB);
                petToken = (TextView) itemView.findViewById(R.id.petToken);
                petType = (TextView) itemView.findViewById(R.id.petType);
            }
        }
    }
}
