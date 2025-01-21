package vip.blockchain.agriculture.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PartitionsModifyPartitionsInfoInputBO {
  private String a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String e;
  
  public List toArgs() {
    ArrayList<String> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    arrayList.add(this.b);
    arrayList.add(this.c);
    arrayList.add(this.d);
    arrayList.add(this.e);
    return arrayList;
  }
  
  public String get_partitionsName() {
    return this.a;
  }
  
  public String get_plantingVarieties() {
    return this.b;
  }
  
  public String get_plantingName() {
    return this.c;
  }
  
  public String get_plantingDate() {
    return this.d;
  }
  
  public String get_notes() {
    return this.e;
  }
  
  public void set_partitionsName(String paramString) {
    this.a = paramString;
  }
  
  public void set_plantingVarieties(String paramString) {
    this.b = paramString;
  }
  
  public void set_plantingName(String paramString) {
    this.c = paramString;
  }
  
  public void set_plantingDate(String paramString) {
    this.d = paramString;
  }
  
  public void set_notes(String paramString) {
    this.e = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsModifyPartitionsInfoInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsModifyPartitionsInfoInputBO partitionsModifyPartitionsInfoInputBO;
    if (!(partitionsModifyPartitionsInfoInputBO = this instanceof PartitionsModifyPartitionsInfoInputBO))
      return false; 
    String str1 = get_partitionsName();
    String str2 = paramObject.get_partitionsName();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_plantingVarieties();
    str2 = paramObject.get_plantingVarieties();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_plantingName();
    str2 = paramObject.get_plantingName();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_plantingDate();
    str2 = paramObject.get_plantingDate();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_notes();
    paramObject = paramObject.get_notes();
    return !((str1 == null) ? (paramObject != null) : !str1.equals(paramObject));
  }
  
  private static boolean a(PartitionsModifyPartitionsInfoInputBO paramPartitionsModifyPartitionsInfoInputBO) {
    return paramPartitionsModifyPartitionsInfoInputBO instanceof PartitionsModifyPartitionsInfoInputBO;
  }
  
  public int hashCode() {
    String str1 = get_partitionsName();
    int i = 59 + ((str1 == null) ? 43 : str1.hashCode());
    String str2 = get_plantingVarieties();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = get_plantingName();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = get_plantingDate();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = get_notes();
    return i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
  }
  
  public String toString() {
    return "PartitionsModifyPartitionsInfoInputBO(_partitionsName=" + get_partitionsName() + ", _plantingVarieties=" + get_plantingVarieties() + ", _plantingName=" + get_plantingName() + ", _plantingDate=" + get_plantingDate() + ", _notes=" + get_notes() + ")";
  }
  
  public PartitionsModifyPartitionsInfoInputBO() {}
  
  public PartitionsModifyPartitionsInfoInputBO(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.d = paramString4;
    this.e = paramString5;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsModifyPartitionsInfoInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */