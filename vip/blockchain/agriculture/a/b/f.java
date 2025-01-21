package vip.blockchain.agriculture.a.b;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

public class f {
  private static volatile c a;
  
  public static LicenseManager a(LicenseParam paramLicenseParam) {
    if (a == null)
      synchronized (f.class) {
        if (a == null)
          a = new c(paramLicenseParam); 
      }  
    return a;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */