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
import android.widget.TextView;

import com.example.pavanbora.movieshows.Adapter.CastAdapter;
import com.example.pavanbora.movieshows.Adapter.CrewAdapter;
import com.example.pavanbora.movieshows.Networking.ApiClient;
import com.example.pavanbora.movieshows.Networking.ApiIInterface;
import com.example.pavanbora.movieshows.Pojo.Cast;
import com.example.pavanbora.movieshows.Pojo.CastMain;
import com.example.pavanbora.movieshows.Pojo.Crew;
import com.example.pavanbora.movieshows.Pojo.Details;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverView extends Fragment {
    TextView overView;
    private String PREF_NAME="MOVIEID";

    Context context;
    RecyclerView castRV,crewRV;
    List<Cast> castList;
    List<Crew> crewList;
    Cast castResult ;
    Crew crewResult;
    CastAdapter castAdapter;
    CrewAdapter crewAdapter;

    public OverView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           return inflater.inflate( R.layout.fragment_over_view, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        overView = view.findViewById( R.id.overView );

        castList=new ArrayList<>();
        castRV=view.findViewById(R.id.recyclerView8);
        castRV.setLayoutManager( new LinearLayoutManager( context,LinearLayoutManager.HORIZONTAL,false ) );
        castAdapter=new CastAdapter(context,castList);
        castRV.setAdapter(castAdapter);

        crewList=new ArrayList<>();
        crewRV=view.findViewById(R.id.recyclerView9);
        crewRV.setLayoutManager( new LinearLayoutManager( context,LinearLayoutManager.HORIZONTAL,false ) );
        crewAdapter=new CrewAdapter(context,crewList);
        crewRV.setAdapter(crewAdapter);


        ApiIInterface apiInterface2= ApiClient.getClient().create(ApiIInterface.class);
        SharedPreferences preferences2 = getActivity().getSharedPreferences( PREF_NAME, MODE_PRIVATE );
        int id3 = preferences2.getInt( "id", 1 );
        final Call<CastMain> castMainCall=apiInterface2.getCast(id3,"35fe486c7faba0d25e943e2b4d4a0f87");
        castMainCall.enqueue( new Callback <CastMain>(){

            @Override
            public void onResponse(Call <CastMain> call, Response <CastMain> response) {
                Log.d( "CastMainResponse", response.toString() );
                for (int i=0;i<response.body().getCast().size();i++){
                    Cast cast=new Cast(  );
                    cast.setCharacter( response.body().getCast().get( i ).getCharacter() );
                    cast.setName( response.body().getCast().get( i ).getName() );
                    cast.setProfilePath(response.body().getCast().get( i ).getProfilePath() );

                    castList.add( cast );
                }
                castAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call <CastMain> call, Throwable t) {
                Log.d( "Error", t.toString() );

            }
        });


        ApiIInterface apiInterface3= ApiClient.getClient().create(ApiIInterface.class);
        SharedPreferences preferences3 = getActivity().getSharedPreferences( PREF_NAME, MODE_PRIVATE );
        int id4 = preferences3.getInt( "id", 1 );
        final Call<CastMain> castMainCall1=apiInterface3.getCast(id4,"35fe486c7faba0d25e943e2b4d4a0f87");
        castMainCall1.enqueue( new Callback <CastMain>(){

            @Override
            public void onResponse(Call <CastMain> call, Response <CastMain> response) {
                Log.d( "CastMainResponse", response.toString() );
                for (int i=0;i<response.body().getCrew().size();i++){
                    Crew crew=new Crew(  );
                    crew.setJob( response.body().getCrew().get( i ).getJob() );
                    crew.setName( response.body().getCrew().get( i ).getName() );
                    crew.setProfilePath(response.body().getCrew().get( i ).getProfilePath() );

                    crewList.add( crew );
                }
                crewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call <CastMain> call, Throwable t) {
                Log.d( "Error", t.toString() );

            }
        });

        final ApiIInterface apiInterface = ApiClient.getClient().create( ApiIInterface.class );
        SharedPreferences preferences = getActivity().getSharedPreferences( PREF_NAME, MODE_PRIVATE );
        final int id = preferences.getInt( "id", 1 );
        Call <Details> getMovieDetailsCall = apiInterface.getMovieDetails( id, "35fe486c7faba0d25e943e2b4d4a0f87" );
        getMovieDetailsCall.enqueue( new Callback <Details>() {


            @Override
            public void onResponse(Call <Details> call, Response <Details> response) {
                Log.d( "DetailsResponse", response.toString() );
                Details result = new Details();

                overView.setText( response.body().getOverview() );


            }


            @Override
            public void onFailure(Call <Details> call, Throwable t) {

            }
        } );
    }
    }
