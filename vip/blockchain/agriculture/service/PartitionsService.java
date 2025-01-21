package vip.blockchain.agriculture.service;

import java.util.Arrays;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import vip.blockchain.agriculture.model.bo.PartitionsAddCollectorValueInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsAddFertilizerInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsAddFoodInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsCheckFoodInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsGetCollectorValueInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsGetFertilizerInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsGetFoodInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsModifyFertilizerInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsModifyFoodInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsModifyOfGreenhouseInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsModifyPartitionsInfoInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsModifyoffHarvestInputBO;
import vip.blockchain.agriculture.model.bo.PartitionsRemoverFoodInputBO;
import vip.blockchain.agriculture.utils.IOUtil;

public class PartitionsService {
  private static String a = IOUtil.readResourceAsString("abi/Partitions.abi");
  
  private static String b;
  
  private String c;
  
  private Client d;
  
  private AssembleTransactionProcessor e;
  
  public PartitionsService(Client paramClient, CryptoKeyPair paramCryptoKeyPair, String paramString) {
    this.c = paramString;
    this.d = paramClient;
    try {
      this.e = TransactionProcessorFactory.createAssembleTransactionProcessor(paramClient, paramCryptoKeyPair);
      return;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  public CallResponse checkFood(PartitionsCheckFoodInputBO paramPartitionsCheckFoodInputBO) {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "checkFood", paramPartitionsCheckFoodInputBO.toArgs());
  }
  
  public TransactionResponse modifyOfGreenhouse(PartitionsModifyOfGreenhouseInputBO paramPartitionsModifyOfGreenhouseInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "modifyOfGreenhouse", paramPartitionsModifyOfGreenhouseInputBO.toArgs());
  }
  
  public TransactionResponse removerFood(PartitionsRemoverFoodInputBO paramPartitionsRemoverFoodInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "removerFood", paramPartitionsRemoverFoodInputBO.toArgs());
  }
  
  public TransactionResponse modifyPartitionsInfo(PartitionsModifyPartitionsInfoInputBO paramPartitionsModifyPartitionsInfoInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "modifyPartitionsInfo", paramPartitionsModifyPartitionsInfoInputBO.toArgs());
  }
  
  public CallResponse getFertilizer(PartitionsGetFertilizerInputBO paramPartitionsGetFertilizerInputBO) {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getFertilizer", paramPartitionsGetFertilizerInputBO.toArgs());
  }
  
  public TransactionResponse addFood(PartitionsAddFoodInputBO paramPartitionsAddFoodInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addFood", paramPartitionsAddFoodInputBO.toArgs());
  }
  
  public TransactionResponse modifyoffHarvest(PartitionsModifyoffHarvestInputBO paramPartitionsModifyoffHarvestInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "modifyoffHarvest", paramPartitionsModifyoffHarvestInputBO.toArgs());
  }
  
  public TransactionResponse addCollectorValue(PartitionsAddCollectorValueInputBO paramPartitionsAddCollectorValueInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addCollectorValue", paramPartitionsAddCollectorValueInputBO.toArgs());
  }
  
  public CallResponse getFertilizers() {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getFertilizers", Arrays.asList(new Object[0]));
  }
  
  public TransactionResponse modifyFertilizer(PartitionsModifyFertilizerInputBO paramPartitionsModifyFertilizerInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "modifyFertilizer", paramPartitionsModifyFertilizerInputBO.toArgs());
  }
  
  public TransactionResponse addFertilizer(PartitionsAddFertilizerInputBO paramPartitionsAddFertilizerInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "addFertilizer", paramPartitionsAddFertilizerInputBO.toArgs());
  }
  
  public CallResponse getCollectorValues() {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getCollectorValues", Arrays.asList(new Object[0]));
  }
  
  public CallResponse getCollectorValue(PartitionsGetCollectorValueInputBO paramPartitionsGetCollectorValueInputBO) {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getCollectorValue", paramPartitionsGetCollectorValueInputBO.toArgs());
  }
  
  public CallResponse getPartitions() {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getPartitions", Arrays.asList(new Object[0]));
  }
  
  public CallResponse getFood(PartitionsGetFoodInputBO paramPartitionsGetFoodInputBO) {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getFood", paramPartitionsGetFoodInputBO.toArgs());
  }
  
  public TransactionResponse completeProcessing() {
    return this.e.sendTransactionAndGetResponse(this.c, a, "completeProcessing", Arrays.asList(new Object[0]));
  }
  
  public CallResponse getofGreenhouse() {
    return this.e.sendCall(this.d.getCryptoSuite().getCryptoKeyPair().getAddress(), this.c, a, "getofGreenhouse", Arrays.asList(new Object[0]));
  }
  
  public TransactionResponse modifyFood(PartitionsModifyFoodInputBO paramPartitionsModifyFoodInputBO) {
    return this.e.sendTransactionAndGetResponse(this.c, a, "modifyFood", paramPartitionsModifyFoodInputBO.toArgs());
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
    if (!(paramObject instanceof PartitionsService))
      return false; 
    paramObject = paramObject;
    PartitionsService partitionsService;
    if (!(partitionsService = this instanceof PartitionsService))
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
  
  private static boolean a(PartitionsService paramPartitionsService) {
    return paramPartitionsService instanceof PartitionsService;
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
    return "PartitionsService(address=" + getAddress() + ", client=" + getClient() + ", txProcessor=" + getTxProcessor() + ")";
  }
  
  static {
    IOUtil.readResourceAsString("bin/Partitions.bin");
  }
}


/* Location:              D:\apache-maven-3.9.2\Repository\vip\blockchain\agriculture\resources\1.0.0-SNAPSHOT\resources-1.0.0-SNAPSHOT.jar!\vip\blockchain\agriculture\service\PartitionsService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */