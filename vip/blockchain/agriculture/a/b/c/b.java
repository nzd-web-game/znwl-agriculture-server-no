package vip.blockchain.agriculture.a.b.c;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vip.blockchain.agriculture.a.a.a.a;
import vip.blockchain.agriculture.a.a.c.a.a;
import vip.blockchain.agriculture.a.a.d.a;
import vip.blockchain.agriculture.a.b.g;
import vip.blockchain.agriculture.a.b.i;
import vip.blockchain.agriculture.a.b.j;
import vip.blockchain.agriculture.a.b.k;
import vip.blockchain.agriculture.model.dto.ApplyParam;
import vip.blockchain.agriculture.model.dto.LicenseCheckModel;

@RestController
@RequestMapping({"/api/license"})
public final class b {
  private String a = "zhonghui";
  
  private String b = "中慧科技";
  
  private String c = "https://license.zhonghui.vip/prod-api";
  
  private String d = "C:\\key";
  
  @Autowired
  private RestTemplate e;
  
  @PostMapping({"/apply"})
  private a a(@RequestBody ApplyParam paramApplyParam) {
    i i;
    paramApplyParam.setLicenseId(paramApplyParam.getLicenseId());
    paramApplyParam.setCompanyName(this.b);
    String str;
    if ((str = System.getProperty("os.name").toLowerCase()).startsWith("windows")) {
      k k = new k();
    } else if (str.startsWith("linux")) {
      i = new i();
    } else {
      i = new i();
    } 
    paramApplyParam.setLicenseCheckModel(i.a());
    ResponseEntity responseEntity;
    RestTemplate restTemplate;
    HashMap hashMap = (HashMap)a.a((String)(responseEntity = (restTemplate = new RestTemplate((ClientHttpRequestFactory)a.a())).postForEntity(this.c + "/license/generateLicense", paramApplyParam, String.class, new Object[0])).getBody(), HashMap.class);
    if ("ok".equals(hashMap.get("result"))) {
      BufferedOutputStream bufferedOutputStream;
      if (j.isBlank(this.d)) {
        str1 = System.getProperty("user.dir");
      } else {
        str1 = this.d;
      } 
      String str2 = str1;
      str = this.a + hashMap.get("id") + ".lic";
      String str1 = (String)hashMap.get("msg");
      str2 = str2;
      File file;
      if (!(file = new File(str2)).exists() && !file.isDirectory())
        file.mkdirs(); 
      file = null;
      FileOutputStream fileOutputStream = null;
      try {
        byte[] arrayOfByte = Base64.getDecoder().decode(str1);
        File file1 = new File(str2 + File.separator + str);
        fileOutputStream = new FileOutputStream(file1);
        (bufferedOutputStream = new BufferedOutputStream(fileOutputStream)).write(arrayOfByte);
      } catch (Exception exception2) {
        Exception exception1;
        (exception1 = null).printStackTrace();
      } finally {
        if (bufferedOutputStream != null)
          try {
            bufferedOutputStream.close();
          } catch (IOException iOException) {
            (str1 = null).printStackTrace();
          }  
        if (fileOutputStream != null)
          try {
            fileOutputStream.close();
          } catch (IOException iOException) {
            (str1 = null).printStackTrace();
          }  
      } 
      return a.a(hashMap.get("result"));
    } 
    throw new a((String)hashMap.get("msg"));
  }
  
  @GetMapping({"/getLicense"})
  private static a a() {
    boolean bool;
    if (bool = g.a())
      return a.a("证书校验成功"); 
    HashMap<Object, Object> hashMap;
    (hashMap = new HashMap<>()).put("message", "您的证书无效，请核查服务器是否取得授权或重新申请证书！");
    hashMap.put("status", Integer.valueOf(1000));
    a.a("您的证书无效，请核查服务器是否取得授权或重新申请证书！", hashMap);
    throw new a("证书校验失败");
  }
  
  private static void a(String paramString1, String paramString2, String paramString3) {
    BufferedOutputStream bufferedOutputStream;
    paramString3 = paramString3;
    File file;
    if (!(file = new File(paramString3)).exists() && !file.isDirectory())
      file.mkdirs(); 
    file = null;
    FileOutputStream fileOutputStream = null;
    try {
      byte[] arrayOfByte = Base64.getDecoder().decode(paramString1);
      File file1 = new File(paramString3 + File.separator + paramString2);
      fileOutputStream = new FileOutputStream(file1);
      (bufferedOutputStream = new BufferedOutputStream(fileOutputStream)).write(arrayOfByte);
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
    } finally {
      if (bufferedOutputStream != null)
        try {
          bufferedOutputStream.close();
        } catch (IOException iOException) {
          (paramString2 = null).printStackTrace();
        }  
      if (fileOutputStream != null)
        try {
          fileOutputStream.close();
        } catch (IOException iOException) {
          (paramString2 = null).printStackTrace();
        }  
    } 
  }
  
  private static LicenseCheckModel b() {
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
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\c\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */