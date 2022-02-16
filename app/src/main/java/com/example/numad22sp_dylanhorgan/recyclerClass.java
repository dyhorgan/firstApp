package com.example.numad22sp_dylanhorgan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;

public class recyclerClass extends RecyclerView.Adapter<recyclerClass.MyViewHolder>{
  private ArrayList<ItemCard> linkList;
  private Context ct;

  public recyclerClass(Context ct, ArrayList<ItemCard> linkList){
    this.linkList = linkList;
    this.ct = ct;
  }

  public ArrayList<ItemCard> getLinkList() {
    return linkList;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder{
    public View visit;
    private TextView linkURL;
    private CardView card;

    public MyViewHolder(final View view){
      super(view);
      linkURL = view.findViewById(R.id.linkURL);
      linkURL.setMovementMethod(LinkMovementMethod.getInstance());
      card = view.findViewById(R.id.cardId);
    }
  }

  @NonNull
  @Override
  public recyclerClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(ct).inflate(R.layout.link_card, parent, false);

    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull recyclerClass.MyViewHolder holder, int position) {
    ItemCard card = linkList.get(position);
    holder.linkURL.setText(card.getLinkName());
    holder.linkURL.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        Log.i(null, "Row Firing?");
        String url = card.getLinkURL();
        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        ct.startActivity(browserIntent);
      }
    });

    holder.card.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        String url = card.getLinkURL();
        Uri uri = Uri.parse(url);
        Intent cardIntent = new Intent(Intent.ACTION_VIEW, uri);
        ct.startActivity(cardIntent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return linkList.size();
  }



}
