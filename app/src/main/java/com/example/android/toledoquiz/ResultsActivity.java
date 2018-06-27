package com.example.android.toledoquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    private int correctAnswers;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle bundle = getIntent().getExtras();
        correctAnswers = bundle.getInt("correctAnswers");

        //Establishes a custom full screen toast
        LayoutInflater inflater = getLayoutInflater();
        final View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        final TextView toastText = toastLayout.findViewById(R.id.text);
        final ImageView toastImage = toastLayout.findViewById(R.id.image);
        final Toast toast = Toast.makeText(getApplicationContext(), toastText.getText(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_VERTICAL | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(toastLayout);
        toastText.setText("You got " + correctAnswers + " out of 10 answers correct");
        toastImage.setImageResource(R.drawable.happyface);
        toast.show();

        String score = String.valueOf(correctAnswers * 10) + "%";
        TextView percentageCorrect = findViewById(R.id.results_score);
        percentageCorrect.setText(score);
        TextView resultsTagLine = findViewById(R.id.results_text);
        switch (correctAnswers){
            case 1: resultsTagLine.setText(R.string.res1); break;
            case 2: resultsTagLine.setText(R.string.res2); break;
            case 3: resultsTagLine.setText(R.string.res3); break;
            case 4: resultsTagLine.setText(R.string.res4); break;
            case 5: resultsTagLine.setText(R.string.res5); break;
            case 6: resultsTagLine.setText(R.string.res6); break;
            case 7: resultsTagLine.setText(R.string.res7); break;
            case 8: resultsTagLine.setText(R.string.res8); break;
            case 9: resultsTagLine.setText(R.string.res9); break;
            case 10: resultsTagLine.setText(R.string.res10); break;
        }

        //Locate button
        Button answer = findViewById(R.id.restart_quiz_button);

        //Assign listener to button
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Go to question one
                Intent tryAgain = new Intent(ResultsActivity.this, Q1Activity.class);
                startActivity(tryAgain);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "I got " + correctAnswers
                        + " out of 10 questions right on the Toledo Quiz app!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
            case R.id.menu_item_directions:
                Uri gmmIntentUri = Uri.parse("geo:41.65381,-83.53626,11");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
