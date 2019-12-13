package org.dasin.supply;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class Web3jApiTest extends BaseTest {

    @Autowired Web3j web3j;

    @Test
    public void getBlockNumber() throws IOException {
        BigInteger blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        assertTrue(blockNumber.compareTo(new BigInteger("0")) >= 0);
    }
}
