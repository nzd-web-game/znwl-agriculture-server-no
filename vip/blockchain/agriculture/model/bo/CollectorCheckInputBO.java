package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class CollectorCheckInputBO {
  private String a;
  
  public List toArgs() {
    ArrayList<String> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public String get_collectorId() {
    return this.a;
  }
  
  public void set_collectorId(String paramString) {
    this.a = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof CollectorCheckInputBO))
      return false; 
    paramObject = paramObject;
    CollectorCheckInputBO collectorCheckInputBO;
    if (!(collectorCheckInputBO = this instanceof CollectorCheckInputBO))
      return false; 
    String str = get_collectorId();
    paramObject = paramObject.get_collectorId();
    return !((str == null) ? (paramObject != null) : !str.equals(paramObject));
  }
  
  private static boolean a(CollectorCheckInputBO paramCollectorCheckInputBO) {
    return paramCollectorCheckInputBO instanceof CollectorCheckInputBO;
  }
  
  public int hashCode() {
    String str = get_collectorId();
    int i;
    return i = 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "CollectorCheckInputBO(_collectorId=" + get_collectorId() + ")";
  }
  
  public CollectorCheckInputBO() {}
  
  public CollectorCheckInputBO(String paramString) {
    this.a = paramString;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\CollectorCheckInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */