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

public class ContactsActivity extends AppCompatActivity {

    private MaterialToolbar contactsToolbar;
    private RecyclerView contactsRecyclerView;
    private FloatingActionButton addContactButton;

    private ContactAdapter contactAdapter;
    private List<Contact> allContacts;
    private String currentQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);

        contactsToolbar = findViewById(R.id.contactsToolbar);
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView);
        addContactButton = findViewById(R.id.addContactButton);

        setSupportActionBar(contactsToolbar);

        allContacts = DataRepository.getContacts();

        contactAdapter = new ContactAdapter(new ArrayList<>(allContacts));
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
        allContacts = DataRepository.getContacts();
        filterContacts(currentQuery);
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
