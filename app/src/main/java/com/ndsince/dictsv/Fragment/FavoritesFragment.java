package com.ndsince.dictsv.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ndsince.dictsv.DAO.CategoryDAO;
import com.ndsince.dictsv.DAO.FavoriteDAO;
import com.ndsince.dictsv.DAO.WordDAO;
import com.ndsince.dictsv.R;
import com.ndsince.dictsv.Task.SearchingTask;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link com.ndsince.dictsv.Fragment.FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    private static final String TAG = "FavoritesFragment";

    ListView listViewWordsFavorite;

    private WordDAO wordDAO;
    private CategoryDAO categoryDAO;
    private FavoriteDAO favoriteDAO;

    private View rootView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavoritesFragment.
     */
    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        //initWidget and data
        initWidget();
        setListViewAdapter();

        return rootView;
    }

    //----------------------------------------------------------------------------------------------

    public void setListViewAdapter() {

        SearchingTask searchingTask = new SearchingTask(getActivity(), listViewWordsFavorite,
                String.valueOf(0), null);
        searchingTask.run();

    }

    private void initWidget() {
        listViewWordsFavorite = (ListView) rootView.findViewById(R.id.listViewFavoriteFragment);
    }
}
