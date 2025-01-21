package vip.blockchain.agriculture.a.b.e;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import vip.blockchain.agriculture.a.b.g;
import vip.blockchain.agriculture.a.b.h;

@Component
public class a implements ApplicationListener {
  private static Logger a;
  
  private String b = "zhonghui_license";
  
  private String c = "publicCert";
  
  private String d = "zhonghui_private@123";
  
  private String e = "C:\\key";
  
  private static String f = "C:\\key\\publicCerts";
  
  private void a() {
    h h;
    (h = new h()).a(this.b);
    h.b(this.c);
    h.c(this.d);
    h.d(this.e);
    h.e("C:\\key\\publicCerts");
    g g;
    (g = new g()).a(h);
  }
  
  static {
    LogManager.getLogger(a.class);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\e\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */