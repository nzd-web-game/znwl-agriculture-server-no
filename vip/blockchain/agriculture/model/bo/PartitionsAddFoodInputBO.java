package vip.blockchain.agriculture.model.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PartitionsAddFoodInputBO {
  private String a;
  
  private String b;
  
  private BigInteger c;
  
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
  
  public String get_id() {
    return this.a;
  }
  
  public String get_foodName() {
    return this.b;
  }
  
  public BigInteger get_quality() {
    return this.c;
  }
  
  public String get_weight() {
    return this.d;
  }
  
  public String get_notes() {
    return this.e;
  }
  
  public void set_id(String paramString) {
    this.a = paramString;
  }
  
  public void set_foodName(String paramString) {
    this.b = paramString;
  }
  
  public void set_quality(BigInteger paramBigInteger) {
    this.c = paramBigInteger;
  }
  
  public void set_weight(String paramString) {
    this.d = paramString;
  }
  
  public void set_notes(String paramString) {
    this.e = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsAddFoodInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsAddFoodInputBO partitionsAddFoodInputBO;
    if (!(partitionsAddFoodInputBO = this instanceof PartitionsAddFoodInputBO))
      return false; 
    String str2 = get_id();
    String str4 = paramObject.get_id();
    if ((str2 == null) ? (str4 != null) : !str2.equals(str4))
      return false; 
    str2 = get_foodName();
    str4 = paramObject.get_foodName();
    if ((str2 == null) ? (str4 != null) : !str2.equals(str4))
      return false; 
    BigInteger bigInteger1 = get_quality();
    BigInteger bigInteger2 = paramObject.get_quality();
    if ((bigInteger1 == null) ? (bigInteger2 != null) : !bigInteger1.equals(bigInteger2))
      return false; 
    String str1 = get_weight();
    String str3 = paramObject.get_weight();
    if ((str1 == null) ? (str3 != null) : !str1.equals(str3))
      return false; 
    str1 = get_notes();
    paramObject = paramObject.get_notes();
    return !((str1 == null) ? (paramObject != null) : !str1.equals(paramObject));
  }
  
  private static boolean a(PartitionsAddFoodInputBO paramPartitionsAddFoodInputBO) {
    return paramPartitionsAddFoodInputBO instanceof PartitionsAddFoodInputBO;
  }
  
  public int hashCode() {
    String str1 = get_id();
    int i = 59 + ((str1 == null) ? 43 : str1.hashCode());
    String str3 = get_foodName();
    i = i * 59 + ((str3 == null) ? 43 : str3.hashCode());
    BigInteger bigInteger = get_quality();
    i = i * 59 + ((bigInteger == null) ? 43 : bigInteger.hashCode());
    String str2 = get_weight();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = get_notes();
    return i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
  }
  
  public String toString() {
    return "PartitionsAddFoodInputBO(_id=" + get_id() + ", _foodName=" + get_foodName() + ", _quality=" + get_quality() + ", _weight=" + get_weight() + ", _notes=" + get_notes() + ")";
  }
  
  public PartitionsAddFoodInputBO() {}
  
  public PartitionsAddFoodInputBO(String paramString1, String paramString2, BigInteger paramBigInteger, String paramString3, String paramString4) {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramBigInteger;
    this.d = paramString3;
    this.e = paramString4;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsAddFoodInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */