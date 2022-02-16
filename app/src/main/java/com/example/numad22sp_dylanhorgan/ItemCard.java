package com.example.numad22sp_dylanhorgan;

public class ItemCard implements ItemClickListener{
  private final String linkURL;
  private final String linkName;

  public ItemCard(String linkName, String linkURL) {
    this.linkURL = linkURL;
    this.linkName = linkName;
  }

  public String getLinkName(){ return linkName; }
  public String getLinkURL() {
    return linkURL;
  }

}
