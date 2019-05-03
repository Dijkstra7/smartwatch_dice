package com.dijkstra.rick.watch_dice;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private TextView mDie1;
    private TextView mDie2;
    private TextView mDie3;
    private Button button;
    private Button button2;
    private Button button3;
    private Random randomizer;
    private int nVisible;
    private ConstraintLayout constraintLayoutDie1;
    private ConstraintLayout constraintLayoutDie2;
    private ConstraintLayout constraintLayoutDie3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nVisible = 1;

        setupGUI();

        randomizer = new Random();

        setupButtonListeners();

        // Set starting visibility
        setDiceVisibility(nVisible);

        // Enables Always-on
        setAmbientEnabled();
    }

    private void setupGUI(){
        mTextView = (TextView) findViewById(R.id.text);
        mDie1 = (TextView) findViewById(R.id.die1);
        mDie2 = (TextView) findViewById(R.id.die2);
        mDie3 = (TextView) findViewById(R.id.die3);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        constraintLayoutDie1 = (ConstraintLayout) findViewById(R.id.constraintDie1);
        constraintLayoutDie2 = (ConstraintLayout) findViewById(R.id.constraintDie2);
        constraintLayoutDie3 = (ConstraintLayout) findViewById(R.id.constraintDie3);
    }

    private void setupButtonListeners(){

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollTheDice();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nVisible += 1;
                setDiceVisibility(nVisible);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nVisible -= 1;
                setDiceVisibility(nVisible);
            }
        });

        constraintLayoutDie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Start new activity to change the die type.
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        getString(R.string.msg_sent));
                startActivity(intent);
            }
        });
    }

    protected void rollTheDice(){
        mDie1.setText(String.valueOf(randomizer.nextInt(6) + 1));
        mDie2.setText(String.valueOf(randomizer.nextInt(6) + 1));
        mDie3.setText(String.valueOf(randomizer.nextInt(6) + 1));
    }

    protected void setDiceVisibility(int numberVisible){
        switch (numberVisible){
            case 1:
                constraintLayoutDie1.setVisibility(View.INVISIBLE);
                constraintLayoutDie2.setVisibility(View.VISIBLE);
                constraintLayoutDie3.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);
                break;
            case 2:
                constraintLayoutDie1.setVisibility(View.VISIBLE);
                constraintLayoutDie2.setVisibility(View.INVISIBLE);
                constraintLayoutDie3.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                break;
            case 3:
                constraintLayoutDie1.setVisibility(View.VISIBLE);
                constraintLayoutDie2.setVisibility(View.VISIBLE);
                constraintLayoutDie3.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button2.setVisibility(View.INVISIBLE);
                break;
            default:
                constraintLayoutDie1.setVisibility(View.VISIBLE);
                constraintLayoutDie2.setVisibility(View.INVISIBLE);
                constraintLayoutDie3.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                break;
        }

    }
}
