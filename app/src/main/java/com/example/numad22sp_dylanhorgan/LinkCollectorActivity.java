package com.example.numad22sp_dylanhorgan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LinkCollectorActivity extends AppCompatActivity {

  ArrayList<ItemCard> linkList = new ArrayList<ItemCard>();
  ArrayList<String> nameArray = new ArrayList<String>();
  ArrayList<String> urlArray = new ArrayList<String>();
  RecyclerView recyclerView;
  private AlertDialog.Builder dialogBuilder;
  private AlertDialog dialog;
  private EditText new_site_url, new_site_name;
  private Button cancel, submit;

  public void CreateNewLinkBox(Context cxt){
    dialogBuilder = new AlertDialog.Builder(this);

    final View newLinkBoxView = getLayoutInflater().inflate(R.layout.popup, null);

    new_site_url = (EditText) newLinkBoxView.findViewById(R.id.siteURL);
    new_site_name = (EditText) newLinkBoxView.findViewById(R.id.siteName);

    cancel = (Button) newLinkBoxView.findViewById(R.id.cancelButton);
    submit = (Button) newLinkBoxView.findViewById(R.id.submitButton);

    dialogBuilder.setView(newLinkBoxView);
    dialog = dialogBuilder.create();
    dialog.show();
    submit.setOnClickListener( new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        Log.i(null, "fired add card");



        String inputSiteURL = new_site_url.getText().toString();
        String inputSiteName = new_site_name.getText().toString();
        Log.i(null, inputSiteURL);
        ItemCard newCard = new ItemCard(inputSiteName, inputSiteURL);
        Integer size = linkList.size();
        linkList.add(newCard);
        View linkLayout = findViewById(R.id.linkLayout);
        if(linkList.size() > size){
          recyclerClass newAdapter = new recyclerClass(cxt, linkList);
          recyclerView.setAdapter(newAdapter);
          recyclerView.setLayoutManager(new LinearLayoutManager(cxt));
          Log.i(null, "CREATED THING");
          Snackbar snack = Snackbar.make(linkLayout, "Created Successfully!", Snackbar.LENGTH_INDEFINITE);
          snack.show();
          for(String thing : nameArray){
            System.out.println(thing);
          }
          nameArray.add(inputSiteName);

          System.out.println("PRINT TEST");
          urlArray.add(inputSiteURL);

          for(String thing : nameArray){
            System.out.println(thing);
          }
          dialog.dismiss();
        }else{
          Snackbar snack = Snackbar.make(linkLayout, "Couldn't Be Created!", Snackbar.LENGTH_INDEFINITE);
          snack.show();
          dialog.dismiss();
        }
      }
    });

    cancel.setOnClickListener( new View.OnClickListener(){
      public void onClick(View v){
        dialog.dismiss();
      }
    });
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);



      FloatingActionButton floatingButton = findViewById(R.id.floatingButton);
      Context cxt = this;
      floatingButton.setOnClickListener( new View.OnClickListener(){
        @Override
        public void onClick(View v){

          CreateNewLinkBox(cxt);

        }
      });

      recyclerView = findViewById(R.id.recyclerId);

     // ItemCard card = new ItemCard("Goldfish", "https://en.wikipedia.org/wiki/Goldfish");
      //linkList.add(card);

    //this.onRestoreInstanceState();

      //[] linksArray = getResources().getStringArray(R.array.links);
      //String[] linksArray = new String[] { "Cat", "Dog", "Shark", "Eel", "Goat" };
      //String[] urlArray = new String[] {"https://en.wikipedia.org/wiki/Cat", "https://en.wikipedia.org/wiki/Dog", "https://en.wikipedia.org/wiki/Shark", "https://en.wikipedia.org/wiki/Eel", "https://en.wikipedia.org/wiki/Goat" };
      for(int i = 0; i < nameArray.size(); i++){
        Log.i(null, nameArray.get(i));
        ItemCard card = new ItemCard(nameArray.get(i), urlArray.get(i));
        Log.i(null, card.getLinkURL());
        linkList.add(card);
      }

      recyclerClass adapter = new recyclerClass(this, linkList);

      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        urlArray = savedInstanceState.getStringArrayList("urlArray");
        nameArray = savedInstanceState.getStringArrayList("nameArray");

        System.out.println("RESTORE FIRED");
      for(String thing : nameArray){
        System.out.println(thing);
      }

      for(int i = 0; i < nameArray.size(); i++){
        Log.i(null, nameArray.get(i));
        ItemCard card = new ItemCard(nameArray.get(i), urlArray.get(i));
        Log.i(null, card.getLinkURL());
        linkList.add(card);
      }

      recyclerClass adapter = new recyclerClass(this, linkList);

      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
      outState.putStringArrayList("urlArray", urlArray);
      outState.putStringArrayList("nameArray", nameArray);
      super.onSaveInstanceState(outState);

      System.out.println("SAVE INSTANCE FIRED");
      for(String thing : nameArray){
        System.out.println(thing);
      }
    }
}
