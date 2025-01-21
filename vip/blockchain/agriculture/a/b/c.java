package vip.blockchain.agriculture.a.b;

import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseContentException;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseNotary;
import de.schlichtherle.license.LicenseParam;
import de.schlichtherle.license.NoLicenseInstalledException;
import de.schlichtherle.xml.GenericCertificate;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import vip.blockchain.agriculture.model.dto.LicenseCheckModel;

public class c extends LicenseManager {
  private static Logger a = LogManager.getLogger(c.class);
  
  private static final String b = "UTF-8";
  
  private static final int c = 8192;
  
  private String d = "https://license.zhonghui.vip/prod-api";
  
  public c() {}
  
  public c(LicenseParam paramLicenseParam) {
    super(paramLicenseParam);
  }
  
  protected synchronized byte[] create(LicenseContent paramLicenseContent, LicenseNotary paramLicenseNotary) {
    initialize(paramLicenseContent);
    a(paramLicenseContent);
    GenericCertificate genericCertificate = paramLicenseNotary.sign(paramLicenseContent);
    return getPrivacyGuard().cert2key(genericCertificate);
  }
  
  protected synchronized LicenseContent install(byte[] paramArrayOfbyte, LicenseNotary paramLicenseNotary) {
    GenericCertificate genericCertificate = getPrivacyGuard().key2cert(paramArrayOfbyte);
    paramLicenseNotary.verify(genericCertificate);
    String str;
    LicenseContent licenseContent = (LicenseContent)a(str = genericCertificate.getEncoded().replaceAll("com.zhonghui.license.LicenseCheckModel", LicenseCheckModel.class.getName()));
    validate(licenseContent);
    setLicenseKey(paramArrayOfbyte);
    setCertificate(genericCertificate);
    return licenseContent;
  }
  
  protected synchronized LicenseContent verify(LicenseNotary paramLicenseNotary) {
    getCertificate();
    byte[] arrayOfByte = getLicenseKey();
    if (arrayOfByte == null)
      throw new NoLicenseInstalledException(getLicenseParam().getSubject()); 
    GenericCertificate genericCertificate = getPrivacyGuard().key2cert(arrayOfByte);
    paramLicenseNotary.verify(genericCertificate);
    String str;
    LicenseContent licenseContent = (LicenseContent)a(str = genericCertificate.getEncoded().replaceAll("com.zhonghui.license.LicenseCheckModel", LicenseCheckModel.class.getName()));
    validate(licenseContent);
    setCertificate(genericCertificate);
    return licenseContent;
  }
  
  private synchronized void a(LicenseContent paramLicenseContent) {
    getLicenseParam();
    Date date1 = new Date();
    Date date2 = paramLicenseContent.getNotBefore();
    Date date3 = paramLicenseContent.getNotAfter();
    if (date3 != null && date1.after(date3))
      throw new LicenseContentException("证书失效时间不能早于当前时间"); 
    if (date2 != null && date3 != null && date3.before(date2))
      throw new LicenseContentException("证书生效时间不能晚于证书失效时间"); 
    String str = paramLicenseContent.getConsumerType();
    if (str == null)
      throw new LicenseContentException("用户类型不能为空"); 
  }
  
  protected synchronized void validate(LicenseContent paramLicenseContent) {
    k k;
    i i;
    super.validate(paramLicenseContent);
    LicenseCheckModel licenseCheckModel1 = (LicenseCheckModel)paramLicenseContent.getExtra();
    String str;
    if ((str = System.getProperty("os.name").toLowerCase()).startsWith("windows")) {
      k = new k();
    } else if (k.startsWith("linux")) {
      i = new i();
    } else {
      i = new i();
    } 
    LicenseCheckModel licenseCheckModel2 = i.a();
    if (licenseCheckModel1 != null && licenseCheckModel2 != null) {
      if (!a(licenseCheckModel1.getIpAddress(), licenseCheckModel2.getIpAddress()))
        throw new LicenseContentException("当前服务器的IP没在授权范围内"); 
      if (!a(licenseCheckModel1.getMacAddress(), licenseCheckModel2.getMacAddress()))
        throw new LicenseContentException("当前服务器的Mac地址没在授权范围内"); 
      if (!a(licenseCheckModel1.getMainBoardSerial(), licenseCheckModel2.getMainBoardSerial()))
        throw new LicenseContentException("当前服务器的主板序列号没在授权范围内"); 
      if (!a(licenseCheckModel1.getCpuSerial(), licenseCheckModel2.getCpuSerial()))
        throw new LicenseContentException("当前服务器的CPU序列号没在授权范围内"); 
      if (StringUtils.isNotBlank(licenseCheckModel1.getCpuSerial()) && StringUtils.isNotBlank(licenseCheckModel1.getMainBoardSerial())) {
        HashMap<Object, Object> hashMap;
        (hashMap = new HashMap<>()).put("licenseId", licenseCheckModel1.getLicenseId());
        hashMap.put("licenseCheckModel", licenseCheckModel2);
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory;
        (simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory()).setConnectTimeout(100);
        simpleClientHttpRequestFactory.setReadTimeout(200);
        simpleClientHttpRequestFactory.setTaskExecutor((AsyncListenableTaskExecutor)new SimpleAsyncTaskExecutor());
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate((AsyncClientHttpRequestFactory)simpleClientHttpRequestFactory);
        String str1 = this.d + "/license/checkLicense";
        LinkedMultiValueMap linkedMultiValueMap;
        (linkedMultiValueMap = new LinkedMultiValueMap()).add("Content-Type", "application/json;charset=UTF-8");
        HttpEntity httpEntity = new HttpEntity(hashMap, (MultiValueMap)linkedMultiValueMap);
        ListenableFuture listenableFuture;
        (listenableFuture = asyncRestTemplate.postForEntity(str1, httpEntity, HashMap.class, new Object[0])).addCallback(paramResponseEntity -> {
              if (!((Boolean)((HashMap)paramResponseEntity.getBody()).get("result")).booleanValue())
                try {
                  uninstall();
                  return;
                } catch (Exception exception) {
                  (paramResponseEntity = null).printStackTrace();
                }  
            }paramThrowable -> {
            
            });
        return;
      } 
    } else {
      throw new LicenseContentException("不能获取服务器硬件信息");
    } 
  }
  
  private static Object a(String paramString) {
    BufferedInputStream bufferedInputStream = null;
    XMLDecoder xMLDecoder = null;
    try {
      bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
      return (xMLDecoder = new XMLDecoder(new BufferedInputStream(bufferedInputStream, 8192), null, null)).readObject();
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      (paramString = null).printStackTrace();
    } finally {
      try {
        if (xMLDecoder != null)
          xMLDecoder.close(); 
        if (exception != null)
          exception.close(); 
      } catch (Exception exception1) {
        a.error("XMLDecoder解析XML失败", exception1);
      } 
    } 
    return null;
  }
  
  private static LicenseCheckModel a() {
    k k;
    i i;
    String str;
    if ((str = System.getProperty("os.name").toLowerCase()).startsWith("windows")) {
      k = new k();
    } else if (k.startsWith("linux")) {
      i = new i();
    } else {
      i = new i();
    } 
    return i.a();
  }
  
  private static boolean a(List paramList1, List paramList2) {
    if (paramList1 != null && paramList1.size() > 0) {
      if (paramList2 != null && paramList2.size() > 0)
        for (String str : paramList1) {
          if (paramList2.contains(str.trim()))
            return true; 
        }  
      return false;
    } 
    return true;
  }
  
  private static boolean a(String paramString1, String paramString2) {
    return StringUtils.isNotBlank(paramString1) ? ((StringUtils.isNotBlank(paramString2) && paramString1.equals(paramString2))) : true;
  }
  
  private static Object a(Object paramObject) {
    Properties properties;
    ClassPathResource classPathResource = new ClassPathResource("/application.yaml");
    try {
      YamlPropertiesFactoryBean yamlPropertiesFactoryBean;
      (yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean()).setResources(new Resource[] { (Resource)classPathResource });
      properties = yamlPropertiesFactoryBean.getObject();
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      return null;
    } 
    return properties.get(paramObject);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */