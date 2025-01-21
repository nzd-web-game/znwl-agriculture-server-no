package vip.blockchain.agriculture.model.dto;

public class ApplyParam {
  private static final long a = -7793154252684580872L;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String e = "";
  
  private String f;
  
  private String g;
  
  private String h = "";
  
  private String i;
  
  private LicenseCheckModel j;
  
  public String getLicenseId() {
    return this.b;
  }
  
  public String getCompanyName() {
    return this.c;
  }
  
  public String getStudentName() {
    return this.d;
  }
  
  public String getDescription() {
    return this.e;
  }
  
  public String getCode() {
    return this.f;
  }
  
  public String getMobilePhone() {
    return this.g;
  }
  
  public String getUuid() {
    return this.h;
  }
  
  public String getSmsCode() {
    return this.i;
  }
  
  public LicenseCheckModel getLicenseCheckModel() {
    return this.j;
  }
  
  public void setLicenseId(String paramString) {
    this.b = paramString;
  }
  
  public void setCompanyName(String paramString) {
    this.c = paramString;
  }
  
  public void setStudentName(String paramString) {
    this.d = paramString;
  }
  
  public void setDescription(String paramString) {
    this.e = paramString;
  }
  
  public void setCode(String paramString) {
    this.f = paramString;
  }
  
  public void setMobilePhone(String paramString) {
    this.g = paramString;
  }
  
  public void setUuid(String paramString) {
    this.h = paramString;
  }
  
  public void setSmsCode(String paramString) {
    this.i = paramString;
  }
  
  public void setLicenseCheckModel(LicenseCheckModel paramLicenseCheckModel) {
    this.j = paramLicenseCheckModel;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof ApplyParam))
      return false; 
    paramObject = paramObject;
    ApplyParam applyParam;
    if (!(applyParam = this instanceof ApplyParam))
      return false; 
    String str1 = getLicenseId();
    String str2 = paramObject.getLicenseId();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getCompanyName();
    str2 = paramObject.getCompanyName();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getStudentName();
    str2 = paramObject.getStudentName();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getDescription();
    str2 = paramObject.getDescription();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getCode();
    str2 = paramObject.getCode();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getMobilePhone();
    str2 = paramObject.getMobilePhone();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getUuid();
    str2 = paramObject.getUuid();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    str1 = getSmsCode();
    str2 = paramObject.getSmsCode();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    LicenseCheckModel licenseCheckModel = getLicenseCheckModel();
    paramObject = paramObject.getLicenseCheckModel();
    return !((licenseCheckModel == null) ? (paramObject != null) : !licenseCheckModel.equals(paramObject));
  }
  
  private static boolean a(ApplyParam paramApplyParam) {
    return paramApplyParam instanceof ApplyParam;
  }
  
  public int hashCode() {
    String str1 = getLicenseId();
    int i = 59 + ((str1 == null) ? 43 : str1.hashCode());
    String str2 = getCompanyName();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = getStudentName();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = getDescription();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = getCode();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = getMobilePhone();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = getUuid();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    str2 = getSmsCode();
    i = i * 59 + ((str2 == null) ? 43 : str2.hashCode());
    LicenseCheckModel licenseCheckModel = getLicenseCheckModel();
    return i = i * 59 + ((licenseCheckModel == null) ? 43 : licenseCheckModel.hashCode());
  }
  
  public String toString() {
    return "ApplyParam(licenseId=" + getLicenseId() + ", companyName=" + getCompanyName() + ", studentName=" + getStudentName() + ", description=" + getDescription() + ", code=" + getCode() + ", mobilePhone=" + getMobilePhone() + ", uuid=" + getUuid() + ", smsCode=" + getSmsCode() + ", licenseCheckModel=" + getLicenseCheckModel() + ")";
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\model\dto\ApplyParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */