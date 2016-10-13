package com.example.sayli.fortifiedcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Intro2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);
    }

    public void onNext2(View v){
        Intent i=new Intent(this, Intro3.class);
        startActivity(i);
    }
}
