package com.example.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<com.example.contactsapp.Contacts> Contacts;


    // Constructor and methods for updating data
    public void setContacts(List<Contacts> contacts) {
        this.Contacts = contacts;
        notifyDataSetChanged();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView phoneTextView;
        private TextView emailTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts contact = Contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());
        holder.emailTextView.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return Contacts.size();
    }
}

