package usaf.airshowapp;
//Imports

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class AirshowData extends AppCompatActivity {

    static File cache;
    //Declare variables, buttons, classes, etc.
    ArrayList<Button> allButtons = new ArrayList<>();
    Button btnAttractions;
    Button btnSchedule;
    Button btnDirections;
    Button btnPark;
    Button btnFAQ;
    Button btnProhibited;
    Button btnSponsors;
    Button btnSocial;
    Button btnAbout;
    Boolean bolLoaded = false;
    Context conThis;
    SQL_Connection SQL = new SQL_Connection(SplashPage.policy);
    AirshowInformationStorage Storage = new AirshowInformationStorage();
    //Context con = this;
    String strName = Storage.getAirshowName();
    OnCompleteListener<Void> complete = new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            String msg = "Subscribed";
            if (!task.isSuccessful()) {
                msg = "Failed";
            }
            Log.d("Message:", msg);
            Toast.makeText(AirshowData.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener Attractions = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Attraction = new Intent(conThis, AirshowAttractions.class);
            startActivity(Attraction);
        }
    };
    //Sets what each button click does in the order they are on the screen
    View.OnClickListener Schedule = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Schedule = new Intent(conThis, usaf.airshowapp.Schedule.class);
            startActivity(Schedule);
        }
    };
    View.OnClickListener Directions = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Direction = new Intent(conThis, usaf.airshowapp.Map.class);
            startActivity(Direction);
        }
    };
    View.OnClickListener Park = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent inParking = new Intent(conThis, NewMap.class);
            startActivity(inParking);
        }

    };
    View.OnClickListener FAQ = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent FAQ = new Intent(conThis, usaf.airshowapp.FAQ.class);
            startActivity(FAQ);
        }
    };
    View.OnClickListener Prohibited_Items = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Prohibited = new Intent(conThis, usaf.airshowapp.Prohibited.class);
            startActivity(Prohibited);
        }
    };
    View.OnClickListener Sponsors = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Sponsor = new Intent(conThis, usaf.airshowapp.Sponsor.class);
            startActivity(Sponsor);
        }
    };
    View.OnClickListener SocialMedia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent Social = new Intent(conThis, Social_Media.class);
            startActivity(Social);
        }
    };
    View.OnClickListener About = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent About = new Intent(conThis, AboutPage.class);
            About.putExtra("About", Storage.getAbout());
            About.putExtra("strName", strName);
            startActivity(About);
        }
    };
    View.OnClickListener Settings = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Setting = new Intent(conThis, usaf.airshowapp.Settings.class);
            startActivity(Setting);
        }
    };
    LoadBitmap bitmapLoader = new LoadBitmap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airshow_data);

        cache = getCacheDir();


        try {

            FirebaseMessaging.getInstance().subscribeToTopic(strName.replace(" ", "").replace("&", "And"));

            //Set title to show which airshow is being displayed
            // SQL.setColors();

            ImageButton ibtSettings = findViewById(R.id.ibtSettings);
            ibtSettings.setOnClickListener(Settings);
            ibtSettings.bringToFront();

            //Declare buttons and set their on click events
            btnAttractions = findViewById(R.id.btnAttractions);
            btnAttractions.setOnClickListener(Attractions);

            btnSchedule = findViewById(R.id.btnSchedule);
            btnSchedule.setOnClickListener(Schedule);

            btnDirections = findViewById(R.id.btnDirections);
            btnDirections.setOnClickListener(Directions);

            btnPark = findViewById(R.id.btnPark);
            btnPark.setOnClickListener(Park);

            btnFAQ = findViewById(R.id.btnFAQ);
            btnFAQ.setOnClickListener(FAQ);

            btnProhibited = findViewById(R.id.btnProhibitedItems);
            btnProhibited.setOnClickListener(Prohibited_Items);

            btnSponsors = findViewById(R.id.btnSponsors);
            btnSponsors.setOnClickListener(Sponsors);

            btnSocial = findViewById(R.id.btnSocialMedia);
            btnSocial.setOnClickListener(SocialMedia);

            btnAbout = findViewById(R.id.btnAbout);
            btnAbout.setOnClickListener(About);
            btnAbout.setText("About " + strName);

            int height = btnAbout.getMeasuredHeight();
            int width = btnAbout.getMeasuredWidth();

            //Set context for loading activities later on
            conThis = btnPark.getContext();

            ConstraintLayout cl = findViewById(R.id.DataCL);
            cl.setZ(0);
            boolean bolLoading = true;
            int intChild = 0;
            while (bolLoading) {
                try {
                    View child = cl.getChildAt(intChild);

                    if (child != null) {
                        if (child instanceof Button) {
                            child.setBackgroundColor(Colors.getButtonColor());
                            ((Button) child).setTextColor(Colors.getButtonTextColor());
                            child.setY(child.getY() + 20 * (intChild + 1) + (intChild == 0 ? 20 : 0));
                            ((Button) child).setHeight(height);
                            ((Button) child).setWidth(width);
                        } else if (child instanceof ImageButton) {
                            child.setBackgroundColor(Colors.getButtonColor()
                            );
                        } else if (child instanceof android.support.v7.widget.Toolbar) {
                            child.setBackgroundColor(Color.parseColor("#3F51B5"));

                        } else {
                            child.setBackgroundColor(Colors.getBackgroundColor());
                            if (child instanceof TextView) {
                                TextView txtChild = (TextView) child;
                                txtChild.setTextColor(Colors.getTextColor());
                                if (txtChild.getId() == R.id.txtNoEndorsement) {
                                    txtChild.setTextColor(Colors.getGrayText());
                                }
                            }
                        }
                    } else {
                        bolLoading = false;
                    }
                    intChild++;
                } catch (Exception E) {
                    bolLoading = false;
                    E.printStackTrace();
                }
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        bitmapLoader.loadPerfBitmaps(Storage.getSelected());
        bitmapLoader.loadStaticBitmaps(Storage.getSelected());

    }

    //Checks to see how many days there are left until the selected airshow also returns that number
    private int setDaysLeft() {

        int intDaysLeft;

        try {
            //Date formatting
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            Date dtAirshow = df.parse(Storage.getDate());
            Date dtNow = new Date();

            //Calculates how many days are left until the airshow
            //Math would look like this ([x-y/1000/60/60/24])
            intDaysLeft = (int) Math.abs((dtAirshow.getTime() - dtNow.getTime()) / 1000 / 60 / 60 / 24);

            //Sets display to show how many days are left
            TextView txtDaysLeft = findViewById(R.id.DaysTilAirshow);
            txtDaysLeft.setText(intDaysLeft + " days left until " + Storage.getAirshowName());
            txtDaysLeft.setSingleLine(false);
            //Checks how many days are left then changes the text display accordingly
            if (dtNow.after(dtAirshow)) {
                txtDaysLeft.setText(Storage.getAirshowName() + "has already passed");
                txtDaysLeft.setTextColor(Color.parseColor("#FFFFFF"));
            } else if (intDaysLeft == 0) {
                txtDaysLeft.setText(Storage.getAirshowName() + " is today");
                txtDaysLeft.setTextColor(Color.parseColor("#00E64D"));
                btnSchedule.setVisibility(View.VISIBLE);

            } else if (intDaysLeft < 7)
                txtDaysLeft.setTextColor(Color.parseColor("#00E64D"));

            else if (intDaysLeft < 30)
                txtDaysLeft.setTextColor(Color.parseColor("#FFFF1A"));

            else
                txtDaysLeft.setTextColor(Color.parseColor("#FF3333"));

            //Returns how many days are left


        } catch (Exception e) {

            //If something fails returns 0
            e.printStackTrace();
            intDaysLeft = 0;
        }
        return intDaysLeft;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!bolLoaded) {

            android.support.v7.widget.Toolbar toolBar;
            toolBar = findViewById(R.id.customToolbar);
            toolBar.setZ(10);
            setSupportActionBar(toolBar);
            setTitle(Storage.getAirshowName());
            toolBar.setTitle("");

            //Runs the superclass' on resume method


            //Loads the information for the selected airshow

            //If it is not the day of the airshow remove the schedule button from display
            setDaysLeft();

            btnAbout.setText("About " + Storage.getAirshowName());

            if (setDaysLeft() != 0) {
                btnSchedule.setVisibility(View.INVISIBLE);
                ArrayList<View> allViews = findViewById(R.id.DataCL).getTouchables();
                allViews.remove(8);
                allViews.remove(0);

                for (View v : allViews) {
                    allButtons.add((Button) v);
                }

                for (Button b : allButtons) {
                    b.setY(b.getY() - getDP(48));
                    if (b != btnAttractions) {
                        b.setY(b.getY() - getDP(8));
                    }
                }

                //Boolean for checking if this section was already run
                bolLoaded = true;
            }
        }

        //Writes the information for the selected airshow to a file
        try {
            WritePreferences();

            StoreDatabase();
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    private void StoreDatabase() throws Exception {
        Database database = new Database();

        File databaseFile = new File(getCacheDir() + "/database.json");

        if (databaseFile.isFile()) {
            String jsonString;
            try {
                char[] buf = new char[1024];

                int numbread = 0;

                StringBuffer buffer = new StringBuffer();
                BufferedReader br = new BufferedReader(new FileReader(databaseFile));
                while ((numbread = br.read(buf)) != -1) {
                    String data = String.valueOf(buf, 0, numbread);
                    buffer.append(data);
                }
                br.close();
                jsonString = buffer.toString();
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    database = mapper.readValue(jsonString, Database.class);

                } catch (Exception E) {
                    E.printStackTrace();
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        }

        database.StoreInfo(database.getAirshowInfo(strName.trim()));

    }

    @Override
    public void onBackPressed() {

        //Minimizes the app if the back button is pressed
        Intent Home = new Intent();
        Home.setAction(Intent.ACTION_MAIN);
        Home.addCategory(Intent.CATEGORY_HOME);
        startActivity(Home);
    }

    //Converts dp to pixels for display purposes
    private float getDP(int pixels) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, getResources().getDisplayMetrics());
    }

    private void WritePreferences() throws Exception {
        //String[][][] Tables = Storage.getTables();
        File Preference = new File(getCacheDir() + "/AirshowPreference");
        FileOutputStream fos = new FileOutputStream(Preference);
        /*ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Storage.getSelected().getName());
        oos.close();*/
        BufferedWriter bwPref = new BufferedWriter(new FileWriter(Preference));
        bwPref.write(Storage.getAirshowName());
        bwPref.close();
        //fos.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getCacheDir() + "/UpdateDate")));
        //bw.write(SQL.getUpdateDate());
        //bw.write(Storage.getSelected().getLastupdate());
        bw.close();
    }

    @Override
    public void setTitle(CharSequence Title) {
        TextView title = findViewById(R.id.Title);
        title.setText(Title);
    }
}

