package com.example.passwordkeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRVAdaptor extends RecyclerView.Adapter<ItemRVAdaptor.ViewHolder> {
    private ArrayList<PakmadModal> pakMadModalArrayList;
    private Context context;

    public ItemRVAdaptor(ArrayList<PakmadModal> pakMadModalArrayList, Context context) {
        this.pakMadModalArrayList = pakMadModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        PakmadModal modal = pakMadModalArrayList.get(position);
        holder.entiti.setText(modal.getEntiti());
        holder.userName.setText(modal.getUserName());
        holder.password.setText(modal.getPassword());
        holder.description.setText(modal.getDescription());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, EditEntitiForm.class);

                // below we are passing all our values.
                i.putExtra("entitiShr", modal.getEntiti());
                i.putExtra("usernameShr", modal.getUserName());
                i.putExtra("passwordShr", modal.getPassword());
                i.putExtra("descriptionShr", modal.getDescription());

                // starting our activity.
                context.startActivity(i);
            }


        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return pakMadModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView entiti, userName, password, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            entiti = itemView.findViewById(R.id.idRVEntiti);
            userName = itemView.findViewById(R.id.idRVUserName);
            password = itemView.findViewById(R.id.idRVPassword);
            description = itemView.findViewById(R.id.idRVDescription);
        }
    }

}