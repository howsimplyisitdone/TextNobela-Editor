package cf.howsimplyisitdone.textnobelaeditor.data.model;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class Settings {
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

    public String getSettingId(){
        return this.setting_id;
    }

    public String getSettingAttribute() {
        return this.setting_attribute;
    }

    public String getSettingDescription() {
        return this.setting_description;
    }
}
