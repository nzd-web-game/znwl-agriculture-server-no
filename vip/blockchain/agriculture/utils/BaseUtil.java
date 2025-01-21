package vip.blockchain.agriculture.utils;

import cn.hutool.core.util.IdUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class BaseUtil {
  public static String getSnowflakeId() {
    return IdUtil.getSnowflake(1L, 1L).nextIdStr();
  }
  
  public static String parseContractAddr(String paramString) {
    Matcher matcher;
    Pattern pattern;
    return StringUtils.isBlank(paramString) ? null : ((matcher = (pattern = Pattern.compile("\\[(\"[^\"]+\")\\]")).matcher(paramString)).find() ? matcher.group(1).replaceAll("^\"|\"$", "") : null);
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agricultur\\utils\BaseUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */