package usaf.airshowapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sponsor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);

        ConstraintLayout cl = findViewById(R.id.SponsorCl);

        final AirshowInformationStorage Storage = new AirshowInformationStorage();

        final Context con = this;

        cl.setBackgroundResource(R.drawable.sponsor_image);

        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sponsor;
                if (!Storage.getSponsors().equals("") && Storage.getSponsors() != null)
                    Sponsor = new Intent("android.intent.action.VIEW", Uri.parse(Storage.getSponsors()));
                else
                    Sponsor = new Intent(con, usaf.airshowapp.Sponsors.class);
                startActivity(Sponsor);
            }
        });

    }
}
