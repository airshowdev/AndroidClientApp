package usaf.airshowapp;

//Imports

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SplashPage extends AppCompatActivity {
    public static StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    private static File cache;
    //Declare variables, views, classes, etc.
    AirshowInformationStorage Storage = new AirshowInformationStorage();
    Button btnSelect;
    FirebaseConnection fbCon;
    Database database;

    //Called when the select button is pressed

    public static File getCache() {
        return cache;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        File databaseFile = new File(getCacheDir() + "/database.json");

        cache = getCacheDir();
        fbCon = new FirebaseConnection();

        database = fbCon.getDatabase();

        String jsonString;
        try {
            char[] buf = new char[1024];

            int numberRead;

            StringBuilder buffer = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(databaseFile));
            while ((numberRead = br.read(buf)) != -1) {
                String data = String.valueOf(buf, 0, numberRead);
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
            } catch (Exception E) {
                E.printStackTrace();
                LoadSplashPage();
            }
        } catch (Exception E) {
            E.printStackTrace();
            LoadSplashPage();
        }
        //Checks if the files are present on the device

        if (database != null) {
            Airshow();
        }
    }

    private void Airshow() {
        try {
            //Loads the selected airshows information
            database.StoreInfo(database.getAirshowInfo("Offutt Air & Space Show"));
            //Temp Disable
            //Tables = Storage.getTables();
        } catch (Exception E) {
            E.printStackTrace();
        }
        //Displays the selected airshow's individual page containing its data
        Intent Airshow_Data = new Intent(this, AirshowData.class);
        startActivity(Airshow_Data);
    }

    private void LoadSplashPage() {

            Intent NotLoaded = new Intent(this, usaf.airshowapp.NotLoaded.class);
            startActivity(NotLoaded);

    }


}
