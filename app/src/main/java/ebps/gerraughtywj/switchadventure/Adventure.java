package ebps.gerraughtywj.switchadventure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Adventure extends Activity {

    public static Button btnYes, btnNo;
    public static TextView textField;
    public static String place;
    public static MediaPlayer mediaPlayer;
    public static SharedPreferences preferences;
    public static SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    public static int musicID;
    public static SettingsFragment settingsFragment;

    public static void Switch() {
        switch (place) {
            case Prompts.doorway:
                textField.setText(Prompts.doorwayPrompt);
                btnYes.setText(Prompts.kitchen);
                btnNo.setText(Prompts.upstairs);
                break;
            case Prompts.kitchen:
                textField.setText(Prompts.kitchenPrompt);
                btnYes.setText(Prompts.fridge);
                btnNo.setText(Prompts.cabinet);
                break;
            case Prompts.upstairs:
                textField.setText(Prompts.upstairsPrompt);
                btnYes.setText(Prompts.bedroom);
                btnNo.setText(Prompts.bathroom);
                break;
            case Prompts.fridge:
                textField.setText(Prompts.fridgePrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.cabinet:
                textField.setText(Prompts.cabinetPrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.bedroom:
                textField.setText(Prompts.bedroomPrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.bathroom:
                textField.setText(Prompts.bathroomPrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.done:
                btnYes.setText(Prompts.again);
                btnNo.setText(Prompts.finish);
                mediaPlayer.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        settingsFragment = new SettingsFragment();
        musicID = R.raw.scare;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.d("Switch Adventure", key);
                if (key.equals("johnCena")) {
                    boolean johnCena = sharedPreferences.getBoolean(key, false);
                    if (johnCena) {
                        musicID = R.raw.johncena;
                    } else {
                        musicID = R.raw.scare;
                    }
                }
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(prefListener);
        mediaPlayer = MediaPlayer.create(this, musicID);
        btnYes = (Button) findViewById(R.id.buttonYes);
        btnNo = (Button) findViewById(R.id.buttonNo);
        textField = (TextView) findViewById(R.id.textFieldText);
        place = Prompts.doorway;
        Switch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.settingsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(getBaseContext(), Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnYesClicked(View v) {
        if (place.equals(Prompts.fridge)) {
            textField.setText(Prompts.fridgeYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.cabinet)) {
            textField.setText(Prompts.cabinetYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bedroom)) {
            textField.setText(Prompts.bedroomYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bathroom)) {
            textField.setText(Prompts.bathroomYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.done)) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, musicID);
            place = Prompts.doorway;
            Switch();
        } else {
            place = btnYes.getText().toString();
            Switch();
        }
    }

    public void btnNoClicked(View v) {
        if (place.equals(Prompts.fridge)) {
            textField.setText(Prompts.fridgeNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.cabinet)) {
            textField.setText(Prompts.cabinetNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bedroom)) {
            textField.setText(Prompts.bedroomNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bathroom)) {
            textField.setText(Prompts.bathroomNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.done)) {
            mediaPlayer.stop();
            mediaPlayer.release();
            finish();
        } else {
            place = btnNo.getText().toString();
            Switch();
        }
    }


}
