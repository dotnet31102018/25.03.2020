package hello.itay.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final MyPrefManager prefMan = new MyPrefManager();

    private EditText nameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getBaseContext(), "message restored!", Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });

        prefMan.logPref(this);

        nameET = findViewById(R.id.userET);
        passwordET = findViewById(R.id.passwordET);

        findViewById(R.id.loadbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = prefMan.readPref(MyPrefManager.CREDENTIALS_PREFERENCES,
                        MyPrefManager.CREDENTIALS_PREFERENCES_KEY_USERNAME, getBaseContext());
                String pwd = prefMan.readPref(MyPrefManager.CREDENTIALS_PREFERENCES,
                        MyPrefManager.CREDENTIALS_PREFERENCES_KEY_PASSWORD, getBaseContext());
                nameET.setText(username);
                passwordET.setText(pwd);
            }
        });

        findViewById(R.id.savebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefMan.writePref(MyPrefManager.CREDENTIALS_PREFERENCES,
                        MyPrefManager.CREDENTIALS_PREFERENCES_KEY_USERNAME,
                        nameET.getText().toString(), getBaseContext());
                prefMan.writePref(MyPrefManager.CREDENTIALS_PREFERENCES,
                        MyPrefManager.CREDENTIALS_PREFERENCES_KEY_PASSWORD,
                        passwordET.getText().toString(), getBaseContext());
            }
        });

        loadCred();
    }

    private void loadCred() {
        String username = prefMan.readPref(MyPrefManager.CREDENTIALS_PREFERENCES,
                MyPrefManager.CREDENTIALS_PREFERENCES_KEY_USERNAME, getBaseContext());
        String pwd = prefMan.readPref(MyPrefManager.CREDENTIALS_PREFERENCES,
                MyPrefManager.CREDENTIALS_PREFERENCES_KEY_PASSWORD, getBaseContext());
        nameET.setText(username);
        passwordET.setText(pwd);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(this, "settings clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MyPreferencesActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_login) {
            nameET.setText("");
            passwordET.setText("");
            Toast.makeText(this, "login cleared", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
