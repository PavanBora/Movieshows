package com.example.pavanbora.movieshows;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pavanbora.movieshows.Networking.ApiClient;
import com.example.pavanbora.movieshows.Networking.ApiIInterface;
import com.example.pavanbora.movieshows.Pojo.TrailerMain;
import com.example.pavanbora.movieshows.Pojo.TrailerResult;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trailer extends Fragment implements YouTubePlayer.OnInitializedListener{
    public static final String API_KEY="AIzaSyAjafe5s03FNcPTAyRSCefMDUMfn9FBgwM";
    private static final String TAG = Trailer.class.getSimpleName();
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    List <TrailerResult> trailer;
    private String PREF_NAME = "MOVIEID";
    TrailerResult details;
    ConstraintLayout con;

    YouTubePlayer youTubePlayer;
    public Trailer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_trailer, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        con=view.findViewById( R.id.con );
        trailer=new ArrayList <TrailerResult>(  );
        SharedPreferences preferences1 = getActivity().getSharedPreferences( PREF_NAME, MODE_PRIVATE );
        int id = preferences1.getInt( "id", 1 );
        details = new TrailerResult();

        ApiIInterface apiInterface= ApiClient.getClient().create(ApiIInterface.class);
        Call<TrailerMain>trailerMainCall=apiInterface.getTrailer( id,"35fe486c7faba0d25e943e2b4d4a0f87");
        trailerMainCall.enqueue( new Callback <TrailerMain>() {
            @Override
            public void onResponse(Call <TrailerMain> call, Response<TrailerMain> response) {

                Log.d( "video", response.toString() );
                    details.setKey( response.body().getResults().get(0).getKey() );
                    trailer.add( details );
            }

            @Override
            public void onFailure(Call <TrailerMain> call, Throwable t) {

            }
        } );
        youTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
        youTubePlayerSupportFragment.initialize("AIzaSyAjafe5s03FNcPTAyRSCefMDUMfn9FBgwM", this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.trailer, youTubePlayerSupportFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (trailer.size()!=0) {
            this.youTubePlayer = youTubePlayer;
            this.youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            if(!b) {

                Log.d( "Key",details.getKey() );
                Log.d( "Trailers LIst",trailer.toString() );
                youTubePlayer.loadVideo( details.getKey() );
            }
        }
        else{
            Snackbar.make( con, "No Trailers Found", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this.getActivity(),1).show();
        } else {
            Toast.makeText(this.getActivity(),
                    "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                    Toast.LENGTH_LONG).show();
        }


    }
}


