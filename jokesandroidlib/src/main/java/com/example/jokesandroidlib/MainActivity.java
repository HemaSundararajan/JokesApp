package com.example.jokesandroidlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);

        Intent intent = getIntent();
        String jokeString = intent.getExtras().getString("jokeString");

        TextView jokeView = (TextView) findViewById(R.id.joke_text_view);
        jokeView.setText(jokeString);
    }
}
