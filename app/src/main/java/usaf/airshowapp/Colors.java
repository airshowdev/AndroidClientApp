package localhost3000.airshowapplication;

import android.graphics.Color;


/**
 * Created by USAF_Admin on 2/21/2018.
 */

public class Colors {

    private static int BackgroundColor = Color.parseColor("#424242");
    private static int TextColor = Color.parseColor("#FFFFFF");
    private static int ButtonTextColor = Color.parseColor("#000000");
    private static int ButtonColor = Color.parseColor("#EEEEEE");
    private static int GrayText = Color.GRAY;

    public static int getBackgroundColor()
    {
        return  BackgroundColor;
    }

    public static void setBackgroundColor(int intColor)
    {
        BackgroundColor = intColor;
    }

    public static int getTextColor()
    {
        return TextColor;
    }

    public static void setTextColor(int intColor)
    {
        TextColor = intColor;
    }

    public static int getButtonTextColor()
    {
        return ButtonTextColor;
    }

    public static void setButtonTextColor(int intColor)
    {
        ButtonTextColor = intColor;
    }

    public static int getButtonColor()
    {
        return ButtonColor;
    }

    public static void setButtonColor(int intColor)
    {
        ButtonColor = intColor;
    }

        public static int getGrayText()
    {
        return GrayText;
    }
}
