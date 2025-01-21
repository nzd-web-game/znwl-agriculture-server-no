package vip.blockchain.agriculture.service;

import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import vip.blockchain.agriculture.model.bo.CollectorAddDeviceInputBO;
import vip.blockchain.agriculture.model.bo.CollectorCheckInputBO;
import vip.blockchain.agriculture.model.bo.CollectorRemoveDeviceInputBO;
import vip.blockchain.agriculture.utils.IOUtil;

public class CollectorService {
  private static String a = IOUtil.readResourceAsString("abi/Collector.abi");
  
  private static String b;
  
  private String c;
  
  private Client d;
  
  private AssembleTransactionProcessor e;
  
  public CollectorService(Client paramClient, CryptoKeyPair paramCryptoKeyPair, String paramString) {
    this.c = paramString;
    this.d = paramClient;
    try {
      this.e = TransactionProcessorFactory.createAssembleTransactionProcessor(paramClient, paramCryptoKeyPair);
      return;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  public CallResponse check(CollectorCheckInputBO paramCollectorCheckInputBO) {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "check", paramCollectorCheckInputBO.toArgs());
  }
  
  public TransactionResponse addDevice(CollectorAddDeviceInputBO paramCollectorAddDeviceInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addDevice", paramCollectorAddDeviceInputBO.toArgs());
  }
  
  public TransactionResponse removeDevice(CollectorRemoveDeviceInputBO paramCollectorRemoveDeviceInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "removeDevice", paramCollectorRemoveDeviceInputBO.toArgs());
  }
  
  public String getAddress() {
    return this.c;
  }
  
  public Client getClient() {
    return this.d;
  }
  
  public AssembleTransactionProcessor getTxProcessor() {
    return this.e;
  }
  
  public void setAddress(String paramString) {
    this.c = paramString;
  }
  
  public void setClient(Client paramClient) {
    this.d = paramClient;
  }
  
  public void setTxProcessor(AssembleTransactionProcessor paramAssembleTransactionProcessor) {
    this.e = paramAssembleTransactionProcessor;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof CollectorService))
      return false; 
    paramObject = paramObject;
    CollectorService collectorService;
    if (!(collectorService = this instanceof CollectorService))
      return false; 
    String str1 = getAddress();
    String str2 = paramObject.getAddress();
    if ((str1 == null) ? (str2 != null) : !str1.equals(str2))
      return false; 
    Client client1 = getClient();
    Client client2 = paramObject.getClient();
    if ((client1 == null) ? (client2 != null) : !client1.equals(client2))
      return false; 
    AssembleTransactionProcessor assembleTransactionProcessor = getTxProcessor();
    paramObject = paramObject.getTxProcessor();
    return !((assembleTransactionProcessor == null) ? (paramObject != null) : !assembleTransactionProcessor.equals(paramObject));
  }
  
  private static boolean a(CollectorService paramCollectorService) {
    return paramCollectorService instanceof CollectorService;
  }
  
  public int hashCode() {
    String str = getAddress();
    int i = 59 + ((str == null) ? 43 : str.hashCode());
    Client client = getClient();
    i = i * 59 + ((client == null) ? 43 : client.hashCode());
    AssembleTransactionProcessor assembleTransactionProcessor = getTxProcessor();
    return i = i * 59 + ((assembleTransactionProcessor == null) ? 43 : assembleTransactionProcessor.hashCode());
  }
  
  public String toString() {
    return "CollectorService(address=" + getAddress() + ", client=" + getClient() + ", txProcessor=" + getTxProcessor() + ")";
  }
  
  static {
    IOUtil.readResourceAsString("abi/Collector.bin");
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\service\CollectorService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */