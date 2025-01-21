package vip.blockchain.agriculture.model.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PartitionsAddCollectorValueInputBO {
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
  
  public String get_deviceId() {
    return this.b;
  }
  
  public String get_date() {
    return this.c;
  }
  
  public String get_temperature() {
    return this.d;
  }
  
  public String get_humidity() {
    return this.e;
  }
  
  public String get_airquality() {
    return this.f;
  }
  
  public String get_pressure() {
    return this.g;
  }
  
  public void set_id(BigInteger paramBigInteger) {
    this.a = paramBigInteger;
  }
  
  public void set_deviceId(String paramString) {
    this.b = paramString;
  }
  
  public void set_date(String paramString) {
    this.c = paramString;
  }
  
  public void set_temperature(String paramString) {
    this.d = paramString;
  }
  
  public void set_humidity(String paramString) {
    this.e = paramString;
  }
  
  public void set_airquality(String paramString) {
    this.f = paramString;
  }
  
  public void set_pressure(String paramString) {
    this.g = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PartitionsAddCollectorValueInputBO))
      return false; 
    paramObject = paramObject;
    PartitionsAddCollectorValueInputBO partitionsAddCollectorValueInputBO;
    if (!(partitionsAddCollectorValueInputBO = this instanceof PartitionsAddCollectorValueInputBO))
      return false; 
    BigInteger bigInteger1 = get_id();
    BigInteger bigInteger2 = paramObject.get_id();
    if ((bigInteger1 == null) ? (bigInteger2 != null) : !bigInteger1.equals(bigInteger2))
      return false; 
    String str1 = get_deviceId();
    String str2 = paramObject.get_deviceId();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_date();
    str2 = paramObject.get_date();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_temperature();
    str2 = paramObject.get_temperature();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_humidity();
    str2 = paramObject.get_humidity();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_airquality();
    str2 = paramObject.get_airquality();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = get_pressure();
    paramObject = paramObject.get_pressure();
    return !((str1 == null) ? (paramObject != null) : !str1.equals(paramObject));
  }
  
  private static boolean a(PartitionsAddCollectorValueInputBO paramPartitionsAddCollectorValueInputBO) {
    return paramPartitionsAddCollectorValueInputBO instanceof PartitionsAddCollectorValueInputBO;
  }
  
  public int hashCode() {
    BigInteger bigInteger = get_id();
    int i = 59 + ((bigInteger == null) ? 43 : bigInteger.hashCode());
    String str = get_deviceId();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_date();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_temperature();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_humidity();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_airquality();
    i = i * 59 + ((str == null) ? 43 : str.hashCode());
    str = get_pressure();
    return i = i * 59 + ((str == null) ? 43 : str.hashCode());
  }
  
  public String toString() {
    return "PartitionsAddCollectorValueInputBO(_id=" + get_id() + ", _deviceId=" + get_deviceId() + ", _date=" + get_date() + ", _temperature=" + get_temperature() + ", _humidity=" + get_humidity() + ", _airquality=" + get_airquality() + ", _pressure=" + get_pressure() + ")";
  }
  
  public PartitionsAddCollectorValueInputBO() {}
  
  public PartitionsAddCollectorValueInputBO(BigInteger paramBigInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    this.a = paramBigInteger;
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramString3;
    this.e = paramString4;
    this.f = paramString5;
    this.g = paramString6;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\bo\PartitionsAddCollectorValueInputBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */