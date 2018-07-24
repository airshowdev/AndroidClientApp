package usaf.airshowapp;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class AttractionsMap extends AppCompatActivity{


    AirshowInformationStorage Storage = new AirshowInformationStorage();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_map);

        //Declare variables and views
        Bitmap MapImg = Storage.getMapURL();
        TouchImageView imgMap = findViewById(R.id.imgMap);


        //Checks if map URL has been input set's it if it has displays unable to load if not

        if (MapImg == null)
        {
            setContentView(R.layout.activity_not_loaded);
            TextView txtUnable = findViewById(R.id.Unable);
            txtUnable.setText("Unable to load image. \n Please try again later.");
        }
        else
        {
            imgMap.setImageBitmap(MapImg);
        }



    }
}
