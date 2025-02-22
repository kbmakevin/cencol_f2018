package com.example.inika.simplecontentprovider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class BooksProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.jfdimarzio.provider.Books";
    static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER_NAME + "/books");
    static final String _ID = "_id";
    static final String TITLE = "title";
    static final String ISBN = "isbn";
    static final int BOOKS = 1;
    static final int BOOK_ID = 2;
    //used to match uri
    private static final UriMatcher uriMatcher;
    //static initializer
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "books", BOOKS);
        uriMatcher.addURI(PROVIDER_NAME, "books/#", BOOK_ID);
    }
    //---for database use---
    SQLiteDatabase booksDB;
    static final String DATABASE_NAME = "Books";
    static final String DATABASE_TABLE = "titles";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE +
                    " (_id integer primary key autoincrement, "
                    + "title text not null, isbn text not null);";
    //inner class is private
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        //override the constructor
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w("Provider database",
                    "Upgrading database from version " +
                            oldVersion + " to " + newVersion +
                            ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }

    //crud methods for the content provider
    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        // arg0 = uri
        // arg1 = selection
        // arg2 = selectionArgs
        int count=0;
        //differentiate between different uri objects
        switch (uriMatcher.match(arg0)){
            case BOOKS:
                count = booksDB.delete(
                        DATABASE_TABLE,
                        arg1,
                        arg2);
                break;
            case BOOK_ID:
                String id = arg0.getPathSegments().get(1);
                count = booksDB.delete(
                        DATABASE_TABLE,
                        _ID + " = " + id +
                                (!TextUtils.isEmpty(arg1) ? " AND (" +
                                        arg1 + ')' : ""),
                arg2);
                break;
            default: throw new IllegalArgumentException("Unknown URI " + arg0);
        }
        getContext().getContentResolver().notifyChange(arg0, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            //---get all books---
            case BOOKS:
                return "vnd.android.cursor.dir/vnd.learn2develop.books ";

            //---get a particular book---
            case BOOK_ID:
                return "vnd.android.cursor.item/vnd.learn2develop.books ";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //---add a new book using SQLiteDatabase method insert ---
        long rowID = booksDB.insert(
                DATABASE_TABLE,
                "",
                values);
        //---if added successfully---
        if (rowID>0)
        {
            //gte the uri object
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            //notify for the change
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }
    @Override
    public boolean onCreate() {
        //create the application context
        Context context = getContext();
        //create the DatabaseHelper object
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        //open the database for writing
        booksDB = dbHelper.getWritableDatabase();
        return (booksDB == null)? false:true;
    }

    //allows the other apps (like what's up, etc) to query your data
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(DATABASE_TABLE);
        if (uriMatcher.match(uri) == BOOK_ID)
            //---if getting a particular book---
            //getPathSegments().get(1) will return the path segment with index '1'.
            // for example, if you have content://com.myapp/first/second/third/
            // get(1) will return 'second'. get(2) will return 'third' and so on...
            sqlBuilder.appendWhere(
                    _ID + " = " + uri.getPathSegments().get(1));
        if (sortOrder==null || sortOrder=="")
            sortOrder = TITLE;
        //call SQLBuilder's query method to query the data
        Cursor c = sqlBuilder.query(
                booksDB,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        //---register to watch a content URI for changes---
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case BOOKS:
                count = booksDB.update(
                        DATABASE_TABLE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case BOOK_ID:
                count = booksDB.update(
                        DATABASE_TABLE,
                        values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +
                                        selection + ')' : ""),
                selectionArgs);
                break;
            default: throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
