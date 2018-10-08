package com.newsarticle.UI.Home.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Awesome Pojo Generator
 * */
public class MediaMetadata implements Serializable{
  @SerializedName("format")
  @Expose
  private String format;
  @SerializedName("width")
  @Expose
  private Integer width;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("height")
  @Expose
  private Integer height;
  public void setFormat(String format){
   this.format=format;
  }
  public String getFormat(){
   return format;
  }
  public void setWidth(Integer width){
   this.width=width;
  }
  public Integer getWidth(){
   return width;
  }
  public void setUrl(String url){
   this.url=url;
  }
  public String getUrl(){
   return url;
  }
  public void setHeight(Integer height){
   this.height=height;
  }
  public Integer getHeight(){
   return height;
  }
}