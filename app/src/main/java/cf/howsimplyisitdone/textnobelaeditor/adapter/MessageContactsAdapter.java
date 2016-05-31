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
public class MessageContactsAdapter extends ArrayAdapter<ContactsAndMessages> {

    public MessageContactsAdapter(Context context, List<ContactsAndMessages> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactsAndMessages contacts = getItem(position);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/helvetica.ttf");

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_contacts_list, parent, false);
        }
        TextView listFromId = (TextView) convertView.findViewById(R.id.listFromId);
        TextView listToId = (TextView) convertView.findViewById(R.id.listToId);
        TextView listMessage = (TextView) convertView.findViewById(R.id.listContent);
        TextView listName = (TextView) convertView.findViewById(R.id.listMessageContactName);

        listName.setTypeface(typeface);
        listMessage.setTypeface(typeface);

        listFromId.setText(contacts.from_id);
        listToId.setText(contacts.to_id);
        listMessage.setText(contacts.message_content);
        listName.setText(contacts.contact_name);

//        if (contacts.contact_name.startsWith("D")) {
//            listContactName.setGravity(Gravity.LEFT);
//        } else {
//            listContactName.setGravity(Gravity.RIGHT);
//        }
        return convertView;
    }
}
