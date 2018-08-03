package usaf.airshowapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    //Variables
    AirshowInformationStorage Storage = new AirshowInformationStorage();

    SwitchCompat swtNotifications;

    TextView txtNotifications;

    //String[] Airshows;

    Spinner spnPreference;

    File Settings;

    String strPreference = Storage.getAirshowName();

    Database database = new Database();

    //SQL_Connection SQL = new SQL_Connection(SplashPage.policy);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Set title and views
        setTitle("Settings");

        txtNotifications = findViewById(R.id.txtNotifications);

        swtNotifications = findViewById(R.id.swtNotifications);
        swtNotifications.setOnCheckedChangeListener(Notifications);

        spnPreference =  findViewById(R.id.spnPreference);

        spnPreference.setVisibility(View.INVISIBLE);

        //Load the list of airshows
        //LoadAirshows.run();

        //ArrayList<String> Airshows = database.getAirshowNames();

        //Set adapter for spinner
        //ArrayAdapter<String> AirshowAdapter = new ArrayAdapter<>(this, R.layout.spinner, Airshows);
        //spnPreference.setAdapter(AirshowAdapter);

        //Checks if setting file is a file and loads it if it is
        Settings = new File(getCacheDir()+"/SettingsStore");

        //Loads preferences or sets them to default

        if (Settings.isFile())
        {
            try
            {
                FileInputStream fis = new FileInputStream(Settings);
                ObjectInputStream ois = new ObjectInputStream(fis);
                strPreference =  String.valueOf(ois.readObject());
                boolean boolNotificationsActive = (boolean)ois.readObject();
                swtNotifications.setChecked(boolNotificationsActive);
                Storage.setNotificationsActive(boolNotificationsActive);
                Log.d("Debug",String.valueOf(boolNotificationsActive) );
                fis.close();
                ois.close();

            }
            catch (Exception E)
            {
                E.printStackTrace();
            }
        }
        else {
            swtNotifications.setChecked(true);
        }

        //Makes the preferred airshow show up in the spinner
       /* int i = 0;
        for (String strAirshow : Airshows)
        {
            if (strAirshow.equals(strPreference))
            {
                //spnPreference.setSelection(i);
                break;
            }
            i++;
        }
        */



        //Set the on item selected listener for the spinner
        ////spnPreference.setOnItemSelectedListener(AirshowSelect);

    }

    AdapterView.OnItemSelectedListener AirshowSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Saves selected settings
            SettingsSave();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    Switch.OnCheckedChangeListener Notifications = new Switch.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {

            //Checks position of switch and changes text accordingly
            txtNotifications.setText((isChecked)?"Notifications On" : "Notifications Off");
            if (isChecked)
            {
                FirebaseMessaging.getInstance().subscribeToTopic("Messages");
            }
            else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Messages");
            }
            SettingsSave();
        }
    };


    //Thread to load airshow listing
    /*Thread LoadAirshows = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Airshows = SQL.LoadAirshows();
            }
            catch (Exception E)
            {
                E.printStackTrace();
            }
        }
    });*/

    private void SettingsSave()
    {
        //Saves the selected setting to a file
        try {
            //SQL.LoadSelected(spnPreference.getSelectedItem().toString());

           // database.StoreInfo(database.getAirshowInfo(spnPreference.getSelectedItem().toString()));
            FileOutputStream fos = new FileOutputStream(Settings);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject("Offutt Air & Space Show");
            oos.writeObject(swtNotifications.isChecked());
            oos.close();
            fos.close();
        }
        catch (Exception E)
        {
            E.printStackTrace();
        }
    }

}
