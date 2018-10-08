package com.newsarticle.UI.Home.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class ArticleModel{
  @SerializedName("copyright")
  @Expose
  private String copyright;
  @SerializedName("results")
  @Expose
  private List<Results> results;
  @SerializedName("num_results")
  @Expose
  private Integer num_results;
  @SerializedName("status")
  @Expose
  private String status;
  public void setCopyright(String copyright){
   this.copyright=copyright;
  }
  public String getCopyright(){
   return copyright;
  }
  public void setResults(List<Results> results){
   this.results=results;
  }
  public List<Results> getResults(){
   return results;
  }
  public void setNum_results(Integer num_results){
   this.num_results=num_results;
  }
  public Integer getNum_results(){
   return num_results;
  }
  public void setStatus(String status){
   this.status=status;
  }
  public String getStatus(){
   return status;
  }
}