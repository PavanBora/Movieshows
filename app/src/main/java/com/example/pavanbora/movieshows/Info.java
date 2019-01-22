package com.example.pavanbora.movieshows;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavanbora.movieshows.Networking.ApiClient;
import com.example.pavanbora.movieshows.Networking.ApiIInterface;
import com.example.pavanbora.movieshows.Pojo.Details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Info extends Fragment {
    TextView title,tagline,lang,releaseDate,status,revenue,runtime,popularity,budget,vote;
    private String PREF_NAME="MOVIEID";


    public Info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_info, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        title = view.findViewById( R.id.t1 );
        tagline = view.findViewById( R.id.t2 );
        lang = view.findViewById( R.id.t3 );
        releaseDate = view.findViewById( R.id.t4 );
        status=view.findViewById( R.id.textView6);
        revenue=view.findViewById( R.id.textView9);
        runtime=view.findViewById( R.id.textView8 );
        popularity=view.findViewById( R.id.textView10 );
        budget=view.findViewById( R.id.textView21);
        vote=view.findViewById( R.id.textView23 );

        final ApiIInterface apiInterface = ApiClient.getClient().create( ApiIInterface.class );
        SharedPreferences preferences=getActivity().getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        int id=preferences.getInt( "id",1);
        Call <Details> getMovieDetailsCall = apiInterface.getMovieDetails( id, "35fe486c7faba0d25e943e2b4d4a0f87" );
        getMovieDetailsCall.enqueue( new Callback <Details>() {
            @Override
            public void onResponse(Call <Details> call, Response <Details> response) {
                Log.d("DetailsResponse",response.toString());
                title.setText( response.body().getOriginalTitle() );
                tagline.setText(response.body().getTagline() );
                lang.setText(response.body().getOriginalLanguage() );
                releaseDate.setText(response.body().getReleaseDate() );
                status.setText(response.body().getStatus() );
                runtime.setText(String.valueOf( response.body().getRuntime() ) );
                revenue.setText(String.valueOf( response.body().getRevenue() ) );
                popularity.setText(String.valueOf(response.body().getPopularity()  ) );
                budget.setText( String.valueOf( response.body().getBudget() ) );
                vote.setText(String.valueOf(response.body().getVoteCount()) );


            }

            @Override
            public void onFailure(Call <Details> call, Throwable t) {

            }
        } );
    }
}
