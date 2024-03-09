package com.example.wordwisw1.ui.dashboard;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wordwisw1.DatabaseHelper;
import com.example.wordwisw1.databinding.FragmentDashboardBinding;


public class DashboardFragment extends Fragment  {

    private FragmentDashboardBinding binding;

    Integer puanhesapla = 1;

    private DatabaseHelper dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();






        final  TextView puan=binding.puan;
        final Button egzersiz=binding.egzersiz;
        final TextView kolon=binding.kolon;



        dbHelper = DatabaseHelper.getInstance(requireContext());

        final TextView sonuc= binding.sonuc;
        final Button dene= binding.dene;

        final TextView englishWordTextView1= binding.englishWordTextView1;

        final EditText turkishTranslationTextView1= binding.turkishTranslationTextView1;




        egzersiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kelimegoster();
                if (!dene.isEnabled()) {
                    dene.setEnabled(true);
                }
            }

            private void kelimegoster() {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);

                if (cursor.moveToFirst()) {
                    @SuppressLint("Range") String englishWord = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ENGLISH_WORD));
                    @SuppressLint("Range") String turkishTranslation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TURKISH_TRANSLATION));

                    englishWordTextView1.setText(englishWord);
                    englishWordTextView1.setVisibility(View.VISIBLE);
                    kolon.setText(turkishTranslation);
                    sonuc.setText("");
                    sonuc.setVisibility(View.VISIBLE);


                    dene.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String turkishTranslationFromDB = turkishTranslationTextView1.getText().toString();
                            String userInput = turkishTranslation.toString();



                            if (turkishTranslationFromDB.equalsIgnoreCase(userInput)) {
                                sonuc.setText("DOĞRU, BRAVO!");
                               puan.setText("Puan:" + puanhesapla++);
                                dene.setEnabled(false);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        sonuc.setText("");


                                    }
                                }, 5000);

                            } else {
                                sonuc.setText("Emin misin? Tekrar dene.");
                            }
                        }

                    });


                }
                cursor.close();
                db.close();
            }
        });





        return root;


}






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}