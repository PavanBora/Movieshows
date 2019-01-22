package com.example.pavanbora.movieshows;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.pavanbora.movieshows.Adapter.MyAdapter;
import com.example.pavanbora.movieshows.Adapter.PopularAdapter;
import com.example.pavanbora.movieshows.Adapter.TopAdapter;
import com.example.pavanbora.movieshows.Networking.ApiClient;
import com.example.pavanbora.movieshows.Networking.ApiIInterface;
import com.example.pavanbora.movieshows.Pojo.NowPlayingMain;
import com.example.pavanbora.movieshows.Pojo.PopularMain;
import com.example.pavanbora.movieshows.Pojo.PopularResult;
import com.example.pavanbora.movieshows.Pojo.TopMain;
import com.example.pavanbora.movieshows.Pojo.TopResult;
import com.example.pavanbora.movieshows.Pojo.UpcomingMainPojo;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePage extends AppCompatActivity {
    private static final String PREF_NAME = "pre";
    boolean isLoggedIn = true;
    private FirebaseAuth mAuth;
    RecyclerView upcomingRV;
    List<TopResult> upcomingMoviesList;
    MyAdapter myAdapter;
    PopularAdapter popularAdapter,nowAdapter;
    RecyclerView popularRV;
    PopularResult popularResult;
    List<PopularResult> popularResultList,nowplaylingList;

    RecyclerView  topRV,nowRV;
    List<TopResult> topList,nowList;
    TopResult topResult;
    TopAdapter topAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.signout) {
            AlertDialog.Builder builder = new AlertDialog.Builder( HomePage.this );
            builder.setTitle( "signout" );
            builder.setMessage( "Do you want to signout?" );
            builder.setPositiveButton( "Sign out", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    isLoggedIn=false;
                    SharedPreferences preferences1=getSharedPreferences( PREF_NAME,MODE_PRIVATE );
                    SharedPreferences.Editor editor=preferences1.edit();
                    editor.putBoolean( "isLoggedIn",isLoggedIn );
                    editor.apply();
                    editor.commit();
                    FirebaseAuth fAuth = FirebaseAuth.getInstance();
                    fAuth.signOut();
                    Intent intent = new Intent( HomePage.this, MainActivity.class );
                    startActivity( intent );
                    finish();
                }
            } );
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        popularResultList=new ArrayList<PopularResult>();
        popularRV=findViewById(R.id.rc1);
        popularRV.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));
        popularAdapter=new PopularAdapter(this,popularResultList);
        popularRV.setAdapter(popularAdapter);

        nowplaylingList=new ArrayList<PopularResult>();
        nowRV=findViewById(R.id.rc3);
        nowRV.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));
        nowAdapter=new PopularAdapter(this,nowplaylingList);
        nowRV.setAdapter(nowAdapter);

        topList=new ArrayList<TopResult>();
        topRV=findViewById(R.id.rc2);
        topRV.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));
        topAdapter=new TopAdapter(this,topList);
        topRV.setAdapter(topAdapter);


        upcomingMoviesList=new ArrayList<>();
        upcomingRV=findViewById(R.id.rc);
        upcomingRV.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));
        myAdapter=new MyAdapter(this,upcomingMoviesList);
        upcomingRV.setAdapter(myAdapter);


        ApiIInterface apiInterface= ApiClient.getClient().create(ApiIInterface.class);
        final Call<UpcomingMainPojo> upcomingMainPojoCall=apiInterface.getUpcomingMovies("35fe486c7faba0d25e943e2b4d4a0f87",1);
        upcomingMainPojoCall.enqueue(new Callback<UpcomingMainPojo>() {
                                         @Override
                                         public void onResponse(Call<UpcomingMainPojo> call, Response<UpcomingMainPojo> response) {


                                             Log.d("UpUrl", response.toString());
                                             for(int i = 0;i < response.body().getResults().size();i++){
                                                 TopResult result=new TopResult();
                                                 result.setBackdropPath(response.body().getResults().get(i).getBackdropPath());
                                                 result.setId(response.body().getResults().get(i).getId());
                                                 result.setPosterPath(response.body().getResults().get(i).getPosterPath());
                                                 result.setOriginalTitle(response.body().getResults().get(i).getOriginalTitle());
                                                 result.setVoteAverage(response.body().getResults().get(i).getVoteAverage());
                                                 result.setTitle(response.body().getResults().get(i).getTitle());
                                                 result.setReleaseDate(response.body().getResults().get(i).getReleaseDate());
                                                 upcomingMoviesList.add(result);
                                                 }
                                             myAdapter.notifyDataSetChanged();

                                         }


                                         @Override
                                         public void onFailure(Call<UpcomingMainPojo> call, Throwable t) {
                                             Log.d("Error",t.toString());

                                         }
                                     });

        final Call<PopularMain> popularMainCall;
        popularMainCall = apiInterface.getPopularMovies("35fe486c7faba0d25e943e2b4d4a0f87",1);
        popularMainCall.enqueue(new Callback<PopularMain>() {
            @Override
            public void onResponse(Call<PopularMain> call, Response<PopularMain> response) {
                for (int i=0;i<response.body().getResults().size();i++){
                    Log.d("UpUrl", response.toString());
                    PopularResult result1=new PopularResult();
                    result1.setBackdropPath(response.body().getResults().get(i).getBackdropPath());
                    result1.setId(response.body().getResults().get(i).getId());
                    result1.setPosterPath(response.body().getResults().get(i).getPosterPath());
                    result1.setOriginalTitle(response.body().getResults().get(i).getOriginalTitle());
                    result1.setVoteAverage(response.body().getResults().get(i).getVoteAverage());
                    result1.setTitle(response.body().getResults().get(i).getTitle());
                    result1.setReleaseDate(response.body().getResults().get(i).getReleaseDate());
                    popularResultList.add(result1);

                }
                popularAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<PopularMain> call, Throwable t) {
                Log.d("Error",t.toString());


            }
        });

        final Call<TopMain> topMainCall;
        topMainCall=apiInterface.getTopMovies("35fe486c7faba0d25e943e2b4d4a0f87",1);
        topMainCall.enqueue(new Callback<TopMain>() {
            @Override
            public void onResponse( Call<TopMain> call, Response<TopMain> response) {
                Log.d("UpUrl", response.toString());
                for (int i=0;i<response.body().getResults().size(); i++) {
                    TopResult result=new TopResult();
                    result.setBackdropPath(response.body().getResults().get(i).getBackdropPath());
                    result.setId(response.body().getResults().get(i).getId());
                    result.setPosterPath(response.body().getResults().get(i).getPosterPath());
                    result.setOriginalTitle(response.body().getResults().get(i).getOriginalTitle());
                    result.setVoteAverage(response.body().getResults().get(i).getVoteAverage());
                    result.setTitle(response.body().getResults().get(i).getTitle());
                    result.setReleaseDate(response.body().getResults().get(i).getReleaseDate());
                    topList.add(result);

                }
                topAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopMain> call, Throwable t) {
                Log.d("Error",t.toString());


            }

        });
        final Call<NowPlayingMain> nowPlayingMainCall;
        nowPlayingMainCall=apiInterface.getNowPlayingMovies("35fe486c7faba0d25e943e2b4d4a0f87",1);
        nowPlayingMainCall.enqueue(new Callback<NowPlayingMain>() {
            @Override
            public void onResponse( Call<NowPlayingMain> call, Response<NowPlayingMain> response) {
                Log.d("UpUrl", response.toString());
                for (int i=0;i<response.body().getResults().size(); i++) {
                    PopularResult result=new PopularResult();
                    result.setBackdropPath(response.body().getResults().get(i).getBackdropPath());
                    result.setId(response.body().getResults().get(i).getId());
                    result.setPosterPath(response.body().getResults().get(i).getPosterPath());
                    result.setOriginalTitle(response.body().getResults().get(i).getOriginalTitle());
                    result.setVoteAverage(response.body().getResults().get(i).getVoteAverage());
                    result.setTitle(response.body().getResults().get(i).getTitle());
                    result.setReleaseDate(response.body().getResults().get(i).getReleaseDate());
                    nowplaylingList.add(result);

                }
                nowAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NowPlayingMain> call, Throwable t) {
                Log.d("Error",t.toString());


            }

        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}