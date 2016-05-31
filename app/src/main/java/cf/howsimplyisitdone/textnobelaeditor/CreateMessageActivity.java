package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cf.howsimplyisitdone.textnobelaeditor.data.model.Messages;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.MessagesRepo;

public class CreateMessageActivity extends AppCompatActivity {

    private static final String TAG = "applogs";
    private AdView adView;
    String _chatasid, _chatasname;
    String _toId, _fromId, _message, _messageid;
    TextView tvMessageContact, tvMessageFrom, tvMessageTo;
    EditText etMessage;
    ImageView iBack, iSelectContact, iSend;
    MessagesRepo messagesRepo;
    Messages messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        Log.i(TAG, "This is the CreateMessageActivity");

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        Intent intent = getIntent();
        _chatasid = intent.getStringExtra("chatasid");
        Log.i(TAG, _chatasid + " is the id");
        _chatasname = intent.getStringExtra("chatasname");
        Log.i(TAG, _chatasname + " is the name");

        tvMessageContact = (TextView) findViewById(R.id.tvMessageContact);
        tvMessageFrom = (TextView) findViewById(R.id.tvMessageFrom);
        tvMessageTo = (TextView) findViewById(R.id.tvMessageTo);

        tvMessageContact.setTypeface(typeface);
        tvMessageFrom.setTypeface(typeface);
        tvMessageTo.setTypeface(typeface);

        etMessage = (EditText) findViewById(R.id.etMessage);
        etMessage.setTypeface(typeface);

        iBack = (ImageView) findViewById(R.id.iBackCreateMessage);
        iSelectContact = (ImageView) findViewById(R.id.iAddContact);
        iSend = (ImageView) findViewById(R.id.iSend);

        iBack.setClickable(true);
        iSelectContact.setClickable(true);
        iSend.setClickable(true);

        tvMessageFrom.setText(_chatasid);

        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);

        adView = new AdView(this, "1034786473263529_1058043840937792", AdSize.BANNER_320_50);
        adViewContainer.addView(adView);
        AdSettings.addTestDevice("648699642db48f6478e88fe19d91ce59");
        adView.loadAd();

        etMessage.setOnTouchListener(new View.OnTouchListener() {

             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 etMessage.requestFocus();
                 return false;
             }
         }
        );
    }

    public void clickBackCreateMessage(View v) {
        Log.i(TAG, "Going back to MessageActivity");
        Intent intent = new Intent(CreateMessageActivity.this, MessageActivity.class);
        intent.putExtra("chatasid", String.valueOf(_chatasid));
        intent.putExtra("chatasname", String.valueOf(_chatasname));
        CreateMessageActivity.this.startActivity(intent);
        CreateMessageActivity.this.finish();
    }

    public void clickSelectContact(View v) {
        Log.i(TAG, "clickSelectContact has been pressed");
        Intent intent = new Intent(CreateMessageActivity.this, SelectContactActivity.class);
        intent.putExtra("chatasid", _chatasid);
        CreateMessageActivity.this.startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to MessageActivity");
        Intent intent = new Intent(CreateMessageActivity.this, MessageActivity.class);
        intent.putExtra("chatasid", String.valueOf(_chatasid));
        intent.putExtra("chatasname", String.valueOf(_chatasname));
        CreateMessageActivity.this.startActivity(intent);
        CreateMessageActivity.this.finish();

        adView.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
            String targetName = data.getStringExtra("chattoname");
            String targetId = data.getStringExtra("chattoid");
            tvMessageContact.setText(targetName);
            Log.i(TAG, targetName + " is the target name");
            tvMessageTo.setText(targetId);
            Log.i(TAG, targetId + " is the target id");
        } else {
            Toast.makeText(this, "Select a contact", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickSend(View v) {
        Log.i(TAG, "clickSend has been pressed");

        // checking for values

        _toId = tvMessageTo.getText().toString();
        _fromId = tvMessageFrom.getText().toString();
        _message = etMessage.getText().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String curdate = dateFormat.format(calendar.getTime());
        String curtime = timeFormat.format(calendar.getTime());


        if (_message != null) {
            if (_toId != null) {
                messagesRepo = new MessagesRepo(this);
                messages = new Messages();
                messages.from_id = _fromId;
                messages.to_id = _toId;
                messages.message_content = _message;
                messages.save_date = curdate;
                messages.save_time = curtime;
                Log.i(TAG, _toId + "is toid");
                Log.i(TAG, _fromId + "is fromid");
                Log.i(TAG, _message + "is message");
                _messageid = messagesRepo.insertMessage(messages);

                Toast.makeText(this, "Message saved", Toast.LENGTH_SHORT).show();

                Log.i(TAG, "Going back to MessageActivity");
                Intent intent = new Intent(CreateMessageActivity.this, MessageActivity.class);
                intent.putExtra("chatasid", String.valueOf(_chatasid));
                intent.putExtra("chatasname", String.valueOf(_chatasname));
                CreateMessageActivity.this.startActivity(intent);
                CreateMessageActivity.this.finish();
            } else {
                Toast.makeText(this, "Select a contact", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
