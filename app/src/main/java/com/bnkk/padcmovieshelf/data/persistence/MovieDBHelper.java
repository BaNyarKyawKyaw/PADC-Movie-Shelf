package com.bnkk.padcmovieshelf.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bnkk.padcmovieshelf.data.persistence.MovieContract.MovieEntry;
import com.bnkk.padcmovieshelf.data.persistence.MovieContract.GenreEntry;
import com.bnkk.padcmovieshelf.data.persistence.MovieContract.MovieGenreEntry;

/**
 * Created by E5-575G on 12/16/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";

    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
            MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieEntry.COLUMN_MOVIE_ID + " INTEGER, " +
            MovieEntry.COLUMN_IS_VIDEO + " BOOLEAN, " +
            MovieEntry.COLUMN_VOTE_AVERAGE + " DOUBLE, " +
            MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            MovieEntry.COLUMN_POPULARITY + " DOUBLE, " +
            MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT, " +
            MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT, " +
            MovieEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
            MovieEntry.COLUMN_IS_ADULT + " BOOLEAN, " +
            MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
            MovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +

            " UNIQUE (" + MovieContract.MovieEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_GENRE_TABLE = "CREATE TABLE " + MovieContract.GenreEntry.TABLE_NAME + " (" +
            GenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GenreEntry.COLUMN_GENRE_ID + " INTEGER, " +
            GenreEntry.COLUMN_GENRE_NAME + " TEXT, " +

            " UNIQUE (" + MovieContract.GenreEntry.COLUMN_GENRE_ID + ") ON CONFLICT IGNORE, " +
            " UNIQUE (" + MovieContract.GenreEntry.COLUMN_GENRE_NAME + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_MOVIE_GENRE_TABLE = "CREATE TABLE " + MovieContract.MovieGenreEntry.TABLE_NAME + " (" +
            MovieGenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieGenreEntry.COLUMN_GENRE_ID + " INTEGER, " +
            MovieGenreEntry.COLUMN_MOVIE_ID + " INTEGER, " +

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
        /*
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GenreEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieGenreEntry.TABLE_NAME);

        onCreate(db);
        */
    }
}
