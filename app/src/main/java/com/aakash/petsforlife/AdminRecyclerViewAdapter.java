package com.aakash.petsforlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdminRecyclerViewAdapter extends RecyclerView.Adapter<AdminRecyclerViewAdapter.AdminViewHolder> {

    List<Entity> list;
    Context context;
    String type;

    public AdminRecyclerViewAdapter(Context context, List<Entity> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public AdminRecyclerViewAdapter.AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        if(type.equals("pets")) {
            View view = LayoutInflater.from(context).inflate(R.layout.animal_card, parent, false);
            return new AdminViewHolder(view, type);
        } else if(type.equals("users")) {
            View view = LayoutInflater.from(context).inflate(R.layout.user_card, parent, false);
            return new AdminViewHolder(view, type);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.animal_card, parent, false);
            return new AdminViewHolder(view, type);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        if(type.equals("pets")) {
            holder.animalNameAndType.setText(list.get(position).getAnimalName() + " (" + list.get(position).getAnimalType() +")");
            holder.ownerName.setText("Owner Name: " + list.get(position).getUsername());
            holder.animalToken.setText("Token No: " + list.get(position).getAnimalToken());
            holder.animalDOB.setText("DOB: " + list.get(position).getAnimalDOB());
            holder.animalDesc.setText(list.get(position).getAnimalDesc());
        }else if(type.equals("users")) {
            holder.fullName.setText(list.get(position).getName());
            holder.username.setText(list.get(position).getUsername());
            holder.contact.setText(String.valueOf(list.get(position).getContact()));
            holder.email.setText(list.get(position).getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        TextView animalNameAndType, ownerName, animalToken, animalDOB, animalDesc;
        TextView fullName, username, contact, email;
        View view;

        public AdminViewHolder(@NonNull View itemView, String type) {
            super(itemView);
            if(type.equals("pets")){
                animalNameAndType = (TextView) itemView.findViewById(R.id.animalNameAndType);
                ownerName = (TextView) itemView.findViewById(R.id.ownerName);
                animalToken = (TextView) itemView.findViewById(R.id.animalToken);
                animalDOB = (TextView) itemView.findViewById(R.id.animalDOB);
                animalDesc = (TextView) itemView.findViewById(R.id.animalDesc);

                view = itemView;
            } else if(type.equals("users")) {
                fullName = (TextView) itemView.findViewById(R.id.fullName);
                username = (TextView) itemView.findViewById(R.id.username);
                contact = (TextView) itemView.findViewById(R.id.contact);
                email = (TextView) itemView.findViewById(R.id.email);

                view = itemView;
            } else {
                TextView animalName = (TextView) itemView.findViewById(R.id.animalNameAndType);
                TextView ownerName = (TextView) itemView.findViewById(R.id.ownerName);
                TextView animalToken = (TextView) itemView.findViewById(R.id.animalToken);
                TextView animalDOB = (TextView) itemView.findViewById(R.id.animalDOB);
                TextView animalDesc = (TextView) itemView.findViewById(R.id.animalDesc);

                view = itemView;
            }
        }
    }
}
