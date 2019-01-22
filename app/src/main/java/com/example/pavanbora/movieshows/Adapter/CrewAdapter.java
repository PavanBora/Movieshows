package com.example.pavanbora.movieshows.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavanbora.movieshows.Pojo.Crew;
import com.example.pavanbora.movieshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.myViewHolder> {
    Context context;
    List<Crew> crewList;
    Crew crewResult;

    public CrewAdapter(Context context, List<Crew> crewList) {
        this.context=context;
        this.crewList = crewList;
    }
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.cast, parent, false);
        return new CrewAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.cname.setText( crewList.get( position ).getJob() );
        holder.oname.setText(crewList.get( position ).getName());
        if (crewList.get(position).getProfilePath() != null) {
            String image = "http://image.tmdb.org/t/p/w500" + crewList.get(position).getProfilePath();
            Log.d("imageUrl",image);
            Picasso.with(context).load(image).into(holder.imageView);

        } else

        {
            String image = "http://image.tmdb.org/t/p/w500" + crewList.get(position).getProfilePath();
            Picasso.with(context).load(image).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public class  myViewHolder extends RecyclerView.ViewHolder {
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
