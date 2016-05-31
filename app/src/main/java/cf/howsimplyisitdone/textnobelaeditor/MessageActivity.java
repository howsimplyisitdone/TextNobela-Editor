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

import cf.howsimplyisitdone.textnobelaeditor.adapter.MessageContactsAdapter;
import cf.howsimplyisitdone.textnobelaeditor.data.model.ContactsAndMessages;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.MessagesRepo;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    String _fromid, _toid, _content, _fromname;
    String __toid, __fromid, __toname, __fromname;
    TextView fromid, fromname;
    TextView fromid_, toid_, toname_;
    ImageView iBack, iCreateMessage;
    ListView listMessageContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Log.i(TAG, "This is the MessageActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        _fromid = "";
        _toid = "";
        _fromname = "";
        _content = "";

        Intent intent = getIntent();
        _fromid = intent.getStringExtra("chatasid");
        _fromname = intent.getStringExtra("chatasname");

        iBack = (ImageView) findViewById(R.id.iBackMessageContact);
        iBack.setClickable(true);
        iCreateMessage = (ImageView) findViewById(R.id.iCreateMessage);
        iCreateMessage.setClickable(true);

        fromname = (TextView) findViewById(R.id.tvName);
        fromname.setText(_fromname);
        fromname.setTypeface(typeface);
        Log.i(TAG, _fromname + " is the name");
        fromid = (TextView) findViewById(R.id.tvId);
        fromid.setText(_fromid);
        Log.i(TAG, _fromid + " is the id");

        listMessageContacts = (ListView) findViewById(R.id.listMessageContact);
        MessagesRepo messagesRepo = new MessagesRepo(this);
        List<ContactsAndMessages> contactsandmessages = messagesRepo.getContactsMessage(_fromid);
        MessageContactsAdapter adapter = new MessageContactsAdapter(this, contactsandmessages);
        adapter.notifyDataSetChanged();
        listMessageContacts.setAdapter(adapter);
        listMessageContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toid_ = (TextView) view.findViewById(R.id.listToId);
                __toid = toid_.getText().toString();
                fromid_ = (TextView) view.findViewById(R.id.listFromId);
                __fromid = fromid_.getText().toString();
                toname_ = (TextView) view.findViewById(R.id.listMessageContactName);
                __toname = toname_.getText().toString();
                __fromname = _fromname;
                Intent objIntent = new Intent(MessageActivity.this, MessageDetailActivity.class);
                objIntent.putExtra("chatToId", String.valueOf(__toid));
                objIntent.putExtra("chatToName", String.valueOf(__toname));
                objIntent.putExtra("chatFromId", String.valueOf(__fromid));
                objIntent.putExtra("chatFromName", String.valueOf(__fromname));
                MessageActivity.this.startActivity(objIntent);
                MessageActivity.this.finish();
            }
        });
    }

    public void clickBackMessage (View v){
        Log.i(TAG, "Going back to ChatActivity");
        Intent intent = new Intent(MessageActivity.this, ChatActivity.class);
        MessageActivity.this.startActivity(intent);
        MessageActivity.this.finish();
    }

    public void clickCreateMessage (View v){
        Log.i(TAG, "clickCreateMessage has been pressed");
        Intent intent = new Intent(MessageActivity.this, CreateMessageActivity.class);
        intent.putExtra("chatasid", String.valueOf(_fromid));
        intent.putExtra("chatasname", String.valueOf(_fromname));
        MessageActivity.this.startActivity(intent);
        MessageActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to ChatActivity");
        Intent intent = new Intent(MessageActivity.this, ChatActivity.class);
        MessageActivity.this.startActivity(intent);
        MessageActivity.this.finish();
    }
}
