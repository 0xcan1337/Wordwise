package com.example.wordwisw1.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wordwisw1.DatabaseHelper;
import com.example.wordwisw1.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment  {

    private FragmentNotificationsBinding binding;
    private DatabaseHelper dbHelper;
    private int clickCount = 0;
    Integer puanhesapla=1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       final Button kelimegetir= binding.kelimegetir;
       final TextView gelecekkelime= binding.gelecekkelime;
        final TextView sonuc= binding.sonuc;
        final TextView kelimegir1= binding.kelimegir1;
        final TextView kelimegir2= binding.kelimegir2;
        final TextView kelimegir3= binding.kelimegir3;
        final Button ipucugetir= binding.ipucugetir;
        final Button dene= binding.dene;
        final EditText cevap= binding.cevap;
        final TextView kolon= binding.kolon1;
        final CardView card = binding.cardView;
        final Button simdidegil= binding.simdidegil;
        final Button puanla=binding.puanla;
        final TextView puan= binding.puan;
        dbHelper = DatabaseHelper.getInstance(requireContext());


        simdidegil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setVisibility(View.INVISIBLE);
            }
        });

        puanla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "https://play.google.com/store/games?device=phone&hl=tr&gl=US";



                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl));
                startActivity(intent);
            }
        });

        kelimegetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kelimegoster();
                if (!dene.isEnabled()) {
                    dene.setEnabled(true);
                }

            }

            private void kelimegoster() {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_IPUCU + " ORDER BY RANDOM()", null);

                if (cursor.moveToFirst()) {
                    @SuppressLint("Range") String kelime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.WORD_IPUCU));
                    @SuppressLint("Range") String kelimegetir1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ILK_IPUCU));

                    @SuppressLint("Range") String kelimegetir2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.IKINCI_IPUCU));
                    @SuppressLint("Range") String kelimegetir3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.UCUNCU_IPUCU));
                    @SuppressLint("Range") String cevap13 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TURKCE_IPUCU));
                   gelecekkelime.setText(kelime);
                    gelecekkelime.setVisibility(View.VISIBLE);
                    sonuc.setText("");
                    sonuc.setVisibility(View.VISIBLE);
                    kolon.setText(cevap13);

                    ipucugetir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickCount++;

                            if (clickCount == 1) {
                                kelimegir1.setText(kelimegetir1);
                                kelimegir1.setVisibility(View.VISIBLE);
                            } else if (clickCount == 2) {
                                kelimegir2.setText(kelimegetir2);
                                kelimegir2.setVisibility(View.VISIBLE);
                            } else if (clickCount == 3) {
                                kelimegir3.setText(kelimegetir3);
                                kelimegir3.setVisibility(View.VISIBLE);
                                clickCount = 0; //
                            }
                        }
                    });


                    dene.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String cccs = cevap.getText().toString();
                            String userInput = cevap13.toString();


                            if (cccs.equalsIgnoreCase(userInput)) {

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