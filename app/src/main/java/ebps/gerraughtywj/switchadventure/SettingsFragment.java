package ebps.gerraughtywj.switchadventure;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment {
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPreferences.getBoolean(Prompts.keyJohnCena, false)) {
            findPreference(Prompts.keyJohnCena).setSummary(R.string.johnCena);
        } else {
            findPreference(Prompts.keyJohnCena).setSummary(R.string.hauntedHouse);
        }
        if (sharedPreferences.getBoolean(Prompts.keyBeMean, false)) {
            findPreference(Prompts.keyBeMean).setSummary(R.string.beMeanTrue);
        } else {
            findPreference(Prompts.keyBeMean).setSummary(R.string.beMeanFalse);
        }
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(Prompts.keyJohnCena)) {
                    if (sharedPreferences.getBoolean(key, false)) {
                        findPreference(key).setSummary(R.string.johnCena);
                    } else {
                        findPreference(key).setSummary(R.string.hauntedHouse);
                    }
                }
                if (key.equals(Prompts.keyBeMean)) {
                    if (sharedPreferences.getBoolean(key, false)) {
                        findPreference(Prompts.keyBeMean).setSummary(R.string.beMeanTrue);
                    } else {
                        findPreference(Prompts.keyBeMean).setSummary(R.string.beMeanFalse);
                    }
                }
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

}
