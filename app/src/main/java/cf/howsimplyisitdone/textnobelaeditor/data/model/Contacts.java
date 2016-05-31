package cf.howsimplyisitdone.textnobelaeditor.data.model;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class Contacts {
    // table
    public static final String tableContact = "contact";
    // columns
    public static final String contactId = "contactid";
    public static final String contactName = "name";
    public static final String contactNumber = "number";
    public static final String contactNote = "note";
    // properties
    public String contact_name, contact_number, contact_note, contact_id;

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

    public String getContactId(){
        return this.contact_id;
    }

    public String getContactName() {
        return this.contact_name;
    }

    public String getContactNote() {
        return this.contact_note;
    }

    public String getContactNumber() {
        return this.contact_number;
    }

}
