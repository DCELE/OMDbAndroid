package com.example.omdbandroid.interfaces;

import com.example.omdbandroid.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("search/movie")
    Call<Response> getSearch(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("movie/{movie_id}")
    Call<Response> getMovie(
            @Path("movie_id") String movie_id,
            @Query("api_key") String api_key
    );
}
