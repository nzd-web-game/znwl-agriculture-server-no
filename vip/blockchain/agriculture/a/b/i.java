package vip.blockchain.agriculture.a.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public final class i extends a {
  protected final List b() {
    List list = null;
    List<?> list1;
    if ((list1 = f()).size() > 0)
      list = (List)list1.stream().map(InetAddress::getHostAddress).distinct().map(String::toLowerCase).collect(Collectors.toList()); 
    return list;
  }
  
  protected final List c() {
    List list = null;
    List<?> list1;
    if ((list1 = f()).size() > 0)
      list = (List)list1.stream().map(this::a).distinct().collect(Collectors.toList()); 
    return list;
  }
  
  protected final String d() {
    String str1 = "";
    if (g())
      return str1 = h(); 
    String[] arrayOfString = { "/bin/bash", "-c", "dmidecode -t processor | grep 'ID' | awk -F ':' '{print $2}' | head -n 1" };
    Process process;
    (process = Runtime.getRuntime().exec(arrayOfString)).getOutputStream().close();
    BufferedReader bufferedReader;
    String str2;
    if (StringUtils.isNotBlank(str2 = (bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))).readLine().trim()))
      str1 = str2; 
    bufferedReader.close();
    return str1;
  }
  
  protected final String e() {
    String str1 = "";
    if (g())
      return str1 = h(); 
    String[] arrayOfString = { "/bin/bash", "-c", "dmidecode | grep 'Serial Number' | awk -F ':' '{print $2}' | head -n 1" };
    Process process;
    (process = Runtime.getRuntime().exec(arrayOfString)).getOutputStream().close();
    BufferedReader bufferedReader;
    String str2;
    if (StringUtils.isNotBlank(str2 = (bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))).readLine().trim()))
      str1 = str2; 
    bufferedReader.close();
    return str1;
  }
  
  private static boolean g() {
    boolean bool = false;
    String[] arrayOfString = { "/bin/bash", "-c", "test -e /dev/mem && echo 'false' || echo 'true'" };
    Process process;
    (process = Runtime.getRuntime().exec(arrayOfString)).getOutputStream().close();
    BufferedReader bufferedReader;
    String str;
    if (StringUtils.isNotBlank(str = (bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))).readLine().trim()))
      bool = Boolean.parseBoolean(str); 
    bufferedReader.close();
    return bool;
  }
  
  private static String h() {
    String str1 = "";
    String[] arrayOfString = { "/bin/bash", "-c", "cat /etc/machine-id" };
    Process process;
    (process = Runtime.getRuntime().exec(arrayOfString)).getOutputStream().close();
    BufferedReader bufferedReader;
    String str2;
    if (StringUtils.isNotBlank(str2 = (bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))).readLine().trim()))
      str1 = str2; 
    bufferedReader.close();
    return str1;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */