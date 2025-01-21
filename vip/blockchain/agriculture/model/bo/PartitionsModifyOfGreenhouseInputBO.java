package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsModifyOfGreenhouseInputBO {
  private String a;
  
  public List toArgs() {
    ArrayList<String> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public String get_newOfGreenhouse() {
    return this.a;
  }
  
  public void set_newOfGreenhouse(String paramString) {
    this.a = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsModifyOfGreenhouseInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsModifyOfGreenhouseInputBO partitionsModifyOfGreenhouseInputBO;
    if (!(partitionsModifyOfGreenhouseInputBO = this instanceof PartitionsModifyOfGreenhouseInputBO))
      return false; 
    String str = get_newOfGreenhouse();
    paramObject = paramObject.get_newOfGreenhouse();
    return !((str == null) ? (paramObject != null) : !str.equals(paramObject));
  }
  
  private static boolean a(PartitionsModifyOfGreenhouseInputBO paramPartitionsModifyOfGreenhouseInputBO) {
    return paramPartitionsModifyOfGreenhouseInputBO instanceof PartitionsModifyOfGreenhouseInputBO;
  }
  
  public int hashCode() {
    String str = get_newOfGreenhouse();
    int i;
    return i = 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PartitionsModifyOfGreenhouseInputBO(_newOfGreenhouse=" + get_newOfGreenhouse() + ")";
  }
  
  public PartitionsModifyOfGreenhouseInputBO() {}
  
  public PartitionsModifyOfGreenhouseInputBO(String paramString) {
    this.a = paramString;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsModifyOfGreenhouseInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */