package vip.blockchain.agriculture.utils;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeUtil {
  public static BigInteger LocalDateToBigInteger(LocalDate paramLocalDate) {
    long l;
    return BigInteger.valueOf(l = paramLocalDate.toEpochDay());
  }
  
  public static BigInteger LocalDateTimeToBigInteger(LocalDateTime paramLocalDateTime) {
    long l;
    return BigInteger.valueOf(l = paramLocalDateTime.toEpochSecond(ZoneOffset.UTC)).multiply(BigInteger.valueOf(1000L));
  }
  
  public static LocalDateTime timestampToLocalDateTime(String paramString) {
    Instant instant;
    long l1;
    long l2;
    return LocalDateTime.ofInstant(instant = Instant.ofEpochSecond(l2 = (l1 = Long.parseLong(paramString = paramString.substring(2), 16)) / 1000L), ZoneId.systemDefault());
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agricultur\\utils\TimeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */