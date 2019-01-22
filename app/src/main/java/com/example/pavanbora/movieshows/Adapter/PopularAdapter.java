package com.example.pavanbora.movieshows.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavanbora.movieshows.MovieDetails;
import com.example.pavanbora.movieshows.Pojo.PopularResult;
import com.example.pavanbora.movieshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularAdapter extends Adapter<PopularAdapter.MyViewHolder> {

    Context context;
    List <PopularResult> popularList;
    PopularResult result;

    public PopularAdapter(Context context, List <PopularResult> popularList) {
        this.context = context;
        this.popularList =popularList;
    }

    @Override
    public PopularAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new PopularAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.MyViewHolder holder, int position) {
        holder.movieTitle.setText(popularList.get(position).getTitle());
        holder.releaseDate.setText(popularList.get(position).getReleaseDate());
        holder.ratingBar.setRating((float) (popularList.get(position).getVoteAverage()/2));
        if (popularList.get(position).getBackdropPath() != null) {
            String image = "http://image.tmdb.org/t/p/w500" + popularList.get(position).getBackdropPath();
            Picasso.with(context).load(image).into(holder.poster);

        } else

        {
            String image = "http://image.tmdb.org/t/p/w500" + popularList.get(position).getPosterPath();
            Picasso.with(context).load(image).into(holder.poster);
        }

    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle,releaseDate;
        CardView cardView;
        RatingBar ratingBar;
        ImageView poster;
        public MyViewHolder(View view) {
            super(view);

            movieTitle=itemView.findViewById(R.id.movieTitle);
            releaseDate=itemView.findViewById(R.id.releasedate);
            ratingBar=itemView.findViewById(R.id.movierating);
            poster=itemView.findViewById(R.id.poster);
            cardView=itemView.findViewById(R.id.listItemCard);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+popularList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, MovieDetails.class);
                    intent.putExtra("id",popularList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
