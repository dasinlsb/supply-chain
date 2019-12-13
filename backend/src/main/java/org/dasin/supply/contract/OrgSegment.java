package org.dasin.supply.contract;

import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class OrgSegment extends Contract {
    public static String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034801561005057600080fd5b50610848806100606000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632618811a146100675780638da5cb5b1461011657806398c9abfd1461016d578063f4d6d9f51461021c575b600080fd5b34801561007357600080fd5b50610114600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061036a565b005b34801561012257600080fd5b5061012b61044e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561017957600080fd5b5061021a600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610473565b005b34801561022857600080fd5b50610283600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610557565b604051808060200180602001838103835285818151815260200191508051906020019080838360005b838110156102c75780820151818401526020810190506102ac565b50505050905090810190601f1680156102f45780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561032d578082015181840152602081019050610312565b50505050905090810190601f16801561035a5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156103c657600080fd5b816001846040518082805190602001908083835b6020831015156103ff57805182526020820191506020810190506020830392506103da565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000019080519060200190610448929190610777565b50505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff168073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156104cf57600080fd5b816001846040518082805190602001908083835b60208310151561050857805182526020820191506020810190506020830392506104e3565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206001019080519060200190610551929190610777565b50505050565b6060806001836040518082805190602001908083835b602083101515610592578051825260208201915060208101905060208303925061056d565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000016001846040518082805190602001908083835b60208310151561060057805182526020820191506020810190506020830392506105db565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600101818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106cb5780601f106106a0576101008083540402835291602001916106cb565b820191906000526020600020905b8154815290600101906020018083116106ae57829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107675780601f1061073c57610100808354040283529160200191610767565b820191906000526020600020905b81548152906001019060200180831161074a57829003601f168201915b5050505050905091509150915091565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107b857805160ff19168380011785556107e6565b828001600101855582156107e6579182015b828111156107e55782518255916020019190600101906107ca565b5b5090506107f391906107f7565b5090565b61081991905b808211156108155760008160009055506001016107fd565b5090565b905600a165627a7a72305820953e0f33b885224f3a2fef3ae426bb6f047bbbe9540d45987187e75c5414f97b0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"orgId\",\"type\":\"string\"}],\"name\":\"setOrgId\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"orgType\",\"type\":\"string\"}],\"name\":\"setOrgType\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"string\"}],\"name\":\"getOrgDetails\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]";

    public static final String FUNC_SETORGID = "setOrgId";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETORGTYPE = "setOrgType";

    public static final String FUNC_GETORGDETAILS = "getOrgDetails";

    @Deprecated
    protected OrgSegment(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected OrgSegment(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected OrgSegment(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected OrgSegment(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setOrgId(String addr, String orgId) {
        final Function function = new Function(
                FUNC_SETORGID, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setOrgId(String addr, String orgId, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETORGID,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgId)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setOrgIdSeq(String addr, String orgId) {
        final Function function = new Function(
                FUNC_SETORGID,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgId)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setOrgType(String addr, String orgType) {
        final Function function = new Function(
                FUNC_SETORGTYPE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgType)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setOrgType(String addr, String orgType, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETORGTYPE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgType)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setOrgTypeSeq(String addr, String orgType) {
        final Function function = new Function(
                FUNC_SETORGTYPE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgType)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple2<String, String>> getOrgDetails(String addr) {
        final Function function = new Function(FUNC_GETORGDETAILS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<String, String>>(
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    @Deprecated
    public static OrgSegment load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new OrgSegment(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static OrgSegment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new OrgSegment(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static OrgSegment load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OrgSegment(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OrgSegment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OrgSegment(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<OrgSegment> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OrgSegment.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OrgSegment> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OrgSegment.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<OrgSegment> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OrgSegment.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OrgSegment> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OrgSegment.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
