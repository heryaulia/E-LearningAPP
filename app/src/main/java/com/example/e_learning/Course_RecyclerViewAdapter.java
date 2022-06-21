package com.example.e_learning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Course_RecyclerViewAdapter extends RecyclerView.Adapter<Course_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<CourseModel> courseModels;

    public Course_RecyclerViewAdapter(Context context, ArrayList<CourseModel> courseModels){
        this.context = context;
        this.courseModels = courseModels;

    }

    @NonNull
    @Override
    public Course_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is where you inflate the layout (giving look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false );

        return new Course_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Course_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we create in recycler_view_row layout file
        //base on the position of the recycler view

        holder.tvCourseName.setText(courseModels.get(position).getCourseName());
        holder.tvLessonAmount.setText(courseModels.get(position).getLessonsamount());
        holder.imageView.setImageResource(courseModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        //the recycler view just want to know the number of item you want to displayed
        return courseModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from our recycler_view_row layout file
        //kinda like onCreate method

        ImageView imageView;
        TextView tvCourseName, tvLessonAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //dari course fragment ke course activity tapi bawa data belum bisa
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // start new intent
                    Intent intent = new Intent(v.getContext(), CourseActivity.class);
                    intent.putExtra("title", getAdapterPosition()); //sending title of the card view
                    v.getContext().startActivity(intent); //start new activity from view "v
                }
            });

            imageView = itemView.findViewById(R.id.imageView11);
            tvCourseName = itemView.findViewById(R.id.textView9);
            tvLessonAmount = itemView.findViewById(R.id.textView10);


        }
    }
}
