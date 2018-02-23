package com.example.android.toledoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Locate button
        Button startQuiz = (Button) findViewById(R.id.start_quiz_button);

        //Assign listener to button
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Advance to question one when button clicked
                Intent intent = new Intent(MainActivity.this, Q1Activity.class);
                startActivity(intent);
            }
        });

    }
}
