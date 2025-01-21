package vip.blockchain.agriculture.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;

public class BarcodeUtil {
  public static String getBarcode(String paramString) {
    BitMatrix bitMatrix;
    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix = (new MultiFormatWriter()).encode(paramString, BarcodeFormat.CODE_128, 300, 150));
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    String str = Base64.getEncoder().encodeToString(arrayOfByte);
    return str = "data:image/png;base64," + str;
  }
  
  public static String getQRCode(String paramString) {
    BitMatrix bitMatrix;
    QRCodeWriter qRCodeWriter;
    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix = (qRCodeWriter = new QRCodeWriter()).encode(paramString, BarcodeFormat.QR_CODE, 300, 300));
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    String str = Base64.getEncoder().encodeToString(arrayOfByte);
    return str = "data:image/png;base64," + str;
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agricultur\\utils\BarcodeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */