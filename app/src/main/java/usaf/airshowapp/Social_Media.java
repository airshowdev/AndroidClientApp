package usaf.airshowapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class Social_Media extends AppCompatActivity {
    AirshowInformationStorage Storage = new AirshowInformationStorage();
    android.util.Pair<String, Boolean> WebsiteLink = Storage.getWebsiteLink();
    android.util.Pair<String, Boolean> TwitterLink = Storage.getTwitterLink();
    android.util.Pair<String, Boolean> FacebookLink = Storage.getFacebookLink();
    Pair<String, Boolean> IGLink = Storage.getIGLink();
    View.OnClickListener Web = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent Website = new Intent("android.intent.action.VIEW", Uri.parse(WebsiteLink.first));///
            startActivity(Website);
        }
    };


    //Creates on click events to load web pages
    View.OnClickListener Facebook = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Facebook = new Intent("android.intent.action.VIEW", Uri.parse(FacebookLink.first));
            startActivity(Facebook);
        }
    };
    View.OnClickListener Twitter = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Twitter = new Intent("android.intent.action.VIEW", Uri.parse(TwitterLink.first));
            startActivity(Twitter);
        }
    };
    View.OnClickListener IG = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent InstaGram = new Intent("android.intent.action.VIEW", Uri.parse(IGLink.first));
            startActivity(InstaGram);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social__media);


        //Sets title and declares ImageButtons
        setTitle("Social Media");

        ImageButton btnIG = findViewById(R.id.btnIG);
        btnIG.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageButton btnFB = findViewById(R.id.btnFacebook);
        btnFB.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageButton btnWWW = findViewById(R.id.btnWebsite);
        btnWWW.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageButton btnTwitter = findViewById(R.id.btnTwitter);
        btnTwitter.setScaleType(ImageView.ScaleType.FIT_XY);

        btnIG.setImageResource(R.drawable.ig_logo);
        btnFB.setImageResource(R.drawable.facebookicon);
        btnTwitter.setImageResource(R.drawable.twitter_logo);
        btnWWW.setImageResource(R.drawable.hud60);

        //Checks if the social media URLs are set and if not hides the ImageButtons
        if (WebsiteLink.second) {
            btnWWW.setOnClickListener(Web);
        } else {
            btnWWW.setVisibility(View.INVISIBLE);
        }
        if (TwitterLink.second) {
            btnTwitter.setOnClickListener(Twitter);
        } else {
            btnTwitter.setVisibility(View.INVISIBLE);
        }

        if (FacebookLink.second) {
            btnFB.setOnClickListener(Facebook);
        } else {
            btnFB.setVisibility(View.INVISIBLE);
        }

        if (IGLink.second) {
            btnIG.setOnClickListener(IG);
        } else {
            btnFB.setVisibility(View.INVISIBLE);
        }


    }

}
