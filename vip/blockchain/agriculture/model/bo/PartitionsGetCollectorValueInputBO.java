package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsGetCollectorValueInputBO {
  private List a;
  
  public List toArgs() {
    ArrayList<List> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public List get_ids() {
    return this.a;
  }
  
  public void set_ids(List paramList) {
    this.a = paramList;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsGetCollectorValueInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsGetCollectorValueInputBO partitionsGetCollectorValueInputBO;
    if (!(partitionsGetCollectorValueInputBO = this instanceof PartitionsGetCollectorValueInputBO))
      return false; 
    List list = get_ids();
    paramObject = paramObject.get_ids();
    return !((list == null) ? (paramObject != null) : !list.equals(paramObject));
  }
  
  private static boolean a(PartitionsGetCollectorValueInputBO paramPartitionsGetCollectorValueInputBO) {
    return paramPartitionsGetCollectorValueInputBO instanceof PartitionsGetCollectorValueInputBO;
  }
  
  public int hashCode() {
    List list = get_ids();
    int i;
    return i = 59 + ((list == null) ? 43 : list.hashCode());
  }
  
  public String toString() {
    return "PartitionsGetCollectorValueInputBO(_ids=" + get_ids() + ")";
  }
  
  public PartitionsGetCollectorValueInputBO() {}
  
  public PartitionsGetCollectorValueInputBO(List paramList) {
    this.a = paramList;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsGetCollectorValueInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */