package org.liang.web;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liang.domain.entity.City;
import org.liang.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    /**
     * 测试 获取所有城市 api.
     */
    @Test
    public void testGet() throws Exception {
        given(this.cityService.fetchAll())
            .willReturn(new ArrayList<City>());

        mvc.perform(get("/cities/"))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void testGetOne() throws Exception {
        City city = initCIty();
        String exceped = "{\"id\":1,\"page\":1,\"rows\":10,"
                    + "\"createtime\":null,\"updatetime\":null,\"name\":\"上海\",\"state\":\"20\"}";
        given(this.cityService.fetchById(1L)).willReturn(city);
        mvc.perform(get("/cities/1"))
            .andExpect(content().string(equalTo(exceped)));
    }

    /**
     * 添加城市请求.
     */
    @Test
    public void testPost() throws Exception {
        City city = initCIty();
        given(this.cityService.save(city)).willReturn(true);

        mvc.perform(post("/cities/")
            .param("id", "1")
            .param("name", "上海")
            .param("state", "20")
            .flashAttr("city", city))
            .andExpect(content().string(equalTo("success")));
    }

    /**
     * 测试更新城市请求.
     */
    @Test
    public void testPut() throws Exception {
        City city = mock(City.class);
        city.setId(1L);
        city.setName("北京");
        city.setState("30");
        
        given(this.cityService.updateCity(1L, city)).willReturn(true);
        
        mvc.perform(put("/cities/1")
            .param("name", "北京")
            .param("state", "30")
            .flashAttr("city", city))
            .andExpect(content().string(equalTo("success")));
    }

    /**
     * 测试删除请求.
     */
    @Test
    public void testDelete() throws Exception {
        given(this.cityService.deleteCity(1L)).willReturn(true);
        mvc.perform(delete("/cities/1"))
            .andExpect(content().string(equalTo("success")));
    }

    private City initCIty() {
        City city = new City();
        city.setId(1L);
        city.setName("上海");
        city.setState("20");
        return city;
    }

}
