package cf.howsimplyisitdone.textnobelaeditor.data.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.data.DatabaseHelper;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;
import cf.howsimplyisitdone.textnobelaeditor.data.model.ContactsAndMessages;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Messages;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class MessagesRepo {
    private DatabaseHelper helper;

    public MessagesRepo(Context context) {
        helper = new DatabaseHelper(context);
    }

    public static String createMessage() {
        return "CREATE TABLE " + Messages.tableMessage +
                "(" + Messages.messageId +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + Messages.messageContent +
                " TEXT NOT NULL, " + Messages.fromId +
                " TEXT, " + Messages.toId +
                " TEXT, " + Messages.timeSaved +
                " TEXT, " + Messages.dateSaved +
                " TEXT)";

    }

    public String insertMessage(Messages message) {
        String messageid;

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Messages.fromId, message.from_id);
        values.put(Messages.toId, message.to_id);
        values.put(Messages.messageContent, message.message_content);
        values.put(Messages.timeSaved, message.save_time);
        values.put(Messages.dateSaved, message.save_date);

        messageid = String.valueOf(db.insert(Messages.tableMessage, null, values));
        db.close();

        return messageid;
    }

    public void deleteMessage(int messageId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Messages.tableMessage, Messages.messageId + "=?", new String[]{String.valueOf(messageId)});
        db.close();
    }

    public void updateMessage(Messages message) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Messages.messageContent, message.message_content);

        db.update(Messages.tableMessage, values, Messages.messageId + "=?", new String[]{String.valueOf(message.message_id)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getMessageContactListById(int fromid) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String master = "(SELECT " + Contacts.contactName +
                ", " + Contacts.contactId +
                " FROM " + Contacts.tableContact + ") contactTo";
        String sub = "(SELECT " + Messages.fromId +
                ", " + Messages.toId +
                ", CASE ? WHEN " + Messages.fromId +
                " THEN " + Messages.toId +
                " WHEN " + Messages.toId +
                " THEN " + Messages.fromId +
                " ELSE 0 END AS joinID, " + Messages.messageContent +
                " FROM " + Messages.tableMessage +
                " ) messageTo";
        String query = "SELECT contactTo." + Contacts.contactName +
                ", messageTo." + Messages.fromId +
                ", messageTo." + Messages.toId +
                ", messageTo." + Messages.messageContent +
                " FROM " + master +
                " INNER JOIN " + sub +
                " ON contactTo." + Contacts.contactId +
                " = messageTo.joinID GROUP BY contactTo."
                + Contacts.contactName +
                " ORDER BY contactTo." + Contacts.contactName;

        ArrayList<HashMap<String, String>> messageList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(fromid)});
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> messages = new HashMap<String, String>();
                messages.put("contactName", cursor.getString(0));
                messages.put("fromId", cursor.getString(1));
                messages.put("toId", cursor.getString(2));
                messages.put("messageContent", cursor.getString(3));
                messageList.add(messages);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return messageList;
    }

    public List<ContactsAndMessages> getContactsMessage(String chatasid){
        SQLiteDatabase db = helper.getReadableDatabase();
        String master = "(SELECT " + ContactsAndMessages.contactName +
                ", " + ContactsAndMessages.contactId +
                " FROM " + ContactsAndMessages.tableContact + ") contactTo";
        String sub = "(SELECT " + ContactsAndMessages.fromId +
                ", " + ContactsAndMessages.toId +
                ", CASE ? WHEN " + ContactsAndMessages.fromId +
                " THEN " + ContactsAndMessages.toId +
                " WHEN " + ContactsAndMessages.toId +
                " THEN " + ContactsAndMessages.fromId +
                " ELSE 0 END AS joinID, " + ContactsAndMessages.messageContent +
                " FROM " + ContactsAndMessages.tableMessage +
                " ) messageTo";
        String selectQuery = "SELECT contactTo." + ContactsAndMessages.contactName +
                ", CASE ? WHEN messageTo." + ContactsAndMessages.fromId +
                " THEN messageTo." + ContactsAndMessages.fromId +
                " ELSE messageTo." + ContactsAndMessages.toId +
                " END AS "+ ContactsAndMessages.fromId +
                ", CASE ? WHEN messageTo." + ContactsAndMessages.toId +
                " THEN messageTo." + ContactsAndMessages.fromId +
                " ELSE messageTo." + ContactsAndMessages.toId +
                " END AS "+ ContactsAndMessages.toId +
                ", messageTo." + ContactsAndMessages.messageContent +
                " FROM " + master +
                " INNER JOIN " + sub +
                " ON contactTo." + ContactsAndMessages.contactId +
                " = messageTo.joinID GROUP BY contactTo."
                + ContactsAndMessages.contactName +
                " ORDER BY contactTo." + ContactsAndMessages.contactName;

        List<ContactsAndMessages> contactsandmessages = new ArrayList<ContactsAndMessages>();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(chatasid), String.valueOf(chatasid), String.valueOf(chatasid)});

        if (cursor.moveToFirst()) {
            do {
                ContactsAndMessages contacts =  new ContactsAndMessages();
                contacts.setContactName(cursor.getString(0));
                contacts.setFromId(cursor.getString(1));
                contacts.setToId(cursor.getString(2));
                contacts.setMessageContent(cursor.getString(3));
                contactsandmessages.add(contacts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactsandmessages;
    }

    public List<ContactsAndMessages> getMessages (String fromid, String toid){
        SQLiteDatabase db = helper.getReadableDatabase();
        String toquery = "SELECT " + ContactsAndMessages.messageId +
                ", " + ContactsAndMessages.fromId +
                " AS toid, " + ContactsAndMessages.toId +
                " AS fromid, " + ContactsAndMessages.messageContent +
                ", CASE " + ContactsAndMessages.toId +
                " WHEN ? THEN 'RIGHT' ELSE 'LEFT' END AS type FROM " + ContactsAndMessages.tableMessage +
                " WHERE " + ContactsAndMessages.toId +
                " = ? AND " + ContactsAndMessages.fromId +
                " = ? ";
        String fromquery = "SELECT " + ContactsAndMessages.messageId +
                ", " + ContactsAndMessages.toId +
                " AS toid, " + ContactsAndMessages.fromId +
                " AS fromid, " + ContactsAndMessages.messageContent +
                ", CASE " + ContactsAndMessages.fromId +
                " WHEN ? THEN 'RIGHT' ELSE 'LEFT' END AS type FROM " + ContactsAndMessages.tableMessage +
                " WHERE " + ContactsAndMessages.fromId +
                " = ? AND " + ContactsAndMessages.toId +
                " = ? ";
        String selectQuery = toquery +
                " UNION " + fromquery +
                " ORDER BY " + ContactsAndMessages.messageId;

        List<ContactsAndMessages> contactsandmessages = new ArrayList<ContactsAndMessages>();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(fromid), String.valueOf(toid), String.valueOf(fromid), String.valueOf(toid), String.valueOf(fromid), String.valueOf(toid)});

        if (cursor.moveToFirst()) {
            do {
                ContactsAndMessages contacts =  new ContactsAndMessages();
                contacts.setMessageId(cursor.getString(0));
                contacts.setFromId(cursor.getString(1));
                contacts.setToId(cursor.getString(2));
                contacts.setMessageContent(cursor.getString(3));
                contacts.setType(cursor.getString(4));
                contactsandmessages.add(contacts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactsandmessages;
    }

    public List<ContactsAndMessages> getMessage (String fromid, String toid){
        SQLiteDatabase db = helper.getReadableDatabase();
        String from = "SELECT DISTINCT " + ContactsAndMessages.messageId +
                " FROM " + ContactsAndMessages.tableMessage +
                " WHERE " + ContactsAndMessages.toId +
                " = ? AND " + ContactsAndMessages.fromId +
                " = ?";
        String to = "SELECT DISTINCT " + ContactsAndMessages.messageId +
                " FROM " + ContactsAndMessages.tableMessage +
                " WHERE " + ContactsAndMessages.fromId +
                " = ? AND " + ContactsAndMessages.toId +
                " = ? ";
        String filter = "(" + from +
                " UNION " + to +
                ") filter";
        String layout = "(SELECT " + ContactsAndMessages.settingAttrib +
                ", 'layout' AS " + ContactsAndMessages.settingDesc +
                " FROM " + ContactsAndMessages.tableSetting +
                ") layout";
        String body = "(SELECT DISTINCT " + ContactsAndMessages.messageId +
                ", " + ContactsAndMessages.messageContent +
                ", CASE ? WHEN " + ContactsAndMessages.fromId +
                " THEN 'me' WHEN " + ContactsAndMessages.toId +
                " THEN 'notme' ELSE 'unknown' END AS type, CASE ? WHEN " + ContactsAndMessages.fromId +
                " THEN " + ContactsAndMessages.fromId +
                " ELSE " + ContactsAndMessages.toId +
                " END AS " + ContactsAndMessages.fromId +
                ", CASE ? WHEN " + ContactsAndMessages.fromId +
                " THEN " + ContactsAndMessages.toId +
                " ELSE " + ContactsAndMessages.fromId +
                " END AS " + ContactsAndMessages.toId +
                ", " + ContactsAndMessages.dateSaved +
                ", " + ContactsAndMessages.timeSaved +
                ", 'layout' AS " + ContactsAndMessages.settingDesc +
                " FROM " + ContactsAndMessages.tableMessage +
                ") body";
        String selectQuery = "SELECT DISTINCT body." + ContactsAndMessages.messageId +
                ", body." + ContactsAndMessages.fromId +
                ", body." + ContactsAndMessages.toId +
                ", body." + ContactsAndMessages.messageContent +
                ", body.type, body." + ContactsAndMessages.dateSaved +
                ", body." + ContactsAndMessages.timeSaved +
                ", layout." + ContactsAndMessages.settingAttrib +
                " FROM " + body +
                " INNER JOIN " + filter +
                " ON body." + ContactsAndMessages.messageId +
                " = filter." + ContactsAndMessages.messageId +
                " LEFT JOIN " + layout +
                " ON body." + ContactsAndMessages.settingDesc +
                " = layout." + ContactsAndMessages.settingDesc +
                " ORDER BY body." + ContactsAndMessages.messageId;

        List<ContactsAndMessages> contactsandmessages = new ArrayList<ContactsAndMessages>();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(fromid), String.valueOf(fromid), String.valueOf(fromid), String.valueOf(fromid), String.valueOf(toid), String.valueOf(fromid), String.valueOf(toid)});

        if (cursor.moveToFirst()) {
            do {
                ContactsAndMessages contacts =  new ContactsAndMessages();
                contacts.setMessageId(cursor.getString(0));
                contacts.setFromId(cursor.getString(1));
                contacts.setToId(cursor.getString(2));
                contacts.setMessageContent(cursor.getString(3));
                contacts.setType(cursor.getString(4));
                contacts.setDate(cursor.getString(5));
                contacts.setTime(cursor.getString(6));
                contacts.setSettingAttribute(cursor.getString(7));
                contactsandmessages.add(contacts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactsandmessages;
    }
}
