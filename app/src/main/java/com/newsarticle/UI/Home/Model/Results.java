package com.newsarticle.UI.Home.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Results implements Serializable{

  private String column;
  @SerializedName("section")
  @Expose
  private String section;

  @SerializedName("source")
  @Expose
  private String source;
  @SerializedName("asset_id")
  @Expose
  private Integer asset_id;
  @SerializedName("media")
  @Expose
  private List<Media> media;
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("adx_keywords")
  @Expose
  private String adx_keywords;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("byline")
  @Expose
  private String byline;
  @SerializedName("published_date")
  @Expose
  private String published_date;
  @SerializedName("views")
  @Expose
  private Integer views;

  public void setColumn(String column){
   this.column=column;
  }
  public String getColumn(){
   return column;
  }
  public void setSection(String section){
   this.section=section;
  }
  public String getSection(){
   return section;
  }
  public void setSource(String source){
   this.source=source;
  }
  public String getSource(){
   return source;
  }
  public void setAsset_id(Integer asset_id){
   this.asset_id=asset_id;
  }
  public Integer getAsset_id(){
   return asset_id;
  }
  public void setMedia(List<Media> media){
   this.media=media;
  }
  public List<Media> getMedia(){
   return media;
  }
  public void setType(String type){
   this.type=type;
  }
  public String getType(){
   return type;
  }
  public void setTitle(String title){
   this.title=title;
  }
  public String getTitle(){
   return title;
  }
  public String getUrl(){
   return url;
  }
  public void setAdx_keywords(String adx_keywords){
   this.adx_keywords=adx_keywords;
  }
  public String getAdx_keywords(){
   return adx_keywords;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setByline(String byline){
   this.byline=byline;
  }
  public String getByline(){
   return byline;
  }
  public void setPublished_date(String published_date){
   this.published_date=published_date;
  }
  public String getPublished_date(){
   return published_date;
  }
  public void setViews(Integer views){
   this.views=views;
  }
  public Integer getViews(){
   return views;
  }
}