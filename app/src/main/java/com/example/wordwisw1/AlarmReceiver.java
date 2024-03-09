package com.example.wordwisw1;

import static android.app.AlarmManager.*;


import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.view.View;

 public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1;

    private DatabaseHelper dbHelper;
    String degisken;
    String degisken2;


   public void onReceive(Context context, Intent intent) {
            dbHelper = DatabaseHelper.getInstance(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);

            if (cursor.moveToFirst()) {

                @SuppressLint("Range") String englishWord = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ENGLISH_WORD));
                @SuppressLint("Range") String turkishTranslation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TURKISH_TRANSLATION));
                degisken = englishWord.toString();
                degisken2 = turkishTranslation.toString();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                        .setSmallIcon(R.drawable.dusunce)
                        .setContentTitle("Bunu biliyor musun?")
                        .setContentText(degisken + " kelimesinin türkçe karşılığı: " + degisken2 + "'dir!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }

            cursor.close();
            db.close();



        }


    }