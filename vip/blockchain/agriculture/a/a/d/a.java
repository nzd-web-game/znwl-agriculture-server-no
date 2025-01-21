package vip.blockchain.agriculture.a.a.d;

public final class a extends RuntimeException {
  private static final long a = 1L;
  
  private Integer b;
  
  private String c;
  
  public a(String paramString) {
    this.c = paramString;
  }
  
  private a(String paramString, Integer paramInteger) {
    this.c = paramString;
    this.b = paramInteger;
  }
  
  private a(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
    this.c = paramString;
  }
  
  public final String getMessage() {
    return this.c;
  }
  
  private Integer a() {
    return this.b;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\d\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */