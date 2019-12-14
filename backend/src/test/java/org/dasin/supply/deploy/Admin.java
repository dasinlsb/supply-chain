package org.dasin.supply.deploy;

import org.dasin.supply.BaseTest;
import org.dasin.supply.constants.GasConstants;
import org.dasin.supply.contract.Supply;
import org.dasin.supply.model.Organization;
import org.dasin.supply.service.ChainService;
import org.fisco.bcos.channel.client.PEMManager;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;


public class Admin extends BaseTest {
    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;
    @Autowired private ChainService chainService;

    @Test
    public void deployAndCallHelloWorld() throws Exception {
        Supply supply = Supply.deploy(web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
        System.out.println(" deploy Asset success, contract address is " + supply.getContractAddress());
        Properties prop = new Properties();
        prop.setProperty("address", supply.getContractAddress());
        prop.setProperty("account", credentials.getAddress());
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "admin info");
    }

    @Test
    public void addOrg() throws Exception {
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load supply chain contract address failed, please deploy it first. ");
        }
        String account = prop.getProperty("account");
        if (account == null || account.trim().equals("")) {
            throw new Exception(" load supply chain contract account failed, please deploy it first. ");
        }
        PEMManager pem = new PEMManager();
        pem.setPemFile("classpath:" + account + ".pem");
        pem.load();
        ECKeyPair keyPair = pem.getECKeyPair();
        Credentials credentials = GenCredential.create(keyPair.getPrivateKey().toString(16));
        System.out.println(credentials.getAddress());
        Supply supply = Supply.load(contractAddress, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        Organization org = new Organization();
//        org.setOrgAddr("0x68572e0577310257fc1237105b2a76edd2509154");
//        org.setOrgId("2");
//        org.setOrgType("enterprise");
//        org.setIouLimit(100L);
//        org.setOrgAddr("0x953da8d59629a5cf1db5efcb3e2ad8608b55714e");
//        org.setOrgId("3");
//        org.setOrgType("enterprise");
//        org.setIouLimit(150L);
        org.setOrgAddr("0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2");
        org.setOrgId("4");
        org.setOrgType("enterprise");
        org.setIouLimit(200L);
        TransactionReceipt receipt = supply.addOrg(org.getOrgAddr(), org.getOrgId(), org.getOrgType(), BigInteger.valueOf(org.getIouLimit())).send();
        List<Supply.OrgCreationEventResponse> responses = supply.getOrgCreationEvents(receipt);
        if (responses.isEmpty()) {
            System.out.println("ERROR WHEN ADMIN IS CREATING ORGANIZATION");
        }
    }
}
