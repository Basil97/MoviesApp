package com.MSP.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<MovieDetails> movies = new ArrayList<>();
        movies.add(new MovieDetails("Avengers: Infinity War (2018)",
                getString(R.string.avengers), R.drawable.avengers, R.drawable.avengers_small));
        movies.add(new MovieDetails("Deadpool 2 (2018)",
                getString(R.string.deadpool), R.drawable.deadpool, R.drawable.deadpool_small));
        movies.add(new MovieDetails("The Meg (2018)",
                getString(R.string.themeg), R.drawable.themeg, R.drawable.themeg_small));
        movies.add(new MovieDetails("Mission: Impossible - Fallout (2018)",
                getString(R.string.missionimpossible), R.drawable.missionimpossible, R.drawable.missionimpossible_small));
        movies.add(new MovieDetails("Black Panther (2018)",
                getString(R.string.blackpanther), R.drawable.blackpanther, R.drawable.blackpanther_small));
        movies.add(new MovieDetails("Ant-Man and the Wasp (2018)",
                getString(R.string.antman), R.drawable.antman, R.drawable.antman_small));

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new MyAdapter(movies));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieDetails selectedMovie = movies.get(i);
                Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);

                intent.putExtra("movie", selectedMovie);
                /*
                Bundle bundle = new Bundle();
                bundle.putString("name", selectedMovie.getName());
                bundle.putString("des", selectedMovie.getDes());
                bundle.putInt("imgId", selectedMovie.getImgId());

                intent.putExtras(bundle);
                */
                startActivity(intent);
            }
        });
    }

    public class MyAdapter extends BaseAdapter{

        private ArrayList<MovieDetails> movies;

        public MyAdapter(ArrayList<MovieDetails> movies) {
            this.movies = movies;
        }

        @Override
        public int getCount() {
            return movies.size();
        }

        @Override
        public MovieDetails getItem(int i) {
            return movies.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(R.layout.list_item, null);

            ImageView img = view.findViewById(R.id.img);
            TextView txtName = view.findViewById(R.id.txtname);

            MovieDetails currentMovie = getItem(i);
            img.setImageResource(currentMovie.getsImgId());
            txtName.setText(currentMovie.getName());

            return view;
        }
    }
}
