package com.example.reyna591;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.SharedPreferences;


public class ContactsActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "appPreferences";
    private static final String LOGIN_KEY = "userLoggedIn";

    private MaterialToolbar contactsToolbar;
    private RecyclerView contactsRecyclerView;
    private FloatingActionButton addContactButton;

    private ContactAdapter contactAdapter;
    private List<Contact> allContacts;
    private String currentQuery = "";

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);

        contactsToolbar = findViewById(R.id.contactsToolbar);
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView);
        addContactButton = findViewById(R.id.addContactButton);

        setSupportActionBar(contactsToolbar);

        databaseHelper = new DatabaseHelper(this);

        allContacts = databaseHelper.getAllContacts();

        contactAdapter = new ContactAdapter(new ArrayList<>(allContacts), contact -> {
            Intent intent = new Intent(this, EditContactActivity.class);
            intent.putExtra("contactId", contact.getId());
            intent.putExtra("firstName", contact.getFirstName());
            intent.putExtra("lastName", contact.getLastName());
            intent.putExtra("phone", contact.getPhone());
            startActivity(intent);
        });

        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.setAdapter(contactAdapter);

        addContactButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewContactActivity.class);
            startActivity(intent);
        });
    }

    //actualiza la lista de contactos al volver a la actividad
    @Override
    protected void onResume() {
        super.onResume();
        allContacts = databaseHelper.getAllContacts();
        filterContacts(currentQuery);
    }

    //cierra la sesion
    private void logoutUser() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOGIN_KEY, false);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    //menu de cerrar sesion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //menu de busqueda
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setQueryHint("Buscar por nombre");

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    currentQuery = query;
                    filterContacts(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    currentQuery = newText;
                    filterContacts(newText);
                    return true;
                }
            });

            searchView.setOnCloseListener(() -> {
                currentQuery = "";
                filterContacts("");
                return false;
            });
        }

        return true;
    }

    //filtra la lista de contactos
    private void filterContacts(String query) {
        List<Contact> filteredList = new ArrayList<>();
        String normalizedQuery = query == null ? "" : query.toLowerCase(Locale.getDefault());

        for (Contact contact : allContacts) {
            String firstName = contact.getFirstName().toLowerCase(Locale.getDefault());

            if (firstName.contains(normalizedQuery)) {
                filteredList.add(contact);
            }
        }

        contactAdapter.updateList(filteredList);
    }
}
