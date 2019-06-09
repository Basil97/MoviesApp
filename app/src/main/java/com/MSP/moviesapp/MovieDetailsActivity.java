package com.MSP.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ImageView poster = findViewById(R.id.poster);
        TextView txtTitle = findViewById(R.id.txttitle);
        TextView txtOverview = findViewById(R.id.txtoverview);
        RatingBar barVote = findViewById(R.id.votebar);

        Intent intent = getIntent();

        if (intent.getExtras() == null) return;
            MovieDetails movie = intent.getExtras().getParcelable("movie");

        String posterPath;
        if (movie != null) {
            posterPath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();

            Picasso.get().load(posterPath).into(poster);
            txtTitle.setText(movie.getTitle());
            txtOverview.setText(movie.getOverview());
            barVote.setIsIndicator(true);
            barVote.setRating(movie.getVoteAverage() / 2);
        }

    }
}
