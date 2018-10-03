package com.MSP.moviesapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MovieDetails> movies;

    public MyAdapter(Context context, ArrayList<MovieDetails> movies) {
        this.context = context;
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


        view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);

        ImageView poster = view.findViewById(R.id.poster);
        TextView txtTitle = view.findViewById(R.id.txttitle);
        TextView txtVote = view.findViewById(R.id.txtvote);

        MovieDetails currentMovie = getItem(i);

        String posterPath = "https://image.tmdb.org/t/p/w185" + currentMovie.getPosterPath();
        Picasso.get().load(posterPath).into(poster);
        txtTitle.setText(currentMovie.getTitle());
        txtVote.setText(String.valueOf(currentMovie.getVoteAverage()));

        GradientDrawable drawable = (GradientDrawable) txtVote.getBackground();
        drawable.setColor(getVoteColor(currentMovie.getVoteAverage()));
        drawable.setStroke(6, getStrokeColor(currentMovie.getVoteAverage()));

        return view;
    }

    private int getStrokeColor(float voteAverage) {
        if (voteAverage >= 7) {
            return ContextCompat.getColor(context, R.color.DarkGreen);
        }else if (voteAverage >= 4) {
            return ContextCompat.getColor(context, R.color.DarkLime);
        }else if (voteAverage >= 0) {
            return ContextCompat.getColor(context, R.color.DarkRed);
        }
        return 0;
    }

    private int getVoteColor(float voteAverage) {
        if (voteAverage >= 7) {
            return ContextCompat.getColor(context, R.color.LightGreen);
        }else if (voteAverage >= 4) {
            return ContextCompat.getColor(context, R.color.LightLime);
        }else if (voteAverage >= 0) {
            return ContextCompat.getColor(context, R.color.LightRed);
        }
        return 0;
    }
}