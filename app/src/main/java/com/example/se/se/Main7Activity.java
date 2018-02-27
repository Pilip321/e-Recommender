package com.example.se.se;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main7Activity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
        }

        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity8();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(Main7Activity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true)
                        .start();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCSV();
            }
        });
    }


    public void openActivity8() {
        Intent intent = new Intent(this, Main8Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            deleteCSV();
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
            file = new File(filePath);
            Log.d( "File Path", "" + filePath);
            readCSV();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    public void readCSV() {
        DatabaseHelper2 db = new DatabaseHelper2(this);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            try {
                while ( (line = bufferedReader.readLine()) != null) {
                    String[] tokens = line.split(",");
                    db.addQuestion(new CSVReader(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
                }
            } catch (IOException e) {}
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error in getting file", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCSV() {
        DatabaseHelper2 db = new DatabaseHelper2(this);
        db.deleteDatabase(this);
    }
}