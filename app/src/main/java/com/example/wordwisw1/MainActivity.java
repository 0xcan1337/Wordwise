package com.example.wordwisw1;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wordwisw1.databinding.ActivityMainBinding;
import com.example.wordwisw1.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Log Log;
    String dynamicContent = "";

    private TextToSpeech textToSpeech;
    private Button showWordButton;
    private TextView englishWordTextView, wordTypeTextView, turkishTranslationTextView, exampleSentenceTextView,defination,hosgeldin,hosgeldin1,hosgeldin2,deftext,example;
    private DatabaseHelper dbHelper;

    boolean isTextChanged = false;
    private ActivityMainBinding binding;

    private static final int NOTIFICATION_ID = 1;
    private static final long INTERVAL = 60 * 1000;
    private CardView cardView,cardVie1w,material;

    private ImageView imageView,imageView1,imageview2,hosgeldinimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNotificationChannel();

        scheduleNotification();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        example=findViewById(R.id.example);
        material=findViewById(R.id.material);
        cardVie1w=findViewById(R.id.cardVie1w);
        cardView=findViewById(R.id.cardView);
        deftext=findViewById(R.id.deftext);
        hosgeldin=findViewById(R.id.hosgeldin);
        hosgeldin1=findViewById(R.id.hosgeldin1);
        hosgeldin2=findViewById(R.id.hosgeldin2);
        hosgeldinimage=findViewById(R.id.hosgeldinimage);
        defination = findViewById(R.id.defination);
        imageView1 = findViewById(R.id.imageView1);
        imageView = findViewById(R.id.imageView);
        imageview2 = findViewById(R.id.imageView2);
        showWordButton = findViewById(R.id.showWordButton);
        englishWordTextView = findViewById(R.id.englishWordTextView);
        wordTypeTextView = findViewById(R.id.wordTypeTextView);
        turkishTranslationTextView = findViewById(R.id.turkishTranslationTextView);
        exampleSentenceTextView = findViewById(R.id.exampleSentenceTextView);
        dbHelper = DatabaseHelper.getInstance(this);


        showWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayRandomWord();
                imageView.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
                hosgeldin.setVisibility(View.INVISIBLE);
                hosgeldin1.setVisibility(View.INVISIBLE);
                hosgeldin2.setVisibility(View.INVISIBLE);
                hosgeldinimage.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
                deftext.setVisibility(View.VISIBLE);
                imageview2.setVisibility(View.VISIBLE);
                cardVie1w.setVisibility(View.VISIBLE);
                defination.setVisibility(View.VISIBLE);
                example.setVisibility(View.VISIBLE);
                material.setVisibility(View.VISIBLE);
                exampleSentenceTextView.setVisibility(View.VISIBLE);
                showWordButton.setText("DEVAM!");
            }




        });


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "https://translate.google.com/?hl=tr&sl=auto&tl=tr&text=";
                dynamicContent = englishWordTextView.getText().toString();
                String fullUrl = baseUrl + dynamicContent;


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl));
                startActivity(intent);
            }
        });


        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "https://www.dictionary.com/browse/";
                dynamicContent = englishWordTextView.getText().toString();
                String fullUrl = baseUrl + dynamicContent;


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl));
                startActivity(intent);
            }
        });


    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "bildirim";
            String description = "kelime";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }


    private void scheduleNotification() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long triggerAtMillis = System.currentTimeMillis();

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, INTERVAL, pendingIntent);
    }



    private void displayRandomWord() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String englishWord = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ENGLISH_WORD));
            @SuppressLint("Range") String wordType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WORD_TYPE));
            @SuppressLint("Range") String turkishTranslation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TURKISH_TRANSLATION));
            @SuppressLint("Range") String defination1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DEFINATION));
            @SuppressLint("Range") String exampleSentence = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EXAMPLE_SENTENCE));

            englishWordTextView.setText( englishWord);
            wordTypeTextView.setText(wordType);
            turkishTranslationTextView.setText(turkishTranslation);
            exampleSentenceTextView.setText(exampleSentence);
            defination.setText(defination1);
        }

        cursor.close();
        db.close();
    }



}