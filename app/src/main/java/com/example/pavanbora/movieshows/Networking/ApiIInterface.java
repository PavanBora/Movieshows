package com.example.pavanbora.movieshows.Networking;

import com.example.pavanbora.movieshows.Pojo.CastMain;
import com.example.pavanbora.movieshows.Pojo.Details;
import com.example.pavanbora.movieshows.Pojo.NowPlayingMain;
import com.example.pavanbora.movieshows.Pojo.PopularMain;
import com.example.pavanbora.movieshows.Pojo.ReviewMain;
import com.example.pavanbora.movieshows.Pojo.TopMain;
import com.example.pavanbora.movieshows.Pojo.UpcomingMainPojo;
import com.example.pavanbora.movieshows.Pojo.TrailerMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiIInterface {

    @GET("movie/upcoming")
    Call<UpcomingMainPojo>getUpcomingMovies(@Query("api_key")String apikey, @Query("page")int page);
    @GET("movie/popular")
    Call<PopularMain>getPopularMovies(@Query("api_key")String api_key,@Query("page")int page);
    @GET("movie/top_rated")
    Call<TopMain>getTopMovies(@Query("api_key")String api_key, @Query("page")int page);
    @GET("movie/now_playing")
    Call<NowPlayingMain>getNowPlayingMovies(@Query("api_key")String api_key,@Query("page")int page);
    @GET("movie/{movie_id}")
    Call<Details>getMovieDetails(@Path("movie_id")int id,@Query("api_key")String api_key);
    @GET("movie/{movie_id}/reviews")
    Call<ReviewMain>getReviews(@Path( "movie_id")int id,@Query( "api_key" )String api_key);
    @GET("movie/{movie_id}/credits")
    Call<CastMain>getCast(@Path( "movie_id" )int id,@Query( "api_key" )String api_key);
    @GET("movie/{movie_id}/credits")
    Call<CastMain>getCrew(@Path( "movie_id" )int id,@Query( "api_key" )String api_key);

    @GET("movie/{movie_id}/videos")
    Call<TrailerMain>getTrailer(@Path( "movie_id" )int id, @Query( "api_key" )String api_key);

}
