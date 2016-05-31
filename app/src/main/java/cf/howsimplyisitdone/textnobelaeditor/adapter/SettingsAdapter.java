package cf.howsimplyisitdone.textnobelaeditor.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.R;
import cf.howsimplyisitdone.textnobelaeditor.data.model.ContactsAndMessages;

/**
 * Created by dominic.m.condes on 5/19/2016.
 */
public class SettingsAdapter extends BaseAdapter {

    String[] setting;
    Context context;

    public SettingsAdapter(Context context, String[] categories) {
        this.context = context;
        this.setting = categories;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/helvetica.ttf");

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.setting_layout_list, parent, false);
        }
        TextView listLayoutName = (TextView) convertView.findViewById(R.id.listLayoutName);

        listLayoutName.setTypeface(typeface);

        listLayoutName.setText(setting[position]);

        return convertView;
    }
}
