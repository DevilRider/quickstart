package org.liang.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class CityControllerTest {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new CityController()).build();
    }

    @Test
    public void testCityController() throws Exception {
        // 测试CityController
        RequestBuilder request;

        // 1、get查一下city列表，应该为空
        request = get("/cities/");
        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("[]")));

        // 2、post提交一个city
        request = post("/cities/")
            .param("id", "1")
            .param("name", "上海")
            .param("state", "20");
        mvc.perform(request)
            .andExpect(content().string(equalTo("success")));

        // 3、get获取city列表，应该有刚才插入的数据
        String get_exceped = "[{\"id\":1,\"page\":1,\"rows\":10,\""
                    + "createtime\":null,\"updatetime\":null,\"name\":\"上海\",\"state\":\"20\"}]";
        request = get("/cities/");
        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo(get_exceped)));

        // 4、put修改id为1的city
        request = put("/cities/1")
            .param("name", "测试终极大师")
            .param("state", "30");
        mvc.perform(request)
            .andExpect(content().string(equalTo("success")));

        String get_exceped_put = "{\"id\":1,\"page\":1,\"rows\":10,\""
            + "createtime\":null,\"updatetime\":null,\"name\":\"测试终极大师\",\"state\":\"30\"}";
        // 5、get一个id为1的city
        request = get("/cities/1");
        mvc.perform(request)
            .andExpect(content().string(equalTo(get_exceped_put)));

        // 6、del删除id为1的city
        request = delete("/cities/1");
        mvc.perform(request)
            .andExpect(content().string(equalTo("success")));

        // 7、get查一下city列表，应该为空
        request = get("/cities/");
        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("[]")));

    }
}
