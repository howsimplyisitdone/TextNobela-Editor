package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import cf.howsimplyisitdone.textnobelaeditor.adapter.SelectContactsAdapter;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Contacts;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.ContactsRepo;

public class SelectContactActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    String _chatasid, _chattoid, _chattoname;
    TextView tvChatToId, tvChatToName, tvselectcontact;
    ListView listContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contact);

        Log.i(TAG, "This is the SelectContactActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        tvselectcontact = (TextView) findViewById(R.id.tvselectcontact);
        tvselectcontact.setTypeface(typeface);

        Intent intent = getIntent();
        _chatasid = intent.getStringExtra("chatasid");
        Log.i(TAG, _chatasid + " is the id");

        listContact = (ListView) findViewById(R.id.listContact);
        ContactsRepo contactsRepo = new ContactsRepo(this);
        List<Contacts> contactsList = contactsRepo.getContactsNotById(_chatasid);
        SelectContactsAdapter adapter = new SelectContactsAdapter(this, contactsList);
        listContact.setAdapter(adapter);
        listContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvChatToId = (TextView) view.findViewById(R.id.tvChatToId);
                tvChatToName = (TextView) view.findViewById(R.id.tvChatToName);
                _chattoid = tvChatToId.getText().toString();
                _chattoname = tvChatToName.getText().toString();
                Intent objIntent = new Intent(SelectContactActivity.this, CreateMessageActivity.class);
                objIntent.putExtra("chattoid", String.valueOf(_chattoid));
                objIntent.putExtra("chattoname", String.valueOf(_chattoname));
                setResult(RESULT_OK, objIntent);
                SelectContactActivity.this.finish();
            }
        });

        Log.i(TAG, String.valueOf(contactsList));
    }
}
