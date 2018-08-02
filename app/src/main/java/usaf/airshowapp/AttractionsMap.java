package usaf.airshowapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;


public class AttractionsMap extends AppCompatActivity{


    AirshowInformationStorage Storage = new AirshowInformationStorage();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions_map);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //Declare variables and views
        Bitmap MapImg = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        TouchImageView imgMap = findViewById(R.id.imgMap);

        MapImg = Bitmap.createScaledBitmap(MapImg,width, height, true);


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
