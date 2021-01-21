package com.example.webrater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Rating extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        TextView mRatingScale = (TextView) findViewById(R.id.textRatingScale);

        mRatingBar.setOnRatingBarChangeListener(
                (ratingBar, rating, fromUser) -> {
                    mRatingScale.setText(String.valueOf(rating));
                    switch ((int) ratingBar.getRating()) {
                        case 1:
                            mRatingScale.setText("Very bad");
                            break;
                        case 2:
                            mRatingScale.setText("Need some improvement");
                            break;
                        case 3:
                            mRatingScale.setText("Good");
                            break;
                        case 4:
                            mRatingScale.setText("Great");
                            break;
                        case 5:
                            mRatingScale.setText("Awesome. I love it");
                            break;
                        default:
                            mRatingScale.setText("");
                    }
                }
        );
    }

    public void sendFeedBack(View view) {
        Toast toast = Toast.makeText(Rating.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT);
        toast.show();
    }
}