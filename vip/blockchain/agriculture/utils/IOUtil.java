package vip.blockchain.agriculture.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
  private static final Logger a = LoggerFactory.getLogger(IOUtil.class);
  
  private static final int b = 2048;
  
  public static String readAsString(File paramFile) {
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    Throwable throwable = null;
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      copy(fileInputStream, byteArrayOutputStream);
      return new String(byteArrayOutputStream.toByteArray());
    } catch (Throwable throwable2) {
      Throwable throwable1 = null;
      throw throwable1;
    } finally {
      if (throwable != null) {
        try {
          fileInputStream.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
      } else {
        throwable1.close();
      } 
    } 
  }
  
  public static String readResourceAsString(String paramString) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    try {
      Throwable throwable;
      InputStream inputStream = classLoader.getResourceAsStream(paramString);
      classLoader = null;
      try {
        return readAsString(inputStream);
      } catch (Throwable throwable2) {
        Throwable throwable1 = null;
        throw throwable1;
      } finally {
        if (inputStream != null)
          if (throwable != null) {
            try {
              inputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            } 
          } else {
            throwable1.close();
          }  
      } 
    } catch (IOException iOException) {
      a.error("Error reading resource", iOException);
      return null;
    } 
  }
  
  public static String readAsString(InputStream paramInputStream) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, byteArrayOutputStream);
    paramInputStream.close();
    return new String(byteArrayOutputStream.toByteArray());
  }
  
  public static void writeString(File paramFile, String paramString) {
    null = new ByteArrayInputStream(paramString.getBytes());
    FileOutputStream fileOutputStream = new FileOutputStream(paramFile, false);
    ByteArrayInputStream byteArrayInputStream = null;
    try {
      copy(null, fileOutputStream);
      return;
    } catch (Throwable throwable) {
      byteArrayInputStream = null = null;
      throw null;
    } finally {
      if (byteArrayInputStream != null) {
        try {
          fileOutputStream.close();
        } catch (Throwable throwable) {
          byteArrayInputStream.addSuppressed(throwable);
        } 
      } else {
        throwable.close();
      } 
    } 
  }
  
  public static void copyFolder(File paramFile1, File paramFile2) {
    File[] arrayOfFile;
    int i = (arrayOfFile = paramFile1.listFiles()).length;
    for (byte b = 0; b < i; b++) {
      File file1 = arrayOfFile[b];
      File file2 = new File(paramFile2, file1.getName());
      if (!file1.isDirectory()) {
        copyFile(file1, file2);
      } else {
        if (!file2.mkdirs())
          throw new IOException("Dir " + file2.getAbsolutePath() + " create failed"); 
        copyFolder(file1, file2);
      } 
    } 
  }
  
  public static void copyFile(File paramFile1, File paramFile2) {
    FileInputStream fileInputStream = new FileInputStream(paramFile1);
    File file = null;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(paramFile2, false);
      Throwable throwable = null;
      try {
        copy(fileInputStream, fileOutputStream);
      } catch (Throwable throwable2) {
        Throwable throwable1 = null;
        throw throwable1;
      } finally {
        if (throwable != null) {
          try {
            fileOutputStream.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          } 
        } else {
          throwable1.close();
        } 
      } 
      return;
    } catch (Throwable throwable) {
      file = paramFile2 = null;
      throw paramFile2;
    } finally {
      if (file != null) {
        try {
          fileInputStream.close();
        } catch (Throwable throwable) {
          file.addSuppressed(throwable);
        } 
      } else {
        throwable.close();
      } 
    } 
  }
  
  public static void copy(InputStream paramInputStream, OutputStream paramOutputStream) {
    paramInputStream = new BufferedInputStream(paramInputStream);
    OutputStream outputStream = null;
    try {
      paramOutputStream = new BufferedOutputStream(paramOutputStream);
      Throwable throwable = null;
      try {
        byte[] arrayOfByte = new byte[2048];
        int i;
        while ((i = paramInputStream.read(arrayOfByte)) != -1)
          paramOutputStream.write(arrayOfByte, 0, i); 
        paramOutputStream.flush();
      } catch (Throwable throwable2) {
        Throwable throwable1 = null;
        throw throwable1;
      } finally {
        if (throwable != null) {
          try {
            paramOutputStream.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          } 
        } else {
          throwable1.close();
        } 
      } 
      return;
    } catch (Throwable throwable) {
      outputStream = paramOutputStream = null;
      throw paramOutputStream;
    } finally {
      if (outputStream != null) {
        try {
          paramInputStream.close();
        } catch (Throwable throwable) {
          outputStream.addSuppressed(throwable);
        } 
      } else {
        throwable.close();
      } 
    } 
  }
  
  public static void removeItem(File paramFile) {
    if (!paramFile.isDirectory()) {
      paramFile.delete();
      return;
    } 
    File[] arrayOfFile;
    int i = (arrayOfFile = paramFile.listFiles()).length;
    for (byte b = 0; b < i; b++) {
      File file;
      removeItem(file = arrayOfFile[b]);
    } 
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agricultur\\utils\IOUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */