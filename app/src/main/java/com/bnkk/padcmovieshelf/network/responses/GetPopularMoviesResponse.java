package com.bnkk.padcmovieshelf.network.responses;

import com.bnkk.padcmovieshelf.data.vos.MovieVO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by E5-575G on 12/12/2017.
 */

public class GetPopularMoviesResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int page;

    @SerializedName("popular-movies")
    private List<MovieVO> popularMovies;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPage() {
        return page;
    }

    public List<MovieVO> getPopularMovies() {
        return popularMovies;
    }
}
