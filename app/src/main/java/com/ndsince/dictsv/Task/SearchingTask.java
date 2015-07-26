package com.ndsince.dictsv.Task;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import com.ndsince.dictsv.Adapter.CustomAdapter;
import com.ndsince.dictsv.DAO.Category;
import com.ndsince.dictsv.DAO.CategoryDAO;
import com.ndsince.dictsv.DAO.Favorite;
import com.ndsince.dictsv.DAO.FavoriteDAO;
import com.ndsince.dictsv.DAO.Word;
import com.ndsince.dictsv.DAO.WordDAO;
import com.ndsince.dictsv.Fragment.FavoritesFragment;
import com.ndsince.dictsv.Fragment.SearchFragment;
import com.ndsince.dictsv.LogCheck;
import com.ndsince.dictsv.MainActivity;

import java.util.HashMap;

/**
 * Created by Since on 18/3/2558.
 */
public class SearchingTask implements Runnable {

    final private static String TAG = "SearchingTask";

    Handler handler = new Handler();
    Context mContext;

    ListView listView;

    private FavoriteDAO favoriteDAO;
    private CategoryDAO categoryDAO;
    private WordDAO wordDAO;
    private Word word;

    private HashMap<Long, Word> hashMapWords;
    private HashMap<Integer, Category> hashMapCategory;
    private HashMap<Long, Favorite> hashMapFavorites;
    private HashMap<Long, Word> hashMapWordsSelect;

    private String inputText, whereArgs;
    private int intCaseListWord = 0;

    public SearchingTask(Context context, ListView listView, String inputText, String whereArg) {
        this.mContext = context;
        this.inputText = inputText;
        this.listView = listView;
        this.whereArgs = whereArg;

        wordDAO = new WordDAO(mContext);
        categoryDAO = new CategoryDAO(mContext);
        favoriteDAO = new FavoriteDAO(mContext);
    }

    @Override
    public void run() {
        int textLength = inputText.length();

        //get all Word
        if (whereArgs != null) hashMapWords = wordDAO.getWordByWhereArgs(whereArgs);
        else hashMapWords = wordDAO.getAllWord();
        hashMapCategory = categoryDAO.getAllCategory();
        hashMapFavorites = favoriteDAO.getAllFavorite();
        hashMapWordsSelect = new HashMap<>();

        if (inputText.length() == 0) {
            intCaseListWord = 1;

        } else {
            if (inputText.matches("[ก-๙].*")) {

                for (long keyWord : hashMapWords.keySet()) {
                    word = hashMapWords.get(keyWord);
                    try {
                        if (word.getmTermino() != null) {//Transliterated
                            if (inputText.equalsIgnoreCase(word.getmTermino()
                                    .subSequence(0, textLength).toString())) {
                                hashMapWordsSelect.put(word.getmId(), word);
                            }
                        }

                        if (word.getmTrans() != null) {//Terminology
                            if (inputText.equalsIgnoreCase(word.getmTrans()
                                    .subSequence(0, textLength).toString())) {
                                hashMapWordsSelect.put(word.getmId(), word);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                intCaseListWord = 2;

            } else if (inputText.matches("[a-z,A-Z].*")) {

                for (long keyWord : hashMapWords.keySet()) {
                    Word word = hashMapWords.get(keyWord);
                    try {
                        if (inputText.equalsIgnoreCase(word.getmWord()
                                .subSequence(0, textLength).toString())) {
                            hashMapWordsSelect.put(word.getmId(), word);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                intCaseListWord = 2;

            } else if (inputText.matches(String.valueOf(0))) {

                word = new Word();
                for (long keyFavorites : hashMapFavorites.keySet()) {
                    if (hashMapWords.get(keyFavorites) != null) {
                        word = hashMapWords.get(keyFavorites);
                        hashMapWordsSelect.put(word.getmId(), word);
                    }
                }
                intCaseListWord = 3;
            }
        }

        if (!inputText.matches(String.valueOf(0))) {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (inputText.length() > 0 && hashMapWordsSelect.size()==0) {
                        MainActivity mainActivity = (MainActivity) mContext;
                        SearchFragment searchFragment = (SearchFragment) mainActivity.getActiveFragment(null, 0);
                        searchFragment.setFrameWordNotFond();
                    }

                    switch (intCaseListWord) {
                        case 1:
                            listView.setAdapter(null);
                            break;
                        case 2:
                            listView.setAdapter(new CustomAdapter(mContext,
                                    hashMapWordsSelect, hashMapFavorites, hashMapCategory, "searchTab", listView));
                            break;
                    }




                }
            });
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity mainActivity = (MainActivity) mContext;
                    FavoritesFragment favoritesFragment = (FavoritesFragment) mainActivity.getActiveFragment(null, 1);

                    if (hashMapWordsSelect.size() == 0) {
                        listView.setAdapter(null);
                        favoritesFragment.setFrameFavoriteSuggest(true);

                    } else {
                        listView.setAdapter(new CustomAdapter(mContext,
                                hashMapWordsSelect, hashMapFavorites, hashMapCategory, "favoriteTab", listView));
                        favoritesFragment.setFrameFavoriteSuggest(false);
                    }

                }
            }, 100);
        }
    }
}
