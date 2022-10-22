package com.example.omdbandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.omdbandroid.adapter.HomeRecyclerAdapter;
import com.example.omdbandroid.interfaces.MovieApi;
import com.example.omdbandroid.model.Response;
import com.example.omdbandroid.model.Result;
import com.example.omdbandroid.services.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private HomeRecyclerAdapter adapter;
    String movie_id = "popular";
    RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        callRetrofit();
    }

    private void callRetrofit() {
        MovieApi movieApi = Api.retrofitBuilder().create(MovieApi.class);
        Call<Response> call = movieApi.getMovie(movie_id,Api.API_KEY);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> list = response.body().getResults();
                adapter = new HomeRecyclerAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.fillInStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1){
                    MovieApi movieApi = Api.retrofitBuilder().create(MovieApi.class);
                    Call<Response> call = movieApi.getSearch(Api.API_KEY,newText);
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            List<Result> list = response.body().getResults();
                            adapter = new HomeRecyclerAdapter(MainActivity.this, list);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            t.fillInStackTrace();
                        }
                    });
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}