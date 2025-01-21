package vip.blockchain.agriculture.a.b;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
import java.io.File;
import java.util.prefs.Preferences;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class g {
  private static Logger a = LogManager.getLogger(g.class);
  
  public final synchronized LicenseContent a(h paramh) {
    LicenseContent licenseContent = null;
    try {
      h h1 = paramh;
      Preferences preferences = Preferences.userNodeForPackage(g.class);
      DefaultCipherParam defaultCipherParam = new DefaultCipherParam(h1.c());
      b b = new b(g.class, h1.e(), h1.b(), h1.c(), null);
      LicenseManager licenseManager;
      (licenseManager = f.a((LicenseParam)new DefaultLicenseParam(h1.a(), preferences, (KeyStoreParam)b, (CipherParam)defaultCipherParam))).uninstall();
      if (j.isBlank(paramh.d()))
        paramh.d(System.getProperty("user.dir")); 
      File[] arrayOfFile;
      File file2;
      if ((arrayOfFile = (file2 = new File(paramh.d())).listFiles(new d())).length == 0)
        throw new Exception("没有证书文件"); 
      File file1 = arrayOfFile[0];
      licenseContent = licenseManager.install(file1);
    } catch (Exception exception) {
      a.error("证书安装失败！", exception);
    } 
    return licenseContent;
  }
  
  public static boolean a() {
    LicenseManager licenseManager = f.a(null);
    try {
      licenseManager.verify();
      return true;
    } catch (Exception exception) {
      a.error("证书校验失败！", exception);
      return false;
    } 
  }
  
  private static LicenseParam b(h paramh) {
    Preferences preferences = Preferences.userNodeForPackage(g.class);
    DefaultCipherParam defaultCipherParam = new DefaultCipherParam(paramh.c());
    b b = new b(g.class, paramh.e(), paramh.b(), paramh.c(), null);
    return (LicenseParam)new DefaultLicenseParam(paramh.a(), preferences, (KeyStoreParam)b, (CipherParam)defaultCipherParam);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */