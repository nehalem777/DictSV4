package com.ndsince.dictsv.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Handler;

import com.alertdialogpro.AlertDialogPro;
import com.ndsince.dictsv.DAO.CategoryDAO;
import com.ndsince.dictsv.DAO.DBHelper;
import com.ndsince.dictsv.DAO.FavoriteDAO;
import com.ndsince.dictsv.DAO.WordDAO;
import com.ndsince.dictsv.LogCheck;
import com.ndsince.dictsv.MainActivity;
import com.ndsince.dictsv.R;
import com.ndsince.dictsv.Task.AddWordTask;
import com.ndsince.dictsv.Task.SearchingTask;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link com.ndsince.dictsv.Fragment.SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private final String TAG = "SearchFragment";

    Button btnChoiceCate;
    EditText edtSearch;
    ListView listViewWords;
    LinearLayout frameWordNotFond, frameShearchSuggest;
    LayoutInflater inflater;

    private WordDAO wordDAO;
    private CategoryDAO categoryDAO;
    private FavoriteDAO favoriteDAO;

    private View rootView;
    final Handler handler = new Handler();

    ArrayList<String> listStringAllCategoryName;
    ArrayList<String> listStringSelectCate;
    List<String> listStringCategoryName;
    boolean[] booleanSelectCate, booleanCheckItem;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        wordDAO = new WordDAO(getActivity());
        categoryDAO = new CategoryDAO(getActivity());
        favoriteDAO = new FavoriteDAO(getActivity());

        this.inflater = inflater;

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        //initWidget and data
        initWidget();
        setOnClickListenerButton();
        setTextChangedListenerEditText();

        return rootView;
    }

    @Override
    public void onPause() {
        clearEditText();

        //clear boolean after Pause
        booleanSelectCate = null;
        booleanCheckItem = null;

        super.onPause();
    }

    //----------------------------------------------------------------------------------------------

    /**
     * initWidget
     */
    private void initWidget() {

        btnChoiceCate = (Button) rootView.findViewById(R.id.btnChoiceCate);

        listViewWords = (ListView) rootView.findViewById(R.id.listViewSearchFragment);

        frameWordNotFond = (LinearLayout) rootView.findViewById(R.id.frameNotFoundWord);
        frameShearchSuggest = (LinearLayout) rootView.findViewById(R.id.frameSearchSuggest);

        frameShearchSuggest.setVisibility(View.VISIBLE);
        frameWordNotFond.setVisibility(View.GONE);
    }

    /**
     * setDialogSelectCategory
     */
    private void setDialogSelectCategory() {
        listStringAllCategoryName = categoryDAO.getAllStringListCategory();
        listStringCategoryName = new ArrayList<>();

        //set Check box list
        listStringCategoryName.add("All category");
        for (String categoryName : listStringAllCategoryName)
            listStringCategoryName.add(categoryName);

        CharSequence[] charSequences = listStringCategoryName.toArray(
                new CharSequence[listStringCategoryName.size()]);


        //set boolean array
        if (booleanSelectCate == null) { // first run set All Category
            int i = 0;
            booleanSelectCate = new boolean[listStringCategoryName.size()];
            booleanCheckItem = new boolean[listStringCategoryName.size()];

            for (boolean b : booleanSelectCate) {
                booleanSelectCate[i] = true;
                i++;
            }
            booleanCheckItem = booleanSelectCate.clone();
        } else {
            booleanCheckItem = booleanSelectCate.clone();
        }

        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getActivity());
        builder.setTitle("Category");

        builder.setMultiChoiceItems(charSequences, booleanCheckItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked) {
                    if (which == 0) {//check all cate
                        int i = 0;
                        for (boolean b : booleanSelectCate) {
                            booleanCheckItem[i] = true;
                            ((AlertDialog) dialog).getListView().setItemChecked(i, true);
                            i++;
                        }
                    } else {
                        if (getBooleanCountTrue(booleanCheckItem) ==
                                listStringCategoryName.size() - 1) {
                            booleanCheckItem[0] = true;
                            ((AlertDialog) dialog).getListView().setItemChecked(0, true);
                        }
                        booleanCheckItem[which] = true;
                    }

                } else {
                    if (which == 0) {
                        int i = 0;
                        for (boolean b : booleanSelectCate) {
                            booleanCheckItem[i] = false;
                            ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                            i++;
                        }
                    } else {
                        if (booleanCheckItem[0]) {
                            booleanCheckItem[0] = false;
                            ((AlertDialog) dialog).getListView().setItemChecked(0, false);
                        }
                        booleanCheckItem[which] = false;
                    }

                }
            }
        });
        builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set list select category
                listStringSelectCate = new ArrayList<>();

                int i = 0, booleanCount = 0;
                for (boolean b : booleanCheckItem) {
                    if(b) listStringSelectCate.add(listStringCategoryName.get(i));
                    if(!b) booleanCount++;
                    i++;
                }

                if(booleanCount == i) listStringSelectCate.add(listStringCategoryName.get(0));
                booleanSelectCate = booleanCheckItem.clone();

                //Set Search list
                getWordList();

                //setText category button
                if (booleanSelectCate[0]) btnChoiceCate.setText("All");
                else btnChoiceCate.setText("Category");
                //TODO setText btnCate like category select

                //Refresh listView
                String strInput = edtSearch.getText().toString().trim();
                edtSearch.setText("");
                edtSearch.setText(strInput);
                dialog.dismiss();


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    //TODO dummy code
/*    private void setDialogSelectCategory() {
        listStringAllCategoryName = categoryDAO.getAllStringListCategory();
        listStringCategoryName = new ArrayList<>();


        //set Check box list
        listStringCategoryName.add("All category");
        for (String categoryName : listStringAllCategoryName)
            listStringCategoryName.add(categoryName);
        CharSequence[] charSequences = listStringCategoryName.toArray(
                new CharSequence[listStringCategoryName.size()]);

        //set boolean array
        if (booleanSelectCate == null) { // first run
            int i = 0;
            booleanSelectCate = new boolean[listStringCategoryName.size()];
            booleanCheckItem = new boolean[listStringCategoryName.size()];

            for (boolean b : booleanSelectCate) {
                booleanSelectCate[i] = true;
                i++;
            }
            booleanCheckItem = booleanSelectCate.clone();
        } else {
            booleanCheckItem = booleanSelectCate.clone();
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Category");

        dialog.setMultiChoiceItems(charSequences, booleanCheckItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    if (which == 0) {//check all cate
                        int i = 0;
                        for (boolean b : booleanSelectCate) {
                            booleanCheckItem[i] = true;
                            ((AlertDialog) dialog).getListView().setItemChecked(i, true);
                            i++;
                        }
                    } else {
                        if (getBooleanCountTrue(booleanCheckItem) ==
                                listStringCategoryName.size() - 1) {
                            booleanCheckItem[0] = true;
                            ((AlertDialog) dialog).getListView().setItemChecked(0, true);
                        }
                        booleanCheckItem[which] = true;
                    }

                } else {
                    if (which == 0) {
                        int i = 0;
                        for (boolean b : booleanSelectCate) {
                            booleanCheckItem[i] = false;
                            ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                            i++;
                        }
                    } else {
                        if (booleanCheckItem[0]) {
                            booleanCheckItem[0] = false;
                            ((AlertDialog) dialog).getListView().setItemChecked(0, false);
                        }
                        booleanCheckItem[which] = false;
                    }

                }
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set list select category
                listStringSelectCate = new ArrayList<>();

                int i = 0;
                for (boolean b : booleanCheckItem) {
                    if(b) listStringSelectCate.add(listStringCategoryName.get(i));
                    i++;
                }

                booleanSelectCate = booleanCheckItem.clone();
                getWordList();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }*/

    /**
     * setOnClickListenerButton
     */
    private void setOnClickListenerButton() {

        //Button
        btnChoiceCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogSelectCategory();
            }
        });
    }

    /**
     * setTextChangedListenerEditText
     */
    private void setTextChangedListenerEditText() {
        edtSearch = (EditText) rootView.findViewById(R.id.edtSearchingWord);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(edtSearch.getText().toString().trim().length() == 0) {
                    frameShearchSuggest.setVisibility(View.VISIBLE);
                    frameWordNotFond.setVisibility(View.GONE);
                } else {
                    frameWordNotFond.setVisibility(View.GONE);
                    frameShearchSuggest.setVisibility(View.GONE);
                }

                SearchingTask searchingTask = new SearchingTask(getActivity(),
                        listViewWords, edtSearch.getText().toString().trim(), getWordList());
                searchingTask.run();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void clearEditText() {
/*        if (edtSearch != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    edtSearch.setText("");
                }
            },300);
        }*/
        if(edtSearch != null) edtSearch.setText("");

    }

    public void setFrameWordNotFond() {

        frameShearchSuggest.setVisibility(View.GONE);
        frameWordNotFond.setVisibility(View.VISIBLE);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * getWordList
     */
    private String getWordList() {
        //TODO convert list to database

        if (listStringSelectCate == null) {
            return null;
        } else if (listStringSelectCate.contains(listStringCategoryName.get(0))) {
            return null;
        } else {

            //whrerArg : Cate_id = '0'
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < listStringSelectCate.size(); i++) {

                buffer.append(DBHelper.COLUMN_WORD_CATEGORY_ID);
                buffer.append(" = '");
                buffer.append(categoryDAO.getCategoryObjByName(
                        listStringSelectCate.get(i)).getmId());
                buffer.append("'");

                if (i < listStringSelectCate.size() - 1) buffer.append(" OR ");
            }

            //Log-Debug getWordByWhereArgs
            LogCheck.d(TAG, "getWordList", buffer.toString());

            // search by category id
            wordDAO.getWordByWhereArgs(buffer.toString());

            LogCheck.d(TAG, "TestFragment", String.valueOf(((MainActivity) getActivity()).getwordlist()));
            return buffer.toString();
        }
    }

    /**
     * getBooleanCountTrue
     * @param booleanArray
     * @return
     */
    public int getBooleanCountTrue(boolean[] booleanArray) {
        int chkTrue = 0;
        for (boolean b : booleanArray) {
            if (b) chkTrue++;
        }
        return chkTrue;
    }
}
