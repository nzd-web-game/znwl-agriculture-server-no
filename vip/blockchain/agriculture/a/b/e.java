package vip.blockchain.agriculture.a.b;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class e {
  private static final Logger a = LoggerFactory.getLogger(e.class);
  
  private static ObjectMapper b;
  
  private static String a(Object paramObject) {
    try {
      if (paramObject != null)
        return b.writeValueAsString(paramObject); 
    } catch (JsonProcessingException jsonProcessingException) {
      a.error("JsonUtil fromJson toJson error ,msg {}", (Throwable)jsonProcessingException);
    } 
    return "";
  }
  
  private static Object a(String paramString, Class paramClass) {
    try {
      return b.readValue(paramString, paramClass);
    } catch (JsonProcessingException jsonProcessingException) {
      a.error("JsonUtil fromJson clzs error ,msg {}", (Throwable)jsonProcessingException);
    } catch (IOException iOException) {
      (paramString = null).printStackTrace();
    } 
    return null;
  }
  
  private static Object a(String paramString, TypeReference paramTypeReference) {
    try {
      return b.readValue(paramString, paramTypeReference);
    } catch (JsonProcessingException jsonProcessingException) {
      a.error("JsonUtil fromJson valueTypeRef  error,msg {}", (Throwable)jsonProcessingException);
    } catch (IOException iOException) {
      (paramString = null).printStackTrace();
    } 
    return null;
  }
  
  static {
    (b = new ObjectMapper()).disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, new DeserializationFeature[] { DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES });
    b.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    b.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    b.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\b\e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */