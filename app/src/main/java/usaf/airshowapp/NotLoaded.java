package usaf.airshowapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class NotLoaded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_loaded);
        //Changes title and displays unable to load text
        setTitle("Unable To Load");
        TextView txtUnable = findViewById(R.id.Unable);
        txtUnable.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtUnable.setText("Unable to load airshow data. \n Please check your connection and try again.");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //Closes the app when the back button is pressed
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        System.exit(0);
        return super.onKeyDown(keyCode,event);
    }
}
