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
    public static String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034801561005057600080fd5b506000600460006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff1602179055506125af8061008d6000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631a7c94f8146100ca57806333e3c2ea146101a65780638da5cb5b1461023e5780639693413014610295578063a902bcfc146102f0578063ad899a9114610340578063b875fa031461037d578063c9295bc6146103cd578063d69497f714610502578063d9609392146106a4578063e1403db914610727578063f014ca44146107a9578063f320e97614610841575b600080fd5b3480156100d657600080fd5b506101a4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803560070b9060200190929190505050610957565b005b3480156101b257600080fd5b506101e7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d13565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561022a57808201518184015260208101905061020f565b505050509050019250505060405180910390f35b34801561024a57600080fd5b50610253610de7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102a157600080fd5b506102d6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e0c565b604051808215151515815260200191505060405180910390f35b3480156102fc57600080fd5b5061033e600480360381019080803560070b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e6f565b005b34801561034c57600080fd5b5061037b600480360381019080803560070b9060200190929190803560070b9060200190929190505050610fab565b005b34801561038957600080fd5b506103cb600480360381019080803560070b9060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611142565b005b3480156103d957600080fd5b5061040e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061127e565b6040518080602001806020018460070b60070b8152602001838103835286818151815260200191508051906020019080838360005b8381101561045e578082015181840152602081019050610443565b50505050905090810190601f16801561048b5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156104c45780820151818401526020810190506104a9565b50505050905090810190601f1680156104f15780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561050e57600080fd5b50610530600480360381019080803560070b90602001909291905050506114b0565b604051808860070b60070b81526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018560070b60070b81526020018460070b60070b815260200180602001838103835287818151815260200191508051906020019080838360005b838110156105fc5780820151818401526020810190506105e1565b50505050905090810190601f1680156106295780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b83811015610662578082015181840152602081019050610647565b50505050905090810190601f16801561068f5780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b3480156106b057600080fd5b50610725600480360381019080803560070b9060200190929190803560070b9060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506117c5565b005b34801561073357600080fd5b5061075260048036038101908080359060200190929190505050611b40565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561079557808201518184015260208101905061077a565b505050509050019250505060405180910390f35b3480156107b557600080fd5b506107ea600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611c35565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561082d578082015181840152602081019050610812565b505050509050019250505060405180910390f35b34801561084d57600080fd5b5061093b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803560070b9060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611d09565b604051808260070b60070b815260200191505060405180910390f35b606080610962612325565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156109bd57600080fd5b6000600160008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b60070b141515610a1e57600080fd5b60c0604051908101604052808873ffffffffffffffffffffffffffffffffffffffff1681526020018781526020018681526020018560070b815260200184815260200183815250905080600160008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001019080519060200190610b0a929190612375565b506040820151816002019080519060200190610b27929190612375565b5060608201518160030160006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff1602179055506080820151816004019080519060200190610b769291906123f5565b5060a0820151816005019080519060200190610b939291906123f5565b5090505060028790806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050856040518082805190602001908083835b602083101515610c335780518252602082019150602081019050602083039250610c0e565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207fc689347213400b2e7b61392e7cbb2e8e2e531f7c7cc2b583e2838367a1801248868660405180806020018360070b60070b8152602001828103825284818151815260200191508051906020019080838360005b83811015610ccf578082015181840152602081019050610cb4565b50505050905090810190601f168015610cfc5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a250505050505050565b6060610d1e82610e0c565b1515610d2957600080fd5b600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401805480602002602001604051908101604052809291908181526020018280548015610ddb57602002820191906000526020600020906000905b82829054906101000a900460070b60070b81526020019060080190602082600701049283019260010382029150808411610da45790505b50505050509050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010180546001816001161561010002031660029004905014159050919050565b3373ffffffffffffffffffffffffffffffffffffffff16600360008460070b60070b815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610ee557600080fd5b80600360008460070b60070b815260200190815260200160002060010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160070b7ffd476520dbc98378ad85f2494371a82f9f6263264589efd37f91d9c17203ef6782604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a25050565b8060070b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b60070b1215151561100f57600080fd5b8060070b600360008460070b60070b815260200190815260200160002060030160089054906101000a900460070b60070b1215151561104d57600080fd5b80600360008460070b60070b815260200190815260200160002060030160088282829054906101000a900460070b0392506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff1602179055506000600360008460070b60070b815260200190815260200160002060030160089054906101000a90505050600360008360070b60070b815260200190815260200160002060030160089054906101000a900460070b60070b8260070b7fdb6466df27da84ba28cb558c3bff32450d8c951c5320395d678fc12fe211788983604051808260070b60070b815260200191505060405180910390a35050565b3373ffffffffffffffffffffffffffffffffffffffff16600360008460070b60070b815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156111b857600080fd5b80600360008460070b60070b815260200190815260200160002060000160086101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160070b7f2012565039935dcec92a3d7fca9b77bd82c37fd401c831c92d7c282937641bfa82604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a25050565b606080600061128c84610e0c565b151561129757600080fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114005780601f106113d557610100808354040283529160200191611400565b820191906000526020600020905b8154815290600101906020018083116113e357829003601f168201915b50505050509250818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561149c5780601f106114715761010080835404028352916020019161149c565b820191906000526020600020905b81548152906001019060200180831161147f57829003601f168201915b505050505091509250925092509193909250565b6000806000606060008060606114c46124b4565b611509600360008b60070b60070b815260200190815260200160002060000160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff16610e0c565b151561151457600080fd5b600360008a60070b60070b815260200190815260200160002060e060405190810160405290816000820160009054906101000a900460070b60070b60070b81526020016000820160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561169b5780601f106116705761010080835404028352916020019161169b565b820191906000526020600020905b81548152906001019060200180831161167e57829003601f168201915b505050505081526020016003820160009054906101000a900460070b60070b60070b81526020016003820160089054906101000a900460070b60070b60070b8152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156117775780601f1061174c57610100808354040283529160200191611777565b820191906000526020600020905b81548152906001019060200180831161175a57829003601f168201915b5050505050815250509050806000015181602001518260400151836060015184608001518560a001518660c00151839350809050975097509750975097509750975050919395979092949650565b6117cd6124b4565b8260070b600360008660070b60070b815260200190815260200160002060030160089054906101000a900460070b60070b13801561180e575060008360070b135b151561181957600080fd5b600360008560070b60070b815260200190815260200160002060e060405190810160405290816000820160009054906101000a900460070b60070b60070b81526020016000820160089054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156119a05780601f10611975576101008083540402835291602001916119a0565b820191906000526020600020905b81548152906001019060200180831161198357829003601f168201915b505050505081526020016003820160009054906101000a900460070b60070b60070b81526020016003820160089054906101000a900460070b60070b60070b8152602001600482018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015611a7c5780601f10611a5157610100808354040283529160200191611a7c565b820191906000526020600020905b815481529060010190602001808311611a5f57829003601f168201915b5050505050815250509050611aa08160200151826040015184868560c00151611d09565b5082600360008660070b60070b815260200190815260200160002060030160088282829054906101000a900460070b0392506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff1602179055508360070b7f187939e6da73557a4e32a57e40521674155f9fd3d2224a0e6d11bb61e649dbc184604051808260070b60070b815260200191505060405180910390a250505050565b6060806000836002805490501015611b5b5760028054905093505b83604051908082528060200260200182016040528015611b8a5781602001602082028038833980820191505090505b509150600090505b83811015611c2b57600281815481101515611ba957fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168282815181101515611be257fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508080600101915050611b92565b8192505050919050565b6060611c4082610e0c565b1515611c4b57600080fd5b600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501805480602002602001604051908101604052809291908181526020018280548015611cfd57602002820191906000526020600020906000905b82829054906101000a900460070b60070b81526020019060080190602082600701049283019260010382029150808411611cc65790505b50505050509050919050565b6000611d1486610e0c565b1515611d1f57600080fd5b611d2885610e0c565b1515611d3357600080fd5b8573ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611d6d57600080fd5b8260070b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460070b60070b12151515611dd157600080fd5b6001600460008282829054906101000a900460070b0192506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060e060405190810160405280600460009054906101000a900460070b60070b81526020018773ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018460070b81526020018460070b81526020018381525060036000600460009054906101000a900460070b60070b60070b815260200190815260200160002060008201518160000160006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060208201518160000160086101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506060820151816002019080519060200190611f8c929190612375565b5060808201518160030160006101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060a08201518160030160086101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555060c082015181600401908051906020019061200d929190612375565b5090505082600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160008282829054906101000a900460070b0392506101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff160217905550600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401600460009054906101000a900460070b908060018154018082558091505090600182039060005260206000209060049182820401919006600802909192909190916101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555050600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600501600460009054906101000a900460070b908060018154018082558091505090600182039060005260206000209060049182820401919006600802909192909190916101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff16021790555050600460009054906101000a900460070b60070b7f61d839ccc49071261e6c661dcbe12086f368009c895694a8ecb54a560726215f87878686604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018360070b60070b815260200180602001828103825283818151815260200191508051906020019080838360005b838110156122cd5780820151818401526020810190506122b2565b50505050905090810190601f1680156122fa5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a2600460009054906101000a900460070b905095945050505050565b60c060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff1681526020016060815260200160608152602001600060070b815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106123b657805160ff19168380011785556123e4565b828001600101855582156123e4579182015b828111156123e35782518255916020019190600101906123c8565b5b5090506123f19190612527565b5090565b828054828255906000526020600020906003016004900481019282156124a35791602002820160005b8382111561246d57835183826101000a81548167ffffffffffffffff021916908360070b67ffffffffffffffff160217905550926020019260080160208160070104928301926001030261241e565b80156124a15782816101000a81549067ffffffffffffffff021916905560080160208160070104928301926001030261246d565b505b5090506124b0919061254c565b5090565b60e060405190810160405280600060070b8152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff16815260200160608152602001600060070b8152602001600060070b8152602001606081525090565b61254991905b8082111561254557600081600090555060010161252d565b5090565b90565b61258091905b8082111561257c57600081816101000a81549067ffffffffffffffff021916905550600101612552565b5090565b905600a165627a7a72305820858235cbadd82d6ae4c8631d8e1f34e720cbb908d0988be7967f0367d64062e20029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"},{\"name\":\"orgId\",\"type\":\"string\"},{\"name\":\"orgType\",\"type\":\"string\"},{\"name\":\"iouLimit\",\"type\":\"int64\"}],\"name\":\"addOrg\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getOrgOutIous\",\"outputs\":[{\"name\":\"\",\"type\":\"int64[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"isValidAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"toOrgAddr\",\"type\":\"address\"}],\"name\":\"transferIouTo\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"value\",\"type\":\"int64\"}],\"name\":\"payIou\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"fromOrgAddr\",\"type\":\"address\"}],\"name\":\"transferIouFrom\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getOrgInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int64\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"}],\"name\":\"getIouInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"int64\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int64\"},{\"name\":\"\",\"type\":\"int64\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"iouId\",\"type\":\"int64\"},{\"name\":\"value1\",\"type\":\"int64\"},{\"name\":\"createTime\",\"type\":\"string\"}],\"name\":\"splitIou\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"count\",\"type\":\"uint256\"}],\"name\":\"getOrgAddrs\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getOrgInIous\",\"outputs\":[{\"name\":\"\",\"type\":\"int64[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"fromAddr\",\"type\":\"address\"},{\"name\":\"toAddr\",\"type\":\"address\"},{\"name\":\"createTime\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"int64\"},{\"name\":\"due\",\"type\":\"string\"}],\"name\":\"addIou\",\"outputs\":[{\"name\":\"\",\"type\":\"int64\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"orgId\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"orgType\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"iouLimit\",\"type\":\"int64\"}],\"name\":\"OrgCreation\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"fromAddr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAddr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"due\",\"type\":\"string\"}],\"name\":\"IouCreation\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"value\",\"type\":\"int64\"},{\"indexed\":true,\"name\":\"remain\",\"type\":\"int64\"}],\"name\":\"IouPayback\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"value1\",\"type\":\"int64\"}],\"name\":\"IouSplit\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"toAddr\",\"type\":\"address\"}],\"name\":\"IouTransferTo\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"iouId\",\"type\":\"int64\"},{\"indexed\":false,\"name\":\"fromAddr\",\"type\":\"address\"}],\"name\":\"IouTransferFrom\",\"type\":\"event\"}]";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Int64>() {}));
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
            typedResponse.orgId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.orgType = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.iouLimit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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

        public byte[] orgId;

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
