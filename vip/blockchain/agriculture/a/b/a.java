package vip.blockchain.agriculture.a.b;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vip.blockchain.agriculture.model.dto.LicenseCheckModel;

public abstract class a {
  private static Logger a = LogManager.getLogger(a.class);
  
  public final LicenseCheckModel a() {
    LicenseCheckModel licenseCheckModel = new LicenseCheckModel();
    try {
      licenseCheckModel.setCpuSerial(d());
      licenseCheckModel.setMainBoardSerial(e());
    } catch (Exception exception) {
      a.error("获取服务器硬件信息失败", exception);
    } 
    return licenseCheckModel;
  }
  
  protected abstract List b();
  
  protected abstract List c();
  
  protected abstract String d();
  
  protected abstract String e();
  
  protected static List f() {
    ArrayList<InetAddress> arrayList = new ArrayList(4);
    Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
    while (enumeration.hasMoreElements()) {
      NetworkInterface networkInterface;
      Enumeration<InetAddress> enumeration1 = (networkInterface = enumeration.nextElement()).getInetAddresses();
      while (enumeration1.hasMoreElements()) {
        InetAddress inetAddress;
        if (!(inetAddress = enumeration1.nextElement()).isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && !inetAddress.isMulticastAddress())
          arrayList.add(inetAddress); 
      } 
    } 
    return arrayList;
  }
  
  protected String a(InetAddress paramInetAddress) {
    try {
      byte[] arrayOfByte = NetworkInterface.getByInetAddress(paramInetAddress).getHardwareAddress();
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b = 0; b < arrayOfByte.length; b++) {
        if (b != 0)
          stringBuffer.append("-"); 
        String str;
        if ((str = Integer.toHexString(arrayOfByte[b] & 0xFF)).length() == 1) {
          stringBuffer.append("0" + str);
        } else {
          stringBuffer.append(str);
        } 
      } 
      return stringBuffer.toString().toUpperCase();
    } catch (SocketException socketException) {
      (paramInetAddress = null).printStackTrace();
      return null;
    } 
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */