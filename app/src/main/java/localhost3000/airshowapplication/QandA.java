package localhost3000.airshowapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class QandA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Variables
        AirshowInformationStorage Storage = new AirshowInformationStorage();
        Intent in = getIntent();
        //ArrayList<String> Questions = Storage.getQuestions();
        ArrayList<Question> Questions = Storage.getQnA();
        setContentView(R.layout.activity_qand);
        //Set title and display text view
        setTitle("FAQ");
        TextView Question = findViewById(R.id.Question);
        Question.setText(in.getStringExtra("Question"));

        //Find answer to question
        int index = 0;
        for(Question question : Questions)
        {
            if (question != null)
            if (question.getQuestion().equals( in.getStringExtra("Question")))
                break;
            index++;
        }

        //Display answer
        TextView Answer = findViewById(R.id.Answer);
        Answer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Answer.setText(Questions.get(index).getAnswer());

    }
}
