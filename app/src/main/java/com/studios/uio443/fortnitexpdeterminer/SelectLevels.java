package com.studios.uio443.fortnitexpdeterminer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectLevels extends AppCompatActivity {

    TextView yourXpTextView;
    TextView neededXpTextView;
    Button yourXpLeft;
    Button yourXpDoubleLeft;
    Button yourXpRight;
    Button yourXpDoubleRight;
    Button neededXpLeft;
    Button neededXpDoubleLeft;
    Button neededXpRight;
    Button neededXpDoubleRight;
    Button determineBtn;
    public int yourXpCount = 1;
    public int neededXpCount = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_select_levels);

        initViews();

        yourXpRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourXpCount += 1;
                check();
                yourXpTextView.setText("" + yourXpCount);
            }
        });

        yourXpDoubleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourXpCount += 10;
                check();
                yourXpTextView.setText("" + yourXpCount);
            }
        });

        yourXpLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourXpCount -= 1;
                check();
                yourXpTextView.setText("" + yourXpCount);
            }
        });

        yourXpDoubleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourXpCount -= 10;
                check();
                yourXpTextView.setText("" + yourXpCount);
            }
        });

        neededXpRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neededXpCount += 1;
                check();
                neededXpTextView.setText("" + neededXpCount);
            }
        });

        neededXpDoubleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neededXpCount += 10;
                check();
                neededXpTextView.setText("" + neededXpCount);
            }
        });

        neededXpLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neededXpCount -= 1;
                check();
                neededXpTextView.setText("" + neededXpCount);
            }
        });

        neededXpDoubleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neededXpCount -= 10;
                check();
                neededXpTextView.setText("" + neededXpCount);
            }
        });

        determineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

    }

    void initViews(){
        yourXpTextView = findViewById(R.id.yourXpTextView);
        neededXpTextView = findViewById(R.id.neededXpTextView);
        yourXpLeft = findViewById(R.id.yourXpLeft);
        yourXpDoubleLeft = findViewById(R.id.yourXpDoubleLeft);
        yourXpRight = findViewById(R.id.yourXpRight);
        yourXpDoubleRight = findViewById(R.id.yourXpDoubleRight);
        neededXpLeft = findViewById(R.id.neededXpLeft);
        neededXpDoubleLeft = findViewById(R.id.neededXpDoubleLeft);
        neededXpRight = findViewById(R.id.neededXpRight);
        neededXpDoubleRight = findViewById(R.id.neededXpDoubleRight);
        determineBtn = findViewById(R.id.determineBtn);
    }

    void check(){
        if (yourXpCount <= 1){
            yourXpCount = 1;
        }
        if(yourXpCount >= 100){
            yourXpCount = 100;
        }
        if(neededXpCount <= 1){
            neededXpCount = 1;
        }
        if(neededXpCount >= 100){
            neededXpCount = 100;
        }
    }

    void calculate(){
        Resources res = getResources();
        int[] lvls = res.getIntArray(R.array.levels);
        String selected = String.valueOf(yourXpCount);
        String wanted = String.valueOf(neededXpCount);

        Log.d("LvlSelected", selected);
        Log.d("LvlWanted", wanted);
        int yourXP = lvls[yourXpCount-1];
        int wantedXP = lvls[neededXpCount-1];
        Double percent = (double)yourXP/wantedXP*100;
        Log.d("YourAmountOfXp", String.valueOf(yourXP));
        Log.d("WantedAmountOfXp", String.valueOf(wantedXP));

        Log.d("Percent", String.valueOf(percent));

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("percentage", percent);
        startActivity(intent);




    }

}
