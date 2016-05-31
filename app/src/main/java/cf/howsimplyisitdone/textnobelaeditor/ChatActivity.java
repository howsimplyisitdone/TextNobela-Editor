package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    String _contactId, _contactName;
    ImageView iBack;
    TextView tvContactId, tvContactName, tvChat;
    ListView listChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Log.i(TAG, "This is the ChatActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        tvChat = (TextView) findViewById(R.id.tvChat);
        tvChat.setTypeface(typeface);

        iBack = (ImageView) findViewById(R.id.iBackMessageContact);
        iBack.setClickable(true);

        listChat = (ListView) findViewById(R.id.listChatContact);
        ContactsRepo contactsRepo = new ContactsRepo(this);
        List<Contacts> contactsList = contactsRepo.getContacts();
        ContactsAdapter adapter = new ContactsAdapter(this, contactsList);
        listChat.setAdapter(adapter);
        listChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvContactId = (TextView) view.findViewById(R.id.listContactId);
                tvContactName = (TextView) view.findViewById(R.id.listContactName);
                _contactId = tvContactId.getText().toString();
                _contactName = tvContactName.getText().toString();
                Intent objIntent = new Intent(ChatActivity.this, MessageActivity.class);
                objIntent.putExtra("chatasid", String.valueOf(_contactId));
                objIntent.putExtra("chatasname", String.valueOf(_contactName));
                ChatActivity.this.startActivity(objIntent);
                ChatActivity.this.finish();
            }
        });

        Log.i(TAG, String.valueOf(contactsList));
    }

    public void clickBackChat (View v){
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        ChatActivity.this.startActivity(intent);
        ChatActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        ChatActivity.this.startActivity(intent);
        ChatActivity.this.finish();
    }
}
