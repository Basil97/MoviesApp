package com.MSP.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ImageView img = (ImageView) findViewById(R.id.img);
        TextView txtName = (TextView) findViewById(R.id.txtname);
        TextView txtDes = (TextView) findViewById(R.id.txtdes);

        Intent intent = getIntent();

        MovieDetails movie = intent.getExtras().getParcelable("movie");

        img.setImageResource(movie.getImgId());
        txtName.setText(movie.getName());
        txtDes.setText(movie.getDes());
        /*
        Bundle bu = intent.getExtras();

        img.setImageResource(bu.getInt("imgId"));
        txtName.setText(bu.getString("name"));
        txtDes.setText(bu.getString("des"));
        */
    }
}
