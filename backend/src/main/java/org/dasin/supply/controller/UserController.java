package org.dasin.supply.controller;

import com.alibaba.fastjson.JSONObject;
import org.dasin.supply.model.Account;
import org.dasin.supply.model.Organization;
import org.dasin.supply.repository.AccountRepository;
import org.dasin.supply.service.ChainService;
import org.fisco.bcos.channel.client.PEMManager;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private ChainService chainService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "account")String account, HttpServletRequest request, HttpServletResponse response) {
        // try to switch user
        Optional<Account> _account = accountRepository.findById(account);
        if (_account.isPresent()) {
            System.out.println("replace credential with account: " + _account.get().getAddr());
            Credentials credentials = GenCredential.create(_account.get().getPemPrivateKey());
            chainService.setCredentials(credentials);
        }
        if (chainService.getCredentials() == null || !account.equals(chainService.getAccountAddress())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "wrong account"
            );
        }
        if (!_account.isPresent()) {
            Account account1 = new Account();
            account1.setAddr(account);
            account1.setPemPrivateKey(chainService.getCredentials().getEcKeyPair().getPrivateKey().toString(16));
            accountRepository.save(account1);
        }
        Organization org;
        boolean isAdmin;
        try {
            org = chainService.getOrgInfo(account);
            isAdmin = chainService.isAdmin(account);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "query chain error:"+e
            );
        }

        JSONObject info = new JSONObject();
        info.put("account", account);
        info.put("orgId", org.getOrgId());
        info.put("orgType", org.getOrgType());
        JSONObject res = new JSONObject();
        res.put("info", info);
        res.put("isAdmin", isAdmin);
        System.out.println("login with account: "+account + " successfully: "+res);
        return res.toJSONString();
    }

    /**
     * Register an organization, `accounts.pem-file` should be administrator's private key
     * @param map
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/users", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void register(@RequestBody Map<String, String> map,
                           HttpServletRequest request, HttpServletResponse response) {
        String orgAddr = map.get("orgAddr");
        String orgId = map.get("orgId");
        String orgType = map.get("orgType");
        Long iouLimit = Long.parseLong(map.get("iouLimit"));
        Organization org = new Organization();
        org.setOrgAddr(orgAddr);
        org.setOrgId(orgId);
        org.setOrgType(orgType);
        org.setIouLimit(iouLimit);
        Optional<Account> _account = accountRepository.findById(orgAddr);
        if (!_account.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "organization account does not match with pem-file"
            );
        }
        System.out.println("will register orgId is: " + orgId);
        try {
            chainService.addOrg(org);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "operate chain error:"+e
            );
        }
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;charset=UTF-8")
    public void upload_pem_file(@RequestParam(value = "type") String type,
                                @RequestParam(value = "file") MultipartFile multipartFile) {
        if (multipartFile == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "file transfer error"
            );
        }
        String fileName = multipartFile.getOriginalFilename();
        System.out.println("upload file: " + fileName);
        switch (type) {
            case "pem": {
                try {
                    PEMManager pemManager = new PEMManager();
                    pemManager.load(multipartFile.getInputStream());
                    ECKeyPair keyPair = pemManager.getECKeyPair();
                    Credentials credentials = GenCredential.create(keyPair.getPrivateKey().toString(16));
                    Account account = new Account();
                    account.setAddr(credentials.getAddress());
                    account.setPemPrivateKey(keyPair.getPrivateKey().toString(16));
                    accountRepository.save(account);
                } catch (Exception e) {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR, "read uploaded file error"+e
                    );
                }
                break;
            }
            default:
                break;
        }
    }

    @PostMapping(value = "/deploy", produces = "application/json;charset=UTF-8")
    public String deploy() {
        JSONObject res = new JSONObject();
        try {
            Credentials credentials = Credentials.create(Keys.createEcKeyPair());
            chainService.setCredentials(credentials);
            chainService.deployAndSaveAddr();
            // add self
            Organization org = new Organization();
            org.setOrgAddr(credentials.getAddress());
            org.setOrgId("0");
            org.setOrgType("admin");
            org.setIouLimit(10000L);
            chainService.addOrg(org);
            Account account = new Account();
            account.setAddr(credentials.getAddress());
            account.setPemPrivateKey(credentials.getEcKeyPair().getPrivateKey().toString(16));
            accountRepository.save(account);
            res.put("accountAddr", credentials.getAddress());
            res.put("contractAddr", chainService.getContractAddress());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "unknown error: "+e
            );
        }
        return res.toJSONString();
    }
}
