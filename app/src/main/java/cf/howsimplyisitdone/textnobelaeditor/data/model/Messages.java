package cf.howsimplyisitdone.textnobelaeditor.data.model;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class Messages {
    // table
    public static final String tableMessage = "message";
    // columns
    public static final String messageId = "messageid";
    public static final String messageContent = "content";
    public static final String fromId = "fromid";
    public static final String toId = "toid";
    public static final String dateSaved = "savedate";
    public static final String timeSaved = "savetime";
    // properties
    public String message_content, from_id, to_id, message_id, type, save_date, save_time;

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

}
