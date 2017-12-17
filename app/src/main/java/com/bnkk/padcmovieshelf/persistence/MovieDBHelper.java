package com.bnkk.padcmovieshelf.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by E5-575G on 12/16/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";

    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
            MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.MovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER, " +
            MovieContract.MovieEntry.COLUMN_IS_VIDEO + " BOOLEAN, " +
            MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " DOUBLE, " +
            MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            MovieContract.MovieEntry.COLUMN_POPULARITY + " DOUBLE, " +
            MovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            MovieContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT, " +
            MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT, " +
            MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
            MovieContract.MovieEntry.COLUMN_IS_ADULT + " BOOLEAN, " +
            MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
            MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +

            " UNIQUE (" + MovieContract.MovieEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_GENRE_TABLE = "CREATE TABLE " + MovieContract.GenreEntry.TABLE_NAME + " (" +
            MovieContract.GenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.GenreEntry.COLUMN_GENRE_ID + " INTEGER, " +
            MovieContract.GenreEntry.COLUMN_GENRE_NAME + " TEXT, " +

            " UNIQUE (" + MovieContract.GenreEntry.COLUMN_GENRE_ID + ") ON CONFLICT IGNORE, " +
            " UNIQUE (" + MovieContract.GenreEntry.COLUMN_GENRE_NAME + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_MOVIE_GENRE_TABLE = "CREATE TABLE " + MovieContract.MovieGenreEntry.TABLE_NAME + " (" +
            MovieContract.MovieGenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.MovieGenreEntry.COLUMN_GENRE_ID + " INTEGER, " +
            MovieContract.MovieGenreEntry.COLUMN_MOVIE_ID + " INTEGER, " +

            " );";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_GENRE_TABLE);
        db.execSQL(SQL_CREATE_MOVIE_GENRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieGenreEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieContract.GenreEntry.TABLE_NAME);

        onCreate(db);

    }
}
