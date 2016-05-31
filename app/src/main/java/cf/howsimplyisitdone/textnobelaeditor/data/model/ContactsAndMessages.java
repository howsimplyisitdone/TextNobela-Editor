package cf.howsimplyisitdone.textnobelaeditor.data.model;

/**
 * Created by dominic.m.condes on 5/21/2016.
 */
public class ContactsAndMessages {
    // table
    public static final String tableContact = "contact";
    // columns
    public static final String contactId = "contactid";
    public static final String contactName = "name";
    public static final String contactNumber = "number";
    public static final String contactNote = "note";

    // table
    public static final String tableMessage = "message";
    // columns
    public static final String messageId = "messageid";
    public static final String messageContent = "content";
    public static final String fromId = "fromid";
    public static final String toId = "toid";
    public static final String dateSaved = "savedate";
    public static final String timeSaved = "savetime";

    public String contact_name, contact_number, contact_note, contact_id;
    public String message_content, from_id, to_id, message_id, type, save_date, save_time;

    // table
    public static final String tableSetting = "setting";
    // columns
    public static final String settingId = "settingid";
    public static final String settingAttrib = "attribute";
    public static final String settingDesc = "description";
    // properties
    public String setting_id, setting_attribute, setting_description;

    public void setSettingId (String settingid){
        this.setting_id = settingid;
    }

    public void setSettingAttribute(String settingattrib){
        this.setting_attribute = settingattrib;
    }

    public void setSettingDescription(String description){
        this.setting_description = description;
    }

    public void setContactId (String contactid){
        this.contact_id = contactid;
    }

    public void setContactName(String contactname){
        this.contact_name = contactname;
    }

    public void setContactNote(String contactnote){
        this.contact_note = contactnote;
    }

    public void setContactNumber(String contactnumber){
        this.contact_number = contactnumber;
    }

    public void setMessageId (String messageid) {
        this.message_id = messageid;
    }

    public void setMessageContent (String messagecontent) {
        this.message_content = messagecontent;
    }

    public void setFromId (String fromid) {
        this.from_id = fromid;
    }

    public void setToId (String toid) {
        this.to_id = toid;
    }

    public void setType (String type) {
        this.type = type;
    }

    public void setTime (String ctime) {
        this.save_time = ctime;
    }

    public void setDate (String cdate) {
        this.save_date = cdate;
    }
}
