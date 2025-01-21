package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PlatformAddCollectorInputBO {
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
    if (!(paramObject instanceof PlatformAddCollectorInputBO))
      return false; 
    paramObject = paramObject;
    PlatformAddCollectorInputBO platformAddCollectorInputBO;
    if (!(platformAddCollectorInputBO = this instanceof PlatformAddCollectorInputBO))
      return false; 
    String str = get_collectorId();
    paramObject = paramObject.get_collectorId();
    return !((str == null) ? (paramObject != null) : !str.equals(paramObject));
  }
  
  private static boolean a(PlatformAddCollectorInputBO paramPlatformAddCollectorInputBO) {
    return paramPlatformAddCollectorInputBO instanceof PlatformAddCollectorInputBO;
  }
  
  public int hashCode() {
    String str = get_collectorId();
    int i;
    return i = 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PlatformAddCollectorInputBO(_collectorId=" + get_collectorId() + ")";
  }
  
  public PlatformAddCollectorInputBO() {}
  
  public PlatformAddCollectorInputBO(String paramString) {
    this.a = paramString;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PlatformAddCollectorInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */