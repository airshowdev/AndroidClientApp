package localhost3000.airshowapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by USAF_Admin on 11/28/2017.
 */

public class AirshowInformationStorage {

    static boolean bolNotificationsOn;

    public boolean getNotificationsActive()
    {
        return bolNotificationsOn;
    }

    public void setNotificationsActive(boolean Notifications)
    {
        bolNotificationsOn = Notifications;
    }

    static String strBaseID = "";
    public String getBaseID()
    {
        return strBaseID;
    }

    public void setBaseID(String ID)
    {
        strBaseID = ID;
    }

    //Airshow strName declaration and get/set
    static String strAirshowName = "";
    public String getAirshowName ()
    {
        return strAirshowName;
    }



    public void setAirshowName (String AirshowName)
    {
        strAirshowName = AirshowName;
    }

    //Airshow date declaration and get/set
    static String strAirshowDate = "";
    public String getDate ()
    {
        return strAirshowDate;
    }

    public void setDate (String Date)
    {
        strAirshowDate = Date;
    }

    //Airshow attractions and get/sets
    static String[][] Attractions;
    public void setAttractions(String[][] AttractionSet)
    {
        Attractions = AttractionSet;
    }

    public ArrayList<String[]> getFood()
    {
        ArrayList<String[]> FoodList = new ArrayList<>();
        for (String[] Food : Attractions)
        {
            if (Food[1] == "Food")
            {
                FoodList.add(Food);
            }
        }
        return FoodList;
    }

    public ArrayList<String[]> getPerformers()
    {
        ArrayList<String[]> PerformersList = new ArrayList<>();
        for (String[] Performer : Attractions)
        {
            if (Performer[1].equals("Performer"))
            {
                PerformersList.add(Performer);
            }
        }
        return PerformersList;
    }

    public ArrayList<String[]> getStatics()
    {
        ArrayList<String[]> StaticList = new ArrayList<>();
        for (String[] Static : Attractions) {
            if (Static[1] == "Static")
            {
                StaticList.add(Static);
            }
        }
        return StaticList;
    }

    //Airshow parking and get/sets
   static ArrayList<String[]> Parking = new ArrayList<String[]>();

    public ArrayList<String[]> getParking ()
    {
        return Parking;
    }

    public void setParking (String[][] ParkingSet)
    {
        ArrayList<String[]> Parking = new ArrayList<>(Arrays.asList(ParkingSet));
        this.Parking = Parking;


    }


    public String[] getBaseCoords(String strBase)
    {
        String[] Coords = null;
        {
            for (String[] Spot : Parking)
            {
                if (Spot[0].equals(strBase))
                {
                    Coords = Spot;
                    break;
                }
            }
        }
        return Coords;
    }


    //Base about declaration and get/set
    static String strAbout = "";

    public String getAbout ()
    {
        return strAbout;
    }

    public void setAbout (String About)
    {
        strAbout = About;
    }

    //Airshow sponsors declaration and get/set

   static String strSponsors = "";

    public String getSponsors()
    {
        return strSponsors;
    }

    public void setSponsors (String Sponsors)
    {
        strSponsors = Sponsors;
    }

    //Base facebook link declaration and get/set
    static String strFacebookLink = "";

    public Pair<String,Boolean> getFacebookLink ()
    {
        Pair<String,Boolean> LinkGood = new Pair<>("",false);
        if (strFacebookLink != "" && strFacebookLink!= null )
        {
            LinkGood = new Pair<>(strFacebookLink,true);
        }
        return LinkGood;
    }

    public void setFacebookLink(String FacebookLink)
    {
        strFacebookLink = FacebookLink;
    }

    //Base Twitter link declaration and get/set
   static String strTwitterLink = "";

    public Pair<String,Boolean> getTwitterLink ()
    {
        Pair<String,Boolean> LinkGood = new Pair<>("",false);
        if (strTwitterLink != "" && strTwitterLink!=null)
        {
            LinkGood = new Pair<>(strTwitterLink,true);
        }
        return LinkGood;
    }

    public void setTwitterLink (String TwitterLink)
    {
        strTwitterLink = TwitterLink;
    }

    //Base website link declaration and get/set
    static String strWebsiteLink = "";

    public Pair<String,Boolean> getWebsiteLink()
    {
        Pair<String,Boolean> WebsiteGood = new Pair<>("",false);
        if (strWebsiteLink != "" && strWebsiteLink!=null)
        {
            WebsiteGood = new Pair<>(strWebsiteLink,true);
        }
        return WebsiteGood;
    }

    public void setWebsiteLink(String WebsiteLink)
    {
        strWebsiteLink = WebsiteLink;
    }

    private static String IGLink;

    public void setIGLink(String igLink)
    {
        IGLink =igLink;
    }

    public Pair<String,Boolean> getIGLink()
    {
        Pair<String,Boolean> LinkGood = new Pair<>("",false);
        if (IGLink != "" && IGLink != null)
        {
            LinkGood = new Pair<>(IGLink,true);
        }
        return LinkGood;
    }

    //FAQ get set

    static ArrayList<String> Questions = null;
    static ArrayList<String> Answers = null;

    public void setFAQ(String[][] QuestionAnswer)
    {
        Answers = new ArrayList<>();
        Questions = new ArrayList<>();
      for (int i = 0; i < QuestionAnswer.length; i++)
        {
            Answers.add(QuestionAnswer[i][1]);
            Questions.add(QuestionAnswer[i][0]);
        }
    }

    public ArrayList<String> getQuestions()
    {
        return Questions;
    }

    public ArrayList<String> getAnswers()
    {
        return Answers;
    }

    // Url for attractions map set and get

    static Bitmap MapImage;

    public Bitmap getMapURL()
    {
        return MapImage;
    }

    public void setMapImage(Bitmap mapImg)
    {
           MapImage = mapImg;
    }



    public void storeTables(String[][][] Tables)
    {
        this.Tables = Tables;
    }
    static String[][][] Tables;

    public String[][][] getTables()
    {
        return Tables;
    }
    
    public void StoreInfo()
    {
        int intSubStringIndex = 0;

        for (int x = 0; x<Tables[0][0][1].toCharArray().length; x++)
        {
            char c = Tables[0][0][1].toCharArray()[x];
            if (c== '1' || c == '0')
            {
                intSubStringIndex = x;
                break;
            }
        }
        String AirshowName = Tables[0][0][1].substring(0,intSubStringIndex);
        String AirshowDate = Tables[0][0][1].substring(intSubStringIndex);

        //Stores data into the data this class
        this.setBaseID(Tables[0][0][0]);
        this.setAirshowName(AirshowName);
        this.setDate(AirshowDate);
        this.setSponsors(Tables[0][0][2]);
        this.setAbout(Tables[0][0][3]);
        this.setAttractions(Tables[1]);
        this.setParking(Tables[2]);
        this.setFacebookLink(Tables[3][0][1]);
        this.setTwitterLink(Tables[3][0][2]);
        this.setWebsiteLink(Tables[3][0][3]);
        this.setFAQ(Tables[4]);
        this.storeTables(Tables);
    }
    private static ArrayList<Static> statics;

    public ArrayList<Static> getStaticsList()
    {
        return statics;
    }

    public void setStatics(ArrayList<Static>  Statics)
    {
        statics = Statics;
    }

    private static ArrayList<Performer> performers;

    public ArrayList<Performer> getPerformerList()
    {
        Comparator<Performer> comp = new Comparator<Performer>() {
            @Override
            public int compare(Performer performer, Performer t1) {
                if (performer.getOrderIndex() < t1.getOrderIndex())
                {
                    return -1;
                }
                else if (performer.getOrderIndex() > t1.getOrderIndex())
                {
                    return  1;
                }
                else
                {
                    return  0;
                }
            }
        };

        Collections.sort(performers, comp);
        return performers;
    }

    public void setPerformers(ArrayList<Performer> Performers)
    {
        performers = Performers;
    }

    private static ArrayList<Food> foods;

    public ArrayList<Food> getFoods()
    {
        return foods;
    }

    public void setFoods(ArrayList<Food> food)
    {
        foods = food;
    }

    private static ArrayList<ParkingSpot> spots;

    public void setParking(ArrayList<ParkingSpot> spotList)
    {
        spots = spotList;
    }

    public ArrayList<ParkingSpot> getParkingSpots()
    {
        return spots;
    }

    private static Airshow selectedAirshow;

    public Airshow getSelected()
    {
        return selectedAirshow;
    }

    public void setSelected(Airshow airshow)
    {
        selectedAirshow = airshow;
    }

    private static ArrayList<Question> QnAs;
    public ArrayList<Question> getQnA()
    {
        return QnAs;
    }
    public void setQnA(ArrayList<Question> Questions)
    {
        QnAs = Questions;
    }


    private static ArrayList<Bitmap> performerBitmaps, staticBitmaps;

    public  ArrayList<Bitmap> getPerformerBitmaps() {
        return performerBitmaps;
    }

    public  void setPerformerBitmaps(ArrayList<Bitmap> performerBitmaps) {
        AirshowInformationStorage.performerBitmaps = performerBitmaps;
    }

    public  ArrayList<Bitmap> getStaticBitmaps() {
        return staticBitmaps;
    }

    public  void setStaticBitmaps(ArrayList<Bitmap> staticBitmaps) {
        AirshowInformationStorage.staticBitmaps = staticBitmaps;
    }

}
