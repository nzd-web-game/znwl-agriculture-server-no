package vip.blockchain.agriculture.a.a.a;

import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public final class a {
  @Bean
  private static RestTemplate a(ClientHttpRequestFactory paramClientHttpRequestFactory) {
    return new RestTemplate(paramClientHttpRequestFactory);
  }
  
  @Bean
  private static ClientHttpRequestFactory b() {
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory;
    (httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory()).setConnectTimeout(15000);
    httpComponentsClientHttpRequestFactory.setReadTimeout(5000);
    return (ClientHttpRequestFactory)httpComponentsClientHttpRequestFactory;
  }
  
  public static HttpComponentsClientHttpRequestFactory a() {
    TrustStrategy trustStrategy = (paramArrayOfX509Certificate, paramString) -> true;
    SSLContext sSLContext = SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
    SSLConnectionSocketFactory sSLConnectionSocketFactory = new SSLConnectionSocketFactory(sSLContext, (HostnameVerifier)new NoopHostnameVerifier());
    HttpClientBuilder httpClientBuilder;
    (httpClientBuilder = HttpClients.custom()).setSSLSocketFactory((LayeredConnectionSocketFactory)sSLConnectionSocketFactory);
    CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory;
    (httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory()).setHttpClient((HttpClient)closeableHttpClient);
    return httpComponentsClientHttpRequestFactory;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof a))
      return false; 
    (a)paramObject;
    return !!(paramObject = this instanceof a);
  }
  
  private static boolean a(a parama) {
    return parama instanceof a;
  }
  
  public final int hashCode() {
    return 1;
  }
  
  public final String toString() {
    return "RestConfig()";
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\a\a\a\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */