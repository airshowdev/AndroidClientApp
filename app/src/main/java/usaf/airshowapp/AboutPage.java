package usaf.airshowapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        //Instantiate Storage class
        AirshowInformationStorage Storage = new AirshowInformationStorage();

        //Set the screen title to "About 'Airshow Name'"
        setTitle("About " + Storage.getAirshowName());
        //Sets text view to display the about information
        TextView txtAbout = findViewById(R.id.txtAbout);
        txtAbout.setText("\t\t\t"+ Storage.getAbout());
    }
}
