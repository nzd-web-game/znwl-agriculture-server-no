package vip.blockchain.agriculture.model.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GreenhouseModifyGreenhouseInfoInputBO {
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
  
  public String get_greenhouseName() {
    return this.a;
  }
  
  public String get_greenhouseArea() {
    return this.b;
  }
  
  public BigInteger get_maxPartitionQuantity() {
    return this.c;
  }
  
  public String get_greenhousePosition() {
    return this.d;
  }
  
  public String get_notes() {
    return this.e;
  }
  
  public void set_greenhouseName(String paramString) {
    this.a = paramString;
  }
  
  public void set_greenhouseArea(String paramString) {
    this.b = paramString;
  }
  
  public void set_maxPartitionQuantity(BigInteger paramBigInteger) {
    this.c = paramBigInteger;
  }
  
  public void set_greenhousePosition(String paramString) {
    this.d = paramString;
  }
  
  public void set_notes(String paramString) {
    this.e = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof GreenhouseModifyGreenhouseInfoInputBO))
      return false; 
    paramObject = paramObject;
    GreenhouseModifyGreenhouseInfoInputBO greenhouseModifyGreenhouseInfoInputBO;
    if (!(greenhouseModifyGreenhouseInfoInputBO = this instanceof GreenhouseModifyGreenhouseInfoInputBO))
      return false; 
    String str2 = get_greenhouseName();
    String str4 = paramObject.get_greenhouseName();
    if ((str2 == null) ? (str4 != null) : !str2.equals(str4))
      return false; 
    str2 = get_greenhouseArea();
    str4 = paramObject.get_greenhouseArea();
    if ((str2 == null) ? (str4 != null) : !str2.equals(str4))
      return false; 
    BigInteger bigInteger1 = get_maxPartitionQuantity();
    BigInteger bigInteger2 = paramObject.get_maxPartitionQuantity();
    if ((bigInteger1 == null) ? (bigInteger2 != null) : !bigInteger1.equals(bigInteger2))
      return false; 
    String str1 = get_greenhousePosition();
    String str3 = paramObject.get_greenhousePosition();
    if ((str1 == null) ? (str3 != null) : !str1.equals(str3))
      return false; 
    str1 = get_notes();
    paramObject = paramObject.get_notes();
    return !((str1 == null) ? (paramObject != null) : !str1.equals(paramObject));
  }
  
  private static boolean a(GreenhouseModifyGreenhouseInfoInputBO paramGreenhouseModifyGreenhouseInfoInputBO) {
    return paramGreenhouseModifyGreenhouseInfoInputBO instanceof GreenhouseModifyGreenhouseInfoInputBO;
  }
  
  public int hashCode() {
    String str1 = get_greenhouseName();
    int i = 59 + ((str1 == null) ? 43 : str1.hashCode());
    String str3 = get_greenhouseArea();
    i = i * 59 + ((str3 == null) ? 43 : str3.hashCode());
    BigInteger bigInteger = get_maxPartitionQuantity();
    i = i * 59 + ((bigInteger == null) ? 43 : bigInteger.hashCode());
    String str2 = get_greenhousePosition();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = get_notes();
    return i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
  }
  
  public String toString() {
    return "GreenhouseModifyGreenhouseInfoInputBO(_greenhouseName=" + get_greenhouseName() + ", _greenhouseArea=" + get_greenhouseArea() + ", _maxPartitionQuantity=" + get_maxPartitionQuantity() + ", _greenhousePosition=" + get_greenhousePosition() + ", _notes=" + get_notes() + ")";
  }
  
  public GreenhouseModifyGreenhouseInfoInputBO() {}
  
  public GreenhouseModifyGreenhouseInfoInputBO(String paramString1, String paramString2, BigInteger paramBigInteger, String paramString3, String paramString4) {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramBigInteger;
    this.d = paramString3;
    this.e = paramString4;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\GreenhouseModifyGreenhouseInfoInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */