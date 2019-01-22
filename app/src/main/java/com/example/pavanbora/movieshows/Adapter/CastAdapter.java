package com.example.pavanbora.movieshows.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavanbora.movieshows.Pojo.Cast;
import com.example.pavanbora.movieshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public class CastAdapter extends RecyclerView.Adapter <CastAdapter.myViewHolder> {
    Context context;
    List<Cast> castList;
    Cast castResult;

    public CastAdapter(Context context, List<Cast> castList) {
        this.context=context;
        this.castList = castList;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.cast, parent, false);
        return new CastAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.cname.setText( castList.get( position ).getCharacter() );
        holder.oname.setText(castList.get( position ).getName());
        if (castList.get(position).getProfilePath() != null) {
            String image = "http://image.tmdb.org/t/p/w500" + castList.get(position).getProfilePath();
            Log.d("imageUrl",image);
            Picasso.with(context).load(image).into(holder.imageView);

        } else

        {
            String image = "http://image.tmdb.org/t/p/w500" + castList.get(position).getProfilePath();
            Picasso.with(context).load(image).into(holder.imageView);
        }

    }


    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class  myViewHolder extends ViewHolder{
        ImageView imageView;
        TextView cname,oname;

        public myViewHolder(View itemView) {

            super( itemView );
            cname=itemView.findViewById( R.id.cname );
            oname=itemView.findViewById( R.id.oname );
            imageView=itemView.findViewById( R.id.profile );
        }
    }
}