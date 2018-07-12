package localhost3000.airshowapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class AttractionDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_display);
        String selectedAttraction = "";
        setTitle(selectedAttraction);

        String attractionDetails = "";
        TextView txtAbout = findViewById(R.id.txtAtractionAbout);
        txtAbout.setText(attractionDetails);

        AirshowInformationStorage Storage = new AirshowInformationStorage();

        ArrayList<Performer> performers = Storage.getPerformerList();
        ArrayList<Static> statics = Storage.getStaticsList();
        ArrayList<Food> foods = Storage.getFoods();

        Intent sender = this.getIntent();

        String type = (sender.getStringExtra("Type") != null) ? sender.getStringExtra("Type") : "";
        String attractionName = sender.getStringExtra("Name");

        setTitle(attractionName);

        String attractionDesc = "   Unable to find description. Please try again later.";


        if (type.equals("Performer")) {
            for (Performer performer : performers) {
                if (performer != null) {
                    if (performer.getName().equals(attractionName))
                        attractionDesc = performer.getDescription();
                }
            }
        } else if (type.equals("Static")) {
            for (Static statc : statics) {
                if (statc != null) {
                    if (statc.getName().equals(attractionName))
                        attractionDesc = statc.getDescription();
                }
            }
        } else if (type.equals("Food")) {
            for (Food food : foods) {
                if (food != null) {
                    if (food.getName().equals(attractionName))
                        attractionDesc = food.getDescription();
                }
            }
        }

        txtAbout.setText("   " + attractionDesc);

    }
}
