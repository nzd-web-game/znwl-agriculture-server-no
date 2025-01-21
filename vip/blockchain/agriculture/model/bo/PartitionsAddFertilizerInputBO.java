package vip.blockchain.agriculture.model.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PartitionsAddFertilizerInputBO {
  private BigInteger a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String e;
  
  public List toArgs() {
    ArrayList<BigInteger> arrayList;
    (arrayList = new ArrayList<>()).add(this.a);
    arrayList.add(this.b);
    arrayList.add(this.c);
    arrayList.add(this.d);
    arrayList.add(this.e);
    return arrayList;
  }
  
  public BigInteger get_id() {
    return this.a;
  }
  
  public String get_date() {
    return this.b;
  }
  
  public String get_fertilizerNme() {
    return this.c;
  }
  
  public String get_fertilizerNumber() {
    return this.d;
  }
  
  public String get_notes() {
    return this.e;
  }
  
  public void set_id(BigInteger paramBigInteger) {
    this.a = paramBigInteger;
  }
  
  public void set_date(String paramString) {
    this.b = paramString;
  }
  
  public void set_fertilizerNme(String paramString) {
    this.c = paramString;
  }
  
  public void set_fertilizerNumber(String paramString) {
    this.d = paramString;
  }
  
  public void set_notes(String paramString) {
    this.e = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsAddFertilizerInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsAddFertilizerInputBO partitionsAddFertilizerInputBO;
    if (!(partitionsAddFertilizerInputBO = this instanceof PartitionsAddFertilizerInputBO))
      return false; 
    BigInteger bigInteger1 = get_id();
    BigInteger bigInteger2 = paramObject.get_id();
    if ((bigInteger1 == null) ? (bigInteger2 != null) : !bigInteger1.equals(bigInteger2))
      return false; 
    String str1 = get_date();
    String str2 = paramObject.get_date();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_fertilizerNme();
    str2 = paramObject.get_fertilizerNme();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_fertilizerNumber();
    str2 = paramObject.get_fertilizerNumber();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_notes();
    paramObject = paramObject.get_notes();
    return !((str1 == null) ? (paramObject != null) : !str1.equals(paramObject));
  }
  
  private static boolean a(PartitionsAddFertilizerInputBO paramPartitionsAddFertilizerInputBO) {
    return paramPartitionsAddFertilizerInputBO instanceof PartitionsAddFertilizerInputBO;
  }
  
  public int hashCode() {
    BigInteger bigInteger = get_id();
    int i = 59 + ((bigInteger == null) ? 43 : bigInteger.hashCode());
    String str = get_date();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_fertilizerNme();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_fertilizerNumber();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_notes();
    return i = i * 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PartitionsAddFertilizerInputBO(_id=" + get_id() + ", _date=" + get_date() + ", _fertilizerNme=" + get_fertilizerNme() + ", _fertilizerNumber=" + get_fertilizerNumber() + ", _notes=" + get_notes() + ")";
  }
  
  public PartitionsAddFertilizerInputBO() {}
  
  public PartitionsAddFertilizerInputBO(BigInteger paramBigInteger, String paramString1, String paramString2, String paramString3, String paramString4) {
    this.a = paramBigInteger;
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramString3;
    this.e = paramString4;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsAddFertilizerInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */