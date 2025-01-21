package vip.blockchain.agriculture.a.b.c;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vip.blockchain.agriculture.a.a.c.a.a;
import vip.blockchain.agriculture.a.a.d.a;
import vip.blockchain.agriculture.a.a.e.b;
import vip.blockchain.agriculture.model.dto.ApplyParam;

@RestController
public final class c {
  @Autowired
  private RestTemplate a;
  
  private String b = "https://license.zhonghui.vip/prod-api";
  
  @PostMapping({"/api/sendSMS"})
  private a a(@RequestBody ApplyParam paramApplyParam) {
    String str;
    if ((str = (String)b.a(str = "captcha_codes:" + paramApplyParam.getUuid())) == null)
      throw new a("验证码错误"); 
    if (!paramApplyParam.getCode().equalsIgnoreCase(str))
      throw new a("验证码错误"); 
    if (StrUtil.isNotEmpty(str = (String)b.a(paramApplyParam.getMobilePhone())))
      throw new a("验证码已发送,请不要重复请求"); 
    str = RandomUtil.randomNumbers(6);
    HashMap<Object, Object> hashMap1;
    (hashMap1 = new HashMap<>(1)).put("code", str);
    ResponseEntity responseEntity;
    HashMap hashMap = (HashMap)a.a((String)(responseEntity = this.a.postForEntity(this.b + "/sendSMS/" + paramApplyParam.getMobilePhone(), hashMap1, String.class, new Object[0])).getBody(), HashMap.class);
    if ("OK".equals(hashMap.get("code"))) {
      b.a(paramApplyParam.getMobilePhone(), str);
      return a.a(hashMap);
    } 
    throw new a((String)hashMap.get("message"));
  }
  
  @GetMapping({"/getVerificationCode"})
  private static a a() {
    LineCaptcha lineCaptcha;
    String str1 = (lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40)).getCode();
    String str2 = UUID.randomUUID().toString();
    String str3;
    b.a(str3 = "captcha_codes:" + str2, str1);
    HashMap<Object, Object> hashMap;
    (hashMap = new HashMap<>()).put("image", "data:image/image/png;base64," + lineCaptcha.getImageBase64());
    hashMap.put("uuid", str2);
    return a.a(hashMap);
  }
  
  private void a(RestTemplate paramRestTemplate) {
    this.a = paramRestTemplate;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\c\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */