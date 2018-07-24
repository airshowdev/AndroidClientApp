package usaf.airshowapp;


import android.os.StrictMode;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.MySQLConnection;

/**
 * Created by USAF_Admin on 11/30/2017.
 */

public class SQL_Connection{

    public SQL_Connection(StrictMode.ThreadPolicy policy)
    {
        StrictMode.setThreadPolicy(policy);
        ID = Storage.getBaseID();
    }

    AirshowInformationStorage Storage = new AirshowInformationStorage();
    //Initiate connection string, user, and password
    private String connectionString = "jdbc:mysql://10.41.0.200:3306/USAF_Airshows";
    String user = "airshowdev";
    String password = "1qaz@WSX3edc";

    Connection conn = null;

    static String[][][] Tables = new String[5][][];
    private String ID;

    public void LoadSelected(String strAirshow) throws SQLException{

        //Declare more variables

        String Airshow = strAirshow;



        String[][] arrTable;

        try {
            // Sets connection and prepares sql queries
            conn = DriverManager.getConnection(connectionString,user,password);
            Statement Stmt1 = conn.createStatement();
            Statement Stmt2 = conn.createStatement();
            Statement Stmt3 = conn.createStatement();
            Statement Stmt4 = conn.createStatement();
            Statement Stmt5 = conn.createStatement();
            ResultSet rsAbout = Stmt1.executeQuery("SELECT * FROM t_About WHERE col_Airshow LIKE '" + Airshow + "%';");


            int intRowAmount = 0;
            
            try {
                rsAbout.last();
                intRowAmount = rsAbout.getRow();
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            
            ResultSetMetaData ResultsMetaData = rsAbout.getMetaData();
            
            int intColumnAmount = ResultsMetaData.getColumnCount();

            arrTable = new String[intRowAmount][intColumnAmount];
            
            //Sets integers to be later used in loops
            int intArray = 0;
            int i = 0;

            while (i < intRowAmount) {
                for (int j = 0; j < intColumnAmount; j++) {
                    arrTable[i][j] = rsAbout.getString(j+1);
                }
                i++;
            }

            //Adds Table to table array
            Tables[intArray] = arrTable;
            ID = arrTable[0][0];
            intArray++;

            //Creates result sets based off of select statement
            ResultSet rsAttractions = Stmt2.executeQuery("SELECT * FROM t_Attractions WHERE col_ID = " + Integer.valueOf(ID));
            ResultSet rsDirections = Stmt3.executeQuery("SELECT * FROM t_Directions WHERE col_ID = " + Integer.valueOf(ID));
            ResultSet rsSocial_Media = Stmt4.executeQuery("SELECT * FROM t_Social_Media WHERE col_ID = " +Integer.valueOf(ID));
            ResultSet rsFAQ = Stmt5.executeQuery("SELECT * FROM t_FAQ;");

            //Adds result sets to an array
            ResultSet[] ResultTables = new ResultSet[]{rsAttractions, rsDirections, rsSocial_Media, rsFAQ};

            //Takes info from result sets and adds it to an array
            for (ResultSet resultSet : ResultTables)
            {
                intRowAmount = 0;
                try {
                    resultSet.last();
                    intRowAmount = resultSet.getRow();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                ResultsMetaData = resultSet.getMetaData();
                intColumnAmount = ResultsMetaData.getColumnCount();

                arrTable = new String[intRowAmount][intColumnAmount];
                i = 0;
                resultSet.first();
                while (i < intRowAmount) {
                    for (int j = 0; j < intColumnAmount; j++) {
                        arrTable[i][j] = resultSet.getString(j+1);
                    }
                    resultSet.next();
                    i++;
                }
                Tables[intArray] = arrTable;
                intArray++;
                resultSet.close();
            }

            //Closing all connections
            rsAbout.close();
            conn.close();
            Stmt1.close();
            Stmt2.close();
            Stmt3.close();
            Stmt4.close();
            Stmt5.close();

            //Store information to the storage class
            StoreInfo();

        } catch (Exception E) {
            E.printStackTrace();
            throw new SQLException("Unable to load airshow data");
        }
        
    }

    public String[] LoadAirshows() throws SQLException
    {
        String[] strArrAirshows;
        try{
            //Create connection specifically to load airshows
            conn = DriverManager.getConnection(connectionString,user,password);
            Statement selectAirshows = conn.createStatement();
            ResultSet rsAirshows = selectAirshows.executeQuery("Select col_Airshow from t_About");
            rsAirshows.last();


            //Get row count and make array that size
            int intRowCount = rsAirshows.getRow();
            strArrAirshows = new String[intRowCount];
            rsAirshows.first();

            //Store airshow names into the array
                int intSubStringIndex = 0;
                for (int i=0; i<intRowCount; i++) {
                    strArrAirshows[i] = rsAirshows.getString(1);

                    for (int x = 0; x<strArrAirshows[i].toCharArray().length; x++)
                    {
                        char c = strArrAirshows[i].toCharArray()[x];
                        if (c== '1' || c == '0')
                        {
                            intSubStringIndex = x;
                            break;
                        }
                    }
                    strArrAirshows[i] = strArrAirshows[i].substring(0,intSubStringIndex);
                    if (!rsAirshows.next())
                        break;
                }
                //Close connections
                conn.close();
                selectAirshows.close();
                rsAirshows.close();

        }
        catch(Exception E)
        {
            E.printStackTrace();
            throw new SQLException("Unable to load airshow data");
        }

        return strArrAirshows;
    }


    public String getUpdateDate() throws SQLException
    {

        String strAirshowQuery = null;
        strAirshowQuery = Storage.getAirshowName() + Storage.getDate();
        //Initiate variables and date formatter
        Date dtUpdate = null;
        DateFormat dfFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResultSet rsUpdate = null;
        try {
            //Create connection to database
            conn = DriverManager.getConnection(connectionString, user, password);
            Statement stmt = conn.createStatement();
            if (strAirshowQuery != null && strAirshowQuery != "")
                rsUpdate = stmt.executeQuery("SELECT * FROM t_Update Where col_Airshow = \'" + strAirshowQuery + "\'");
            //Go to first row in Table
            rsUpdate.first();

            //Get first time in that row and parse it to a date
            String strDate = rsUpdate.getString(1);
            dtUpdate = dfFormat.parse(strDate);
            rsUpdate.close();
            conn.close();

            //return the date as a string
            return dfFormat.format(dtUpdate);

        }
        catch (Exception E) {
            E.printStackTrace();
            throw new SQLException("Unable to load airshow data");
        }
    }

    
    public void StoreInfo() {
        //Separates name of airshow from it's date
        int intSubStringIndex = 0;

        for (int x = 0; x < Tables[0][0][1].toCharArray().length; x++) {
            char c = Tables[0][0][1].toCharArray()[x];
            if (c == '1' || c == '0') {
                intSubStringIndex = x;
                break;
            }
        }
        String AirshowName = Tables[0][0][1].substring(0, intSubStringIndex);
        String AirshowDate = Tables[0][0][1].substring(intSubStringIndex);

        //Stores data into the data storage class
        Storage.setBaseID(Tables[0][0][0]);
        Storage.setAirshowName(AirshowName);
       Storage.setDate(AirshowDate);
        Storage.setSponsors(Tables[0][0][2]);
        Storage.setAbout(Tables[0][0][3]);
        Storage.setAttractions(Tables[1]);
        Storage.setParking(Tables[2]);
        Storage.setFacebookLink(Tables[3][0][1]);
        Storage.setTwitterLink(Tables[3][0][2]);
        Storage.setWebsiteLink(Tables[3][0][3]);
        Storage.setFAQ(Tables[4]);
        Storage.storeTables(Tables);
    }

    public void setColors()
    {
        String[][] ColorTable = null;
        try {
            Connection conn = DriverManager.getConnection(connectionString, user, password);
            Statement ColorStmt = conn.createStatement();
            ResultSet rsColor = ColorStmt.executeQuery("SELECT * FROM t_Style Where col_Airshow_ID=" + Integer.valueOf(ID));
            if (rsColor.isBeforeFirst() ) {


                rsColor.last();
                int Rows = rsColor.getRow();
                ResultSetMetaData ColorMetaData = rsColor.getMetaData();
                int Columns = ColorMetaData.getColumnCount();
                ColorTable = new String[Rows][Columns];
                rsColor.first();
                for (int x = 0; x < Rows; x++) {
                    for (int y = 1; x <= Columns; y++) {
                        ColorTable[x][y - 1] = rsColor.getString(y);
                    }
                    rsColor.next();
                }
            }

        }
        catch (Exception E)
        {
            E.printStackTrace();
        }
        if (ColorTable != null && ColorTable != new String[5][1])
        {
            if (!ColorTable[0][1].equals("")  && ColorTable[0][1] != null)
                Colors.setBackgroundColor(Integer.valueOf(ColorTable[0][1]));
            if (!ColorTable[0][2].equals("")  && ColorTable[0][2] != null)
                Colors.setButtonColor(Integer.valueOf(ColorTable[0][2]));
            if (!ColorTable[0][3].equals("")  && ColorTable[0][3] != null)
                Colors.setButtonTextColor(Integer.valueOf(ColorTable[0][3]));
            if (!ColorTable[0][4].equals("") && ColorTable[0][4] != null)
                Colors.setTextColor(Integer.valueOf(ColorTable[0][4]));

        }

    }

    public boolean TestConn()
    {
        try{
            conn = DriverManager.getConnection(connectionString, user,password);
            return true;
        }
        catch (Exception E)
        {
            E.printStackTrace();
            return false;
        }
    }
}

