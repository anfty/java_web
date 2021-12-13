package tom.bean;
import com.sun.rowset.CachedRowSetImpl;

public class ShowByPage {
  CachedRowSetImpl rowSet = null;
  
  int pageSize = 10;
  
  int pageAllCount = 0;
  
  int showPage = 1;
  
  StringBuffer presentPageResult;
  
  public void setRowSet(CachedRowSetImpl paramCachedRowSetImpl) { this.rowSet = paramCachedRowSetImpl; }
  
  public CachedRowSetImpl getRowSet() { return this.rowSet; }
  
  public void setPageSize(int paramInt) { this.pageSize = paramInt; }
  
  public int getPageSize() { return this.pageSize; }
  
  public int getPageAllCount() { return this.pageAllCount; }
  
  public void setPageAllCount(int paramInt) { this.pageAllCount = paramInt; }
  
  public void setShowPage(int paramInt) { this.showPage = paramInt; }
  
  public int getShowPage() { return this.showPage; }
  
  public void setPresentPageResult(StringBuffer paramStringBuffer) { this.presentPageResult = paramStringBuffer; }
  
  public StringBuffer getPresentPageResult() { return this.presentPageResult; }
}
