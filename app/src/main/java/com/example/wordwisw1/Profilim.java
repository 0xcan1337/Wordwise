package com.example.wordwisw1;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static com.example.wordwisw1.DatabaseHelper.ID_USER;
import static com.example.wordwisw1.DatabaseHelper.USERNAME_USER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wordwisw1.R;
import com.example.wordwisw1.ui.login.LoginFragment;
import com.example.wordwisw1.ui.settings.SettingsFragment;

import com.example.wordwisw1.ui.settings.SettingsFragment;

public class Profilim extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button settings;

    private Button ppbt;

    private DatabaseHelper dbHelper;

private Button sil;
   private EditText textViewUserValue;
   private EditText textViewEmail;

   private EditText textViewPassword;

    public Profilim() {


    }

    public static Profilim newInstance(String param1, String param2) {
        Profilim fragment = new Profilim();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profilim, container, false);
        textViewUserValue = view.findViewById(R.id.textViewUserValue);
        textViewEmail= view.findViewById(R.id.textViewEmail);
        settings= view.findViewById(R.id.settings);
        dbHelper = DatabaseHelper.getInstance(requireContext());

        ppbt=view.findViewById(R.id.ppbt);
        textViewPassword=view.findViewById(R.id.textViewPassword);



        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String storedUsername = sharedPreferences.getString("  ", "Username eklemediniz.");

        textViewUserValue.setText(storedUsername);


        SharedPreferences mailekle = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String email = mailekle.getString("mail", "Mail eklemediniz.");
        textViewEmail.setText(email);

        ppbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmProfileUpdate(v);
            }
        });






        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SettingsFragment fragment = new SettingsFragment();


                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack("SettingsFragmentTag");
                transaction.commit();
            }
        });
        return view;
    }
    public void confirmProfileUpdate(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Profilinizi güncellemek istediğinizden emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        editProfile();
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void editProfile() {
        String newUsername = textViewUserValue.getText().toString().trim();
        String newEmail = textViewEmail.getText().toString().trim();
        String newPassword = textViewPassword.getText().toString().trim();

        long currentUserID = dbHelper.getCurrentUserID();

        if (!newUsername.isEmpty()) {
            boolean isUsernameUpdated = dbHelper.updateUsername(currentUserID, newUsername);
            if (isUsernameUpdated) {
                Toast.makeText(getContext(), "Profil bilgileriniz başarıyla güncellendi!", Toast.LENGTH_SHORT).show();
            } else {

            }
        }

        if (!newEmail.isEmpty()) {
            boolean isEmailUpdated = dbHelper.updateEmail(currentUserID, newEmail);
            if (isEmailUpdated) {
                Toast.makeText(getContext(), "Epostanızı başarıyla değiştirdiniz!", Toast.LENGTH_SHORT).show();
            } else {

            }
        }

        if (!newPassword.isEmpty()) {
            boolean isPasswordUpdated = dbHelper.updatePassword(currentUserID, newPassword);
            if (isPasswordUpdated) {

            } else {

            }
        }
    }


}