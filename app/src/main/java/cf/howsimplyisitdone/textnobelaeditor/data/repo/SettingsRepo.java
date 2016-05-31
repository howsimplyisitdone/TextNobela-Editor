package cf.howsimplyisitdone.textnobelaeditor.data.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.audiofx.BassBoost;

import java.util.ArrayList;
import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.data.DatabaseHelper;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Settings;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class SettingsRepo {
    private DatabaseHelper helper;

    public SettingsRepo(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public static String createSetting()
    {
        return "CREATE TABLE " + Settings.tableSetting +
                "(" + Settings.settingAttrib+
                " TEXT NOT NULL, " + Settings.settingDesc +
                " TEXT)";
    }

    public String insertSetting(Settings setting) {
        String settingId;

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Settings.settingAttrib, setting.setting_attribute);
        values.put(Settings.settingDesc, setting.setting_description);

        settingId = String.valueOf(db.insert(Settings.tableSetting, null, values));
        db.close();

        return settingId;
    }

    public void updateSetting(Settings setting) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Settings.settingAttrib, setting.setting_attribute);

        db.update(Settings.tableSetting, values, Settings.settingDesc + "=?", new String[]{String.valueOf(setting.setting_description)});
        db.close();
    }

    public List<Settings> getLayout(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT " + Settings.settingDesc +
                ", " + Settings.settingAttrib +
                " FROM " + Settings.tableSetting +
                " ORDER BY " + Settings.settingDesc;

        List<Settings> settingsList = new ArrayList<Settings>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Settings settings =  new Settings();
                settings.setSettingAttribute(cursor.getString(0));
                settings.setSettingDescription(cursor.getString(1));
                settingsList.add(settings);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return settingsList;
    }

//    public Contacts getContactByName(String name)
//    {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        String query = "SELECT " + Contacts.contactId +
//                ", " + Contacts.contactName +
//                ", " + Contacts.contactNumber +
//                ", " + Contacts.contactNote +
//                " FROM " + Contacts.tableContact +
//                " WHERE " + Contacts.contactName +
//                " =?";
//
//        Contacts contacts = new Contacts();
//        Cursor cursor = db.rawQuery(query, new String[]{(name)});
//
//        if (cursor.moveToFirst())
//        {
//            contacts.contact_id = cursor.getString(cursor.getColumnIndex(Contacts.contactId));
//            contacts.contact_name = cursor.getString(cursor.getColumnIndex(Contacts.contactName));
//            contacts.contact_note = cursor.getString(cursor.getColumnIndex(Contacts.contactNote));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return contacts;
//    }
//
//    public List<Contacts> getContactsNotById(String chatasid){
//        SQLiteDatabase db = helper.getReadableDatabase();
//        String selectQuery = "SELECT " + Contacts.contactId +
//                ", " + Contacts.contactName +
//                ", " + Contacts.contactNumber +
//                ", " + Contacts.contactNote +
//                " FROM " + Contacts.tableContact +
//                " WHERE " + Contacts.contactId +
//                " !=? ORDER BY " + Contacts.contactName;
//
//        List<Contacts> contactsList = new ArrayList<Contacts>();
//        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(chatasid)});
//
//        if (cursor.moveToFirst()) {
//            do {
//                Contacts contacts =  new Contacts();
//                contacts.setContactId(cursor.getString(cursor.getColumnIndex(Contacts.contactId)));
//                contacts.setContactName(cursor.getString(cursor.getColumnIndex(Contacts.contactName)));
//                contacts.setContactNote(cursor.getString(cursor.getColumnIndex(Contacts.contactNote)));
//                contacts.setContactNumber(cursor.getString(cursor.getColumnIndex(Contacts.contactNumber)));
//                contactsList.add(contacts);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return contactsList;
//    }
}
