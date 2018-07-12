package localhost3000.airshowapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Statics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics);
        //Set title and variables
        setTitle("Statics");
        AirshowInformationStorage Storage = new AirshowInformationStorage();
        //ArrayList<String[]> Statics = Storage.getStatics();

        ArrayList<Static> Statics = Storage.getStaticsList();

        //Adds statics to layout
        final float scale = getResources().getDisplayMetrics().density;
        int dpHeightInPx = (int) (150 * scale);

        LinearLayout.LayoutParams displayLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        ScrollView performerScroller = new ScrollView(this);
        performerScroller.setLayoutParams(displayLayoutParams);
        performerScroller.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));

        displayLayoutParams.setMargins(5,5,5,5);

        LinearLayout performerDisplayLayout = new LinearLayout(this);
        performerDisplayLayout.setLayoutParams(displayLayoutParams);
        performerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
        performerDisplayLayout.setOrientation(LinearLayout.VERTICAL);

        ArrayList<Bitmap> bitmaps = Storage.getStaticBitmaps();



        for (int i=0; i<Statics.size();  i++)
        {
            Static statc = Statics.get(i);
            Bitmap bitmap = null;
            if (bitmaps != null && bitmaps.size() != 0)
                bitmap = bitmaps.get(i);
            if (statc != null)
            {

                LinearLayout buttonDisplayLayout = new LinearLayout(this);
                buttonDisplayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpHeightInPx);
                buttonDisplayLayout.setWeightSum(10);
                buttonDisplayLayout.setOrientation(LinearLayout.HORIZONTAL);

                Button btnStatic = new Button(this);
                buttonLayoutParams.weight = 5;
                buttonLayoutParams.setMargins(15,7,15,7);
                btnStatic.setLayoutParams(buttonLayoutParams);


                GradientDrawable buttonBackground = new GradientDrawable();
                buttonBackground.setColor(Colors.getButtonColor());
                buttonBackground.setCornerRadius(8);

                btnStatic.setBackground(buttonBackground);
                btnStatic.setText(statc.getName());
                btnStatic.setOnClickListener(DisplayAttraction);



                ImageButton imgBtnStatic;

                /*Bitmap bitmap;
                try {
                    File temp = new File(getCacheDir() + "/temp");
                    if (!temp.exists())
                    {
                        temp.createNewFile();
                    }
                    URL url = new URL(statc.getImage());
                    FileUtils.copyURLToFile(url, temp);
                    bitmap = BitmapFactory.decodeFile(temp.getAbsolutePath());
                    temp.delete();
                } catch (Exception E) {
                    E.printStackTrace();
                    bitmap = null;
                }*/

                if (bitmap != null) {

                    imgBtnStatic = new ImageButton(this);
                    imgBtnStatic.setImageBitmap(bitmap);
                    buttonLayoutParams.weight = 5;
                    imgBtnStatic.setLayoutParams(buttonLayoutParams);
                    imgBtnStatic.setOnClickListener(DisplayAttraction);
                    imgBtnStatic.setClipToOutline(true);
                    imgBtnStatic.setScaleType(ImageView.ScaleType.FIT_XY);
                    buttonDisplayLayout.addView(imgBtnStatic);
                }
                else
                {
                    btnStatic.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    performerDisplayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }

                View Line = new View(this);
                Line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                LinearLayout.LayoutParams lineParams = (LinearLayout.LayoutParams) Line.getLayoutParams();
                lineParams.setMargins(0,7,0,7);
                Line.setLayoutParams(lineParams);
                Line.setBackgroundColor(Color.parseColor("White"));

                buttonDisplayLayout.addView(btnStatic);

                performerDisplayLayout.addView(buttonDisplayLayout);
                performerDisplayLayout.addView(Line);

            }
        }
        performerScroller.addView(performerDisplayLayout);
        setContentView(performerScroller);
    }

    Context con = this;

    View.OnClickListener DisplayAttraction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent attractionDisplay = new Intent(con, AttractionDisplay.class);
            attractionDisplay.putExtra("Name", ((Button) v).getText() );
            attractionDisplay.putExtra("Type", "Static");
            startActivity(attractionDisplay);
        }
    };
}
