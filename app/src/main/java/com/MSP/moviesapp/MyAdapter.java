package com.MSP.moviesapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MovieDetails> movies;

    MyAdapter(Context context, ArrayList<MovieDetails> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieDetails currentMovie = movies.get(position);

        String posterPath = "https://image.tmdb.org/t/p/w185" + currentMovie.getPosterPath();
        Picasso.get().load(posterPath).into(holder.poster);
        holder.txtTitle.setText(currentMovie.getTitle());
        holder.txtVote.setText(String.valueOf(currentMovie.getVoteAverage()));

        GradientDrawable drawable = (GradientDrawable) holder.txtVote.getBackground();
        drawable.setStroke(6, getStrokeColor(currentMovie.getVoteAverage()));
        drawable.setColor(getVoteColor(currentMovie.getVoteAverage()));
    }

    void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        MyViewHolder.setOnMovieClickListener(onMovieClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster;
        private TextView txtTitle, txtVote;
        private static OnMovieClickListener mOnMovieClickListener;

        MyViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            txtTitle = itemView.findViewById(R.id.txttitle);
            txtVote = itemView.findViewById(R.id.txtvote);

            itemView.setOnClickListener(this);
        }

        static void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
            mOnMovieClickListener = onMovieClickListener;
        }

        @Override
        public void onClick(View view) {
            if (mOnMovieClickListener != null)
                mOnMovieClickListener.OnMovieClick(getAdapterPosition());
        }
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

    public interface OnMovieClickListener {
        void OnMovieClick(int position);
    }
}