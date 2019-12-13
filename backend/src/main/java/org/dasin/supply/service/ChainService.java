package org.dasin.supply.service;

import org.dasin.supply.constants.GasConstants;
import org.dasin.supply.contract.Asset;
import org.dasin.supply.contract.Supply;
import org.dasin.supply.model.Iou;
import org.dasin.supply.model.Organization;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int64;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class ChainService {
    static Logger logger = LoggerFactory.getLogger(ChainService.class);


    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;

    private static List<String> contractAddrList = new ArrayList<>();

    public void deployAndSaveAddr() throws Exception {
        Resource contractResource = new ClassPathResource("contract.properties");
        Properties prop = new Properties();
        prop.load(contractResource.getInputStream());
        String address = prop.getProperty("address");
        if (address == null || address.trim().equals("")) {
            Supply supply = Supply.deploy(web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            address = supply.getContractAddress();
            prop.setProperty("address", address);
            FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
            prop.store(fileOutputStream, "supply-chain contract address");
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    public String loadSupplyAddr() throws Exception {
        // load Supply contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Supply Chain contract address failed, please deploy it first. ");
        }
        logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
        return contractAddress;
    }

    public Supply loadSupplyContract() throws Exception {
        String contractAddress = loadSupplyAddr();
        return Supply.load(contractAddress, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
    }

    public void initAllService() throws Exception {
        Assert.assertNotNull(web3j);
        Assert.assertNotNull(credentials);
        deployAndSaveAddr();
        {
            Organization org = new Organization();
            org.setOrgAddr("0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2");
            org.setOrgId("1");
            org.setOrgType("enterprise");
            org.setIouLimit(200L);
            addOrg(org);
            org.setOrgAddr("0x953da8d59629a5cf1db5efcb3e2ad8608b55714e");
            org.setOrgId("2");
            org.setOrgType("enterprise");
            org.setIouLimit(150L);
            addOrg(org);
            org.setOrgAddr("0x68572e0577310257fc1237105b2a76edd2509154");
            org.setOrgId("3");
            org.setOrgType("enterprise");
            org.setIouLimit(150L);
            addOrg(org);

            String assetAddr = "0xdc30f75b2e16d5ccfacb493db741e124220e45ee";
            Asset asset = Asset.load(assetAddr, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

            System.out.println("Asset address is: " + asset.getContractAddress());
            Tuple2<BigInteger, BigInteger> result = asset.select("Alice").send();
            System.out.printf("found %s, value %s\n", "alice", result.getValue2());
        }
        logger.info("initialization of chain ok");
    }

    public String getAddress() {
        return credentials.getAddress();
    }

    public void addOrg(Organization org) throws Exception {
        Supply supply = loadSupplyContract();
        System.out.println("add org addr: "+org.getOrgAddr()+", "+BigInteger.valueOf(org.getIouLimit()));
        TransactionReceipt receipt = supply.addOrg(org.getOrgAddr(), org.getOrgId(), org.getOrgType(), BigInteger.valueOf(org.getIouLimit())).send();
        List<Supply.OrgCreationEventResponse> responses = supply.getOrgCreationEvents(receipt);
        if (responses.isEmpty()) {
            System.out.println("add org transaction error");
        }
    }

    public Organization getOrgInfo(String address) throws Exception {
        Supply supply = loadSupplyContract();
        Tuple3<String, String, BigInteger> res = supply.getOrgInfo(address).send();
        Organization org = new Organization();
        org.setOrgAddr(getAddress());
        org.setOrgId(res.getValue1());
        org.setOrgType(res.getValue2());
        org.setIouLimit(Long.parseLong(res.getValue3().toString()));
        return org;
    }

    public List<Organization> getAllOrgInfo() throws Exception {
        Supply supply = loadSupplyContract();
        List addrs = supply.getOrgAddrs(BigInteger.valueOf(10L)).send();
        List<Organization> orgs = new ArrayList<>();
        for (Object addr : addrs) {
            Organization org = getOrgInfo(addr.toString());
            orgs.add(org);
        }
        return orgs;
    }

    public List<Iou> getAllIouFrom(String address) throws Exception {
        Supply supply = loadSupplyContract();
        List iouIds = supply.getOrgOutIous(address).send();
        List<Iou> ious = new ArrayList<>();
        for (Object _iouId : iouIds) {
            long id = Long.parseLong(_iouId.toString());
            Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, String> res = supply.getIouInfo(BigInteger.valueOf(id)).send();
            Iou iou = new Iou();
            iou.setIouId(id);
            iou.setFromOrgAddr(res.getValue2());
            iou.setToOrgAddr(res.getValue3());
            iou.setCreateTime(res.getValue4());
            iou.setAmount(res.getValue5().longValue());
            iou.setRemain(res.getValue6().longValue());
            iou.setDue(res.getValue7());
            ious.add(iou);
        }
        return ious;
    }

    public void addIou(Iou iou) throws Exception {
        Supply supply = loadSupplyContract();
        String addr = getAddress();
        iou.setFromOrgAddr(addr);
        TransactionReceipt receipt = supply.addIou(iou.getFromOrgAddr(), iou.getToOrgAddr(),
                iou.getCreateTime(), BigInteger.valueOf(iou.getAmount()),
                iou.getDue()).send();
        List<Supply.IouCreationEventResponse> response = supply.getIouCreationEvents(receipt);
        if (!response.isEmpty()) {
            System.out.println("add iou event status: "+response.get(0).iouId);
        } else {
            System.out.println("transaction error");
        }
        List iouIds = supply.getOrgOutIous(addr).send();
        System.out.println("after add one iou, the outIous count: "+iouIds.size());
    }

    public void transIou(Iou iou) throws Exception {
        Supply supply = loadSupplyContract();
        String addr = getAddress();
        supply.splitIou(BigInteger.valueOf(iou.getIouId()), BigInteger.valueOf(iou.getAmount()), iou.getCreateTime()).send();
        supply.transferIouFrom(BigInteger.valueOf(iou.getIouId()), iou.getFromOrgAddr()).send();
    }

    public void payIou(Iou iou) throws Exception {
        Supply supply = loadSupplyContract();
        String addr = getAddress();
        supply.payIou(BigInteger.valueOf(iou.getIouId()), BigInteger.valueOf(iou.getAmount())).send();
    }
}
