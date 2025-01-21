package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsCheckFoodInputBO {
  private String a;
  
  public List toArgs() {
    ArrayList<String> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public String get_id() {
    return this.a;
  }
  
  public void set_id(String paramString) {
    this.a = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsCheckFoodInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsCheckFoodInputBO partitionsCheckFoodInputBO;
    if (!(partitionsCheckFoodInputBO = this instanceof PartitionsCheckFoodInputBO))
      return false; 
    String str = get_id();
    paramObject = paramObject.get_id();
    return !((str == null) ? (paramObject != null) : !str.equals(paramObject));
  }
  
  private static boolean a(PartitionsCheckFoodInputBO paramPartitionsCheckFoodInputBO) {
    return paramPartitionsCheckFoodInputBO instanceof PartitionsCheckFoodInputBO;
  }
  
  public int hashCode() {
    String str = get_id();
    int i;
    return i = 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PartitionsCheckFoodInputBO(_id=" + get_id() + ")";
  }
  
  public PartitionsCheckFoodInputBO() {}
  
  public PartitionsCheckFoodInputBO(String paramString) {
    this.a = paramString;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsCheckFoodInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */