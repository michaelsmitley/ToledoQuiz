package com.example.android.toledoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Q1Activity extends MainActivity {

    //Establishes the initial variable to store a running total of correct answers
    private int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);

        /*resets the number of correct answers to zero when retrying the quiz*/
        correctAnswers = 0;

        //Establishes a custom full screen toast
        LayoutInflater inflater = getLayoutInflater();
        final View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        final TextView toastText = toastLayout.findViewById(R.id.text);
        final ImageView toastImage = toastLayout.findViewById(R.id.image);
        final Toast toast = Toast.makeText(getApplicationContext(), toastText.getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastLayout);

        //Locates the next question button
        Button q1Answer = findViewById(R.id.q1_answer_button);

        //Assigns listener to button
        q1Answer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Identifies the radio button group for validation purposes
                RadioGroup q1Choices = findViewById(R.id.q1_choices);

                //Identifies radio button choice number two as the correct answer
                RadioButton correctAnswer = findViewById(R.id.q1_choice2_radio_button);

                if (q1Choices.getCheckedRadioButtonId() == -1) {
                    toastText.setText(R.string.toast_no_answer);
                    toastImage.setImageResource(R.drawable.raisedhand);
                    toast.show();
                    return;
                } else if (correctAnswer.isChecked()) {
                    correctAnswers++;
                    toastText.setText(R.string.toast_correct);
                    toastImage.setImageResource(R.drawable.happyface);
                    toast.show();
                    //displays a custom toast for an incorrect answer
                } else {
                    toastText.setText(R.string.toast_incorrect_1);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //increments the correct answer if necessary and displays custom toast


                //delays the next activity intent until after the toast has finished displaying
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Advances user while passing the value of correct answers to the second question
                        Intent intent = new Intent(Q1Activity.this, Q2Activity.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        startActivity(intent);

                    }
                }, 3000);
            }
        });
    }
}

