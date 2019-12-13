package org.dasin.supply;

import org.dasin.supply.constants.GasConstants;
import org.dasin.supply.temp.HelloWorld;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class ContractTest extends BaseTest {

    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;

    @Test
    public void deployAndCallHelloWorld() throws Exception {
        // deploy contract
        HelloWorld helloWorld =
                HelloWorld.deploy(
                                web3j,
                                credentials,
                                new StaticGasProvider(
                                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT))
                        .send();
        if (helloWorld != null) {
            System.out.println("HelloWorld address is: " + helloWorld.getContractAddress());
            // call set function
            helloWorld.set("Hello, World!").send();
            // call get function
            String result = helloWorld.get().send();
            System.out.println(result);
            assertTrue("Hello, World!".equals(result));
        }
    }
}
