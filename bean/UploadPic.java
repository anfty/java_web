package tom.bean;

public class UploadPic {
  private String fileName;
  
  private String savedFileName;
  
  private String backNews = "";
  
  public String getBackNews() { return this.backNews; }
  
  public void setBackNews(String paramString) { this.backNews = paramString; }
  
  public String getFileName() { return this.fileName; }
  
  public void setFileName(String paramString) { this.fileName = paramString; }
  
  public String getSavedFileName() { return this.savedFileName; }
  
  public void setSavedFileName(String paramString) { this.savedFileName = paramString; }
}
