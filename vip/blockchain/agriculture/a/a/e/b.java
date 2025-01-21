package vip.blockchain.agriculture.a.a.e;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

public final class b {
  private static final TimedCache a;
  
  private static Object b(String paramString) {
    return a.get(paramString);
  }
  
  public static Object a(String paramString) {
    return a.get(paramString, false);
  }
  
  public static void a(String paramString, Object paramObject) {
    a.put(paramString, paramObject);
  }
  
  static {
    (a = CacheUtil.newTimedCache(60000L)).schedulePrune(1000L);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\e\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */