package com.example.contactsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private RecyclerView contactRecyclerView;
    private ContactAdapter contactAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactRecyclerView = findViewById(R.id.contactRecyclerView);
        contactAdapter = new ContactAdapter();

        // Set layout manager for RecyclerView
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set adapter for RecyclerView
        contactRecyclerView.setAdapter(contactAdapter);

        // Get contacts from intent
        Intent intent = getIntent();
        List<Contacts> contacts = Collections.unmodifiableList(intent.getParcelableArrayListExtra("contacts"));

        // Check if contacts are available
        if (contacts != null && !contacts.isEmpty()) {
            // Set contacts to the adapter
            contactAdapter.setContacts(contacts);
        } else {
            // Display a message if no contacts are available
            Toast.makeText(this, "No contacts available", Toast.LENGTH_SHORT).show();
        }
    }
}
