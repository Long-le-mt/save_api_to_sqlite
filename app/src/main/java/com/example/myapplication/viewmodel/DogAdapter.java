package com.example.myapplication.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.DogBreed;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder>{
    private List<DogBreed> dogBreedList;

    public DogAdapter(List<DogBreed> dogBreedList) {
        this.dogBreedList = dogBreedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DogBreed dogBreed = dogBreedList.get(position);

        if(dogBreed == null)
            return;

        holder.twDogName.setText(dogBreed.getName());
        holder.twBredFor.setText(dogBreed.getBredFor());
        Picasso.get()
                .load(dogBreed.getUrl())
                .placeholder(R.drawable.img)
                .into(holder.imgDog);

    }

    @Override
    public int getItemCount() {
        if (dogBreedList == null)
            return 0;
        return dogBreedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView twDogName, twBredFor;
        private ImageView imgDog;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            twDogName = (TextView) itemView.findViewById(R.id.tw_dog_name);
            twBredFor = (TextView) itemView.findViewById(R.id.tw_bred_for);
            imgDog = (ImageView) itemView.findViewById(R.id.img_dog);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DogBreed dog = dogBreedList.get(getPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dogBreed", dog);
                    Navigation.findNavController(view).navigate(R.id.detailFragment, bundle);
                }
            });
        }
    }
}
