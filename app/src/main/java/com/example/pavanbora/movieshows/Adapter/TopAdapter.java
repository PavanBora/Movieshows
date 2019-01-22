package com.example.pavanbora.movieshows.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavanbora.movieshows.MovieDetails;
import com.example.pavanbora.movieshows.Pojo.TopResult;
import com.example.pavanbora.movieshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.MyViewHolder>{
    Context context;
    List<TopResult> topList;
    TopResult result;

    public TopAdapter(Context context, List<TopResult> topList) {
        this.context = context;
        this.topList = topList;
    }

    @Override
    public TopAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new TopAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopAdapter.MyViewHolder holder, int position) {
        holder.movieTitle.setText(topList.get(position).getTitle());
        holder.releaseDate.setText(topList.get(position).getReleaseDate());
        holder.ratingBar.setRating((float) (topList.get(position).getVoteAverage()/2));
        if (topList.get(position).getBackdropPath() != null) {
            String image = "http://image.tmdb.org/t/p/w500" + topList.get(position).getBackdropPath();
            Log.d("imageUrl",image);
            Picasso.with(context).load(image).into(holder.poster);

        } else

        {
            String image = "http://image.tmdb.org/t/p/w500" + topList.get(position).getPosterPath();
            Picasso.with(context).load(image).into(holder.poster);
        }

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle,releaseDate;
        CardView cardView;
        RatingBar ratingBar;
        ImageView poster;
        public MyViewHolder(View itemView) {
            super(itemView);
            movieTitle=itemView.findViewById(R.id.movieTitle);
            releaseDate=itemView.findViewById(R.id.releasedate);
            ratingBar=itemView.findViewById(R.id.movierating);
            poster=itemView.findViewById(R.id.poster);
            cardView=itemView.findViewById(R.id.listItemCard);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+topList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, MovieDetails.class);
                    intent.putExtra("id",topList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
