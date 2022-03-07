package com.example.numad22sp_dylanhorgan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutMe = findViewById(R.id.aboutMe);

        aboutMe.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

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
      ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
      Snackbar.make(mainLayout, "Created Successfully!", Snackbar.LENGTH_LONG);

      Button locationButton = findViewById(R.id.locationButton);
      locationButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){openLocationActivity();}
      });

      Button serviceButton = findViewById(R.id.serviceButton);
      serviceButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){openServiceActivity();}
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

    public void openLocationActivity(){
      Intent locationIntent = new Intent(this, locationActivity.class);
      startActivity(locationIntent);
    }

    public void openServiceActivity(){
      Intent serviceIntent = new Intent(this, ServiceActivity.class);
      startActivity(serviceIntent);
    }
}
