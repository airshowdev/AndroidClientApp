package usaf.airshowapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Prohibited extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prohibited);

        //Set title and display text view with prohibited items text
        setTitle("Prohibited Items");
        TextView Prohibited = findViewById(R.id.ProhibitiedItems);
        Prohibited.setText("   \nIn order to keep the security lines moving as quickly as possible, please take note of prohibited items while planning your day at Offutt. The following is a list of banned items that will be confiscated or cause visitors to be turned away at the gate if brought to the Offutt Air Show:\n\n " +
        "    • Weapons of any kind including pocket knives, pocket tools, scissors, box cutters, billy clubs, large heavy chain link jewelry or belts, mace and pepper spray\n" +
                "    • Firearms of any type\n" +
                "    • Toys that resemble firearms, laser pointers\n" +
                "    • Flammable items and Fireworks\n" +
                "    • Pets (except for service animals)\n" +
                "    • Spray paint and silly string\n" +
                "    • Outside food, coolers, grills, or glass containers\n" +
                "    • Glass bottles\n" +
                "    • Liquid without factory seal intact (baby bottles are exempt)\n" +
                "    • RC aircraft & drones\n" +
                "    • Walkie-talkies, HAM radios, scanners\n" +
                "    • Alcohol, illegal drugs, medical marijuana\n" +
                "    • Bicycles, skateboards, scooters, heelies, roller skates or roller blades\n" +
                "    • Excessively large items and bags (the only exceptions are diaper bags, small purses, and small camera bags at or below 21 inches long, 10 inches wide, 10 inches deep)\n\n" +
                "    All visitors are subject to search by Security Forces.\n");///

        Prohibited.setTextColor(Color.parseColor("White"));
    }
}
