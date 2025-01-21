package vip.blockchain.agriculture.a.b;

import java.io.File;
import java.io.FilenameFilter;

public final class d implements FilenameFilter {
  public final boolean accept(File paramFile, String paramString) {
    boolean bool = false;
    if (paramString.toLowerCase().endsWith(".lic"))
      bool = true; 
    return bool;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */