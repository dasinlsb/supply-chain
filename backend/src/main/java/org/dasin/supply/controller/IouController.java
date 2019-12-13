package org.dasin.supply.controller;

import com.alibaba.fastjson.JSONObject;
import org.dasin.supply.model.Iou;
import org.dasin.supply.model.Organization;
import org.dasin.supply.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class IouController {
    @Autowired ChainService chainService;

    @PostMapping(value = "/ious", produces = "application/json;charset=UTF-8")
    public void addIou(@RequestBody Map<String,String> map) {
        Iou iou = new Iou();
        iou.setToOrgAddr(map.get("toAddr"));
        iou.setAmount(Long.parseLong(map.get("amount")));
        iou.setCreateTime(new Date().toString());
        iou.setDue(map.get("due"));
        try {
            chainService.addIou(iou);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "chain operation error: "+e
            );
        }
    }

    @GetMapping(value = "/ious/me", produces = "application/json;charset=UTF-8")
    public String getIouListMine() {
        List<Iou> ious;
        try {
            ious = chainService.getAllIouFrom(chainService.getAddress());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "chain operation error: "+e
            );
        }
        System.out.printf("GET /ious/me, address: %s, data: %s\n", chainService.getAddress(), ious.size());
        JSONObject res = new JSONObject();
        res.put("ious", ious);
        return res.toJSONString();
    }

    @GetMapping(value = "/ious", produces = "application/json;charset=UTF-8")
    public String getIouListFrom(@RequestParam(value = "addr")String fromAddr) {
        List<Iou> ious;
        try {
            ious = chainService.getAllIouFrom(fromAddr);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "chain operation error: "+e
            );
        }
        System.out.printf("GET /ious?, address: %s, data: %s\n", fromAddr, ious.size());
        JSONObject res = new JSONObject();
        res.put("ious", ious);
        return res.toJSONString();
    }

    @GetMapping(value = "/users", produces = "application/json;charset=UTF-8")
    public String getUserList(HttpServletRequest request, HttpServletResponse response) {
        List<Organization> orgs = new ArrayList<>();
        try {
            orgs = chainService.getAllOrgInfo();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "chain operation error: "+e
            );
        }
        System.out.printf("GET /users, data: %s\n", orgs.size());
        JSONObject res = new JSONObject();
        res.put("users", orgs);
        return res.toJSONString();
    }

    @PostMapping(value = "/trans", produces = "application/json;charset=UTF-8")
    public void transIou(@RequestBody Map<String,String> map) {
        Iou iou = new Iou();
        iou.setIouId(Long.parseLong(map.get("iouId")));
        iou.setToOrgAddr(map.get("toAddr"));
        iou.setAmount(Long.parseLong(map.get("value")));
        iou.setCreateTime(new Date().toString());
        try {
            chainService.transIouFrom(iou);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "chain operation error: "+e
            );
        }
    }

    @PostMapping(value = "/pay", produces = "application/json;charset=UTF-8")
    public void payIou(@RequestBody Map<String,String> map) {
        Iou iou = new Iou();
        iou.setIouId(Long.parseLong(map.get("iouId")));
        iou.setAmount(Long.parseLong(map.get("value")));
        try {
            chainService.payIou(iou);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "chain operation error: "+e
            );
        }
    }
}
