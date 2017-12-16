package com.bnkk.padcmovieshelf.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class MovieVO {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private int movieId;

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

    public int getMovieId() {
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
}
