package com.example.sayli.fortifiedcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Intro3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro3);
    }

    public void onNext3(View v){
        Intent i=new Intent(this, Intro4.class);
        startActivity(i);
    }
}
