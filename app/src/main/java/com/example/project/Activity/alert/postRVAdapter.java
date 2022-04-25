package com.example.project.Activity.alert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class postRVAdapter extends RecyclerView.Adapter<postRVAdapter.ViewHolder> {
    //creating variables for our list, context, interface and position.
    private ArrayList<com.example.project.Activity.alert.postRVModal> postRVModalArrayList;
    private Context context;
    private postClickInterface postClickInterface;
    int lastPos = -1;

    //creating a constructor.
    public postRVAdapter(ArrayList<com.example.project.Activity.alert.postRVModal> postRVModalArrayList, Context context, postClickInterface postClickInterface) {
        this.postRVModalArrayList = postRVModalArrayList;
        this.context = context;
        this.postClickInterface = postClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.post_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //setting data to our recycler view item on below line.
        com.example.project.Activity.alert.postRVModal postRVModal = postRVModalArrayList.get(position);
        holder.postTV.setText(postRVModal.getpostName());
        holder.postPriceTV.setText(postRVModal.getpostPrice());
        Picasso.get().load(postRVModal.getpostImg()).into(holder.postIV);
        //adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.postIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postClickInterface.onpostClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            //on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return postRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //creating variable for our image view and text view on below line.
        private ImageView postIV;
        private TextView postTV, postPriceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing all our variables on below line.
            postIV = itemView.findViewById(R.id.idIVpost);
            postTV = itemView.findViewById(R.id.idTVpostName);
            postPriceTV = itemView.findViewById(R.id.idTVCousePrice);
        }
    }

    //creating a interface for on click
    public interface postClickInterface {
        void onpostClick(int position);
    }
}
