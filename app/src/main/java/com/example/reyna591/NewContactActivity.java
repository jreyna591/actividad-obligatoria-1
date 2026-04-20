package com.example.reyna591;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class NewContactActivity extends AppCompatActivity {

    private MaterialToolbar newContactToolbar;
    private TextInputEditText firstNameField;
    private TextInputEditText lastNameField;
    private TextInputEditText phoneField;
    private Button saveContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_contact);

        newContactToolbar = findViewById(R.id.newContactToolbar);
        firstNameField = findViewById(R.id.firstNameField);
        lastNameField = findViewById(R.id.lastNameField);
        phoneField = findViewById(R.id.phoneField);
        saveContactButton = findViewById(R.id.saveContactButton);

        setSupportActionBar(newContactToolbar);

        newContactToolbar.setNavigationOnClickListener(v -> finish());

        saveContactButton.setOnClickListener(v -> saveContact());
    }

    private void saveContact() {
        String firstName = firstNameField.getText() != null ? firstNameField.getText().toString().trim() : "";
        String lastName = lastNameField.getText() != null ? lastNameField.getText().toString().trim() : "";
        String phone = phoneField.getText() != null ? phoneField.getText().toString().trim() : "";

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String avatar = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();

        Contact newContact = new Contact(firstName, lastName, phone, avatar);
        DataRepository.addContact(newContact);

        Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
        finish();
    }
}
