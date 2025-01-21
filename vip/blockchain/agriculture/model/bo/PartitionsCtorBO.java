package vip.blockchain.agriculture.model.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PartitionsCtorBO {
  private BigInteger a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String e;
  
  private String f;
  
  private String g;
  
  public List toArgs() {
    ArrayList<BigInteger> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    arrayList.add(this.b);
    arrayList.add(this.c);
    arrayList.add(this.d);
    arrayList.add(this.e);
    arrayList.add(this.f);
    arrayList.add(this.g);
    return arrayList;
  }
  
  public BigInteger get_id() {
    return this.a;
  }
  
  public String get_partitionsName() {
    return this.b;
  }
  
  public String get_plantingVarieties() {
    return this.c;
  }
  
  public String get_plantingName() {
    return this.d;
  }
  
  public String get_plantingDate() {
    return this.e;
  }
  
  public String get_ofGreenhouse() {
    return this.f;
  }
  
  public String get_notes() {
    return this.g;
  }
  
  public void set_id(BigInteger paramBigInteger) {
    this.a = paramBigInteger;
  }
  
  public void set_partitionsName(String paramString) {
    this.b = paramString;
  }
  
  public void set_plantingVarieties(String paramString) {
    this.c = paramString;
  }
  
  public void set_plantingName(String paramString) {
    this.d = paramString;
  }
  
  public void set_plantingDate(String paramString) {
    this.e = paramString;
  }
  
  public void set_ofGreenhouse(String paramString) {
    this.f = paramString;
  }
  
  public void set_notes(String paramString) {
    this.g = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsCtorBO))
      return false; 
    paramObject = paramObject;
    PartitionsCtorBO partitionsCtorBO;
    if (!(partitionsCtorBO = this instanceof PartitionsCtorBO))
      return false; 
    BigInteger bigInteger1 = get_id();
    BigInteger bigInteger2 = paramObject.get_id();
    if ((bigInteger1 == null) ? (bigInteger2 != null) : !bigInteger1.equals(bigInteger2))
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
    str1 = get_ofGreenhouse();
    str2 = paramObject.get_ofGreenhouse();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_notes();
    paramObject = paramObject.get_notes();
    return !((str1 == null) ? (paramObject != null) : !str1.equals(paramObject));
  }
  
  private static boolean a(PartitionsCtorBO paramPartitionsCtorBO) {
    return paramPartitionsCtorBO instanceof PartitionsCtorBO;
  }
  
  public int hashCode() {
    BigInteger bigInteger = get_id();
    int i = 59 + ((bigInteger == null) ? 43 : bigInteger.hashCode());
    String str = get_partitionsName();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_plantingVarieties();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_plantingName();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_plantingDate();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_ofGreenhouse();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_notes();
    return i = i * 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PartitionsCtorBO(_id=" + get_id() + ", _partitionsName=" + get_partitionsName() + ", _plantingVarieties=" + get_plantingVarieties() + ", _plantingName=" + get_plantingName() + ", _plantingDate=" + get_plantingDate() + ", _ofGreenhouse=" + get_ofGreenhouse() + ", _notes=" + get_notes() + ")";
  }
  
  public PartitionsCtorBO() {}
  
  public PartitionsCtorBO(BigInteger paramBigInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    this.a = paramBigInteger;
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramString3;
    this.e = paramString4;
    this.f = paramString5;
    this.g = paramString6;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsCtorBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */