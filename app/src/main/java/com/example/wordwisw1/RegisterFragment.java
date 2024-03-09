package com.example.wordwisw1;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.wordwisw1.DatabaseHelper;
import com.example.wordwisw1.R;

public class RegisterFragment extends Fragment {

    private DatabaseHelper dbHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        EditText editTextMail = view.findViewById(R.id.editTextMail);
        Button buttonRegister = view.findViewById(R.id.buttonRegister);
        dbHelper = DatabaseHelper.getInstance(requireContext());

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String mail = editTextMail.getText().toString().trim();

                boolean success = dbHelper.addUser(username, password, mail);

                if (isValidPassword(password)) {
                    Toast.makeText(requireContext(), "Kayıt işleminiz başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();

                    Profilim fragment = new Profilim();


                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, fragment); //
                    transaction.addToBackStack("ProfilimFragmentTag");
                    transaction.commit();


                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.apply();

                    SharedPreferences mailekle = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor mailekle1 = sharedPreferences.edit();
                    editor.putString("mail", mail); //
                    editor.apply();

                } else {
                    Toast.makeText(requireContext(), "Şifreniz en az bir büyük harf içermeli ve en az 4 karakterden oluşmalıdır!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean isValidPassword(String sifre) {

        if (sifre.length() < 4) {
            return false;
        }


        boolean hasUppercase = !sifre.equals(sifre.toLowerCase());

        return hasUppercase;
    }

}