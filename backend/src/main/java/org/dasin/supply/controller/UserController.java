package org.dasin.supply.controller;

import com.alibaba.fastjson.JSONObject;
import org.dasin.supply.model.Organization;
import org.dasin.supply.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private ChainService chainService;

    @GetMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestParam(value = "account")String account, HttpServletRequest request, HttpServletResponse response) {
        if (!account.equals(chainService.getAddress())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "wrong account"
            );
        }
        Organization org;
        try {
            org = chainService.getOrgInfo(account);
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
        System.out.println("login with account: "+account + " successfully: "+res);
        return res.toJSONString();
    }

//    @PostMapping(value = "/users", produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String register(@RequestBody Map<String, String> map,
//                           HttpServletRequest request, HttpServletResponse response) {
//        String orgId = map.get("orgId");
//        String orgName = map.get("orgName");
//        String orgType = map.get("orgType");
//        String corporationIdCardNumber = map.get("corporationIdCardNumber");
//
//        String phoneNumber = map.get("phoneNumber");
//        String username = map.get("username");
//        String password = map.get("password");
//
//        Long iouLimit = Long.parseLong(map.get("iouLimit"));
//        System.out.println("orgId is: " + orgId);
//        System.out.println("username is: " + username);
//        System.out.println("password is: " + password);
//        Optional<Organization> oldOrg = orgRepository.findById(orgId);
//        if (oldOrg.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "found existing organization id");
//        }
//        Organization org = new Organization(orgId, orgName, orgType, corporationIdCardNumber, phoneNumber, username, password, new Timestamp(System.currentTimeMillis()).toString(), iouLimit);
//        orgRepository.save(org);
//        try {
//            chainService.addOrg(org);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "update on chain error:"+e);
//        }
//
//        request.getSession().setAttribute("orgId", orgId);
//        JSONObject res = new JSONObject();
//        res.put("status", "success");
//        return res.toJSONString();
//    }

    @PostMapping(value = "/logout",  produces = "application/json;charset=UTF-8")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //TODO: use jwt
        request.getSession().setAttribute("orgId", "");
    }

}
