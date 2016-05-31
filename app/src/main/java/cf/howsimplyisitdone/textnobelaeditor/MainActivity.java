package cf.howsimplyisitdone.textnobelaeditor;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.*;

import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.data.model.Settings;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.SettingsRepo;

public class MainActivity extends AppCompatActivity {

    TextView tvMessageHistory, tvAddContact, tvSetting, tvAppName;
    private static final String TAG = "applogs";
    private AdView adView;
    String _layout, _attribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAddContact = (TextView) findViewById(R.id.tvAddContact);
        tvMessageHistory = (TextView) findViewById(R.id.tvMessageHistory);
        tvSetting = (TextView) findViewById(R.id.tvSetting);
        tvAppName = (TextView) findViewById(R.id.tvAppName);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        tvAddContact.setClickable(true);
        tvMessageHistory.setClickable(true);
        tvSetting.setClickable(true);
        tvAddContact.setTypeface(typeface);
        tvMessageHistory.setTypeface(typeface);
        tvSetting.setTypeface(typeface);
        tvAppName.setTypeface(typeface);

        Log.i(TAG, "Welcome! This is the MainActivity");

        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);

        adView = new AdView(this, "1034786473263529_1058043840937792", AdSize.BANNER_320_50);
        adViewContainer.addView(adView);
        AdSettings.addTestDevice("648699642db48f6478e88fe19d91ce59");
        adView.loadAd();

        _layout = "layout";
        SettingsRepo handler = new SettingsRepo(MainActivity.this);
        List<Settings> settings = handler.getLayout();
        Settings setting = new Settings();

        if (settings.size() > 0) {
            _attribute = setting.setting_attribute;
        } else {
            setting.setting_attribute = "Theme 4";
            setting.setting_description = _layout;
            handler.insertSetting(setting);
        }

        Log.i(TAG, _attribute + " is the default Theme");

    }

    // when tvAddContact is pressed
    public void clickAddContact (View v) {
        Log.i(TAG, "clickAddContact has been pressed");

        // redirect to ContactActivity and finished MainActivity
        Intent intent = new Intent(MainActivity.this, ContactActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    // when tvMessageHistory is pressed
    public void clickMessageHistory (View v) {
        //Intent intent = new Intent(MainActivity.this, );
        Log.i(TAG, "clickMessageHistory has been pressed");

        // redirect to ChatActivity and finished MainActivity
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    // when tvSetting is pressed
    public void clickSetting (View v) {
        //Intent intent = new Intent(MainActivity.this, );
        Log.i(TAG, "clickSetting has been pressed");

        // redirect to SettingActivity and finished MainActivity
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();

        //Toast.makeText(this,  "Available soon!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Bye!");

        // close the app
        adView.destroy();
        finish();
    }

    @Override
    protected void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }
}
