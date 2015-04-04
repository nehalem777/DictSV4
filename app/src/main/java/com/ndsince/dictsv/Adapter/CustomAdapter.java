package com.ndsince.dictsv.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;
import com.ndsince.dictsv.DAO.Category;
import com.ndsince.dictsv.DAO.CategoryDAO;
import com.ndsince.dictsv.DAO.Favorite;
import com.ndsince.dictsv.DAO.FavoriteDAO;
import com.ndsince.dictsv.DAO.Word;
import com.ndsince.dictsv.DAO.WordDAO;
import com.ndsince.dictsv.Fragment.FavoritesFragment;
import com.ndsince.dictsv.LogCheck;
import com.ndsince.dictsv.MainActivity;
import com.ndsince.dictsv.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Since on 17/3/2558.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG = "CustomAdapter";

    public static boolean chkReloadFavoriteTab = false;

    private LayoutInflater mInflater;
    private Context mContext;
    ViewHolder mViewHolder;
    ListView listView;
    private TextToSpeech tts;

    private FavoriteDAO favoriteDAO;
    private WordDAO wordDAO;
    private CategoryDAO categoryDAO;
    private Word word;

    HashMap<Long, Word> hashMapWords;
    HashMap<Long, Favorite> hashMapFavorite;
    HashMap<Integer, Category> hashMapCategory;

    Long[] hashMapWordsKey;
    String stringTabName;
    String[] menuListDialog = new String[]{"Edit", "Delete", "TTS"};

    public CustomAdapter(Context context, HashMap<Long, Word> hashMapWords,
                         HashMap<Long, Favorite> hashMapFavorite,
                         HashMap<Integer, Category> hashMapCategory,
                         String stringTabName, ListView listView) {
        this.mContext = context;
        this.hashMapWords = hashMapWords;
        this.hashMapFavorite = hashMapFavorite;
        this.hashMapCategory = hashMapCategory;
        this.stringTabName = stringTabName;

        this.listView = listView;

        this.mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.hashMapWordsKey = hashMapWords.keySet().toArray(new Long[hashMapWords.size()]);


        this.favoriteDAO = new FavoriteDAO(mContext);
        this.categoryDAO = new CategoryDAO(mContext);
        this.wordDAO = new WordDAO(mContext);

    }

    @Override
    public int getCount() {
        return hashMapWords.size();
    }

    @Override
    public Word getItem(int position) {
        word = new Word();
        word = hashMapWords.get(hashMapWordsKey[position]);

        return word;
    }

    public void upDateFavoriteslist(int position) {
        //Check if refresh CustomAdapter in favorite tabs
        hashMapWords.remove(getItem(position).getmId());
        this.hashMapWordsKey = hashMapWords.keySet().toArray(new Long[hashMapWords.size()]);


        MainActivity mainActivity = (MainActivity) mContext;
        FavoritesFragment favoritesFragment = (FavoritesFragment) mainActivity.getActiveFragment(null, 1);
        if (hashMapWords.size() == 0) {
            favoritesFragment.setFrameFavoriteSuggest(true);
        } else {
            favoritesFragment.setFrameFavoriteSuggest(false);
        }

        this.notifyDataSetChanged();
    }

    public void updateView(int position, Word word) {
        View v = listView.getChildAt(position -
                listView.getFirstVisiblePosition());
        if (v == null) return;

        hashMapWords.put(hashMapWordsKey[position], word);

        TextView tvWord = (TextView) v.findViewById(R.id.textViewItemWord);
        TextView tvTerminologyLabel = (TextView) v.findViewById(R.id.textViewItemTerminologyLabel);
        TextView tvTerminology = (TextView) v.findViewById(R.id.textViewItemTerminology);
        TextView tvTransliteratedLabel = (TextView) v.findViewById(R.id.textViewItemTransliteratedLabel);
        TextView tvTransliterated = (TextView) v.findViewById(R.id.textViewItemTransliterated);
        TextView tvCategory = (TextView) v.findViewById(R.id.textViewItemCategoryName);
        ImageButton imgBtnFav = (ImageButton) v.findViewById(R.id.ImgButtonFav);

        tvWord.setText(word.getmWord());
        if (word.getmTermino() != null) {
            tvTerminologyLabel.setVisibility(View.VISIBLE);
            tvTerminology.setVisibility(View.VISIBLE);
            tvTerminology.setText(word.getmTermino());
        } else {
            tvTerminologyLabel.setVisibility(View.GONE);
            tvTerminology.setVisibility(View.GONE);
        }

        if (word.getmTrans() != null) {
            tvTransliteratedLabel.setVisibility(View.VISIBLE);
            tvTransliterated.setVisibility(View.VISIBLE);
            tvTransliterated.setText(word.getmTrans());
        } else {
            tvTransliteratedLabel.setVisibility(View.GONE);
            tvTransliterated.setVisibility(View.GONE);
        }

        tvCategory.setText(word.getmCategory().getmName());

        if (hashMapFavorite.get(getItem(position).getmId()) != null) {
            imgBtnFav.setImageResource(R.drawable.ic_star_grey600_36dp);
            imgBtnFav.setSelected(true);
        } else {
            imgBtnFav.setImageResource(R.drawable.ic_star_outline_grey600_36dp);
            imgBtnFav.setSelected(false);
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View rowView, ViewGroup parent) {

        if (rowView == null) {
            rowView = mInflater.inflate(R.layout.item_list_word, parent, false);

            mViewHolder = new ViewHolder();
            mViewHolder.tvWord = (TextView) rowView.findViewById(R.id.textViewItemWord);
            mViewHolder.tvTerminology = (TextView) rowView.findViewById(R.id.textViewItemTerminology);
            mViewHolder.tvTransliterated = (TextView) rowView.findViewById(R.id.textViewItemTransliterated);
            mViewHolder.tvCategory = (TextView) rowView.findViewById(R.id.textViewItemCategoryName);

            mViewHolder.tvTerminologyLabel = (TextView) rowView.findViewById(R.id.textViewItemTerminologyLabel);
            mViewHolder.tvTernsliteratedLabel = (TextView) rowView.findViewById(R.id.textViewItemTransliteratedLabel);

//            mViewHolder.imgBtnVoice = (ImageButton) rowView.findViewById(R.id.ImgButtonVoice);
            mViewHolder.imgBtnFav = (ImageButton) rowView.findViewById(R.id.ImgButtonFav);

            rowView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) rowView.getTag();
        }

        mViewHolder.tvWord.setText(getItem(position).getmWord());
        if (getItem(position).getmTermino() == null) {
            mViewHolder.tvTerminologyLabel.setVisibility(View.GONE);
            mViewHolder.tvTerminology.setVisibility(View.GONE);
        } else {
            mViewHolder.tvTerminology.setText(getItem(position).getmTermino());
        }
        if (getItem(position).getmTrans() == null) {
            mViewHolder.tvTernsliteratedLabel.setVisibility(View.GONE);
            mViewHolder.tvTransliterated.setVisibility(View.GONE);
        } else {
            mViewHolder.tvTransliterated.setText(getItem(position).getmTrans());
        }


        //Category
        mViewHolder.tvCategory.setText(hashMapCategory.get(
                getItem(position).getmCategory().getmId()).getmName());

        //Favorite
        mViewHolder.imgBtnFav.setTag(mViewHolder.imgBtnFav);
        final ImageButton imgBtnFav = (ImageButton) mViewHolder.imgBtnFav.getTag();

        //set imgResource if have this words in favoriteTable
        if (hashMapFavorite.get(getItem(position).getmId()) != null) {
            imgBtnFav.setImageResource(R.drawable.ic_star_grey600_36dp);
            imgBtnFav.setSelected(true);
        } else {
            imgBtnFav.setImageResource(R.drawable.ic_star_outline_grey600_36dp);
            imgBtnFav.setSelected(false);
        }


        imgBtnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!v.isSelected()) {
                    imgBtnFav.setImageResource(R.drawable.ic_star_grey600_36dp);
                    imgBtnFav.setSelected(true);
                    favoriteDAO.addFavorite(getItem(position));

                } else {
                    imgBtnFav.setImageResource(R.drawable.ic_star_outline_grey600_36dp);
                    imgBtnFav.setSelected(false);
                    favoriteDAO.deleteFavorite(getItem(position));

                    if (stringTabName.equals("favoriteTab"))
                        upDateFavoriteslist(position);
                }

                chkReloadFavoriteTab = true;
                hashMapFavorite = favoriteDAO.getAllFavorite();
            }
        });

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialogPro.Builder dialogMenu = new AlertDialogPro.Builder(mContext);
                dialogMenu.setTitle("Menu")
                        .setItems(menuListDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(mContext, "Select menu : " + i,
                                        Toast.LENGTH_SHORT).show();
                                menuListDialogAction(i, position);
                            }
                        })
                        .show();
                return true;
            }
        });

        return rowView;
    }

    private static class ViewHolder {
        public TextView tvWord;
        public TextView tvTerminology;
        public TextView tvTransliterated;
        public TextView tvCategory;

        public TextView tvTerminologyLabel;
        public TextView tvTernsliteratedLabel;

        public ImageButton imgBtnFav;
        public ImageButton imgBtnVoice;
    }

    public void menuListDialogAction(int item, final int position) {

        final AlertDialogPro.Builder builder = new AlertDialogPro.Builder(mContext);

        //TODO menu
        switch (item) {
            case 0: //edit Word

                builder.setTitle(mContext.getString(R.string.Edit_Word));
                View layoutDialogEditWord = mInflater.inflate(R.layout.dialog_word_edit, null);
                builder.setView(layoutDialogEditWord);


                final EditText edtWord = (EditText) layoutDialogEditWord.findViewById(R.id.edtDialogWord);
                final EditText edtTer = (EditText) layoutDialogEditWord.findViewById(R.id.edtDialogTermino);
                final EditText edtTrans = (EditText) layoutDialogEditWord.findViewById(R.id.edtDialogTrans);
                final Spinner spnDialogCate = (Spinner) layoutDialogEditWord.findViewById(R.id.spnVocabFirst);

                Word word = getItem(position);
                edtWord.setText(word.getmWord());
                edtTer.setText(word.getmTermino());
                edtTrans.setText(word.getmTrans());
                setSpinnerCate(spnDialogCate, position);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text;
                        CustomAdapter.chkReloadFavoriteTab = true;

                        Word word = new Word();
                        word.setmId(getItem(position).getmId());
                        word.setmWord(edtWord.getText().toString().trim());
                        if ((text = edtTrans.getText().toString().trim()).equalsIgnoreCase("")) {
                            word.setmTrans(null);
                        } else {
                            word.setmTrans(edtTrans.getText().toString().trim());
                        }
                        if ((text = edtTer.getText().toString().trim()).equalsIgnoreCase("")) {
                            word.setmTermino(null);
                        } else {
                            word.setmTermino(edtTer.getText().toString().trim());
                        }
                        word.setmCategory(categoryDAO.getCategoryObjByName(
                                spnDialogCate.getSelectedItem().toString().trim()));

                        wordDAO.updateWord(word);

                        //refresh list
                        updateView(position, word);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.show();

                break;
            case 1: //Delete Word

                View layoutDialogDelWord = mInflater.inflate(R.layout.dialog_confirm, null);
                builder.setView(layoutDialogDelWord);

                builder.setTitle("Delete Word");
                TextView textDialogMsg1 = (TextView) layoutDialogDelWord.findViewById(R.id.textDialogMsg1);
                TextView textDialogWordName = (TextView) layoutDialogDelWord.findViewById(R.id.textDialogCategoryName);
                Button btnConfirm = (Button) layoutDialogDelWord.findViewById(R.id.btnConfirm);
                Button btnCancel = (Button) layoutDialogDelWord.findViewById(R.id.btnCancel);

                textDialogMsg1.setVisibility(View.GONE);
                textDialogWordName.setText(getItem(position).getmWord());

                final Dialog dialogDelWord = builder.create();

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogDelWord.dismiss();
                    }
                });

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wordDAO.deleteWord(getItem(position));
                        upDateFavoriteslist(position);
                        CustomAdapter.chkReloadFavoriteTab = true;
                        dialogDelWord.dismiss();
                    }
                });

                dialogDelWord.show();
                break;

            case 2: //etc
                tts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i == TextToSpeech.SUCCESS) {
                            //set Language
                            int result = tts.setLanguage(Locale.ENGLISH);

                            //speed sound
                            tts.setPitch(1);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ) {


                                LogCheck.e(TAG, "TTS", "Language is not supported");
                            } else {

                                tts.speak(getItem(position).getmWord().trim(), TextToSpeech.QUEUE_FLUSH, null);
                                LogCheck.e(TAG, "TTS", "Language is not supported");
                            }
                        } else {
                            LogCheck.e(TAG, "TTS", "Initialization Fail");
                            builder.setTitle("TTS Error");
                            builder.setMessage("Please install Text To Speech");
                            builder.setPositiveButton("INSTALL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String appPackageName = "com.google.android.tts";
                                    try {
                                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                    }
                                }
                            });
                            builder.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        }
                    }
                });
                break;

        }
    }

    private void setSpinnerCate(final Spinner spinner, final int position) {
        final Handler handler = new Handler();

        //background or custom thread - run long task
        new Thread(new Runnable() {
            @Override
            public void run() {

                final ArrayList<String> listStringAllCategoryName = categoryDAO.getAllStringListCategory();
                final ArrayAdapter adapterSpinnerCategory = new ArrayAdapter<>(mContext,
                        android.R.layout.simple_spinner_dropdown_item, listStringAllCategoryName);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Update all user interface
                        spinner.setAdapter(adapterSpinnerCategory);

                        spinner.setSelection(listStringAllCategoryName.indexOf(
                                categoryDAO.getCategoryObjByID(
                                        getItem(position).getmCategory().getmId()).getmName()));
                    }
                }, 500);
            }
        }).start();
    }
}