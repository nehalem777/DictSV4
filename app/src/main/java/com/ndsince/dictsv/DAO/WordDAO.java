package com.ndsince.dictsv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ndsince.dictsv.LogCheck;

import java.util.HashMap;

/**
 * Word Data acess object
 *
 * @see Word
 */
public class WordDAO {

    public static final String TAG = "WordDAO";

    //Datebase field
    private SQLiteDatabase mDb;
    private DBHelper mDBHelper;
    private String[] mAllColumns = {DBHelper.COLUMN_WORD_ID,
            DBHelper.COLUMN_WORD_WORD,
            DBHelper.COLUMN_WORD_TRANSLITERATED,
            DBHelper.COLUMN_WORD_TERMINOLOGY,
            DBHelper.COLUMN_WORD_CATEGORY_ID};

    private Context mContext;

    private Word word;
    private Category category;

    /**
     * Constructor
     *
     * @param context
     */
    public WordDAO(Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(context);

        //open database
        try {
            open();
        } catch (Exception e) {
            //Log.e(TAG, "SQLException on opening database" + e.getMessage());
        }
    }   // Constructor

    /**
     * Open database connection
     */
    public void open() {
        mDb = mDBHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Add word by categoryID(int)
     *
     * @param word       Word
     * @param trans      Transliterated (คำทับศัพท์)
     * @param termino    Terminology (ศัพท์บัญญัติ)
     * @param categoryID Category ID
     */
    public void addWord(String word, String termino, String trans, int categoryID) {
        //Add Content value and insert row
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_WORD_WORD, word);
        values.put(DBHelper.COLUMN_WORD_TERMINOLOGY, termino);
        values.put(DBHelper.COLUMN_WORD_TRANSLITERATED, trans);
        values.put(DBHelper.COLUMN_WORD_CATEGORY_ID, categoryID);

        long insertID = mDb.insert(DBHelper.TABLE_WORDS, null, values);

        //Log-Debug addWord() by WordDB
        LogCheck.d(TAG, "addWord", String.valueOf(insertID) + " - " + word);
    }

    /**
     * Add word by object
     *
     * @param word Word object
     * @see Word
     */
    public void addWord(Word word) {
        //Add Content value and insert row
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_WORD_WORD, word.getmWord());
        values.put(DBHelper.COLUMN_WORD_TERMINOLOGY, word.getmTermino());
        values.put(DBHelper.COLUMN_WORD_TRANSLITERATED, word.getmTrans());
        values.put(DBHelper.COLUMN_WORD_CATEGORY_ID, word.getmCategory().getmId());

        long insertID = mDb.insert(DBHelper.TABLE_WORDS, null, values);

        if(insertID>0) Toast.makeText(mContext, "Add Word Successful", Toast.LENGTH_SHORT).show();

        //Log-Debug addWord() by User
        LogCheck.d(TAG, "addWord", String.valueOf(insertID) + " - " + word.getmWord());
    }

    /**
     * getAllWord()
     * key set is WordID
     */
    public HashMap<Long, Word> getAllWord() {
        HashMap<Long, Word> hashMapWords = new HashMap<>();

        Cursor cursor = mDb.query(DBHelper.TABLE_WORDS, mAllColumns,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                word = cursorToWord(cursor);
                hashMapWords.put(word.getmId(), word);

                cursor.moveToNext();
            }
        }

        //Log-Debug LogCheck all HashMap
        for (Long  wordID : hashMapWords.keySet()){
            LogCheck.e(TAG, "getAllWord()", "ID HashMap : " + wordID);
        }

        if (cursor!=null) cursor.close();
        return hashMapWords;
    }

    /**
     * getWordByWhereArgs
     * @param whereArgs
     */
    public HashMap<Long, Word> getWordByWhereArgs(String whereArgs) {
        HashMap<Long, Word> hashMapWords = new HashMap<>();

        Cursor cursor = mDb.query(DBHelper.TABLE_WORDS, mAllColumns,
                whereArgs, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                word = cursorToWord(cursor);
                hashMapWords.put(word.getmId(), word);

                cursor.moveToNext();
            }
        }

        //Log-Debug LogCheck all HashMap
        for (Long  wordID : hashMapWords.keySet()){
            LogCheck.e(TAG, "getWordByWhereArgs()", "ID HashMap : " + wordID);
        }

        if (cursor!=null) cursor.close();
        return hashMapWords;
    }

    /**
     *updateWord
     * @param word
     */
    public void updateWord(Word word) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_WORD_WORD, word.getmWord());
        values.put(DBHelper.COLUMN_WORD_TERMINOLOGY, word.getmTermino());
        values.put(DBHelper.COLUMN_WORD_TRANSLITERATED, word.getmTrans());
        values.put(DBHelper.COLUMN_WORD_CATEGORY_ID, word.getmCategory().getmId());

        String[] whereArgs = new String[]{String.valueOf(word.getmId())};
        int updateID = mDb.update(DBHelper.TABLE_WORDS, values,
                DBHelper.COLUMN_WORD_ID + "=?", whereArgs);

        //Log-Debug updateWord() by User
        if (updateID > 0) LogCheck.d(TAG, "updateWord()" ,
                "แก้คำศัพท์ " + word.getmWord() + " แล้ว");
    }

    /**
     * deleteWord
     * @param word
     */
    public void deleteWord(Word word) {
        String[] whereArgs = new String[]{String.valueOf(word.getmId())};
        int deleteID = mDb.delete(DBHelper.TABLE_WORDS,
                DBHelper.COLUMN_WORD_ID + "=?", whereArgs);

        //Log-Debug deleteWord() by User
        if (deleteID > 0) LogCheck.d(TAG, "deleteWordByCategoryID" ,
                "ลบคำศัพท์ " + word.getmWord() + " แล้ว");

        FavoriteDAO favoriteDAO = new FavoriteDAO(mContext);
        favoriteDAO.deleteFavorite(word);
    }

    /**
     * deleteWordByCategoryID
     * @param word
     */
    public void deleteWordByCategoryID(Word word) {

        String[] whereArgs = new String[]{String.valueOf(word.getmCategory().getmId())};
        int deleteID = mDb.delete(DBHelper.TABLE_WORDS,
                DBHelper.COLUMN_WORD_CATEGORY_ID + "=?", whereArgs);

        //Log-Debug deleteWordByCategoryID() by User
        if (deleteID > 0) LogCheck.d(TAG, "deleteWordByCategoryID" ,
                "ลบคำศัพท์ในหมวด " + word.getmCategory().getmId() + " แล้ว");
    }

    //----------------------------------------------------------------------------------------------

    public boolean chkRepetitionWord(String wordName, int categoryID) {
        boolean chkRepetitionWord = true;
        //SELECT * FROM Words WHERE category_id = 0 AND word = wordName
        String[] whereArgs = new String[]{String.valueOf(wordName), String.valueOf(categoryID)};
        Cursor cursor = mDb.query(DBHelper.TABLE_WORDS, mAllColumns,
                DBHelper.COLUMN_WORD_WORD + "=? AND " +
                DBHelper.COLUMN_WORD_CATEGORY_ID + "=?",
                whereArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            chkRepetitionWord = !cursor.isAfterLast();
        }

        if (cursor != null) cursor.close();
        return chkRepetitionWord;
    }

    public boolean chkWordByCategoryID(int categoryID) {
        boolean chkWord = true;
        //SELECT * FROM Words WHERE category_id = 0 AND word = wordName
        String[] whereArgs = new String[]{String.valueOf(categoryID)};
        Cursor cursor = mDb.query(DBHelper.TABLE_WORDS, mAllColumns,
                DBHelper.COLUMN_WORD_CATEGORY_ID + "=?",
                whereArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            chkWord = !cursor.isAfterLast();
        }

        if (cursor != null) cursor.close();
        return chkWord;
    }

    private Word cursorToWord(Cursor cursor) {
        word = new Word();
        category = new Category();

        word.setmId(cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_WORD_ID)));
        word.setmWord(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_WORD_WORD)));
        word.setmTermino(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_WORD_TERMINOLOGY)));
        word.setmTrans(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_WORD_TRANSLITERATED)));

        category.setmId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_WORD_CATEGORY_ID)));
        word.setmCategory(category);

        return word;
    }
}
