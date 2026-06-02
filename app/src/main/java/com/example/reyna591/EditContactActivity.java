package com.example.reyna591;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class EditContactActivity extends AppCompatActivity {

    private MaterialToolbar editContactToolbar;
    private TextInputEditText editFirstNameField;
    private TextInputEditText editLastNameField;
    private TextInputEditText editPhoneField;
    private Button updateContactButton;
    private Button deleteContactButton;
    private TextInputEditText editAddressField;
    private RadioGroup editGenderGroup;


    private DatabaseHelper databaseHelper;
    private int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact);

        editContactToolbar = findViewById(R.id.editContactToolbar);
        editFirstNameField = findViewById(R.id.editFirstNameField);
        editLastNameField = findViewById(R.id.editLastNameField);
        editPhoneField = findViewById(R.id.editPhoneField);
        editAddressField = findViewById(R.id.editAddressField);
        editGenderGroup = findViewById(R.id.editGenderGroup);
        updateContactButton = findViewById(R.id.updateContactButton);
        deleteContactButton = findViewById(R.id.deleteContactButton);


        databaseHelper = new DatabaseHelper(this);

        setSupportActionBar(editContactToolbar);
        editContactToolbar.setNavigationOnClickListener(v -> finish());

        loadContactData();

        updateContactButton.setOnClickListener(v -> updateContact());
        deleteContactButton.setOnClickListener(v -> deleteContact());

    }

    private void loadContactData() {
        contactId = getIntent().getIntExtra("contactId", -1);
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");
        String gender = getIntent().getStringExtra("gender");

        editFirstNameField.setText(firstName);
        editLastNameField.setText(lastName);
        editPhoneField.setText(phone);
        editAddressField.setText(address);

        if ("Femenino".equals(gender)) {
            editGenderGroup.check(R.id.editFemaleOption);
        } else if ("Masculino".equals(gender)) {
            editGenderGroup.check(R.id.editMaleOption);
        }
    }

    private void updateContact() {
        String firstName = editFirstNameField.getText() != null ? editFirstNameField.getText().toString().trim() : "";
        String lastName = editLastNameField.getText() != null ? editLastNameField.getText().toString().trim() : "";
        String phone = editPhoneField.getText() != null ? editPhoneField.getText().toString().trim() : "";
        String address = editAddressField.getText() != null ? editAddressField.getText().toString().trim() : "";
        String gender = "";
        int selectedGenderId = editGenderGroup.getCheckedRadioButtonId();

        if (selectedGenderId == R.id.editFemaleOption) {
            gender = "Femenino";
        } else if (selectedGenderId == R.id.editMaleOption) {
            gender = "Masculino";
        }


        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(address) || TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String avatar = firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase();

        Contact updatedContact = new Contact(contactId, firstName, lastName, phone, address, gender, avatar);
        databaseHelper.updateContact(updatedContact);

        Toast.makeText(this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteContact() {
        if (contactId == -1) {
            Toast.makeText(this, "No se pudo eliminar el contacto", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseHelper.deleteContact(contactId);
        Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
        finish();
    }
}
