package cf.howsimplyisitdone.textnobelaeditor.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.R;
import cf.howsimplyisitdone.textnobelaeditor.data.model.ContactsAndMessages;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class MessagesAdapter extends ArrayAdapter<ContactsAndMessages> {

    public MessagesAdapter(Context context, List<ContactsAndMessages> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactsAndMessages contact = getItem(position);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/helvetica.ttf");

        if(convertView == null) {
            if (contact.type.equalsIgnoreCase("me")) {
                if (contact.setting_attribute.equalsIgnoreCase("theme 1")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 2")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left2, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 3")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left3, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 4")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left4, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 5")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left5, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 6")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left6, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 7")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left7, parent, false);
                } else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_left8, parent, false);
                }
            } else {
                if (contact.setting_attribute.equalsIgnoreCase("theme 1")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 2")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right2, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 3")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right3, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 4")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right4, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 5")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right5, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 6")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right6, parent, false);
                } else if (contact.setting_attribute.equalsIgnoreCase("theme 7")) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right7, parent, false);
                } else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_details_right8, parent, false);
                }
            }
        }
            TextView listFromId = (TextView) convertView.findViewById(R.id.listFromId);
            TextView listToId = (TextView) convertView.findViewById(R.id.listToId);
            TextView listMessage = (TextView) convertView.findViewById(R.id.listMessage);
            TextView listTime = (TextView) convertView.findViewById(R.id.listTime);

            listMessage.setTypeface(typeface);
            listTime.setTypeface(typeface);

            listFromId.setText(contact.from_id);
            listToId.setText(contact.to_id);
            listMessage.setText(contact.message_content);
            listTime.setText(contact.save_time);

            return convertView;
        }
}
