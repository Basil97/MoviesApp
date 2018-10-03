package com.MSP.moviesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(getApplicationContext(), movies));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieDetails movie = movies.get(i);
                Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
            }
        });
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
