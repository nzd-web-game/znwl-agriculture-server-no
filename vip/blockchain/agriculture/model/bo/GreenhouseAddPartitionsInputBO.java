package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class GreenhouseAddPartitionsInputBO {
  private String a;
  
  public List toArgs() {
    ArrayList<String> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public String get_partitions() {
    return this.a;
  }
  
  public void set_partitions(String paramString) {
    this.a = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof GreenhouseAddPartitionsInputBO))
      return false; 
    paramObject = paramObject;
    GreenhouseAddPartitionsInputBO greenhouseAddPartitionsInputBO;
    if (!(greenhouseAddPartitionsInputBO = this instanceof GreenhouseAddPartitionsInputBO))
      return false; 
    String str = get_partitions();
    paramObject = paramObject.get_partitions();
    return !((str == null) ? (paramObject != null) : !str.equals(paramObject));
  }
  
  private static boolean a(GreenhouseAddPartitionsInputBO paramGreenhouseAddPartitionsInputBO) {
    return paramGreenhouseAddPartitionsInputBO instanceof GreenhouseAddPartitionsInputBO;
  }
  
  public int hashCode() {
    String str = get_partitions();
    int i;
    return i = 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "GreenhouseAddPartitionsInputBO(_partitions=" + get_partitions() + ")";
  }
  
  public GreenhouseAddPartitionsInputBO() {}
  
  public GreenhouseAddPartitionsInputBO(String paramString) {
    this.a = paramString;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\GreenhouseAddPartitionsInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */