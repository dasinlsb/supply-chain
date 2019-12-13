package org.dasin.supply.solidity;

import org.apache.commons.io.FileUtils;
import org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.fisco.bcos.web3j.solidity.compiler.CompilationResult;
import org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.ABI;
import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.BIN;
import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.INTERFACE;
import static org.fisco.bcos.web3j.solidity.compiler.SolidityCompiler.Options.METADATA;

public class SolidityGeneratorTest {
    protected String tempDirPath =  new File("src/main/java/").getAbsolutePath();
    protected String packageName =  "org.dasin.supply.contract";
    private static final Logger log = LoggerFactory.getLogger(SolidityGeneratorTest.class);

    @Test
    public void generateClassFromABIForHelloWorld() throws Exception {
        String binFile1 =  new ClassPathResource("solidity/HelloWorld.bin").getFile().getAbsolutePath();
        String abiFile1 =  new ClassPathResource("solidity/HelloWorld.abi").getFile().getAbsolutePath();
        SolidityFunctionWrapperGenerator.main(Arrays.asList(
               "-b", binFile1,
                "-a", abiFile1,
                "-p", packageName,
                "-o", tempDirPath
        ).toArray(new String[0]));
    }

//    @Test
//    public void compileSolFilesToJavaTest() throws IOException {
//        String prefix = "";
//        File solFileList = new File(prefix + "src/main/resources/contract");
//        File[] solFiles = solFileList.listFiles();
//        for (File solFile : solFiles) {
//            SolidityCompiler.Result res = SolidityCompiler.compile(solFile, true, ABI, BIN, INTERFACE, METADATA);
//            log.info("Out: '{}'" , res.output);
//            log.info("Err: '{}'" , res.errors);
//            CompilationResult result = CompilationResult.parse(res.output);
//            log.info("contractname  {}" , solFile.getName());
//            String contractname = solFile.getName().split("\\.")[0];
//            CompilationResult.ContractMetadata a = result.getContract(solFile.getName().split("\\.")[0]);
//            log.info("abi   {}" , a.abi);
//            log.info("bin   {}" , a.bin);
//            FileUtils.writeStringToFile(new File(prefix+"src/main/resources/solidity/" + contractname + ".abi"), a.abi);
//            FileUtils.writeStringToFile(new File(prefix+"src/main/resources/solidity/" + contractname + ".bin"), a.bin);
//            String binFile;
//            String abiFile;
//            String tempDirPath = new File(prefix+"src/main/java/").getAbsolutePath();
//            String packageName = "org.dasin.supply.contract";
//            String filename = contractname;
//            abiFile = "src/main/resources/solidity/" + filename + ".abi";
//            binFile = "src/main/resources/solidity/" + filename + ".bin";
//            SolidityFunctionWrapperGenerator.main(Arrays.asList(
//                    "-a", abiFile,
//                    "-b", binFile,
//                    "-p", packageName,
//                    "-o", tempDirPath
//            ).toArray(new String[0]));
//        }
//        System.out.println("generate successfully");
//    }

}
