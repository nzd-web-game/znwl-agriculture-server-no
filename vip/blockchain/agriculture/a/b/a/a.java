package vip.blockchain.agriculture.a.b.a;

import cn.hutool.json.JSONUtil;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import vip.blockchain.agriculture.a.b.g;

public class a implements HandlerInterceptor {
  private static Logger a;
  
  public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject) {
    if (paramHttpServletRequest.getMethod().equals("OPTIONS"))
      return true; 
    boolean bool;
    if (bool = g.a())
      return true; 
    HashMap<Object, Object> hashMap;
    (hashMap = new HashMap<>()).put("message", "您的证书无效，请核查服务器是否取得授权或重新申请证书！");
    hashMap.put("code", Integer.valueOf(1000));
    paramHttpServletResponse.setContentType("application/json");
    paramHttpServletResponse.getOutputStream().write(JSONUtil.toJsonStr(hashMap).getBytes());
    return false;
  }
  
  static {
    LogManager.getLogger(a.class);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\a\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */