package usaf.airshowapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Performers extends AppCompatActivity {

    Context con = this;
    View.OnClickListener DisplayAttraction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (v instanceof Button) {
                Intent attractionDisplay = new Intent(con, AttractionDisplay.class);
                attractionDisplay.putExtra("Name", ((Button) v).getText());
                attractionDisplay.putExtra("Type", "Performer");
                startActivity(attractionDisplay);
            } else if (v instanceof ImageButton) {
                LinearLayout ll = (LinearLayout) v.getParent();
                for (int i = 0; i < ll.getChildCount(); i++) {
                    if (ll.getChildAt(i) instanceof Button) {
                        Button b = (Button) ll.getChildAt(i);
                        b.setOnClickListener(DisplayAttraction);
                        b.callOnClick();
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set title and declare variables
        setTitle("Performers");


        AirshowInformationStorage Storage = new AirshowInformationStorage();
        ArrayList<Performer> Performers = Storage.getPerformerList();
        ArrayList<Bitmap> bitmaps = Storage.getPerformerBitmaps();
        //ArrayList<String[]> Performers = Storage.getPerformers();
        //Declare layout to add performers to

        final float scale = getResources().getDisplayMetrics().density;
        int dpHeightInPx = (int) (150 * scale);

        LinearLayout.LayoutParams displayLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        ScrollView performerScroller = new ScrollView(this);
        performerScroller.setLayoutParams(displayLayoutParams);
        performerScroller.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));

        displayLayoutParams.setMargins(5, 5, 5, 5);

        LinearLayout performerDisplayLayout = new LinearLayout(this);
        performerDisplayLayout.setLayoutParams(displayLayoutParams);
        performerDisplayLayout.setOrientation(LinearLayout.VERTICAL);


        for (int i = 0;i<Performers.size(); i++) {
            Performer performer = Performers.get(i);
            Bitmap bitmap;

            try {
                bitmap = bitmaps.get(i);
            }
            catch (Exception E)
            {
                bitmap = null;
            }
            if (performer != null) {

                LinearLayout buttonDisplayLayout = new LinearLayout(this);
                buttonDisplayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpHeightInPx);
                buttonDisplayLayout.setWeightSum(10);
                buttonDisplayLayout.setOrientation(LinearLayout.HORIZONTAL);

                Button btnPerformer = new Button(this);
                buttonLayoutParams.weight = 5;
                buttonLayoutParams.setMargins(15, 7, 15, 7);
                btnPerformer.setLayoutParams(buttonLayoutParams);


                switch (performer.getInAir()) {
                    case "In Air":
                        btnPerformer.setTextColor(Color.parseColor("Green"));
                        break;
                    case "On Deck":
                        btnPerformer.setTextColor(Color.parseColor("Yellow"));
                        break;
                    case "Preparing":
                        btnPerformer.setTextColor(Color.parseColor("Red"));
                        break;
                    default:
                        btnPerformer.setTextColor(Colors.getButtonTextColor());
                        break;
                }
                btnPerformer.setText(performer.getName());
                btnPerformer.setOnClickListener(DisplayAttraction);


                ImageButton imgBtnPerformer;
                /*try {
                    File temp = new File(getCacheDir() + "/temp");
                    if (!temp.exists()) {
                        temp.createNewFile();
                    }
                    URL url = new URL(performer.getImage());
                    FileUtils.copyURLToFile(url, temp);
                    bitmap = BitmapFactory.decodeFile(temp.getAbsolutePath());
                    temp.delete();
                } catch (Exception E) {
                    E.printStackTrace();
                    bitmap = null;
                }*/

                if (bitmap != null) {

                    imgBtnPerformer = new ImageButton(this);
                    imgBtnPerformer.setImageBitmap(bitmap);
                    buttonLayoutParams.weight = 5;
                    imgBtnPerformer.setLayoutParams(buttonLayoutParams);
                    imgBtnPerformer.setOnClickListener(DisplayAttraction);
                    imgBtnPerformer.setClipToOutline(true);
                    buttonDisplayLayout.addView(imgBtnPerformer);
                } else {
                    btnPerformer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    performerDisplayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }

                View Line = new View(this);
                Line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                LinearLayout.LayoutParams lineParams = (LinearLayout.LayoutParams) Line.getLayoutParams();
                lineParams.setMargins(0, 7, 0, 7);
                Line.setLayoutParams(lineParams);
                Line.setBackgroundColor(Color.parseColor("White"));

                buttonDisplayLayout.addView(btnPerformer);

                performerDisplayLayout.addView(buttonDisplayLayout);
                performerDisplayLayout.addView(Line);

            }
        }
        performerScroller.addView(performerDisplayLayout);
        setContentView(performerScroller);
    }
}
