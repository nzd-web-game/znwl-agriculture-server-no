package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsGetFertilizerInputBO {
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
    if (!(paramObject instanceof PartitionsGetFertilizerInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsGetFertilizerInputBO partitionsGetFertilizerInputBO;
    if (!(partitionsGetFertilizerInputBO = this instanceof PartitionsGetFertilizerInputBO))
      return false; 
    List list = get_ids();
    paramObject = paramObject.get_ids();
    return !((list == null) ? (paramObject != null) : !list.equals(paramObject));
  }
  
  private static boolean a(PartitionsGetFertilizerInputBO paramPartitionsGetFertilizerInputBO) {
    return paramPartitionsGetFertilizerInputBO instanceof PartitionsGetFertilizerInputBO;
  }
  
  public int hashCode() {
    List list = get_ids();
    int i;
    return i = 59 + ((list == null) ? 43 : list.hashCode());
  }
  
  public String toString() {
    return "PartitionsGetFertilizerInputBO(_ids=" + get_ids() + ")";
  }
  
  public PartitionsGetFertilizerInputBO() {}
  
  public PartitionsGetFertilizerInputBO(List paramList) {
    this.a = paramList;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsGetFertilizerInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */