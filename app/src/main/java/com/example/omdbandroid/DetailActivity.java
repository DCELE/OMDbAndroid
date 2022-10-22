package com.example.omdbandroid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.omdbandroid.model.Result;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String extra = "extra";
    String title,overview,image, release_date;
    ImageView movie_poster;
    double vote_average;
    TextView movie_title, movie_synopsis, movie_rate, movie_release;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        movie_title = findViewById(R.id.movieTitle);
        movie_synopsis = findViewById(R.id.movieSynosis);
        movie_poster = findViewById(R.id.moviePoster);
        movie_rate = findViewById(R.id.movieRate);
        movie_release = findViewById(R.id.movieRelease);

        result = getIntent().getParcelableExtra(extra);

        title = result.getTitle();
        overview = result.getOverview();
        image = result.getPosterPath();
        vote_average = result.getVoteAverage();
        release_date = result.getReleaseDate();

        movie_title.setText(title);
        movie_synopsis.setText(overview);
        movie_rate.setText(String.valueOf(result.getVoteAverage()));
        movie_release.setText(release_date);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + image).into(movie_poster);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
