package com.data.www.datasavedemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.data.www.datasavedemo.entity.Person;
import com.data.www.datasavedemo.utils.DBHelper;

public class MyContentProvider extends ContentProvider {
    public static final int TABLE = 0;
    public static final int TABLE_ITEM = 1;
    public static final String auth = "com.data.www.datasavedemo.provider";
    private DBHelper helper;
    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(auth,"person/",TABLE);
        uriMatcher.addURI(auth,"person/#",TABLE_ITEM);
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        switch (uriMatcher.match(uri)){
            case TABLE:
                return "vnd.android.cursor.dir/vnd.com.data.www.datasavedemo.person";
            case TABLE_ITEM:
                return "vnd.android.cursor.item/vnd.com.data.www.datasavedemo.person";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case TABLE:
                Person person = new Person();
                person.address = values.getAsString("address");
                person.name = values.getAsString("name");
                person.phone = values.getAsString("phone");
                helper.insert(person);
            break;
            case TABLE_ITEM:

            break;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE:
                String table = "person";
                return helper.getAll(table);
            case TABLE_ITEM:
                return helper.getSelectPersons(selection,selectionArgs,sortOrder);
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
