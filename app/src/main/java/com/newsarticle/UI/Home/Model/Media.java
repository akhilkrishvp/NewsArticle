package com.newsarticle.UI.Home.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Media implements Serializable{
  @SerializedName("copyright")
  @Expose
  private String copyright;
  @SerializedName("media-metadata")
  @Expose
  private List<MediaMetadata> mediaMetadata;
  @SerializedName("subtype")
  @Expose
  private String subtype;
  @SerializedName("caption")
  @Expose
  private String caption;
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("approved_for_syndication")
  @Expose
  private Integer approved_for_syndication;

    public List<MediaMetadata> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadata> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

    public void setCopyright(String copyright){
   this.copyright=copyright;
  }
  public String getCopyright(){
   return copyright;
  }
  public void setSubtype(String subtype){
   this.subtype=subtype;
  }
  public String getSubtype(){
   return subtype;
  }
  public void setCaption(String caption){
   this.caption=caption;
  }
  public String getCaption(){
   return caption;
  }
  public void setType(String type){
   this.type=type;
  }
  public String getType(){
   return type;
  }
  public void setApproved_for_syndication(Integer approved_for_syndication){
   this.approved_for_syndication=approved_for_syndication;
  }
  public Integer getApproved_for_syndication(){
   return approved_for_syndication;
  }
}