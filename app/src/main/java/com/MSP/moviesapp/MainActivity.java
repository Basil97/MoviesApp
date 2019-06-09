package com.MSP.moviesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TMDB_URL = "https://api.themoviedb.org/3/movie/popular?api_key=695b65f9b780614359f31b01a1b39b12&language=en-US&page=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLoader loader = new MyLoader();
        loader.execute(TMDB_URL);
    }

    public void updateUI(final ArrayList<MovieDetails> movies) {
        MyAdapter adapter = new MyAdapter(this, movies);
        adapter.setOnMovieClickListener(new MyAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClick(int position) {
                Intent intent = new Intent(getBaseContext(), MovieDetailsActivity.class);
                intent.putExtra("movie", movies.get(position));
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public class MyLoader extends AsyncTask<String, Void, ArrayList<MovieDetails>> {

        @Override
        protected ArrayList<MovieDetails> doInBackground(String... strings) {
            if (TextUtils.isEmpty(strings[0])) return null;
            return QueryUtils.fetchMovies(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDetails> movies) {
            super.onPostExecute(movies);

            if (movies != null) {
                updateUI(movies);
            }
        }
    }
}
