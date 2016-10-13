package com.example.sayli.fortifiedcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Intro4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro4);
    }

    public void onDone(View v){
        Intent i=new Intent(this, CScan.class);
        startActivity(i);
    }
}
