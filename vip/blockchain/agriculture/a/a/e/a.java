package vip.blockchain.agriculture.a.a.e;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public final class a {
  private static String a(String paramString, Object... paramVarArgs) {
    MessageSource messageSource;
    return (messageSource = (MessageSource)c.a(MessageSource.class)).getMessage(paramString, paramVarArgs, LocaleContextHolder.getLocale());
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\e\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */