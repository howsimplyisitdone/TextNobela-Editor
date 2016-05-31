package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import cf.howsimplyisitdone.textnobelaeditor.adapter.MessagesAdapter;
import cf.howsimplyisitdone.textnobelaeditor.data.model.ContactsAndMessages;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Messages;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.MessagesRepo;

public class MessageDetailActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    String _fromid, _toid, _fromname, _toname, _message, _messageid;

    TextView fromid, fromname, toid, toname;
    ListView listMessages;
    EditText etmessages;
    ImageView iBack, iSend;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message_detail);
        setContentView(R.layout.message_detail_float);

        Log.i(TAG, "This is the MessageDetailActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        _fromid = "";
        _toid = "";
        _fromname = "";
        _toname = "";

        Intent intent = getIntent();
        _fromid = intent.getStringExtra("chatFromId");
        Log.i(TAG, _fromid + " is the fromId");
        _fromname = intent.getStringExtra("chatFromName");
        Log.i(TAG, _fromname + " is the fromName");
        _toid = intent.getStringExtra("chatToId");
        Log.i(TAG, _toid + " is the toId");
        _toname = intent.getStringExtra("chatToName");
        Log.i(TAG, _toname + " is the toName");

        iBack = (ImageView) findViewById(R.id.iBackMessageDetail);
        iBack.setClickable(true);
        iSend = (ImageView) findViewById(R.id.iSend);
        iSend.setClickable(true);

        toid = (TextView) findViewById(R.id.tvMessageTo);
        toid.setText(_toid);
        toname = (TextView) findViewById(R.id.tvToContact);
        toname.setText(_toname);
        toname.setTypeface(typeface);
        fromid = (TextView) findViewById(R.id.tvMessageFrom);
        fromid.setText(_fromid);
        fromname = (TextView) findViewById(R.id.tvFromContact);
        fromname.setText(_fromname);
        fromname.setTypeface(typeface);

        etmessages = (EditText) findViewById(R.id.etMessage);
        etmessages.setTypeface(typeface);

        listMessages = (ListView) findViewById(R.id.listMessage);

        fab = (FloatingActionButton) findViewById(R.id.capture);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RelativeLayout main = (RelativeLayout) findViewById(R.id.Main);
                main.setDrawingCacheEnabled(true);
                main.buildDrawingCache(true);

                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), main.getDrawingCache(), UUID.randomUUID()
                                .toString() + ".jpg", "message");

                if (imgSaved != null)
                {
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Message captured", Toast.LENGTH_SHORT);
                    savedToast.show();
                } else {
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);

                    unsavedToast.show();
                }

                main.setDrawingCacheEnabled(false);
            }
        });

        MessagesRepo messagesRepo = new MessagesRepo(this);
        List<ContactsAndMessages> messages = messagesRepo.getMessage(_fromid, _toid);
        MessagesAdapter adapter = new MessagesAdapter(this, messages);
        listMessages.setAdapter(adapter);

        etmessages.setOnTouchListener(new View.OnTouchListener(){

              @Override
              public boolean onTouch(View v, MotionEvent event) {
                  etmessages.requestFocus();
                  return false;
              }
          }
        );
    }

    public void clickBackMessageDetail(View v) {
        Log.i(TAG, "Going back to MessageActivity");
        Intent intent = new Intent(MessageDetailActivity.this, MessageActivity.class);
        intent.putExtra("chatasid", String.valueOf(_fromid));
        intent.putExtra("chatasname", String.valueOf(_fromname));
        MessageDetailActivity.this.startActivity(intent);
        MessageDetailActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to MessageActivity");
        Intent intent = new Intent(MessageDetailActivity.this, MessageActivity.class);
        intent.putExtra("chatasid", String.valueOf(_fromid));
        intent.putExtra("chatasname", String.valueOf(_fromname));
        MessageDetailActivity.this.startActivity(intent);
        MessageDetailActivity.this.finish();
    }

    public void clickSendMessage(View v) {
        Log.i(TAG, "clickSend has been pressed");

        // checking for values
        _message = etmessages.getText().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String curdate = dateFormat.format(calendar.getTime());
        String curtime = timeFormat.format(calendar.getTime());

        if (_message != null) {
            MessagesRepo messagesRepo = new MessagesRepo(this);
            Messages messages = new Messages();
            messages.from_id = _fromid;
            messages.to_id = _toid;
            messages.message_content = _message;
            messages.save_date = curdate;
            messages.save_time = curtime;
            Log.i(TAG, _toid + "is toid");
            Log.i(TAG, _fromid + "is fromid");
            Log.i(TAG, _message + "is message");
            _messageid = messagesRepo.insertMessage(messages);

            Toast.makeText(this, "Message saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MessageDetailActivity.this, ChatActivity.class);
            MessageDetailActivity.this.startActivity(intent);
            MessageDetailActivity.this.finish();
        } else {
            Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
