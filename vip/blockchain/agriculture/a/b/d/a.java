package vip.blockchain.agriculture.a.b.d;

import org.springframework.context.ApplicationEvent;

public final class a extends ApplicationEvent {
  private String a;
  
  private String b;
  
  public a(Object paramObject) {
    super(paramObject);
  }
  
  private a(Object paramObject, String paramString1, String paramString2) {
    super(paramObject);
    this.a = paramString1;
    this.b = paramString2;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\d\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */