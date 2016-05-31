package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import cf.howsimplyisitdone.textnobelaeditor.adapter.ContactsAdapter;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.ContactsRepo;

public class ContactActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    String _contactId, _contactName, _contactNote;
    ImageView iBack, iCreateContact;
    TextView tvContactId, tvContactName, tvContactNote, tvContact;
    ListView listContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Log.i(TAG, "This is the ContactActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        tvContact = (TextView) findViewById(R.id.tvContact);
        tvContact.setTypeface(typeface);

        iBack = (ImageView) findViewById(R.id.iBackContact);
        iBack.setClickable(true);
        iCreateContact = (ImageView) findViewById(R.id.iCreateContact);
        iCreateContact.setClickable(true);

        listContact = (ListView) findViewById(R.id.listContact);
        ContactsRepo contactsRepo = new ContactsRepo(this);
        List<Contacts> contactsList = contactsRepo.getContacts();
        ContactsAdapter adapter = new ContactsAdapter(this, contactsList);
        listContact.setAdapter(adapter);
        listContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvContactId = (TextView) view.findViewById(R.id.listContactId);
                tvContactName = (TextView) view.findViewById(R.id.listContactName);
                tvContactNote = (TextView) view.findViewById(R.id.listContactNote);
                _contactId = tvContactId.getText().toString();
                _contactName = tvContactName.getText().toString();
                _contactNote = tvContactNote.getText().toString();
                Intent objIntent = new Intent(ContactActivity.this, ContactDetailActivity.class);
                objIntent.putExtra("contactid", String.valueOf(_contactId));
                objIntent.putExtra("contactname", String.valueOf(_contactName));
                objIntent.putExtra("contactnote", String.valueOf(_contactNote));
                ContactActivity.this.startActivity(objIntent);
                ContactActivity.this.finish();
            }
        });

        Log.i(TAG, String.valueOf(contactsList));
    }

    public void clickBack (View v){
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(ContactActivity.this, MainActivity.class);
        ContactActivity.this.startActivity(intent);
        ContactActivity.this.finish();
    }

    public void clickCreateContact (View v){
        Log.i(TAG, "clickCreateContact has been pressed");
        Intent intent = new Intent(ContactActivity.this, CreateContactActivity.class);
        ContactActivity.this.startActivity(intent);
        ContactActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(ContactActivity.this, MainActivity.class);
        ContactActivity.this.startActivity(intent);
        ContactActivity.this.finish();
    }
}
