package usaf.airshowapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Sponsors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        //Sets title and text to display "Unable to load X"
        TextView Sponsors = findViewById(R.id.txtSponsors);
        setTitle("Unable To Load Sponsors");
        Sponsors.setText("Unable to load sponsors. \n Please try again later.");
        Sponsors.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}
