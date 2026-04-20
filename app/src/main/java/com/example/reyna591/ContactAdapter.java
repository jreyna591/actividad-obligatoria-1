package com.example.reyna591;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    //actualiza la lista de contactos
    public void updateList(List<Contact> newContacts) {
        this.contacts = newContacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    //vincula los datos del contacto con la vista
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.avatarTextView.setText(contact.getAvatarText());
        holder.fullNameTextView.setText(contact.getFullName());
        holder.phoneTextView.setText(contact.getPhone());
        holder.callButton.setOnClickListener(v -> openDialer(contact.getPhone(), v));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    //vincula los datos del contacto con la vista
    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView avatarTextView;
        TextView fullNameTextView;
        TextView phoneTextView;
        ImageButton callButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarTextView = itemView.findViewById(R.id.avatarTextView);
            fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            callButton = itemView.findViewById(R.id.callButton);
        }
    }

    private void openDialer(String phoneNumber, View view) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));
        view.getContext().startActivity(dialIntent);
    }
}
