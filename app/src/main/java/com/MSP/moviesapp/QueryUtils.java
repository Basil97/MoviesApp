package com.MSP.moviesapp;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {

    private QueryUtils() {}

    public static ArrayList<MovieDetails> fetchMovies(String stringURL) {
        URL url = createURL(stringURL);
        ArrayList<MovieDetails> movies = null;
        if (url != null) {
            String response = null;
            try {
                response = makeHTTPConnection(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            movies = extractMovies(response);
        }
        return movies;
    }

    private static String makeHTTPConnection(URL url) throws IOException {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        String jsonResponse = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
            }
        }
        return output.toString();
    }

    private static ArrayList<MovieDetails> extractMovies(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        ArrayList<MovieDetails> movies = null;
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray results = root.getJSONArray("results");

            movies = new ArrayList<>();
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);

                movies.add(new MovieDetails(movie.getString("title"),
                                            movie.getString("overview"),
                                            movie.getString("poster_path"),
                                            (float) movie.getDouble("vote_average")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
