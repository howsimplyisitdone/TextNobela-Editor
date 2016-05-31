package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.*;

import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.ContactsRepo;

public class CreateContactActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    private String _contactname, _contactnote;
    private AdView adView;
    ImageView iBack, iCreateContact;
    EditText etContactName, etContactNote;
    TextView tvSaveContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        Log.i(TAG, "This is the CreateContactActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        iBack = (ImageView) findViewById(R.id.iBackCreateContact);
        iBack.setClickable(true);
        iCreateContact = (ImageView) findViewById(R.id.iCreateContact);
        iCreateContact.setClickable(true);

        etContactName = (EditText) findViewById(R.id.etContactName);
        etContactNote = (EditText) findViewById(R.id.etContactNote);

        tvSaveContact = (TextView) findViewById(R.id.tvSaveContact);

        etContactName.setTypeface(typeface);
        etContactNote.setTypeface(typeface);

        tvSaveContact.setTypeface(typeface);

        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);

        adView = new AdView(this, "1034786473263529_1058043840937792", AdSize.BANNER_320_50);
        adViewContainer.addView(adView);
        AdSettings.addTestDevice("648699642db48f6478e88fe19d91ce59");
        adView.loadAd();
    }

    public void clickSaveContact(View v){
        Log.i(TAG, "clickSaveContact has been pressed");

        // setting values in preparation for insert
        ContactsRepo contactsRepo = new ContactsRepo(this);
        Contacts contacts = new Contacts();
        contacts.contact_name = etContactName.getText().toString();
        contacts.contact_note = etContactNote.getText().toString();


        // when Name isn't empty or just spaces
        if (etContactName.getText().toString().trim().length() > 0) {
            Log.i(TAG, "Contacts will be saved...");
            Log.i(TAG, contacts.contact_name);
            Log.i(TAG, contacts.contact_note);

            // checking if Name already exists
            Contacts contactCheck = new Contacts();
            contactCheck = contactsRepo.getContactByName(contacts.contact_name);
            String _contactname = contactCheck.contact_name;
            Log.i(TAG, _contactname + " is the name");

            if (_contactname == null) {
                // insert the record
                int _contactid = contactsRepo.insertContact(contacts);
                Log.i(TAG, String.valueOf(_contactid));

                Toast.makeText(this,  contacts.contact_name + " has been added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateContactActivity.this, ContactActivity.class);
                CreateContactActivity.this.startActivity(intent);
                CreateContactActivity.this.finish();
            }
            else if(_contactname.equalsIgnoreCase(contacts.contact_name)) {
                Toast.makeText(this, contacts.contact_name + " already exists", Toast.LENGTH_SHORT).show();
                etContactName.setText("");
                etContactNote.setText("");
                etContactName.isFocused();
            } else {
                // insert the record
                int _contactid = contactsRepo.insertContact(contacts);
                Log.i(TAG, String.valueOf(_contactid));

                Toast.makeText(this,  contacts.contact_name + " has been added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateContactActivity.this, ContactActivity.class);
                CreateContactActivity.this.startActivity(intent);
                CreateContactActivity.this.finish();
            }
        } else {
            Log.i(TAG, "etContactName is blank");
            Toast.makeText(this, "Name is blank", Toast.LENGTH_SHORT).show();
            etContactName.isFocusable();
        }
    }


    public void clickContactBack(View v){
        Log.i(TAG, "Going back to ContactActivity");
        Intent intent = new Intent(CreateContactActivity.this, ContactActivity.class);
        CreateContactActivity.this.startActivity(intent);
        CreateContactActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to ContactActivity");
        Intent intent = new Intent(CreateContactActivity.this, ContactActivity.class);
        CreateContactActivity.this.startActivity(intent);
        CreateContactActivity.this.finish();

        adView.destroy();
    }
}
