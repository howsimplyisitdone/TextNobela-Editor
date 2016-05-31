package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.ContactsRepo;

public class ContactDetailActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    String _contactId, _contactName, _contactNote;
    TextView tvName, tvNote, tvId, tvContact;
    ImageView iBack, iEdit, iSave;
    EditText etContactName, etContactNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        tvId = (TextView) findViewById(R.id.tvId);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNote = (TextView) findViewById(R.id.tvNote);
        tvContact = (TextView) findViewById(R.id.tvContactName);
        tvId.setTypeface(typeface);
        tvName.setTypeface(typeface);
        tvNote.setTypeface(typeface);
        tvContact.setTypeface(typeface);

        iBack = (ImageView) findViewById(R.id.iBackContactDetail);
        iBack.setClickable(true);
        iEdit = (ImageView) findViewById(R.id.iEditContactDetail);
        iEdit.setClickable(true);
        iSave = (ImageView) findViewById(R.id.iSaveContactDetail);
        iSave.setClickable(false);

        etContactName = (EditText) findViewById(R.id.etContactName);
        etContactNote = (EditText) findViewById(R.id.etContactNote);
        etContactNote.setTypeface(typeface);
        etContactName.setTypeface(typeface);

        Intent intent = getIntent();
        _contactId = intent.getStringExtra("contactid");
        _contactName = intent.getStringExtra("contactname");
        _contactNote = intent.getStringExtra("contactnote");

        tvName.setText(_contactName);
        tvNote.setText(_contactNote);
        tvId.setText(_contactId);
    }

    public void clickBackContactDetail(View v) {
        Log.i(TAG, "Going back to ContactActivity");
        Intent intent = new Intent(ContactDetailActivity.this, ContactActivity.class);
        ContactDetailActivity.this.startActivity(intent);
        ContactDetailActivity.this.finish();
    }

    public void clickEditContact(View v) {
        Log.i(TAG, "clickEditContact has been pressed");

        iEdit.setVisibility(View.GONE);
        iEdit.setClickable(false);
        iSave.setVisibility(View.VISIBLE);
        iSave.setClickable(true);

        tvName.setVisibility(View.GONE);
        tvNote.setVisibility(View.GONE);

        etContactName.setVisibility(View.VISIBLE);
        etContactName.setText(_contactName);
        Log.i(TAG, _contactName + " is the name");
        etContactNote.setVisibility(View.VISIBLE);
        etContactNote.setText(_contactNote);
        Log.i(TAG, _contactNote + " is the note");
        tvId.setText(_contactId);
        Log.i(TAG, _contactId + " is the id");

        String eText = getResources().getString(R.string.edit_detail);
        tvContact.setText(eText);
    }

    public void clickSaveContact(View v) {
        Log.i(TAG, "clickSaveContact has been pressed");

        ContactsRepo handler = new ContactsRepo(this);
        Contacts contacts = new Contacts();

        contacts.contact_id = tvId.getText().toString();
        contacts.contact_name = etContactName.getText().toString();
        contacts.contact_note = etContactNote.getText().toString();
        handler.updateContact(contacts);
        Toast.makeText(this, contacts.contact_name + " has been updated!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ContactDetailActivity.this, ContactActivity.class);
        ContactDetailActivity.this.startActivity(intent);
        ContactDetailActivity.this.finish();
    }
}
