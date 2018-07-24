package usaf.airshowapp;

//Imports

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SplashPage extends AppCompatActivity {
    public static StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    private static File cache;
    //Declare variables, views, classes, etc.
    Spinner AirshowPicker;
    AirshowInformationStorage Storage = new AirshowInformationStorage();
    String[][][] Tables;
    ArrayList<String> arrAirshows;
    SQL_Connection SQL = new SQL_Connection(policy);
    String strLastUpdate = null;
    String strDBUpdated = "";
    Button btnSelect;
    FirebaseConnection fbCon;
    Database database;
    AdapterView.OnItemSelectedListener AirshowSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            btnSelect.setClickable(SpinnerCheck());
            String strColor = (!SpinnerCheck()) ? "gray" : "black";
            btnSelect.setTextColor(Color.parseColor(strColor));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };


    //Called when the select button is pressed
    View.OnClickListener Select = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.layout_loading);
            Airshow(AirshowPicker.getSelectedItem().toString(), "Button");
        }
    };

    /*
    try {
        //Reads the date the app last updated
        BufferedReader br = new BufferedReader(new FileReader(Update));
        strLastUpdate = br.readLine();
        br.close();
    } catch (Exception E) {
        E.printStackTrace();
    }
    if (strLastUpdate != null && !strLastUpdate.equals("")) {
        try {
            //Pulls the stored preference files
            FileInputStream fis = new FileInputStream(Preference);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Storage.storeTables(Tables = (String[][][]) ois.readObject());
            fis.close();
            ois.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
        try {
            //Stores the information from the preference file
            Storage.StoreInfo();

            //Pulls the date the database was last updated
            try {
                strDBUpdated = SQL.getUpdateDate();
            } catch (Exception E) {
                E.printStackTrace();
            }
            //Updates the stored database if it is out of date
            if (!strDBUpdated.equals(strLastUpdate)) {
                SQL.LoadSelected(Tables[0][0][1]);
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(Update));
                    bw.write(strDBUpdated);
                    bw.close();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
            //Loads the airshow data page
            Airshow(Storage.getAirshowName(), "Preferences");
        } catch (Exception E) {
            E.printStackTrace();

            //Loads the information for the splash page
            LoadSplashPage();
        }
    } else
        LoadSplashPage();
} else if (!SQL.TestConn()) {
    Intent NotLoaded = new Intent(this, localhost3000.airshowapplication.NotLoaded.class);
    startActivity(NotLoaded);
} else {
    LoadSplashPage();
    }
    */

    public static File getCache() {
        return cache;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);


        //Sets the path for the files
        File Preference = new File(getCacheDir() + "/AirshowPreference");
        File Update = new File(getCacheDir() + "/UpdateDate");

        File databseFile = new File(getCacheDir() + "/database.json");

        cache = getCacheDir();
        fbCon = new FirebaseConnection();

        database = fbCon.getDatabase();


        //Checks if the files are present on the device
        if (Preference.isFile() && Update.isFile()) {
            String strAirshowPrefered = null;
            String strUpdateDate = null;
            try {
                BufferedReader br = new BufferedReader(new FileReader(Preference));
                strAirshowPrefered = br.readLine();

                br.close();
                br = new BufferedReader(new FileReader(Update));
                //strUpdateDate = br.readLine();
                br.close();

            } catch (Exception E) {
                E.printStackTrace();
            }
            if (strAirshowPrefered != null && !strAirshowPrefered.equals(""))
                //if (strUpdateDate != null && !strUpdateDate.equals(""))

                if (!databseFile.isFile()) {
                    try {
                        databseFile.createNewFile();
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                }

            if (databseFile.isFile()) {
                String jsonString;
                try {
                    char[] buf = new char[1024];

                    int numbread = 0;

                    StringBuffer buffer = new StringBuffer();
                    BufferedReader br = new BufferedReader(new FileReader(databseFile));
                    while ((numbread = br.read(buf)) != -1) {
                        String data = String.valueOf(buf, 0, numbread);
                        buffer.append(data);
                    }
                    br.close();
                    jsonString = buffer.toString();
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        database = mapper.readValue(jsonString, Database.class);

                        Log.d("Database Json", database.toString());

                        for (Airshow airshow : database.getAirshows()) {
                            if (airshow != null)
                                Log.d("Airshow", airshow.getName());
                                if (airshow.getName().equals(strAirshowPrefered)) {
                                    Airshow(strAirshowPrefered, "Preference");
                                }
                        }




                    } catch (Exception E) {
                        E.printStackTrace();
                        LoadSplashPage();
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                    LoadSplashPage();
                }
            } else
                LoadSplashPage();
        } else
            LoadSplashPage();
        if (database!= null)
        {
            Airshow("Offutt Air & Space Show", "Preference");
        }
    }

    private void Airshow(String strAirshowName, String Sender) {
        //Displays the selected airshows data page
        Toast preference;

        if (Sender == "Button") {
            Context con = getApplicationContext();
            CharSequence seq = "Preference set to " + strAirshowName;
            int length = Toast.LENGTH_SHORT;
            preference = Toast.makeText(con, seq, length);
            preference.show();
        }
        try {
            //Loads the selected airshows information
            database.StoreInfo(database.getAirshowInfo(strAirshowName.trim()));
            //Temp Disable
            //Tables = Storage.getTables();
        } catch (Exception E) {
            E.printStackTrace();
        }
        //Displays the selected airshow's individual page containing its data
        Intent Airshow_Data = new Intent(this, AirshowData.class);
        startActivity(Airshow_Data);
    }

    private boolean SpinnerCheck() {
        //If the spinner displays "Select an airshow" disable the select button
        return !AirshowPicker.getSelectedItem().toString().equals("Select an airshow");
    }

    private void LoadSplashPage() {
        try {
            //Populates the splash page and sets the onclick events if needed
            //Temp disable
            /*arrAirshows = new ArrayList<>(Arrays.asList(SQL.LoadAirshows()));
            arrAirshows.add(0, "Select an airshow");*/
            ArrayList<String> airshows = database.getAirshowNames();
            airshows.add(0, "Select an airshow");
            ArrayAdapter<String> AirshowAdapter = new ArrayAdapter<>(this, R.layout.spinner, airshows);
            AirshowPicker = findViewById(R.id.AirshowList);
            AirshowPicker.setAdapter(AirshowAdapter);
            AirshowPicker.setOnItemSelectedListener(AirshowSelected);

            btnSelect = findViewById(R.id.btnSelect);
            btnSelect.setOnClickListener(Select);
        } catch (Exception E) {
            E.printStackTrace();
            Intent NotLoaded = new Intent(this, usaf.airshowapp.NotLoaded.class);
            startActivity(NotLoaded);
        }
    }


}
