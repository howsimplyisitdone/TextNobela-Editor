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
import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class SelectContactsAdapter extends ArrayAdapter<Contacts> {

    public SelectContactsAdapter(Context context, List<Contacts> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contacts contacts = getItem(position);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/helvetica.ttf");

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.select_contacts_list, parent, false);
        }
        TextView listContactId = (TextView) convertView.findViewById(R.id.tvChatToId);
        TextView listContactName = (TextView) convertView.findViewById(R.id.tvChatToName);

        listContactName.setTypeface(typeface);

        listContactId.setText(contacts.contact_id);
        listContactName.setText(contacts.contact_name);

        return convertView;
    }
}
