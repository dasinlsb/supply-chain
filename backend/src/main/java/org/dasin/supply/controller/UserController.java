package org.dasin.supply.controller;

import com.alibaba.fastjson.JSONObject;
import org.dasin.supply.model.Organization;
import org.dasin.supply.repository.OrgRepository;
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
    private OrgRepository orgRepository;

    @Autowired
    private ChainService chainService;
    /**
     * Login with username & password
     * @param map - { username, password }
     * @param request
     * @param response
     * @return - { status: "success" | "fail" }
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {
        String username = map.get("username");
        String password = map.get("password");

        System.out.printf("is searching for username: %s, password: %s\n", username, password);
        List<Organization> orgs = orgRepository.findByUsernameAndPassword(username, password);

        JSONObject res = new JSONObject();
        if (orgs.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "wrong username or password"
            );
        } else {
            Organization org = orgs.get(0);
            request.getSession().setAttribute("orgId", org.getOrgId());
            res.put("status", "success");
            res.put("orgInfo", org);
        }

        return res.toJSONString();
    }

    /**
     * Register an organization
     * @param map
     * @param request
     * @param response
     * @return
     */
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
