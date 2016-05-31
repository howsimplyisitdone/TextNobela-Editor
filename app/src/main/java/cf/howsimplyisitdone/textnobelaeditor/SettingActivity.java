package cf.howsimplyisitdone.textnobelaeditor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cf.howsimplyisitdone.textnobelaeditor.adapter.SettingsAdapter;
import cf.howsimplyisitdone.textnobelaeditor.data.model.Settings;
import cf.howsimplyisitdone.textnobelaeditor.data.repo.SettingsRepo;

public class SettingActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "applogs";
    Spinner spinner;
    TextView tvSetting, tvCategory, tvPreview, tvPreviewLeft, tvPreviewRight, tvPreviewTimeLeft, tvPreviewTimeRight;
    String layoutDescription, layoutAttribute, layoutSelection;
    String[] categories = {"Theme 1", "Theme 2", "Theme 3", "Theme 4", "Theme 5", "Theme 6", "Theme 7", "Theme 8"};
    String _layout, _attribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Log.i(TAG, "Welcome! This is the SettingActivity");

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinnerLayout);
        spinner.setOnItemSelectedListener(this);

        tvSetting = (TextView) findViewById(R.id.tvSetting);
        tvCategory = (TextView) findViewById(R.id.tvLayout);
        tvPreview = (TextView) findViewById(R.id.tvPreview);
        tvPreviewLeft = (TextView) findViewById(R.id.tvPreviewLeft);
        tvPreviewRight = (TextView) findViewById(R.id.tvPreviewRight);
        tvPreviewTimeLeft = (TextView) findViewById(R.id.tvPreviewTimeLeft);
        tvPreviewTimeRight = (TextView) findViewById(R.id.tvPreviewTimeRight);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");

        tvCategory.setTypeface(typeface);
        tvSetting.setTypeface(typeface);
        tvPreview.setTypeface(typeface);
        tvPreviewLeft.setTypeface(typeface);
        tvPreviewRight.setTypeface(typeface);
        tvPreviewTimeLeft.setTypeface(typeface);
        tvPreviewTimeRight.setTypeface(typeface);

        SettingsAdapter dataAdapter = new SettingsAdapter(getApplicationContext(), categories);

        spinner.setAdapter(dataAdapter);

        _layout = "layout";
        SettingsRepo handler = new SettingsRepo(SettingActivity.this);
        List<Settings> settings = handler.getLayout();
        Settings setting = new Settings();

        if (settings.size() > 0) {
            _attribute = setting.setting_attribute;
        } else {
            setting.setting_attribute = "Theme 4";
            setting.setting_description = _layout;
            handler.insertSetting(setting);
        }
    }

    public void clickSaveSetting(View v) {
        if (layoutSelection == "Theme 1") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 2") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 3") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 4") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 5") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 6") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 7") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else if (layoutSelection == "Theme 8") {
            layoutAttribute = layoutSelection;
            layoutDescription = "layout";

            SettingsRepo handler = new SettingsRepo(this);
            Settings setting = new Settings();

            setting.setting_attribute = layoutAttribute;
            setting.setting_description = layoutDescription;
            handler.updateSetting(setting);

            Toast.makeText(this, "Themes has been updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No changes made!", Toast.LENGTH_SHORT).show();
        }

        Log.i(TAG, layoutAttribute + " is the layout");
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        SettingActivity.this.startActivity(intent);
        SettingActivity.this.finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //layoutSelection = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), categories[position] + " has been selected!", Toast.LENGTH_SHORT).show();
        layoutSelection = categories[position];

        if (layoutSelection == "Theme 1") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left);
            tvPreviewRight.setBackgroundResource(R.drawable.right);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else if (layoutSelection == "Theme 2") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left2);
            tvPreviewRight.setBackgroundResource(R.drawable.right2);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else if (layoutSelection == "Theme 3") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left3);
            tvPreviewRight.setBackgroundResource(R.drawable.right3);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else if (layoutSelection == "Theme 4") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left4);
            tvPreviewRight.setBackgroundResource(R.drawable.right4);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else if (layoutSelection == "Theme 5") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left5);
            tvPreviewRight.setBackgroundResource(R.drawable.right5);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else if (layoutSelection == "Theme 6") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left6);
            tvPreviewRight.setBackgroundResource(R.drawable.right6);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else if (layoutSelection == "Theme 7") {
            tvPreviewLeft.setBackgroundResource(R.drawable.left7);
            tvPreviewRight.setBackgroundResource(R.drawable.right7);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        } else {
            tvPreviewLeft.setBackgroundResource(R.drawable.left8);
            tvPreviewRight.setBackgroundResource(R.drawable.right8);
            tvPreviewLeft.setPaddingRelative(15, 10, 15, 10);
            tvPreviewRight.setPaddingRelative(15, 10, 15, 10);
        }

        Log.i(TAG, layoutSelection + " is the layout");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clickBackSetting(View v) {
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        SettingActivity.this.startActivity(intent);
        SettingActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Going back to MainActivity");
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        SettingActivity.this.startActivity(intent);
        SettingActivity.this.finish();
    }
}
