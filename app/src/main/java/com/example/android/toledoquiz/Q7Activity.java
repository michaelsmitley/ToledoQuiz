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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Q7Activity extends AppCompatActivity {

    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q7);

        //Establishes a custom full screen toast
        LayoutInflater inflater = getLayoutInflater();
        final View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        final TextView toastText = toastLayout.findViewById(R.id.text);
        final ImageView toastImage = toastLayout.findViewById(R.id.image);
        final Toast toast = Toast.makeText(getApplicationContext(), toastText.getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastLayout);

        //Locate button
        Button answer = findViewById(R.id.q7_answer_button);

        //Assign listener to button
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Gets the value of correct answers from previous activity
                Bundle bundle = getIntent().getExtras();
                correctAnswers = bundle.getInt("correctAnswers");

                //Identifies the correct answer's radio button
                RadioButton correctAnswer = findViewById(R.id.q7_choice4_radio_button);

                //increments the correct answer if necessary and displays custom toast
                if (correctAnswer.isChecked()) {
                    correctAnswers++;
                    toastText.setText(R.string.toast_correct);
                    toastImage.setImageResource(R.drawable.happyface);
                    toast.show();
                    //displays a custom toast for an incorrect answer
                } else {
                    toastText.setText(R.string.toast_incorrect_7);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //delays the next activity intent until after the toast has finished displaying
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Go to question three and pass value of correct answers
                        Intent intent = new Intent(Q7Activity.this, Q8Activity.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        startActivity(intent);
                    }
                }, 3000);
            }
        });
    }
}
