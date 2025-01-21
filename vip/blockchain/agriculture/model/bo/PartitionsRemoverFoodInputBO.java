package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsRemoverFoodInputBO {
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
    if (!(paramObject instanceof PartitionsRemoverFoodInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsRemoverFoodInputBO partitionsRemoverFoodInputBO;
    if (!(partitionsRemoverFoodInputBO = this instanceof PartitionsRemoverFoodInputBO))
      return false; 
    String str = get_id();
    paramObject = paramObject.get_id();
    return !((str == null) ? (paramObject != null) : !str.equals(paramObject));
  }
  
  private static boolean a(PartitionsRemoverFoodInputBO paramPartitionsRemoverFoodInputBO) {
    return paramPartitionsRemoverFoodInputBO instanceof PartitionsRemoverFoodInputBO;
  }
  
  public int hashCode() {
    String str = get_id();
    int i;
    return i = 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PartitionsRemoverFoodInputBO(_id=" + get_id() + ")";
  }
  
  public PartitionsRemoverFoodInputBO() {}
  
  public PartitionsRemoverFoodInputBO(String paramString) {
    this.a = paramString;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsRemoverFoodInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */