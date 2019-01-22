package com.example.pavanbora.movieshows.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavanbora.movieshows.MovieDetails;
import com.example.pavanbora.movieshows.Pojo.Result;
import com.example.pavanbora.movieshows.Pojo.TopResult;
import com.example.pavanbora.movieshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<TopResult> moviesList;
    Result result;


    public MyAdapter(Context context, List<TopResult> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.movieTitle.setText(moviesList.get(position).getTitle());
        holder.releaseDate.setText(moviesList.get(position).getReleaseDate());
        holder.ratingBar.setRating((float) (moviesList.get(position).getVoteAverage()/2));
        if (moviesList.get(position).getBackdropPath() != null) {
            String image = "http://image.tmdb.org/t/p/w500" + moviesList.get(position).getBackdropPath();
            Picasso.with(context).load(image).into(holder.poster);

        } else

        {
            String image = "http://image.tmdb.org/t/p/w500" + moviesList.get(position).getPosterPath();
            Picasso.with(context).load(image).into(holder.poster);
        }
    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle,releaseDate;
        CardView cardView;
        RatingBar ratingBar;
        ImageView poster;
        public MyViewHolder(View itemView){
            super(itemView);
            movieTitle=itemView.findViewById(R.id.movieTitle);
            releaseDate=itemView.findViewById(R.id.releasedate);
            ratingBar=itemView.findViewById(R.id.movierating);
            poster=itemView.findViewById(R.id.poster);
            cardView=itemView.findViewById(R.id.listItemCard);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MovieDetails.class);
                    intent.putExtra("id",moviesList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                    Toast.makeText(context, ""+moviesList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();




                }
            });
        }
    }
}
