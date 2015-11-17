package ebps.gerraughtywj.switchadventure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

public class Adventure extends Activity {

    public static Button btnYes, btnNo;
    public static TextView textField, splashText, buildEdition;
    public static String place;
    public static MediaPlayer mediaPlayer;
    public static int musicID;
    public static File splashes, insults;
    public static ArrayList splash, insult;
    public static Random RNG;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    SharedPreferences sharedPreferences;

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
        btnYes = (Button) findViewById(R.id.buttonYes);
        btnNo = (Button) findViewById(R.id.buttonNo);
        textField = (TextView) findViewById(R.id.textFieldText);
        buildEdition = (TextView) findViewById(R.id.buildEdition);
        buildEdition.setText(BuildConfig.VERSION_NAME + " R#" + BuildConfig.VERSION_CODE);
        splashes = new File(getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()), Prompts.splashes);
        insults = new File(getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()), Prompts.insults);
        if (!splashes.exists()) {
            try {
                InputStream is = getResources().openRawResource(R.raw.splash);
                OutputStream os = new FileOutputStream(splashes);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                while (line != null) {
                    os.write(line.getBytes());
                    os.write(Prompts.newline.getBytes());
                    line = br.readLine();
                }
                is.close();
                os.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!insults.exists()) {
            try {
                InputStream is = getResources().openRawResource(R.raw.insults);
                OutputStream os = new FileOutputStream(insults);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                while (line != null) {
                    os.write(line.getBytes());
                    os.write(Prompts.newline.getBytes());
                    line = br.readLine();
                }
                is.close();
                os.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        splashText = (TextView) findViewById(R.id.textView3);
        place = Prompts.doorway;
        splash = new <String>ArrayList();
        insult = new <String>ArrayList();
        RNG = new Random();
        try {
            InputStream is = new FileInputStream(splashes);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while (line != null) {
                splash.add(line);
                line = br.readLine();
            }
            is.close();
            br.close();
        } catch (IOException e) {
            Log.w("Switch Adventure", "Error reading splashes!");
        }
        try {
            InputStream is = new FileInputStream(insults);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while (line != null) {
                insult.add(line);
                line = br.readLine();
            }
            is.close();
            br.close();
        } catch (IOException e) {
            Log.w("Switch Adventure", "Error reading insults!");
        }
        splashText.setText(splash.get(RNG.nextInt(splash.size())).toString());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean(Prompts.keyJohnCena, false)) {
            musicID = R.raw.johncena;
        } else {
            musicID = R.raw.scare;
        }
        mediaPlayer = MediaPlayer.create(this, musicID);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(Prompts.keyJohnCena)) {
                    if (sharedPreferences.getBoolean(key, false)) {
                        musicID = R.raw.johncena;
                    } else {
                        musicID = R.raw.scare;
                    }
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), musicID);
                }
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        Switch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settingsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnYesClicked(View v) {
        splashText.setText(splash.get(RNG.nextInt(splash.size())).toString());
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
        splashText.setText(splash.get(RNG.nextInt(splash.size())).toString());
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
