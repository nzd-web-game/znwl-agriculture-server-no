package vip.blockchain.agriculture.a.a.e;

import cn.hutool.core.lang.Validator;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class c implements BeanFactoryPostProcessor, ApplicationContextAware {
  private static ConfigurableListableBeanFactory a;
  
  private static ApplicationContext b;
  
  public final void postProcessBeanFactory(ConfigurableListableBeanFactory paramConfigurableListableBeanFactory) {
    a = paramConfigurableListableBeanFactory;
  }
  
  public final void setApplicationContext(ApplicationContext paramApplicationContext) {
    b = paramApplicationContext;
  }
  
  private static Object a(String paramString) {
    return a.getBean(paramString);
  }
  
  public static Object a(Class paramClass) {
    Object object;
    return object = a.getBean(paramClass);
  }
  
  private static boolean b(String paramString) {
    return a.containsBean(paramString);
  }
  
  private static boolean c(String paramString) {
    return a.isSingleton(paramString);
  }
  
  private static Class d(String paramString) {
    return a.getType(paramString);
  }
  
  private static String[] e(String paramString) {
    return a.getAliases(paramString);
  }
  
  private static Object a() {
    return AopContext.currentProxy();
  }
  
  private static String[] b() {
    return b.getEnvironment().getActiveProfiles();
  }
  
  private static String c() {
    String[] arrayOfString;
    return Validator.isNotEmpty(arrayOfString = b.getEnvironment().getActiveProfiles()) ? arrayOfString[0] : null;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\e\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */