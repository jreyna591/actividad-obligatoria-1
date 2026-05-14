package com.example.reyna591;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contactsApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTACTS = "contacts";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_AVATAR_TEXT = "avatar_text";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTableQuery = "CREATE TABLE " + TABLE_CONTACTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT, "
                + COLUMN_AVATAR_TEXT + " TEXT)";

        database.execSQL(createTableQuery);

        insertStartingContacts(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(database);
    }

    private void insertStartingContacts(SQLiteDatabase database) {
        insertContact(database, new Contact("Belen", "Garcia", "2234567890", "AG"));
        insertContact(database, new Contact("Bruno", "Lopez", "2234551111", "BL"));
        insertContact(database, new Contact("Carla", "Martinez", "2234778899", "CM"));
        insertContact(database, new Contact("Diego", "Fernandez", "2234001122", "DF"));
        insertContact(database, new Contact("Elena", "Suarez", "2234998877", "ES"));
        insertContact(database, new Contact("Facundo", "Perez", "2234123456", "FP"));
    }

    private void insertContact(SQLiteDatabase database, Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(COLUMN_LAST_NAME, contact.getLastName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_AVATAR_TEXT, contact.getAvatarText());

        database.insert(TABLE_CONTACTS, null, values);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase database = this.getWritableDatabase();
        insertContact(database, contact);
        database.close();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
                String avatarText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR_TEXT));

                Contact contact = new Contact(id, firstName, lastName, phone, avatarText);
                contactList.add(contact);

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return contactList;
    }

    public void updateContact(Contact contact) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(COLUMN_LAST_NAME, contact.getLastName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_AVATAR_TEXT, contact.getAvatarText());

        database.update(
                TABLE_CONTACTS,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())}
        );

        database.close();
    }

    public void deleteContact(int contactId) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(
                TABLE_CONTACTS,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactId)}
        );

        database.close();
    }
}
