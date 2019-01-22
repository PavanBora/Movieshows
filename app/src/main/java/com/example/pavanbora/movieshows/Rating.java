package com.example.pavanbora.movieshows;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.pavanbora.movieshows.Adapter.ReviewAdapter;
import com.example.pavanbora.movieshows.Networking.ApiClient;
import com.example.pavanbora.movieshows.Networking.ApiIInterface;
import com.example.pavanbora.movieshows.Pojo.Details;
import com.example.pavanbora.movieshows.Pojo.ReviewMain;
import com.example.pavanbora.movieshows.Pojo.ReviewResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rating extends Fragment {
    RatingBar ratingBar;
Context context;
    RecyclerView reviewRV;
    List<ReviewResult> reviewList;
    ReviewResult reviewResult ;
    ReviewAdapter reviewAdapter;


    private String PREF_NAME = "MOVIEID";



    public Rating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_rating, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ratingBar = view.findViewById( R.id.ratingBar );

        reviewList=new ArrayList<>();
        reviewRV=view.findViewById(R.id.recyclerView4);
        reviewRV.setLayoutManager( new LinearLayoutManager( context,LinearLayoutManager.VERTICAL,false ) );
        reviewAdapter=new ReviewAdapter(context,reviewList);
        reviewRV.setAdapter(reviewAdapter);

        ApiIInterface apiInterface1= ApiClient.getClient().create(ApiIInterface.class);
        SharedPreferences preferences1 = getActivity().getSharedPreferences( PREF_NAME, MODE_PRIVATE );
        int id = preferences1.getInt( "id", 1 );
        final Call<ReviewMain> reviewMainCall=apiInterface1.getReviews(id,"35fe486c7faba0d25e943e2b4d4a0f87");
        reviewMainCall.enqueue( new Callback <ReviewMain>() {
            @Override
            public void onResponse(Call <ReviewMain> call, Response <ReviewMain> response) {
                Log.d( "ReviewMainResponse", response.toString() );

                for (int i = 0; i < response.body().getResults().size(); i++) {
                    ReviewResult reviewResult = new ReviewResult();
                    reviewResult.setAuthor( response.body().getResults().get( i ).getAuthor() );
                    reviewResult.setContent( response.body().getResults().get( i ).getContent() );

                    reviewList.add( reviewResult );
                }
                reviewAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call <ReviewMain> call, Throwable t) {
                Log.d( "Error", t.toString() );

            }
        });



        final ApiIInterface apiInterface = ApiClient.getClient().create( ApiIInterface.class );
        SharedPreferences preferences = getActivity().getSharedPreferences( PREF_NAME, MODE_PRIVATE );
        int id1 = preferences.getInt( "id", 1 );
        Call <Details> getMovieDetailsCall = apiInterface.getMovieDetails( id1, "35fe486c7faba0d25e943e2b4d4a0f87" );
        getMovieDetailsCall.enqueue( new Callback <Details>() {
            @Override
            public void onResponse(Call <Details> call, Response <Details> response) {
                Log.d( "DetailsResponse", response.toString() );
                ratingBar.setRating( (float) response.body().getVoteAverage() / 2 );


            }

            @Override
            public void onFailure(Call <Details> call, Throwable t) {
                Log.d( "Error", t.toString() );

            }
        } );



}
}