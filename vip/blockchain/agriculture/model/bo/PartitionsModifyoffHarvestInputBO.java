package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsModifyoffHarvestInputBO {
  private Boolean a;
  
  public List toArgs() {
    ArrayList<Boolean> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    return arrayList;
  }
  
  public Boolean get_offHarvest() {
    return this.a;
  }
  
  public void set_offHarvest(Boolean paramBoolean) {
    this.a = paramBoolean;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsModifyoffHarvestInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsModifyoffHarvestInputBO partitionsModifyoffHarvestInputBO;
    if (!(partitionsModifyoffHarvestInputBO = this instanceof PartitionsModifyoffHarvestInputBO))
      return false; 
    Boolean bool = get_offHarvest();
    paramObject = paramObject.get_offHarvest();
    return !((bool == null) ? (paramObject != null) : !bool.equals(paramObject));
  }
  
  private static boolean a(PartitionsModifyoffHarvestInputBO paramPartitionsModifyoffHarvestInputBO) {
    return paramPartitionsModifyoffHarvestInputBO instanceof PartitionsModifyoffHarvestInputBO;
  }
  
  public int hashCode() {
    Boolean bool = get_offHarvest();
    int i;
    return i = 59 + ((bool == null) ? 43 : bool.hashCode());
  }
  
  public String toString() {
    return "PartitionsModifyoffHarvestInputBO(_offHarvest=" + get_offHarvest() + ")";
  }
  
  public PartitionsModifyoffHarvestInputBO() {}
  
  public PartitionsModifyoffHarvestInputBO(Boolean paramBoolean) {
    this.a = paramBoolean;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsModifyoffHarvestInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */