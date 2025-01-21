package vip.blockchain.agriculture.model.dto;

import java.io.Serializable;
import java.util.List;

public class LicenseCheckModel implements Serializable {
  private static final long a = 8600137500316662317L;
  
  private List b;
  
  private List c;
  
  private String d;
  
  private String e;
  
  private String f;
  
  private String g;
  
  private Long h;
  
  private Long i;
  
  private Long j;
  
  public List getIpAddress() {
    return this.b;
  }
  
  public void setIpAddress(List paramList) {
    this.b = paramList;
  }
  
  public List getMacAddress() {
    return this.c;
  }
  
  public void setMacAddress(List paramList) {
    this.c = paramList;
  }
  
  public String getCpuSerial() {
    return this.d;
  }
  
  public void setCpuSerial(String paramString) {
    this.d = paramString;
  }
  
  public String getMainBoardSerial() {
    return this.e;
  }
  
  public void setMainBoardSerial(String paramString) {
    this.e = paramString;
  }
  
  public String getLicenseId() {
    return this.f;
  }
  
  public void setLicenseId(String paramString) {
    this.f = paramString;
  }
  
  public String getCompanyName() {
    return this.g;
  }
  
  public void setCompanyName(String paramString) {
    this.g = paramString;
  }
  
  public Long getClientCount() {
    return this.h;
  }
  
  public Long getAdminCount() {
    return this.i;
  }
  
  public void setAdminCount(Long paramLong) {
    this.i = paramLong;
  }
  
  public void setClientCount(Long paramLong) {
    this.h = paramLong;
  }
  
  public Long getHasSystemInfo() {
    return this.j;
  }
  
  public void setHasSystemInfo(Long paramLong) {
    this.j = paramLong;
  }
  
  public String toString() {
    return "LicenseCheckModel{ipAddress=" + this.b + ", macAddress=" + this.c + ", cpuSerial='" + this.d + '\'' + ", mainBoardSerial='" + this.e + '\'' + '}';
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\dto\LicenseCheckModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */