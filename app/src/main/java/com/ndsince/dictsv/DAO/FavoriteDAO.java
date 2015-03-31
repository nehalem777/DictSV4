package com.ndsince.dictsv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ndsince.dictsv.LogCheck;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Since on 18/3/2558.
 */
public class FavoriteDAO {
    public static final String TAG = "FavoriteDAO";

    //Database field
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_FAVORITE_ID,
            DBHelper.COLUMN_FAVORITE_WORD_ID};

    private Context mContext;
    private Word word;
    private Category category;
    private Favorite favorite;

    public FavoriteDAO(Context context) {
        this.mContext = context;
        this.mDbHelper = new DBHelper(context);

        //open database
        try{
            open();
        } catch (SQLException e){

        }
    }

    public void open() throws SQLException{
        mDb = mDbHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------------------------------------

    public void addFavorite(Word word){
        //Insert Content value
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_FAVORITE_WORD_ID, word.getmId());

        //insert row and id category
        long insertID = mDb.insert(DBHelper.TABLE_FAVORITE, null, values);

        //Log-Debug addFavorite
        //Check Word ID
        if (insertID > 0) {
            LogCheck.d(TAG, "addFavorite(Word word)", "Create " + insertID + " : " + word.getmId());
        } else {
            LogCheck.d(TAG, "addFavorite(Word word)", "No Word");
        }
    }

    /**
     * hashMap keySet is WordID Favorite
     * @return hashMapFavorities
     */
    public HashMap<Long,Favorite> getAllFavorite() {

        HashMap<Long,Favorite> hashMapFavorities = new HashMap<>();
        word = new Word();

        Cursor cursor = mDb.query(DBHelper.TABLE_FAVORITE, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                favorite = cursorToFavorite(cursor);
                hashMapFavorities.put(favorite.getWord().getmId(), favorite);

                cursor.moveToNext();
            }
        }

        //Log-Debug getAllFavorite()
        for (Long  wordID : hashMapFavorities.keySet()){
            LogCheck.e(TAG, "getAllFavorite()", "ID HashMap : " + wordID);
        }

        if(cursor!=null) cursor.close();
        return hashMapFavorities;
    }

    /**
     * deleteFavorite
     * @param word
     */
    public void deleteFavorite(Word word) {
        String[] whereArgs = new String[]{String.valueOf(word.getmId())};
        int deleteID = mDb.delete(DBHelper.TABLE_FAVORITE,
                DBHelper.COLUMN_FAVORITE_WORD_ID + "=?", whereArgs);

        //Log-Debug deleteFavorite()
        if (deleteID > 0) LogCheck.d(TAG, "deleteFavorite" ,
                "ลบ Fav " + word.getmWord() + " แล้ว");
        else LogCheck.d(TAG, "deleteFavorite" ,
                "ไม่พบ Fav " + word.getmWord());
    }

    //----------------------------------------------------------------------------------------------
    public Favorite cursorToFavorite(Cursor cursor){
        favorite = new Favorite();
        word = new Word();

        favorite.setmId(cursor.getLong(
                cursor.getColumnIndex(DBHelper.COLUMN_FAVORITE_ID)));
        //set word object
        word.setmId(cursor.getLong(
                cursor.getColumnIndex(DBHelper.COLUMN_FAVORITE_WORD_ID)));
        favorite.setWord(word);

        return favorite;
    }
}
