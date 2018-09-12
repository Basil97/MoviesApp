package com.MSP.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Osama on 06/09/2018.
 */

public class MovieDetails implements Parcelable{

    private String name;
    private String des;
    private int imgId;
    private int sImgId;

    public MovieDetails(String name, String des, int imgId, int sImgId) {
        this.name = name;
        this.des = des;
        this.imgId = imgId;
        this.sImgId = sImgId;
    }

    protected MovieDetails(Parcel in) {
        name = in.readString();
        des = in.readString();
        imgId = in.readInt();
        sImgId = in.readInt();
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

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public int getImgId() {
        return imgId;
    }

    public int getsImgId() {
        return sImgId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(des);
        parcel.writeInt(imgId);
        parcel.writeInt(sImgId);
    }
}
