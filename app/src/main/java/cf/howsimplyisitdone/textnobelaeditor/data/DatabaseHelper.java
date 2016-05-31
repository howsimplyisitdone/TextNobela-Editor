package cf.howsimplyisitdone.textnobelaeditor.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Messages;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Settings;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.ContactsRepo;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.MessagesRepo;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.SettingsRepo;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // the database version
    private static final int DATABASE_VERSION = 9;
    // the database name
    private static final String DATABASE_NAME = "textnobela.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactsRepo.createContact());
        db.execSQL(MessagesRepo.createMessage());
        db.execSQL(SettingsRepo.createSetting());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contacts.tableContact);
        db.execSQL("DROP TABLE IF EXISTS " + Messages.tableMessage);
        db.execSQL("DROP TABLE IF EXISTS " + Settings.tableSetting);
        this.onCreate(db);
    }
}
