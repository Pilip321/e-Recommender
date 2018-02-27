package com.example.se.se;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText fname, lname, age, school;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final DatabaseHelper db = new DatabaseHelper(this);

        fname = (EditText)findViewById(R.id.first_name);
        lname = (EditText)findViewById(R.id.last_name);
        age = (EditText)findViewById(R.id.age);
        school = (EditText)findViewById(R.id.school);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addStudent(new Student_Database(fname.getText().toString(), lname.getText().toString(),
                        Integer.parseInt(age.getText().toString()), school.getText().toString()));
                openActivity4();
            }
        });
    }

    public void openActivity4() {
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }
}
