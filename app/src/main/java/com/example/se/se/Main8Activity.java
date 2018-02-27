package com.example.se.se;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main8Activity extends AppCompatActivity {
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        DatabaseHelper db = new DatabaseHelper(this);
        List<Student_Database> studList = db.getAllStudents();
        ArrayList<String> stud = new ArrayList<>();

        list = (ListView)findViewById(R.id.list_item);

        for (Student_Database s : studList) {
            stud.add("" + s.getFname() + " " + s.getLname());
        }

        String[] str = new String[stud.size()];
        for (int i = 0; i < stud.size(); i++) {
            str[i] = stud.get(i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        list.setAdapter(arrayAdapter);
    }
}