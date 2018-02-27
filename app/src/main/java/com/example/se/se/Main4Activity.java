package com.example.se.se;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textView;
    private TextView textView2;
    private int id = 0;
    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        try {
            textView = (TextView)findViewById(R.id.textView);
            textView2 = (TextView)findViewById(R.id.textView2);
            button = (Button)findViewById(R.id.button);
            button2 = (Button)findViewById(R.id.button2);
            button3 = (Button)findViewById(R.id.button3);
            button4 = (Button)findViewById(R.id.button4);
            question = "Question" + (id+1);

            nextQuestion();
        } catch (Exception e) {
            openActivity5();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    id++;
                    nextQuestion();
                } catch (Exception e) {
                    openActivity5();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    id++;
                    nextQuestion();
                } catch (Exception e) {
                    openActivity5();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    id++;
                    nextQuestion();
                } catch (Exception e) {
                    openActivity5();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    id++;
                    nextQuestion();
                } catch (Exception e) {
                    openActivity5();
                }
            }
        });
    }

    public void openActivity5() {
        Intent intent = new Intent(this, Main5Activity.class);
        startActivity(intent);
    }

    public void nextQuestion() {
        DatabaseHelper2 db = new DatabaseHelper2(this);
        List<CSVReader> questionList = db.getAllQuestions();
        CSVReader csv = new CSVReader();

        textView.setText(question);
        textView2.setText(questionList.get(id).getQuestion());
        button.setText(questionList.get(id).getOption1());
        button2.setText(questionList.get(id).getOption2());
        button3.setText(questionList.get(id).getOption3());
        button4.setText(questionList.get(id).getOption4());
    }
}
