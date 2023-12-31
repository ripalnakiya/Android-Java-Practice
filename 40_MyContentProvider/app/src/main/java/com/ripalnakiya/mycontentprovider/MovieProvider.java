package com.ripalnakiya.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MovieProvider extends ContentProvider {

    SQLiteDatabase database;
    private static final String AUTHORITY = "com.ripalnakiya.movie.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/movies");
    private static final String TABLE_MOVIES = "movies";

    static int MOVIES = 1;
    static int MOVIES_ID = 2;

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "movies", MOVIES);
        uriMatcher.addURI(AUTHORITY, "movies/#", MOVIES_ID);
    }

    public MovieProvider() {
    }

    @Override
    public boolean onCreate() {
        DBHelper dbHelper = new DBHelper(getContext());
        database = dbHelper.getWritableDatabase();
        if(database != null)
            return true;
        return false;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = database.insert(TABLE_MOVIES, null, values);
        if(row > 0) {
            uri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_MOVIES, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int row = database.delete(TABLE_MOVIES, selection, selectionArgs);
        return row;
    }
}