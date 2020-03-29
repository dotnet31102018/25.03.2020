package hello.itay.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Itay kan on 3/30/2020.
 */

public class MyPrefManager {

    public static final String CREDENTIALS_PREFERENCES = "CREDENTIALS_PREFERENCES";
    public static final String CREDENTIALS_PREFERENCES_KEY_USERNAME = "CREDENTIALS_PREFERENCES_KEY_USERNAME";
    public static final String CREDENTIALS_PREFERENCES_KEY_PASSWORD = "CREDENTIALS_PREFERENCES_KEY_PASSWORD";

    public void logPref(Context ctx) {
        // read from shared pref.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);

        String username = prefs.getString("username", "Incognito");

        boolean autoupdates = prefs.getBoolean("applicationUpdates", false);

        String dt = prefs.getString("downloadDetials","ALL");

        StringBuilder builder = new StringBuilder();
        builder.append(username).append(" ").append(autoupdates).append(" ").append(dt);
        Log.d("=== prefs: ", builder.toString());
    }

    public String readPref(String credentialsPreferences, String credentialsPreferencesKey, Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(credentialsPreferences, MODE_PRIVATE);
        String result = prefs.getString(credentialsPreferencesKey, "");
        return result;
    }

    public void writePref(String credentialsPreferences, String credentialsPreferencesKey, String value, Context ctx) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(credentialsPreferences, MODE_PRIVATE).edit();
        editor.putString(credentialsPreferencesKey, value);
        editor.apply();
    }
}
