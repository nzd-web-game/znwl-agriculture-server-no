package vip.blockchain.agriculture.a.a.d;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.blockchain.agriculture.a.a.c.a.a;

@RestControllerAdvice
@Order(1)
public class b {
  private static final Logger a;
  
  @ExceptionHandler({a.class})
  private static a a(a parama) {
    return a.b(parama.getMessage());
  }
  
  static {
    LoggerFactory.getLogger(b.class);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\d\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */