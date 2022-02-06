package com.example.numad22sp_dylanhorgan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    static void clickFunc(TextView t, Button b) {
      String s = "Pressed: " + b.getText();
      Log.i("tag", "ClickMessageThing");
      t.setText(s);
    }
  TextView textButton = null;

  static void rotateFunc(TextView t){
    String s = "Pressed: A";
    t.setText(s);
  }
  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    Log.i("tag", "RotationMessageThing");
    rotateFunc(this.textButton);
  }

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button A = findViewById(R.id.A);
        Button B = findViewById(R.id.B);
        Button C = findViewById(R.id.C);
        Button D = findViewById(R.id.D);
        Button E = findViewById(R.id.E);
        Button F = findViewById(R.id.F);
        TextView pressed = findViewById(R.id.pressed);
        this.textButton = pressed;
        A.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view) {

                  clickFunc(pressed, A);
          }
        });

      B.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          clickFunc(pressed, B);
        }
      });

      C.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          clickFunc(pressed, C);
        }
      });

      D.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          clickFunc(pressed, D);
        }
      });

      E.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          clickFunc(pressed, E);
        }
      });

      F.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
          clickFunc(pressed, F);
        }
      });



    }
}
