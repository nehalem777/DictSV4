package com.ndsince.dictsv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ndsince.dictsv.Adapter.CustomAdapter;
import com.ndsince.dictsv.DAO.Category;
import com.ndsince.dictsv.DAO.CategoryDAO;
import com.ndsince.dictsv.DAO.DBHelper;
import com.ndsince.dictsv.DAO.Favorite;
import com.ndsince.dictsv.DAO.FavoriteDAO;
import com.ndsince.dictsv.DAO.Word;
import com.ndsince.dictsv.DAO.WordDAO;
import com.ndsince.dictsv.DAO.WordDB;
import com.ndsince.dictsv.Fragment.EditFragment;
import com.ndsince.dictsv.Fragment.FavoritesFragment;
import com.ndsince.dictsv.Fragment.SearchFragment;
import com.ndsince.dictsv.Task.AddWordTask;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    private static final String TAG = "MainActivity";

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;

    private WordDAO wordDAO;
    private CategoryDAO categoryDAO;
    private FavoriteDAO favoriteDAO;
    AddWordTask addWordTask;

    private HashMap<Long, Word> hashMapWords;
    private ArrayList<String> StringListCategories;
    private HashMap<Long, Favorite> hashMapFavorites;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    final int[] iconTabs = new int[]{R.drawable.ic_search_white_24dp,
                                     R.drawable.ic_star_white_24dp,
                                     R.drawable.ic_playlist_add_white_24dp};

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            LogCheck.i(TAG, "dispatchTouchEvent", "MotionEvent.ACTION_DOWN");
            hideKeyboard();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******************************************************************************************
         * Database
         ******************************************************************************************/
        sp = getSharedPreferences(Preferences.PREF_NAME, Preferences.MODE);
        spEditor = sp.edit();
        DBHelper.DATABASE_VERSION = sp.getInt(Preferences.KEY_DATABASE_VERSION, 1);
        boolean firstRun = sp.getBoolean(Preferences.KEY_FIRSTRUN, true);

        //Create
        //TODO first run add Word Database and check version  database
        if (firstRun) {

            AddWordTask addWordTask = new AddWordTask(MainActivity.this);
            addWordTask.execute();

            LogCheck.i(TAG, "firstRun", "firstRun");

            CustomAdapter.chkNewFavoriteTab = true;
            spEditor.putBoolean(Preferences.KEY_FIRSTRUN, false);
            spEditor.commit();

        } else if (DBHelper.DATABASE_VERSION < WordDB.DATABASE_VERSION) {
            LogCheck.i(TAG, "SP", "mDb.getVersion() < WordDB.DATABASE_VERSION");

            addWordTask = new AddWordTask(MainActivity.this);
            addWordTask.execute();

            CustomAdapter.chkNewFavoriteTab = true;
        }

        /******************************************************************************************
         * Log TEST
         ******************************************************************************************/
        //add data dummy
        //WordDB wordDB = new WordDB(this);

        setStringListCategories();


        /******************************************************************************************
         * Action bar
         ******************************************************************************************/
        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
//                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setIcon(this.getResources().getDrawable(iconTabs[i]))
                            .setTabListener(this));

        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hideKeyboard();

//                Fragment fragment = getActiveFragment(mViewPager, 1);
//                FavoritesFragment favoritesFragment = (FavoritesFragment) fragment;

                SearchFragment searchFragment = (SearchFragment) getActiveFragment(mViewPager, 0);
                FavoritesFragment favoritesFragment = (FavoritesFragment) getActiveFragment(mViewPager, 1);
                //EditFragment editFragment = (EditFragment) getActiveFragment(mViewPager, 2);

                switch (position) {
                    case 1:
                        if(searchFragment != null)
                            searchFragment.clearEditText();

                        if (favoritesFragment != null && CustomAdapter.chkNewFavoriteTab) {
                            favoritesFragment.setListViewAdapter();
                            CustomAdapter.chkNewFavoriteTab = false;
                        }
                        break;

                    /*case 2:
                        if(editFragment != null)
                            CustomAdapter.chkNewFavoriteTab = true;*/
                }
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset_database) {
            new AddWordTask(this).execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            ////return PlaceholderFragment.newInstance(position + 1);

            switch (position) {
                case 0:
                    return SearchFragment.newInstance();
                case 1:
                    return FavoritesFragment.newInstance();
                case 2:
                    return EditFragment.newInstance();
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    //----------------------------------------------------------------------------------------------
    public Fragment getActiveFragment(ViewPager container, int position) {
        String name = "android:switcher:" + container.getId() + ":" + position;
        return getSupportFragmentManager().findFragmentByTag(name);
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Get&Set

    public int getwordlist() {
        return 1;
    }

    public void setHashMapAllWords() {
        wordDAO = new WordDAO(this);
        hashMapWords = wordDAO.getAllWord();
    }

    public HashMap<Long, Word> getHashMapAllWords() {
        if (hashMapWords != null)
            return hashMapWords;
        else
            return null;
    }

    public void setStringListCategories() {
        categoryDAO = new CategoryDAO(this);
        StringListCategories = categoryDAO.getAllStringListCategory();
    }

    public ArrayList<String> getStringListCategories() {
        if(StringListCategories != null)
            return StringListCategories;
        else
            return null;
    }

    public void setHashMapFavorites() {
        favoriteDAO = new FavoriteDAO(this);
        hashMapFavorites = favoriteDAO.getAllFavorite();
    }

    public HashMap<Long, Favorite> getHashMapFavorites() {
        if(hashMapFavorites != null)
            return hashMapFavorites;
        else
            return null;
    }
    TextToSpeech tts;

}