package com.example.reyna591;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "appPreferences";
    private static final String LOGIN_KEY = "userLoggedIn";

    private TextInputEditText usernameField;
    private TextInputEditText passwordField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        checkSavedLogin();

        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> validateLogin());
    }

    //verifica si el usuario ya inicio sesion
    private void checkSavedLogin() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        boolean userLoggedIn = preferences.getBoolean(LOGIN_KEY, false);

        if (userLoggedIn) {
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //guarda el estado de la sesion
    private void saveLoginState() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOGIN_KEY, true);
        editor.apply();
    }



    //verifica el usuario y password
    private void validateLogin() {
        String username = usernameField.getText() != null ? usernameField.getText().toString().trim() : "";
        String password = passwordField.getText() != null ? passwordField.getText().toString().trim() : "";

        //verifica si los campos estan vacios
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Ingresá usuario y contrasena", Toast.LENGTH_SHORT).show();
            return;
        }

        //verifica si el usuario y password son correctos
        if (DataRepository.validateLogin(username, password)) {
            saveLoginState();
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Usuario y/o password incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
