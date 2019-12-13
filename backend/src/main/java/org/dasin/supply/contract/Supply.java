package org.dasin.supply.contract;

import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.*;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int64;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
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
public class Supply extends Contract {
    public static String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034801561005057600080fd5b506000600460006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555061262f8061008d6000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631a7c94f8146100ca57806333e3c2ea146101a65780638da5cb5b1461023e5780639693413014610295578063a902bcfc146102f0578063ad899a9114610340578063b875fa031461037d578063c9295bc6146103cd578063d69497f714610502578063d9609392146106a4578063e1403db914610727578063f014ca44146107a9578063f320e97614610841575b600080fd5b3480156100d657600080fd5b506101a4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803560070b9060200190929190505050610957565b005b3480156101b257600080fd5b506101e7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d11565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561022a57808201518184015260208101905061020f565b505050509050019250505060405180910390f35b34801561024a57600080fd5b50610253610de5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102a157600080fd5b506102d6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e0a565b604051808215151515815260200191505060405180910390f35b3480156102fc57600080fd5b5061033e600480360381019080803560070b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e63565b005b34801561034c57600080fd5b5061037b600480360381019080803560070b9060200190929190803560070b9060200190929190505050610f9f565b005b34801561038957600080fd5b506103cb600480360381019080803560070b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506111ac565b005b3480156103d957600080fd5b5061040e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506112e8565b6040518080602001806020018460070b60070b8152602001838103835286818151815260200191508051906020019080838360005b8381101561045e578082015181840152602081019050610443565b50505050905090810190601f16801561048b5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156104c45780820151818401526020810190506104a9565b50505050905090810190601f1680156104f15780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561050e57600080fd5b50610530600480360381019080803560070b906020019092919050505061151a565b604051808860070b60070b81526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018560070b60070b81526020018460070b60070b815260200180602001838103835287818151815260200191508051906020019080838360005b838110156105fc5780820151818401526020810190506105e1565b50505050905090810190601f1680156106295780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b83811015610662578082015181840152602081019050610647565b50505050905090810190601f16801561068f5780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b3480156106b057600080fd5b50610725600480360381019080803560070b9060200190929190803560070b9060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061181a565b005b34801561073357600080fd5b5061075260048036038101908080359060200190929190505050611b95565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561079557808201518184015260208101905061077a565b505050509050019250505060405180910390f35b3480156107b557600080fd5b506107ea600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611c9a565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561082d578082015181840152602081019050610812565b505050509050019250505060405180910390f35b34801561084d57600080fd5b5061093b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803560070b9060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611d6e565b604051808260070b60070b815260200191505060405180910390f35b60608061096261239c565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156109bd57600080fd5b6109c687610e0a565b1515156109d257600080fd5b60e0604051908101604052806001151581526020018873ffffffffffffffffffffffffffffffffffffffff1681526020018781526020018681526020018560070b815260200184815260200183815250905080600160008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff02191690831515021790555060208201518160000160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816001019080519060200190610ae79291906123f5565b506060820151816002019080519060200190610b049291906123f5565b5060808201518160030160006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060a0820151816004019080519060200190610b53929190612475565b5060c0820151816005019080519060200190610b70929190612475565b5090505060028790806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550508673ffffffffffffffffffffffffffffffffffffffff167fcd0b7613b83c7fefe51b446544ce4181d492592b217f7313caeb1b2a0835970d8787876040518080602001806020018460070b60070b8152602001838103835286818151815260200191508051906020019080838360005b83811015610c65578082015181840152602081019050610c4a565b50505050905090810190601f168015610c925780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b83811015610ccb578082015181840152602081019050610cb0565b50505050905090810190601f168015610cf85780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a250505050505050565b6060610d1c82610e0a565b1515610d2757600080fd5b600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401805480602002602001604051908101604052809291908181526020018280548015610dd957602002820191906000526020600020906000905b82829054906101000a900460070b60070b81526020019060080190602082600701049283019260010382029150808411610da25790505b50505050509050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff169050919050565b3373ffffffffffffffffffffffffffffffffffffffff16600360008460070b60070b815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610ed957600080fd5b80600360008460070b60070b815260200190815260200160002060010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160070b7ffd476520dbc98378ad85f2494371a82f9f6263264589efd37f91d9c17203ef6782604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a25050565b600360008360070b60070b815260200190815260200160002060000160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561101557600080fd5b8060070b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b60070b1215151561107957600080fd5b8060070b600360008460070b60070b815260200190815260200160002060030160089054906101000a900460070b60070b121515156110b757600080fd5b80600360008460070b60070b815260200190815260200160002060030160088282829054906101000a900460070b0392506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff1602179055506000600360008460070b60070b815260200190815260200160002060030160089054906101000a90505050600360008360070b60070b815260200190815260200160002060030160089054906101000a900460070b60070b8260070b7fdb6466df27da84ba28cb558c3bff32450d8c951c5320395d678fc12fe211788983604051808260070b60070b815260200191505060405180910390a35050565b3373ffffffffffffffffffffffffffffffffffffffff16600360008460070b60070b815260200190815260200160002060000160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561122257600080fd5b80600360008460070b60070b815260200190815260200160002060000160086101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160070b7f2012565039935dcec92a3d7fca9b77bd82c37fd401c831c92d7c282937641bfa82604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a25050565b60608060006112f684610e0a565b151561130157600080fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b828054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561146a5780601f1061143f5761010080835404028352916020019161146a565b820191906000526020600020905b81548152906001019060200180831161144d57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115065780601f106114db57610100808354040283529160200191611506565b820191906000526020600020905b8154815290600101906020018083116114e957829003601f168201915b505050505091509250925092509193909250565b60008060006060600080606061152e612534565b6000600360008b60070b60070b815260200190815260200160002060000160009054906101000a900460070b60070b13151561156957600080fd5b600360008a60070b60070b815260200190815260200160002060e060405190810160405290816000820160009054906101000a900460070b60070b60070b81526020016000820160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116f05780601f106116c5576101008083540402835291602001916116f0565b820191906000526020600020905b8154815290600101906020018083116116d357829003601f168201915b505050505081526020016003820160009054906101000a900460070b60070b60070b81526020016003820160089054906101000a900460070b60070b60070b8152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117cc5780601f106117a1576101008083540402835291602001916117cc565b820191906000526020600020905b8154815290600101906020018083116117af57829003601f168201915b5050505050815250509050806000015181602001518260400151836060015184608001518560a001518660c00151839350809050975097509750975097509750975050919395979092949650565b611822612534565b8260070b600360008660070b60070b815260200190815260200160002060030160089054906101000a900460070b60070b138015611863575060008360070b135b151561186e57600080fd5b600360008560070b60070b815260200190815260200160002060e060405190810160405290816000820160009054906101000a900460070b60070b60070b81526020016000820160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156119f55780601f106119ca576101008083540402835291602001916119f5565b820191906000526020600020905b8154815290600101906020018083116119d857829003601f168201915b505050505081526020016003820160009054906101000a900460070b60070b60070b81526020016003820160089054906101000a900460070b60070b60070b8152602001600482018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015611ad15780601f10611aa657610100808354040283529160200191611ad1565b820191906000526020600020905b815481529060010190602001808311611ab457829003601f168201915b5050505050815250509050611af58160200151826040015184868560c00151611d6e565b5082600360008660070b60070b815260200190815260200160002060030160088282829054906101000a900460070b0392506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff1602179055508360070b7f187939e6da73557a4e32a57e40521674155f9fd3d2224a0e6d11bb61e649dbc184604051808260070b60070b815260200191505060405180910390a250505050565b606080600083600011151515611baa57600080fd5b836002805490501015611bc05760028054905093505b83604051908082528060200260200182016040528015611bef5781602001602082028038833980820191505090505b509150600090505b83811015611c9057600281815481101515611c0e57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168282815181101515611c4757fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508080600101915050611bf7565b8192505050919050565b6060611ca582610e0a565b1515611cb057600080fd5b600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501805480602002602001604051908101604052809291908181526020018280548015611d6257602002820191906000526020600020906000905b82829054906101000a900460070b60070b81526020019060080190602082600701049283019260010382029150808411611d2b5790505b50505050509050919050565b6000611d7986610e0a565b1515611d8457600080fd5b611d8d85610e0a565b1515611d9857600080fd5b8573ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611dd257600080fd5b8260070b6000121515611de457600080fd5b8260070b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b60070b12151515611e4857600080fd5b6001600460008282829054906101000a900460070b0192506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060e060405190810160405280600460009054906101000a900460070b60070b81526020018773ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018460070b81526020018460070b81526020018381525060036000600460009054906101000a900460070b60070b60070b815260200190815260200160002060008201518160000160006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060208201518160000160086101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160020190805190602001906120039291906123f5565b5060808201518160030160006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060a08201518160030160086101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060c08201518160040190805190602001906120849291906123f5565b5090505082600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160008282829054906101000a900460070b0392506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff160217905550600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401600460009054906101000a900460070b908060018154018082558091505090600182039060005260206000209060049182820401919006600802909192909190916101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555050600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501600460009054906101000a900460070b908060018154018082558091505090600182039060005260206000209060049182820401919006600802909192909190916101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555050600460009054906101000a900460070b60070b7f61d839ccc49071261e6c661dcbe12086f368009c895694a8ecb54a560726215f87878686604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018360070b60070b815260200180602001828103825283818151815260200191508051906020019080838360005b83811015612344578082015181840152602081019050612329565b50505050905090810190601f1680156123715780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a2600460009054906101000a900460070b905095945050505050565b60e060405190810160405280600015158152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016060815260200160608152602001600060070b815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061243657805160ff1916838001178555612464565b82800160010185558215612464579182015b82811115612463578251825591602001919060010190612448565b5b50905061247191906125a7565b5090565b828054828255906000526020600020906003016004900481019282156125235791602002820160005b838211156124ed57835183826101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff160217905550926020019260080160208160070104928301926001030261249e565b80156125215782816101000a81549067ffffffffffffffff02191690556008016020816007010492830192600103026124ed565b505b50905061253091906125cc565b5090565b60e060405190810160405280600060070b8152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff16815260200160608152602001600060070b8152602001600060070b8152602001606081525090565b6125c991905b808211156125c55760008160009055506001016125ad565b5090565b90565b61260091905b808211156125fc57600081816101000a81549067ffffffffffffffff0219169055506001016125d2565b5090565b905600a165627a7a723058202f0cfb5c4d190790026486e3ff72649180d10b1803f31c5f29865a3d5b2781d60029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"orgId\",\"type\":\"string\"},{\"name\":\"orgType\",\"type\":\"string\"},{\"name\":\"iouLimit\",\"type\":\"int64\"}],\"name\":\"addOrg\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getOrgOutIous\",\"outputs\":[{\"name\":\"\",\"type\":\"int64[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"isValidAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"toOrgAddr\",\"type\":\"address\"}],\"name\":\"transferIouTo\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"value\",\"type\":\"int64\"}],\"name\":\"payIou\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"fromOrgAddr\",\"type\":\"address\"}],\"name\":\"transferIouFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getOrgInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int64\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"}],\"name\":\"getIouInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"int64\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int64\"},{\"name\":\"\",\"type\":\"int64\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"value1\",\"type\":\"int64\"},{\"name\":\"createTime\",\"type\":\"string\"}],\"name\":\"splitIou\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"count\",\"type\":\"uint256\"}],\"name\":\"getOrgAddrs\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getOrgInIous\",\"outputs\":[{\"name\":\"\",\"type\":\"int64[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"fromAddr\",\"type\":\"address\"},{\"name\":\"toAddr\",\"type\":\"address\"},{\"name\":\"createTime\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"int64\"},{\"name\":\"due\",\"type\":\"string\"}],\"name\":\"addIou\",\"outputs\":[{\"name\":\"\",\"type\":\"int64\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"addr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"orgId\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"orgType\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"iouLimit\",\"type\":\"int64\"}],\"name\":\"OrgCreation\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"fromAddr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAddr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"due\",\"type\":\"string\"}],\"name\":\"IouCreation\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"int64\"},{\"indexed\":true,\"name\":\"remain\",\"type\":\"int64\"}],\"name\":\"IouPayback\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"value1\",\"type\":\"int64\"}],\"name\":\"IouSplit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"toAddr\",\"type\":\"address\"}],\"name\":\"IouTransferTo\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"fromAddr\",\"type\":\"address\"}],\"name\":\"IouTransferFrom\",\"type\":\"event\"}]";

    public static final String FUNC_ADDORG = "addOrg";

    public static final String FUNC_GETORGOUTIOUS = "getOrgOutIous";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ISVALIDADDR = "isValidAddr";

    public static final String FUNC_TRANSFERIOUTO = "transferIouTo";

    public static final String FUNC_PAYIOU = "payIou";

    public static final String FUNC_TRANSFERIOUFROM = "transferIouFrom";

    public static final String FUNC_GETORGINFO = "getOrgInfo";

    public static final String FUNC_GETIOUINFO = "getIouInfo";

    public static final String FUNC_SPLITIOU = "splitIou";

    public static final String FUNC_GETORGADDRS = "getOrgAddrs";

    public static final String FUNC_GETORGINIOUS = "getOrgInIous";

    public static final String FUNC_ADDIOU = "addIou";

    public static final Event ORGCREATION_EVENT = new Event("OrgCreation",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int64>() {}));
    ;

    public static final Event IOUCREATION_EVENT = new Event("IouCreation",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int64>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Int64>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event IOUPAYBACK_EVENT = new Event("IouPayback",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int64>(true) {}, new TypeReference<Int64>() {}, new TypeReference<Int64>(true) {}));
    ;

    public static final Event IOUSPLIT_EVENT = new Event("IouSplit",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int64>(true) {}, new TypeReference<Int64>() {}));
    ;

    public static final Event IOUTRANSFERTO_EVENT = new Event("IouTransferTo",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int64>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event IOUTRANSFERFROM_EVENT = new Event("IouTransferFrom",
            Arrays.<TypeReference<?>>asList(new TypeReference<Int64>(true) {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Supply(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Supply(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Supply(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Supply(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> addOrg(String addr, String orgId, String orgType, BigInteger iouLimit) {
        final Function function = new Function(
                FUNC_ADDORG, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgId),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgType),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouLimit)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addOrg(String addr, String orgId, String orgType, BigInteger iouLimit, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDORG,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgId),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgType),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouLimit)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addOrgSeq(String addr, String orgId, String orgType, BigInteger iouLimit) {
        final Function function = new Function(
                FUNC_ADDORG,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgId),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(orgType),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouLimit)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getOrgOutIous(String addr) {
        final Function function = new Function(FUNC_GETORGOUTIOUS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Int64>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isValidAddr(String addr) {
        final Function function = new Function(FUNC_ISVALIDADDR,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> transferIouTo(BigInteger iouId, String toOrgAddr) {
        final Function function = new Function(
                FUNC_TRANSFERIOUTO,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.Address(toOrgAddr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferIouTo(BigInteger iouId, String toOrgAddr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERIOUTO,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.Address(toOrgAddr)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferIouToSeq(BigInteger iouId, String toOrgAddr) {
        final Function function = new Function(
                FUNC_TRANSFERIOUTO,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.Address(toOrgAddr)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> payIou(BigInteger iouId, BigInteger value) {
        final Function function = new Function(
                FUNC_PAYIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void payIou(BigInteger iouId, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PAYIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(value)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String payIouSeq(BigInteger iouId, BigInteger value) {
        final Function function = new Function(
                FUNC_PAYIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(value)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> transferIouFrom(BigInteger iouId, String fromOrgAddr) {
        final Function function = new Function(
                FUNC_TRANSFERIOUFROM,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.Address(fromOrgAddr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferIouFrom(BigInteger iouId, String fromOrgAddr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERIOUFROM,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.Address(fromOrgAddr)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferIouFromSeq(BigInteger iouId, String fromOrgAddr) {
        final Function function = new Function(
                FUNC_TRANSFERIOUFROM,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.Address(fromOrgAddr)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> getOrgInfo(String addr) {
        final Function function = new Function(FUNC_GETORGINFO,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int64>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, String>> getIouInfo(BigInteger iouId) {
        final Function function = new Function(FUNC_GETIOUINFO,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int64>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int64>() {}, new TypeReference<Int64>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, String>>(
                new Callable<Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue(),
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> splitIou(BigInteger iouId, BigInteger value1, String createTime) {
        final Function function = new Function(
                FUNC_SPLITIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(value1),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(createTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void splitIou(BigInteger iouId, BigInteger value1, String createTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SPLITIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(value1),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(createTime)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String splitIouSeq(BigInteger iouId, BigInteger value1, String createTime) {
        final Function function = new Function(
                FUNC_SPLITIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(iouId),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(value1),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(createTime)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<List> getOrgAddrs(BigInteger count) {
        final Function function = new Function(FUNC_GETORGADDRS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(count)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getOrgInIous(String addr) {
        final Function function = new Function(FUNC_GETORGINIOUS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Int64>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addIou(String fromAddr, String toAddr, String createTime, BigInteger amount, String due) {
        final Function function = new Function(
                FUNC_ADDIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(fromAddr),
                new org.fisco.bcos.web3j.abi.datatypes.Address(toAddr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(createTime),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(amount),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(due)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addIou(String fromAddr, String toAddr, String createTime, BigInteger amount, String due, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(fromAddr),
                new org.fisco.bcos.web3j.abi.datatypes.Address(toAddr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(createTime),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(amount),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(due)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addIouSeq(String fromAddr, String toAddr, String createTime, BigInteger amount, String due) {
        final Function function = new Function(
                FUNC_ADDIOU,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(fromAddr),
                new org.fisco.bcos.web3j.abi.datatypes.Address(toAddr),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(createTime),
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int64(amount),
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(due)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public List<OrgCreationEventResponse> getOrgCreationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ORGCREATION_EVENT, transactionReceipt);
        ArrayList<OrgCreationEventResponse> responses = new ArrayList<OrgCreationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgCreationEventResponse typedResponse = new OrgCreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.orgId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.orgType = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.iouLimit = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOrgCreationEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ORGCREATION_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOrgCreationEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(ORGCREATION_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<IouCreationEventResponse> getIouCreationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(IOUCREATION_EVENT, transactionReceipt);
        ArrayList<IouCreationEventResponse> responses = new ArrayList<IouCreationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IouCreationEventResponse typedResponse = new IouCreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.iouId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromAddr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.toAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.due = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerIouCreationEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUCREATION_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerIouCreationEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUCREATION_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<IouPaybackEventResponse> getIouPaybackEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(IOUPAYBACK_EVENT, transactionReceipt);
        ArrayList<IouPaybackEventResponse> responses = new ArrayList<IouPaybackEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IouPaybackEventResponse typedResponse = new IouPaybackEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.iouId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.remain = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerIouPaybackEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUPAYBACK_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerIouPaybackEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUPAYBACK_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<IouSplitEventResponse> getIouSplitEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(IOUSPLIT_EVENT, transactionReceipt);
        ArrayList<IouSplitEventResponse> responses = new ArrayList<IouSplitEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IouSplitEventResponse typedResponse = new IouSplitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.iouId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value1 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerIouSplitEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUSPLIT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerIouSplitEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUSPLIT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<IouTransferToEventResponse> getIouTransferToEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(IOUTRANSFERTO_EVENT, transactionReceipt);
        ArrayList<IouTransferToEventResponse> responses = new ArrayList<IouTransferToEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IouTransferToEventResponse typedResponse = new IouTransferToEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.iouId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.toAddr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerIouTransferToEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUTRANSFERTO_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerIouTransferToEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUTRANSFERTO_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<IouTransferFromEventResponse> getIouTransferFromEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(IOUTRANSFERFROM_EVENT, transactionReceipt);
        ArrayList<IouTransferFromEventResponse> responses = new ArrayList<IouTransferFromEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IouTransferFromEventResponse typedResponse = new IouTransferFromEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.iouId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromAddr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerIouTransferFromEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUTRANSFERFROM_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerIouTransferFromEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(IOUTRANSFERFROM_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Supply load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Supply(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Supply load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Supply(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Supply load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Supply(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Supply load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Supply(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Supply> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Supply.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Supply> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Supply.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Supply> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Supply.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Supply> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Supply.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OrgCreationEventResponse {
        public Log log;

        public String addr;

        public String orgId;

        public String orgType;

        public BigInteger iouLimit;
    }

    public static class IouCreationEventResponse {
        public Log log;

        public BigInteger iouId;

        public String fromAddr;

        public String toAddr;

        public BigInteger amount;

        public String due;
    }

    public static class IouPaybackEventResponse {
        public Log log;

        public BigInteger iouId;

        public BigInteger remain;

        public BigInteger value;
    }

    public static class IouSplitEventResponse {
        public Log log;

        public BigInteger iouId;

        public BigInteger value1;
    }

    public static class IouTransferToEventResponse {
        public Log log;

        public BigInteger iouId;

        public String toAddr;
    }

    public static class IouTransferFromEventResponse {
        public Log log;

        public BigInteger iouId;

        public String fromAddr;
    }
}
