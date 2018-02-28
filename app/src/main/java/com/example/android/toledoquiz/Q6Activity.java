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

public class Q6Activity extends AppCompatActivity {

    private int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q6);

        //Establishes a custom full screen toast
        LayoutInflater inflater = getLayoutInflater();
        final View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        final TextView toastText = toastLayout.findViewById(R.id.text);
        final ImageView toastImage = toastLayout.findViewById(R.id.image);
        final Toast toast = Toast.makeText(getApplicationContext(), toastText.getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastLayout);

        //Locate button
        Button answer = findViewById(R.id.q6_answer_button);

        //Assign listener to button
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Gets the value of correct answers from previous activity
                Bundle bundle = getIntent().getExtras();
                correctAnswers = bundle.getInt("correctAnswers");

                //Identifies the text answer input field
                EditText PresAnswer = findViewById(R.id.q6_answer);

                //Checks to see if the answer is "0"
                //Increments value of correct answers if necessary
                //Displays a custom toast for a correct answer
                if (PresAnswer.getText().toString().equalsIgnoreCase("")) {
                    toastText.setText(R.string.toast_no_answer);
                    toastImage.setImageResource(R.drawable.raisedhand);
                    toast.show();
                    return;
                }else if (PresAnswer.getText().toString().equalsIgnoreCase("0")) {
                    correctAnswers++;
                    toastText.setText(R.string.toast_correct);
                    toastImage.setImageResource(R.drawable.happyface);
                    toast.show();
                    //displays a custom toast for an incorrect answer
                } else {
                    toastText.setText(R.string.toast_incorrect_6);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //delays the next activity intent until after the toast has finished displaying
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Go to question three and pass value of correct answers
                        Intent intent = new Intent(Q6Activity.this, Q7Activity.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        startActivity(intent);
                    }
                }, 3000);
            }
        });
    }
}
