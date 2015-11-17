package ebps.gerraughtywj.switchadventure;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Random;

public class SettingsFragment extends PreferenceFragment {
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    Preference.OnPreferenceClickListener prefListener;
    SharedPreferences sharedPreferences;
    Preference beMeanToDom;
    Random RNG;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        RNG = new Random();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPreferences.getBoolean(Prompts.keyJohnCena, false)) {
            findPreference(Prompts.keyJohnCena).setSummary(R.string.johnCena);
        } else {
            findPreference(Prompts.keyJohnCena).setSummary(R.string.hauntedHouse);
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
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        beMeanToDom = findPreference(Prompts.keyBeMean);
        prefListener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(), Adventure.insult.get(RNG.nextInt(Adventure.insult.size())).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        };
        beMeanToDom.setOnPreferenceClickListener(prefListener);
    }

}
