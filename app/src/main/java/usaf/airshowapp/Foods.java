package usaf.airshowapp;

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

public class Foods extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        //Sets title
        setTitle("Food");

        //Declare variables
        AirshowInformationStorage Storage = new AirshowInformationStorage();
        //ArrayList<String[]> Food = Storage.getFood();

        ArrayList<Food> foods = Storage.getFoods();

        final float scale = getResources().getDisplayMetrics().density;
        int dpHeightInPx = (int) (150 * scale);

        LinearLayout.LayoutParams displayLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        ScrollView performerScroller = new ScrollView(this);
        performerScroller.setLayoutParams(displayLayoutParams);
        performerScroller.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));

        displayLayoutParams.setMargins(5,5,5,5);

        LinearLayout performerDisplayLayout = new LinearLayout(this);
        performerDisplayLayout.setLayoutParams(displayLayoutParams);
        performerDisplayLayout.setBackgroundColor(Color.parseColor("Gray"));
        performerDisplayLayout.setOrientation(LinearLayout.VERTICAL);



        for (Food food : foods)
        {
            if (food != null)
            {

                LinearLayout buttonDisplayLayout = new LinearLayout(this);
                buttonDisplayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpHeightInPx);
                buttonDisplayLayout.setWeightSum(10);
                buttonDisplayLayout.setOrientation(LinearLayout.HORIZONTAL);

                Button btnPerformer = new Button(this);
                buttonLayoutParams.weight = 5;
                buttonLayoutParams.setMargins(15,7,15,7);
                btnPerformer.setLayoutParams(buttonLayoutParams);


                GradientDrawable buttonBackground = new GradientDrawable();
                buttonBackground.setColor(Colors.getButtonColor());
                buttonBackground.setCornerRadius(8);

                btnPerformer.setBackground(buttonBackground);
                btnPerformer.setText(food.getName());
                btnPerformer.setOnClickListener(DisplayAttraction);

                btnPerformer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                performerDisplayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                View Line = new View(this);
                Line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                LinearLayout.LayoutParams lineParams = (LinearLayout.LayoutParams) Line.getLayoutParams();
                lineParams.setMargins(0,7,0,7);
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
    Context con = this;

    View.OnClickListener DisplayAttraction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button btnClicked = (Button) v;
            Intent attractionDisplay = new Intent(con, AttractionDisplay.class);
            attractionDisplay.putExtra("Name", btnClicked.getText() );
            attractionDisplay.putExtra("Type", "Food");
            startActivity(attractionDisplay);
        }
    };
}
