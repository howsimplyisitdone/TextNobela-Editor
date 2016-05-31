package cf.howsimplyisitdone.textnobelaeditor.data.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.data.DatabaseHelper;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class ContactsRepo {
    private DatabaseHelper helper;

    public ContactsRepo(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public static String createContact()
    {
        return "CREATE TABLE " + Contacts.tableContact +
                "(" + Contacts.contactId +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Contacts.contactName +
                " TEXT NOT NULL, " + Contacts.contactNumber +
                " TEXT, " + Contacts.contactNote +
                " TEXT)";
    }

    public int insertContact(Contacts contact) {
        int contactId;

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contacts.contactName, contact.contact_name);
        values.put(Contacts.contactNote, contact.contact_note);
        values.put(Contacts.contactNumber, contact.contact_number);

        contactId = (int)db.insert(Contacts.tableContact, null, values);
        db.close();

        return contactId;
    }

    public void deleteContact(int contactId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Contacts.tableContact, Contacts.contactId + "=?", new String[]{String.valueOf(contactId)} );
        db.close();
    }

    public void updateContact(Contacts contact) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contacts.contactName, contact.contact_name);
        values.put(Contacts.contactNumber, contact.contact_number);
        values.put(Contacts.contactNote, contact.contact_note);

        db.update(Contacts.tableContact, values, Contacts.contactId + "=?", new String[]{String.valueOf(contact.contact_id)});
        db.close();
    }

    public List<Contacts> getContacts(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT " + Contacts.contactId +
                ", " + Contacts.contactName +
                ", " + Contacts.contactNote +
                ", " + Contacts.contactNumber +
                " FROM " + Contacts.tableContact +
                " ORDER BY " + Contacts.contactName;

        List<Contacts> contactsList = new ArrayList<Contacts>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contacts contacts =  new Contacts();
                contacts.setContactId(cursor.getString(cursor.getColumnIndex(Contacts.contactId)));
                contacts.setContactName(cursor.getString(cursor.getColumnIndex(Contacts.contactName)));
                contacts.setContactNote(cursor.getString(cursor.getColumnIndex(Contacts.contactNote)));
                contacts.setContactNumber(cursor.getString(cursor.getColumnIndex(Contacts.contactNumber)));
                contactsList.add(contacts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactsList;
    }

    public Contacts getContactByName(String name)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = "SELECT " + Contacts.contactId +
                ", " + Contacts.contactName +
                ", " + Contacts.contactNumber +
                ", " + Contacts.contactNote +
                " FROM " + Contacts.tableContact +
                " WHERE " + Contacts.contactName +
                " =?";

        Contacts contacts = new Contacts();
        Cursor cursor = db.rawQuery(query, new String[]{(name)});

        if (cursor.moveToFirst())
        {
            contacts.contact_id = cursor.getString(cursor.getColumnIndex(Contacts.contactId));
            contacts.contact_name = cursor.getString(cursor.getColumnIndex(Contacts.contactName));
            contacts.contact_note = cursor.getString(cursor.getColumnIndex(Contacts.contactNote));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return contacts;
    }

    public List<Contacts> getContactsNotById(String chatasid){
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT " + Contacts.contactId +
                ", " + Contacts.contactName +
                ", " + Contacts.contactNumber +
                ", " + Contacts.contactNote +
                " FROM " + Contacts.tableContact +
                " WHERE " + Contacts.contactId +
                " !=? ORDER BY " + Contacts.contactName;

        List<Contacts> contactsList = new ArrayList<Contacts>();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(chatasid)});

        if (cursor.moveToFirst()) {
            do {
                Contacts contacts =  new Contacts();
                contacts.setContactId(cursor.getString(cursor.getColumnIndex(Contacts.contactId)));
                contacts.setContactName(cursor.getString(cursor.getColumnIndex(Contacts.contactName)));
                contacts.setContactNote(cursor.getString(cursor.getColumnIndex(Contacts.contactNote)));
                contacts.setContactNumber(cursor.getString(cursor.getColumnIndex(Contacts.contactNumber)));
                contactsList.add(contacts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactsList;
    }
}
