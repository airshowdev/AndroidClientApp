package usaf.airshowapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AirshowAttractions extends AppCompatActivity {

    //Instantiate class and variable
    AirshowInformationStorage Storage = new AirshowInformationStorage();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airshow_attractions);

        //Get context of the activity for calling new activities
        context = this.getBaseContext();

        //Set title of page to "Attractions"
        setTitle("Attractions");

        //Declare buttons and give them their on click events
        Button btnPerformers = findViewById(R.id.Performers);
        btnPerformers.setOnClickListener(Performers);

        Button btnStatics = findViewById(R.id.Statics);
        btnStatics.setOnClickListener(Statics);

        Button btnFood = findViewById(R.id.Food);
        btnFood.setOnClickListener(Food);

        /*Button btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(Map);*/

        //Declare text views and give them their text
        TextView txtPerformers = findViewById(R.id.PerformersText);
        txtPerformers.setText("A list of performers you will see at " + Storage.getAirshowName());
        txtPerformers.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtStatics = findViewById(R.id.StaticsText);
        txtStatics.setText("A list of interactive figures and models of aircraft at "+Storage.getAirshowName());
        txtStatics.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtFood = findViewById(R.id.FoodText);
        txtFood.setText("A list of vendors at " + Storage.getAirshowName());
        txtFood.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        /*TextView txtMap = findViewById(R.id.txtMap);
        txtMap.setText("A map of the attractions at "+Storage.getAirshowName());
        txtMap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);*/

    }

    protected void onResume()
    {
        super.onResume();
        setContentView(R.layout.activity_airshow_attractions);

        Button btnPerformers = findViewById(R.id.Performers);
        btnPerformers.setOnClickListener(Performers);

        Button btnStatics = findViewById(R.id.Statics);
        btnStatics.setOnClickListener(Statics);

        Button btnFood = findViewById(R.id.Food);
        btnFood.setOnClickListener(Food);

        /*Button btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(Map);*/

        //Declare text views and give them their text
        TextView txtPerformers = findViewById(R.id.PerformersText);
        txtPerformers.setText("A list of performers you will see at " + Storage.getAirshowName());
        txtPerformers.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtStatics = findViewById(R.id.StaticsText);
        txtStatics.setText("A list of interactive figures and models of aircraft at "+Storage.getAirshowName());
        txtStatics.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtFood = findViewById(R.id.FoodText);
        txtFood.setText("A list of vendors at " + Storage.getAirshowName());
        txtFood.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        /*TextView txtMap = findViewById(R.id.txtMap);
        txtMap.setText("A map of the attractions at "+Storage.getAirshowName());
        txtMap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);*/
        setTitle("Attractions");
    }



    //Create on click events for all of the buttons

    View.OnClickListener Food = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Food = new Intent(context, Foods.class);
            startActivity(Food);
            setContentView(R.layout.layout_loading);
            setTitle("Loading...");
        }
    };

    View.OnClickListener Performers = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Performers = new Intent(context, usaf.airshowapp.Performers.class);
            startActivity(Performers);
            setContentView(R.layout.layout_loading);
            setTitle("Loading...");

        }
    };

    View.OnClickListener Statics = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Statics = new Intent(context, usaf.airshowapp.Statics.class);
            startActivity(Statics);
            setContentView(R.layout.layout_loading);
            setTitle("Loading...");
        }
    };

    /*View.OnClickListener Map = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Map = new Intent(context, AttractionsMap.class);
            startActivity(Map);
            setContentView(R.layout.layout_loading);
            setTitle("Loading...");
        }
    };*/

}
