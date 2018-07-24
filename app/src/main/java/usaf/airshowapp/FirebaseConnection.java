package usaf.airshowapp;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by USAF_Admin on 4/10/2018.
 */

public class FirebaseConnection {

    File json = new File(SplashPage.getCache() + "/database.json");


    static Database database = null;
    private String jsonString;

    public FirebaseConnection() {
        try {
            URL jsonUrl = new URL("https://airshowapp-d193b.firebaseio.com/.json");

            if (!json.exists()) {
                json.createNewFile();
            }

            FileUtils.copyURLToFile(jsonUrl, json);

            /*char[] buf = new char[1024];

            int numbread = 0;

            StringBuffer buffer = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader(json));
            while ((numbread = br.read(buf)) != -1) {
                String data = String.valueOf(buf, 0, numbread);
                Log.d("Data Read", data);
                buffer.append(data);
            }
            br.close();
            jsonString = buffer.toString();

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                database = mapper.readValue(jsonString, Database.class);

                Log.d("JSON", jsonString);

            } catch (Exception E) {
                E.printStackTrace();
            }*/

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public ArrayList<String> getAirshowNames() {

        List<Airshow> airshows;
        airshows = database.getAirshows();

        ArrayList<String> Names = new ArrayList<>();

        for (Airshow air : airshows) {
            if (air != null)
                Names.add(air.getName());
        }

        return Names;
    }

    public Database getDatabase() {
        try{
        char[] buf = new char[1024];

        int numbread = 0;

        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(json));
        while ((numbread = br.read(buf)) != -1) {
            String data = String.valueOf(buf, 0, numbread);
            Log.d("Data Read", data);
            buffer.append(data);
        }
        br.close();
        jsonString = buffer.toString();


            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            database = mapper.readValue(jsonString, Database.class);

            Log.d("JSON", jsonString);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return database;
    }


}
