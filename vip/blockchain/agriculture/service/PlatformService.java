package vip.blockchain.agriculture.service;

import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import vip.blockchain.agriculture.model.bo.PlatformAddCollectorInputBO;
import vip.blockchain.agriculture.model.bo.PlatformAddGreenhouseInfoInputBO;
import vip.blockchain.agriculture.model.bo.PlatformAddPartitionsInputBO;
import vip.blockchain.agriculture.model.bo.PlatformCheckInputBO;
import vip.blockchain.agriculture.model.bo.PlatformOffHarvestInputBO;
import vip.blockchain.agriculture.model.bo.PlatformRemoveCollectorInputBO;
import vip.blockchain.agriculture.utils.IOUtil;

public class PlatformService {
  private static String a = IOUtil.readResourceAsString("abi/Platform.abi");
  
  private static String b;
  
  private String c;
  
  private Client d;
  
  private AssembleTransactionProcessor e;
  
  public PlatformService(String paramString, Client paramClient, CryptoKeyPair paramCryptoKeyPair) {
    this.c = paramString;
    this.d = paramClient;
    try {
      this.e = TransactionProcessorFactory.createAssembleTransactionProcessor(paramClient, paramCryptoKeyPair);
      return;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  public TransactionResponse addGreenhouseInfo(PlatformAddGreenhouseInfoInputBO paramPlatformAddGreenhouseInfoInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addGreenhouseInfo", paramPlatformAddGreenhouseInfoInputBO.toArgs());
  }
  
  public CallResponse check(PlatformCheckInputBO paramPlatformCheckInputBO) {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "check", paramPlatformCheckInputBO.toArgs());
  }
  
  public TransactionResponse removeCollector(PlatformRemoveCollectorInputBO paramPlatformRemoveCollectorInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "removeCollector", paramPlatformRemoveCollectorInputBO.toArgs());
  }
  
  public TransactionResponse addPartitions(PlatformAddPartitionsInputBO paramPlatformAddPartitionsInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addPartitions", paramPlatformAddPartitionsInputBO.toArgs());
  }
  
  public TransactionResponse addCollector(PlatformAddCollectorInputBO paramPlatformAddCollectorInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addCollector", paramPlatformAddCollectorInputBO.toArgs());
  }
  
  public TransactionResponse offHarvest(PlatformOffHarvestInputBO paramPlatformOffHarvestInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "offHarvest", paramPlatformOffHarvestInputBO.toArgs());
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
    if (!(paramObject instanceof PlatformService))
      return false; 
    paramObject = paramObject;
    PlatformService platformService;
    if (!(platformService = this instanceof PlatformService))
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
  
  private static boolean a(PlatformService paramPlatformService) {
    return paramPlatformService instanceof PlatformService;
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
    return "PlatformService(address=" + getAddress() + ", client=" + getClient() + ", txProcessor=" + getTxProcessor() + ")";
  }
  
  static {
    IOUtil.readResourceAsString("bin/Platform.bin");
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\service\PlatformService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */