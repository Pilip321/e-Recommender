package com.example.se.se;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main6Activity extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity7();
            }
        });
    }

    public void openActivity7() {
        Intent intent = new Intent(this, Main7Activity.class);
        startActivity(intent);
    }
    /*
    private List<Quiz_Csv> csv = new ArrayList<>();
    public void readCsv() {
        InputStream is = getResources().openRawResource(R.raw.quiz);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ( (line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                Quiz_Csv quiz = new Quiz_Csv();
                quiz.setQuestion(tokens[0]);
                quiz.setOption1(tokens[1]);
                quiz.setOption2(tokens[2]);
                quiz.setOption3(tokens[3]);
                quiz.setOption4(tokens[4]);
                csv.add(quiz);

                Log.d("Question 1", "" + quiz);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading csv file " + line, e);
            e.printStackTrace();
        }
    }
    */
}
