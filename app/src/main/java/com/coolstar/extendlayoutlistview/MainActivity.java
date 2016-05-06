package com.coolstar.extendlayoutlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View btn1,btn2;
        btn1 = findViewById(R.id.scrolllistbtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSrollEventActivity();
            }

        });
        btn2 = findViewById(R.id.touchlistbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTouchScollActivity();
            }
        });
    }

    private void startTouchScollActivity() {
        Intent intent = new Intent(this,TDListViewActivity.class);
        startActivity(intent);
    }

    private void startSrollEventActivity() {
        Intent intent = new Intent(this,SDListViewActivity.class);
        startActivity(intent);
    }
}
