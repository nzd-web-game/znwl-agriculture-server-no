package vip.blockchain.agriculture.a.b;

import java.net.InetAddress;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class k extends a {
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
    String str = "";
    Process process;
    (process = Runtime.getRuntime().exec("wmic cpu get processorid")).getOutputStream().close();
    Scanner scanner;
    if ((scanner = new Scanner(process.getInputStream())).hasNext())
      scanner.next(); 
    if (scanner.hasNext())
      str = scanner.next().trim(); 
    scanner.close();
    return str;
  }
  
  protected final String e() {
    String str = "";
    Process process;
    (process = Runtime.getRuntime().exec("wmic csproduct get UUID")).getOutputStream().close();
    Scanner scanner;
    if ((scanner = new Scanner(process.getInputStream())).hasNext())
      scanner.next(); 
    if (scanner.hasNext())
      str = scanner.next().trim(); 
    scanner.close();
    return str;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */