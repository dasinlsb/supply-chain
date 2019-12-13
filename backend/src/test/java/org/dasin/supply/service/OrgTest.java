package org.dasin.supply.service;


import com.alibaba.fastjson.JSONObject;
import org.dasin.supply.model.Organization;
import org.dasin.supply.repository.OrgRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrgTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrgRepository orgRepository;

//    @InjectMocks
//    private UserController userController;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }

//    @Test
//    public void testRegister() throws Exception {
//        Organization org = new Organization(
//                "1",
//                "sysu",
//                "enterprise",
//                "33108",
//                "1332605",
//                "dasin",
//                "123",
//                "2019",
//                200L);
//        mvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSONObject.toJSONString(org))
//        ).andExpect(status().isOk())
//        .andDo(print())
//        .andExpect(jsonPath("$.status").value("success"));
//
//        JSONObject user = new JSONObject();
//        user.put("username", "dasin");
//        user.put("password", "123");
//        mvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(JSONObject.toJSONString(user)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("success"))
//                .andDo(print())
//                .andExpect(jsonPath("$.orgInfo.orgId").value(org.getOrgId()));
//        //orgRepository.findByUsernameAndPassword("dasin", "123");
//    }

    @Test
    public void testLogin() throws Exception {

    }
}
