package com.example.oh.week13;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("오늘 뭐 먹지?");
        setContentView(R.layout.activity_main2);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent cActivity = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(cActivity);
                finish();
            }
        }, 3000);
    }
}
