package org.dasin.supply.init;


import org.dasin.supply.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

@Component
public class InitServlet {

    @Autowired private ChainService chainService;

    @PostConstruct
    public void init() throws Exception {
        chainService.initAllService();
        System.out.println("chain initialize ok");
    }
}
