package vip.blockchain.agriculture.a.b;

import de.schlichtherle.license.AbstractKeyStoreParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public final class b extends AbstractKeyStoreParam {
  private String a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  public b(Class paramClass, String paramString1, String paramString2, String paramString3, String paramString4) {
    super(paramClass, paramString1);
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.d = null;
  }
  
  public final String getAlias() {
    return this.b;
  }
  
  public final String getStorePwd() {
    return this.c;
  }
  
  public final String getKeyPwd() {
    return this.d;
  }
  
  public final InputStream getStream() {
    FileInputStream fileInputStream;
    return fileInputStream = new FileInputStream(new File(this.a));
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */