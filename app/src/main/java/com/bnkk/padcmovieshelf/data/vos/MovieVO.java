package com.bnkk.padcmovieshelf.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.bnkk.padcmovieshelf.persistence.MovieContract;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieVO {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private String movieId;

    @SerializedName("video")
    private boolean isVideo;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("overview")
    private String overView;

    @SerializedName("release_date")
    private String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public String getMovieId() {
        return movieId;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieContract.MovieEntry.COLUMN_VOTE_COUNT, voteCount);
        contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movieId);
        contentValues.put(MovieContract.MovieEntry.COLUMN_IS_VIDEO, isVideo);
        contentValues.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, title);
        contentValues.put(MovieContract.MovieEntry.COLUMN_POPULARITY, popularity);
        contentValues.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
        contentValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE, originalLanguage);
        contentValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        contentValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, backdropPath);
        contentValues.put(MovieContract.MovieEntry.COLUMN_IS_ADULT, isAdult);
        contentValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, overView);
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);

        return contentValues;
    }

    public static MovieVO parseFromCursor(Context context, Cursor cursor) {

        MovieVO movie = new MovieVO();

        movie.voteCount = cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_COUNT));
        movie.movieId = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_ID));
        //movie.isVideo = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IS_VIDEO) == 1;
        if (cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IS_VIDEO) == 1) {
            movie.isVideo = true;
        } else if (cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IS_VIDEO) == 0) {
            movie.isVideo = false;
        }
        movie.voteAverage = cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE));
        movie.title = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_ID));
        movie.popularity = cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POPULARITY));
        movie.posterPath = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_PATH));
        movie.originalLanguage = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        movie.originalTitle = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE));
        movie.backdropPath = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH));
        //movie.isAdult = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IS_ADULT) == 1;
        if (cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IS_ADULT) == 1) {
            movie.isAdult = true;
        } else if (cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IS_ADULT) == 0) {
            movie.isAdult = false;
        }
        movie.overView = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_OVERVIEW));
        movie.releaseDate = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE));

        movie.genreIds = loadGenreInMovie(context, movie.getMovieId());

        return movie;
    }

    private static List<Integer> loadGenreInMovie(Context context, String movieId) {
        Cursor genreInMovieCursor = context.getContentResolver().query(MovieContract.MovieGenreEntry.CONTENT_URI,
                null,
                MovieContract.MovieGenreEntry.COLUMN_MOVIE_ID + " = ?", new String[]{movieId},
                null);

        if (genreInMovieCursor != null && genreInMovieCursor.moveToFirst()) {
            List<Integer> genreInMovie = new ArrayList<>();
            do {
                genreInMovie.add(
                        genreInMovieCursor.getInt(
                                genreInMovieCursor.getColumnIndex(MovieContract.MovieGenreEntry.COLUMN_GENRE_ID)
                        )
                );
            } while (genreInMovieCursor.moveToNext());
            genreInMovieCursor.close();
            return genreInMovie;
        }
        return null;
    }
}
