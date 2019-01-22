package com.example.pavanbora.movieshows;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.pavanbora.movieshows.Networking.ApiClient;
import com.example.pavanbora.movieshows.Networking.ApiIInterface;
import com.example.pavanbora.movieshows.Pojo.Details;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetails extends AppCompatActivity {
    ImageView imageView;
   TabLayout tabLayout;
    ViewPager viewPager;
    private String PREF_NAME="MOVIEID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imageView=findViewById(R.id.detailsposter);



        tabLayout=findViewById( R.id.tabs );
        viewPager=findViewById(R.id.viewpager);




        MyPagerAdapter myPagerAdapter=new MyPagerAdapter( getSupportFragmentManager() );
        myPagerAdapter.addFragment( new Info(),"info" );
        myPagerAdapter.addFragment( new OverView(),"OverView" );
        myPagerAdapter.addFragment( new Rating(),"Rating" );
        myPagerAdapter.addFragment( new Trailer(),"Trailer" );

        viewPager.setAdapter( myPagerAdapter );
        tabLayout.setupWithViewPager( viewPager );


       int id= getIntent().getExtras().getInt("id");
        SharedPreferences preferences=getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("id",id);
        editor.apply();
        editor.commit();




        final ApiIInterface apiInterface= ApiClient.getClient().create(ApiIInterface.class);


        Call<Details>getMovieDetailsCall=apiInterface.getMovieDetails(id,"35fe486c7faba0d25e943e2b4d4a0f87");
        getMovieDetailsCall.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                Log.d("DetailsResponse",response.toString());
                Details result=new Details();
                result.setPosterPath("http://image.tmdb.org/t/p/w500"+response.body().getPosterPath());
                Picasso.with(MovieDetails.this).load(result.getPosterPath()).into(imageView);



            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {

            }
        });

    }

    }
