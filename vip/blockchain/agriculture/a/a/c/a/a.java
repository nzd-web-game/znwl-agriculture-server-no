package vip.blockchain.agriculture.a.a.c.a;

import cn.hutool.core.lang.Validator;
import java.util.HashMap;

public final class a extends HashMap {
  private static final long a = 1L;
  
  private static String b = "code";
  
  private static String c = "message";
  
  private static String d = "data";
  
  private Integer a() {
    return (Integer)get("code");
  }
  
  private String b() {
    return (String)get("message");
  }
  
  private Object c() {
    return get("data");
  }
  
  public a() {}
  
  private a(int paramInt, String paramString) {
    put((K)"code", (V)Integer.valueOf(paramInt));
    put((K)"message", (V)paramString);
  }
  
  private a(int paramInt, String paramString, Object paramObject) {
    put((K)"code", (V)Integer.valueOf(paramInt));
    put((K)"message", (V)paramString);
    if (Validator.isNotNull(paramObject))
      put((K)"data", (V)paramObject); 
  }
  
  private static a d() {
    String str;
    return b(str = "操作成功", null);
  }
  
  public static a a(Object paramObject) {
    return b("操作成功", paramObject);
  }
  
  public static a a(String paramString) {
    return b(paramString, null);
  }
  
  private static a b(String paramString, Object paramObject) {
    return new a(200, paramString, paramObject);
  }
  
  private static a e() {
    String str;
    return a(str = "操作失败", (Object)null);
  }
  
  public static a b(String paramString) {
    return a(paramString, (Object)null);
  }
  
  public static a a(String paramString, Object paramObject) {
    return new a(401, paramString, paramObject);
  }
  
  private static a a(int paramInt, String paramString) {
    return new a(paramInt, paramString, null);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\c\a\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */