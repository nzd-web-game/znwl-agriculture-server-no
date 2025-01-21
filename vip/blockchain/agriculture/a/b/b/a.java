package vip.blockchain.agriculture.a.b.b;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public final class a implements WebMvcConfigurer {
  public final void addInterceptors(InterceptorRegistry paramInterceptorRegistry) {
    paramInterceptorRegistry.addInterceptor((HandlerInterceptor)new vip.blockchain.agriculture.a.b.a.a()).addPathPatterns(new String[] { "/**" }).excludePathPatterns(new String[] { "/api/license/getLicense", "/api/sendSMS", "/api/license/apply", "/api/install", "/getVerificationCode" });
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\b\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */