package com.studios.uio443.fortnitexpdeterminer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class SelectLevels extends AppCompatActivity {

    EditText yourXpTextView;
    String yourXpCountText;
    EditText neededXpTextView;
    String neededXpCountText;
    Button determineBtn;
    public int yourXpCount = 1;
    public int neededXpCount = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_select_levels);

        initViews();

        yourXpTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                yourXpCountText = yourXpTextView.getText().toString();
                if(yourXpCountText.equals("")){
                }
                else {
                    yourXpCount = Integer.parseInt(yourXpCountText);
                    check();
                }
            }
        });

        neededXpTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                neededXpCountText = neededXpTextView.getText().toString();
                if(neededXpCountText.equals("")) {
                }
                else{
                    neededXpCount = Integer.parseInt(neededXpCountText);
                    Log.d("needXpCount", String.valueOf(neededXpCount));
                    check();
                }
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
        determineBtn = findViewById(R.id.determineBtn);
    }

    void check(){

//        if(neededXpCount >= 100){
//            neededXpCount = 100;
//            neededXpTextView.setText(Integer.toString(neededXpCount));
//            neededXpTextView.setSelection(3);
//        }
    }

    void calculate(){
        if (neededXpCount <= yourXpCount) {
            neededXpCount = yourXpCount + 1;
            neededXpTextView.setText(String.valueOf(neededXpCount));
            Toast.makeText(getApplicationContext(), "Your desired level cannot be lower than you current level", Toast.LENGTH_LONG).show();
            return;
        } if (neededXpCount > 100) {

            neededXpCount = 100;
            neededXpTextView.setText(String.valueOf(neededXpCount));
            Log.d("needXpCount", String.valueOf(neededXpCount));
            Log.d("works", "works");
            Toast.makeText(getApplicationContext(), "Your desired level cannot be higher than 100.", Toast.LENGTH_LONG).show();
            return;
        } else {
            Resources res = getResources();
            int[] lvls = res.getIntArray(R.array.levels);
            String selected = String.valueOf(yourXpCount);
            String wanted = String.valueOf(neededXpCount);

            Log.d("LvlSelected", selected);
            Log.d("LvlWanted", wanted);
            int yourXP = lvls[yourXpCount - 1];
            int wantedXP = lvls[neededXpCount - 1];
            Double percent = (double) yourXP / wantedXP * 100;

            Double daysPercentage = (((double) ((Days.daysBetween(new DateTime(), new DateTime(2018, 7, 12, 0, 0, 0, 0)).getDays() * -1) + 1)) / 75.0d) * 100.0d;
            Log.d("daysPercentage", String.valueOf(daysPercentage));

            Log.d("YourAmountOfXp", String.valueOf(yourXP));
            Log.d("WantedAmountOfXp", String.valueOf(wantedXP));

            Log.d("Percent", String.valueOf(percent));

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("percentage", percent);
            intent.putExtra("bpPercentage", daysPercentage);
            startActivity(intent);


        }

    }

}
