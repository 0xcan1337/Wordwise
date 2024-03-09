package com.example.wordwisw1.ui.login;

import static android.app.ProgressDialog.show;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wordwisw1.DatabaseHelper;
import com.example.wordwisw1.Profilim;
import com.example.wordwisw1.RegisterFragment;
import com.example.wordwisw1.databinding.FragmentLogin3Binding;


import com.example.wordwisw1.R;
import com.example.wordwisw1.ui.dashboard.DashboardFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLogin3Binding binding;


    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLogin3Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.editTextLoginUsername;
        final EditText passwordEditText = binding.editTextLoginPassword;
        final Button girisyap = binding.buttonLogin;
        final Button kayitol= binding.register;


        dbHelper = DatabaseHelper.getInstance(requireContext());



        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullaniciAdi = usernameEditText.getText().toString().trim();
                String sifre = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(kullaniciAdi) || TextUtils.isEmpty(sifre)) {
                } else {
                    Login(kullaniciAdi, sifre);
                }
            }
        });

        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 RegisterFragment fragment = new RegisterFragment();


                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack("RegisterFragmentTag");
                transaction.commit();
            }
        });

    }

    private void Login(String username, String password) {


            boolean girisDurumu = dbHelper.Login(username, password);
            if (girisDurumu) {
                Toast.makeText(getContext(), "Hoş geldin " + username + "!", Toast.LENGTH_SHORT).show();

                Profilim fragment = new Profilim();

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack("SettingsFragmentTag");
                transaction.commit();

                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username); //
                editor.apply();

            } else {
                // Giriş başarısız
                Toast.makeText(getContext(), "Kullanıcı adı veya şifre hatalı", Toast.LENGTH_SHORT).show();
            }

    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}