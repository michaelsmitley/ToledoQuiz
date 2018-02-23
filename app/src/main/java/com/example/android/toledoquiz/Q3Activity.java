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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Q3Activity extends AppCompatActivity {

    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3);

        //Establishes a custom full screen toast
        LayoutInflater inflater = getLayoutInflater();
        final View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        final TextView toastText = toastLayout.findViewById(R.id.text);
        final ImageView toastImage = toastLayout.findViewById(R.id.image);
        final Toast toast = Toast.makeText(getApplicationContext(), toastText.getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastLayout);

        //Locates next question button
        Button answer = findViewById(R.id.q3_answer_button);

        //Assigns listener to button
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Gets the value of correct answers from previous activity
                Bundle bundle = getIntent().getExtras();
                correctAnswers = bundle.getInt("correctAnswers");

                // part1 value is later changed to equal 1 if the first of two correct answers is selected
                int part1 = 0;

                // part2 value is later changed to equal 1 if the first of two correct answers is selected
                int part2 = 0;

                //Identifies possible answers
                CheckBox answer1 = findViewById(R.id.q3_choice1_checkbox);
                CheckBox answer2 = findViewById(R.id.q3_choice2_checkbox);
                CheckBox answer3 = findViewById(R.id.q3_choice3_checkbox);
                CheckBox answer4 = findViewById(R.id.q3_choice4_checkbox);

                //Identifies an incorrect answer and displays a custom toast
                if (answer2.isChecked()) {
                    toastText.setText(R.string.toast_incorrect_3);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //Identifies an incorrect answer and displays a custom toast
                if (answer3.isChecked()) {
                    toastText.setText(R.string.toast_incorrect_3);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //Assigns value if one correct answer has been selected
                if (answer1.isChecked()) {
                    part1 = 1;
                }

                //Assigns value if the second correct answer has been selected
                if (answer4.isChecked()) {
                    part2 = 1;
                }

                /*checks to see if both of the correct answers are selected
                and displays the "correct" or "incorrect" toast appropriately
                 */
                if ((part1 + part2) == 2) {
                    correctAnswers++;
                    toastText.setText(R.string.toast_correct);
                    toastImage.setImageResource(R.drawable.happyface);
                    toast.show();

                    //Displays a custom toast for an incorrect answer
                } else {
                    toastText.setText(R.string.toast_incorrect_3);
                    toastImage.setImageResource(R.drawable.sadface);
                    toast.show();
                }

                //delays the next activity intent until after the toast has finished displaying
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Goes to question four and passes value of correct answers
                        Intent intent = new Intent(Q3Activity.this, Q4Activity.class);
                        intent.putExtra("correctAnswers", correctAnswers);
                        startActivity(intent);
                    }
                }, 3000);
            }
        });
    }
}
