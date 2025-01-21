package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PlatformOffHarvestInputBO {
  private List a;
  
  public List toArgs() {
    ArrayList<List> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public List get_partitionsss() {
    return this.a;
  }
  
  public void set_partitionsss(List paramList) {
    this.a = paramList;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PlatformOffHarvestInputBO))
      return false; 
    paramObject = paramObject;
    PlatformOffHarvestInputBO platformOffHarvestInputBO;
    if (!(platformOffHarvestInputBO = this instanceof PlatformOffHarvestInputBO))
      return false; 
    List list = get_partitionsss();
    paramObject = paramObject.get_partitionsss();
    return !((list == null) ? (paramObject != null) : !list.equals(paramObject));
  }
  
  private static boolean a(PlatformOffHarvestInputBO paramPlatformOffHarvestInputBO) {
    return paramPlatformOffHarvestInputBO instanceof PlatformOffHarvestInputBO;
  }
  
  public int hashCode() {
    List list = get_partitionsss();
    int i;
    return i = 59 + ((list == null) ? 43 : list.hashCode());
  }
  
  public String toString() {
    return "PlatformOffHarvestInputBO(_partitionsss=" + get_partitionsss() + ")";
  }
  
  public PlatformOffHarvestInputBO() {}
  
  public PlatformOffHarvestInputBO(List paramList) {
    this.a = paramList;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PlatformOffHarvestInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */