package com.example.android.toledoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Q2Activity extends AppCompatActivity {

    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);

        //Establishes a custom full screen toast
        LayoutInflater inflater = getLayoutInflater();
        final View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        final TextView toastText = toastLayout.findViewById(R.id.text);
        final ImageView toastImage = toastLayout.findViewById(R.id.image);
        final Toast toast = Toast.makeText(getApplicationContext(),toastText.getText(),Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_VERTICAL|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastLayout);

        //Locates next answer button
        Button answer = findViewById(R.id.q2_answer_button);

        //Assigns listener to button
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Gets the value of correct answers from previous activity
                Bundle bundle = getIntent().getExtras();
                correctAnswers = bundle.getInt("correctAnswers");

                //Identifies the text answer input field
                EditText stateAnswer = findViewById(R.id.q2_answer);

                //Checks to see if the answer is "Ohio"
                //Increments value of correct answers if necessary
                //Displays a custom toast for a correct answer
                if (stateAnswer.getText().toString().equalsIgnoreCase("ohio")) {
                    correctAnswers++;
                    toastText.setText(R.string.toast_correct);
                    toastImage.setImageResource(R.drawable.happyface);
                    toast.show();
                    //Displays a custom toast for an incorrect answer
                } else {
                    toastText.setText(R.string.toast_incorrect_2);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //delays the next activity intent until after the toast has finished displaying
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Goes to question three and passes value of correct answers
                        Intent intent = new Intent(Q2Activity.this, Q3Activity.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        startActivity(intent);
                    }
                },3000);
            }
        });
    }
}
