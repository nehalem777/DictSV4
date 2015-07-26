package com.ndsince.dictsv.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.alertdialogpro.ProgressDialogPro;
import com.ndsince.dictsv.DAO.DBHelper;
import com.ndsince.dictsv.DAO.WordDB;
import com.ndsince.dictsv.LogCheck;
import com.ndsince.dictsv.Preferences;

/**
 * Created by Since on 26/3/2558.
 */
public class AddWordTask extends AsyncTask<Void, Integer, Boolean> {

    private static final String TAG = "AddWordTask";

    ProgressDialogPro progressDialogPro;
    Context mContext;
    DBHelper mDBHelper;
    SQLiteDatabase mDb;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    boolean running;

    public AddWordTask(Context context) {
        super();
        this.mContext = context;
        progressDialogPro = new ProgressDialogPro(mContext);
        mDBHelper = new DBHelper(mContext);
        mDb = mDBHelper.getWritableDatabase();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //SharedPreferences
        sp = mContext.getSharedPreferences(Preferences.PREF_NAME, Preferences.MODE);
        spEditor = sp.edit();

        if (!sp.getBoolean(Preferences.KEY_FIRSTRUN, true)) {
            mDBHelper.onUpgrade(mDb, sp.getInt(Preferences.KEY_DATABASE_VERSION, 1),
                    WordDB.DATABASE_VERSION);
        }

        //progressDialog
        running = true;

        progressDialogPro.setTitle("Add Database");
        progressDialogPro.setMessage("Please Wait");
        progressDialogPro.show();

        progressDialogPro.setCancelable(false);
        progressDialogPro.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        //progressDialogPro.setMessage(String.valueOf(values[0]));


    }


    @Override
    protected Boolean doInBackground(Void... voids) {

        int i = 1;
        while(running){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i-- == 0){
                running = false;
            }
            publishProgress(i);
        }

/*        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

        WordDB wordDB = new WordDB(mContext);
        wordDB.createCategory();
        boolean insertCheck = wordDB.createWord();
        wordDB.createFavorite();

        return insertCheck;
    }

    @Override
    protected void onPostExecute(Boolean insertCheck) {
        super.onPostExecute(insertCheck);

        if (insertCheck) {
            mDb.setVersion(WordDB.DATABASE_VERSION);
            DBHelper.DATABASE_VERSION = WordDB.DATABASE_VERSION;
            spEditor = sp.edit();
            spEditor.putInt(Preferences.KEY_DATABASE_VERSION, WordDB.DATABASE_VERSION);
            spEditor.commit();

            mDb.setVersion(WordDB.DATABASE_VERSION);
        }
        progressDialogPro.dismiss();
    }


}
