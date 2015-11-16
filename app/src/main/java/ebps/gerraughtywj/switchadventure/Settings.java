package ebps.gerraughtywj.switchadventure;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class Settings extends Activity {
    private static SharedPreferences preferences;
    private static SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SettingsFragment settingsFragment = new SettingsFragment();

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, settingsFragment)
                .commit();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Log.d("Switch Adventure", key);
                if (key.equals("johnCena")) {
                    boolean johnCena = sharedPreferences.getBoolean(key, false);
                    if (johnCena) {
                        settingsFragment.findPreference(key).setSummary("Suddenly, John Cena");
                    } else {
                        settingsFragment.findPreference(key).setSummary("Spooky scary monster noises");
                    }
                }
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(prefListener);

    }

}
