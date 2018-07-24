package localhost3000.airshowapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FAQ extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        //Sets title to "FAQ"
        setTitle("FAQ");

        //Declare variables
        AirshowInformationStorage Storage = new AirshowInformationStorage();

        //ArrayList<String> Questions = Storage.getQuestions();

        ArrayList<Question> Questions = Storage.getQnA();


        //Declare a layout to put the question text views in
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.VERTICAL);
        LL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //Dynamically populate FAQ text views depending on how many questions are in the database
        //for (int i = 0; i<Questions.size(); i++)
            for(Question question : Questions){
                if (question != null) {
                    View line = new View(this);
                    line.setBackgroundColor(getResources().getColor(android.R.color.white));
                    line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (getDP(2))));

                    TextView Question = new TextView(this);
                    Question.setText(question.getQuestion());
                    Question.setGravity(Gravity.LEFT);
                    Question.setTextSize(20);
                    Question.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    Question.setTextColor(getResources().getColor(android.R.color.white));
                    Question.setPadding(15, 15, 15, 15);
                    Question.setOnClickListener(QuestionClick);
                    LL.addView(Question);
                    LL.addView(line);
                    LL.setBackgroundColor(Color.parseColor("#424242"));
                }
        }
        //Display the layout
        setContentView(LL);



    }
    //When question is clicked shows question with answer
    View.OnClickListener QuestionClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;
            Intent QandA = new Intent(context, localhost3000.airshowapplication.QandA.class);
            QandA.putExtra("Question", tv.getText());
            startActivity(QandA);
        }
    };

    //Converts input dp to pixels for display
    private float getDP(int pixels) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, getResources().getDisplayMetrics());
    }
}
