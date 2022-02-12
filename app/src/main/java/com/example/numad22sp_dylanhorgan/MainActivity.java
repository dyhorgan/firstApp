package com.example.numad22sp_dylanhorgan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutMe = findViewById(R.id.aboutMe);

        aboutMe.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            //Log.i("tag", "randomMessageThing");
            //Toast.makeText(getApplicationContext(), "Dylan Horgan - dyhorgan@gmail.com", Toast.LENGTH_SHORT).show();
            openAboutMeActivity();
          }
        });

        Button clickyclicky = findViewById(R.id.clickyclicky);

        clickyclicky.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view){
            openActivity2();
          }
        });

        Button linkCollector = findViewById(R.id.linkCollector);

        linkCollector.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view){openLinkCollector();}
        });
    }

  public void openActivity2() {
      Intent theIntent = new Intent(this, Activity2.class);
      startActivity(theIntent);
  };

    public void openAboutMeActivity() {
      Intent aboutMeIntent = new Intent(this, AboutMeActivity.class);
      startActivity(aboutMeIntent);
    };

    public void openLinkCollector() {
      Intent LinkCollectorIntent = new Intent(this, LinkCollectorActivity.class);
      startActivity(LinkCollectorIntent);
    }
}
