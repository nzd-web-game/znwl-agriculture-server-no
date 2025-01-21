package vip.blockchain.agriculture.a.b.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import vip.blockchain.agriculture.a.b.d.a;

@RestController
public final class d {
  @Autowired
  private WebApplicationContext a;
  
  @GetMapping({"/api/install"})
  private void a() {
    Thread.sleep(500L);
    a a = new a("object");
    this.a.publishEvent((ApplicationEvent)a);
  }
  
  private void a(WebApplicationContext paramWebApplicationContext) {
    this.a = paramWebApplicationContext;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\c\d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */