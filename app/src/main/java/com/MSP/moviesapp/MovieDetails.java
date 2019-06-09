package com.MSP.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Osama on 06/09/2018.
 */

public class MovieDetails implements Parcelable{

    private String title;
    private String overview;
    private String posterPath;
    private float voteAverage;

    MovieDetails(String title, String overview, String posterPath, float voteAverage) {
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    private MovieDetails(Parcel in) {
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        voteAverage = in.readFloat();
    }

    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };

    String getTitle() {
        return title;
    }

    String getOverview() {
        return overview;
    }

    String getPosterPath() {
        return posterPath;
    }

    float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
        parcel.writeFloat(voteAverage);
    }
}
