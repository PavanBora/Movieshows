package com.example.pavanbora.movieshows.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavanbora.movieshows.Pojo.ReviewResult;
import com.example.pavanbora.movieshows.R;
import com.example.pavanbora.movieshows.Rating;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.myViewHolder>{
    Context context;
    Rating rating;
    private List<ReviewResult> list;
    ReviewResult result;

    public ReviewAdapter(Context context, List<ReviewResult> reviewList) {
        this.context=context;
        this.list = reviewList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.review, parent, false);
        return new ReviewAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        if (list.get( position ).getUrl() != null) {
            holder.userid.setText( "No Reviews yet" );
            }

        else{

            holder.userid.setText( list.get( position ).getAuthor() );
            holder.review.setText( list.get( position ).getContent() );
        }
    }
    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView userid,review;

        public myViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.pic );
            userid=itemView.findViewById( R.id.userid );
            review=itemView.findViewById( R.id.review );
        }
    }
}
