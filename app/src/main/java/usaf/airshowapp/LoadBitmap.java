package usaf.airshowapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USAF_Admin on 7/9/2018.
 */

public class LoadBitmap extends AsyncTask<Object, Void, Void> {

    AirshowInformationStorage Storage = new AirshowInformationStorage();

    @Override
    protected Void doInBackground(Object... params) {
        List<Bitmap> bitmaps = new ArrayList<>();

        if (params[0] instanceof ArrayList) {
            ArrayList<Object> attractions = (ArrayList<Object>) params[0];
            if (attractions.get(0) instanceof Performer) {
                ArrayList<Performer> perfs = new ArrayList<>();
                for (Object at : attractions) {
                    perfs.add((Performer) at);
                }
                for (Performer perf : perfs) {
                    if (perf != null)
                        bitmaps.add(loadBitmap(perf.getImage()));
                }
                storeBitmaps((ArrayList<Bitmap>) bitmaps, "Performers");
            } else if (attractions.get(0) instanceof Static) {
                ArrayList<Static> statics = new ArrayList<>();
                for (Object at : attractions) {
                    statics.add((Static) at);
                }
                for (Static statc : statics) {
                    if (statc != null)
                        bitmaps.add(loadBitmap(statc.getImage()));
                }

                storeBitmaps((ArrayList<Bitmap>) bitmaps, "Statics");
            }
        }
        return null;
    }

    public void loadPerfBitmaps(Airshow airshow) {
            doInBackground(airshow.getPerformers(), airshow);
    }

    public void loadStaticBitmaps(Airshow airshow) {
            doInBackground(airshow.getStatics() , airshow);
    }

    private Bitmap loadBitmap(String urlString) {
        Bitmap bitmap;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception E) {
            E.printStackTrace();
            bitmap = null;
        }

        return bitmap;

    }

    private void storeBitmaps (ArrayList<Bitmap> bitmaps, String attractionType)
    {
        if (attractionType.equals("Performers"))
            Storage.setPerformerBitmaps(bitmaps);
        else if (attractionType.equals("Statics"))
            Storage.setStaticBitmaps(bitmaps);
    }

}
