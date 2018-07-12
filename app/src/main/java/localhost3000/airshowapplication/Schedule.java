package localhost3000.airshowapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Set title and variables
        setTitle("Schedule");
        AirshowInformationStorage Storage = new AirshowInformationStorage();
        ArrayList<Performer> Performers = Storage.getPerformerList();

        //Declares layout for performers
        LinearLayout LL = new LinearLayout(this);
        LL.setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
        LL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        LL.setOrientation(LinearLayout.VERTICAL);

        ArrayList<TextView> textList = new ArrayList<>();

        //Adds performers to layout
        for (Performer performer : Performers)
        {
            TextView txtPerform = new TextView(LL.getContext());
            txtPerform.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            txtPerform.setPadding(5,5,5,0);
            txtPerform.setText(performer.getOrderIndex() + " " + performer.getName());
            txtPerform.setTextSize(20);
            txtPerform.setTextColor(Color.parseColor("White"));
            View line = new View(LL.getContext());
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            line.setBackgroundColor(Color.parseColor("White"));
            LL.addView(txtPerform);
            LL.addView(line);
            textList.add(txtPerform);
        }

        //Displays layout
        setContentView(LL);

    }
}
