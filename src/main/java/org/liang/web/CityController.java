package org.liang.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.liang.domain.entity.City;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
class CityController {

    // 创建线程安全的Map
    private static Map<Long, City> cities = Collections.synchronizedMap(new HashMap<Long, City>());

    @RequestMapping(value = "/", method = GET)
    public List<City> getCityList() {
        // 处理"/cities/"的GET请求，用来获取城市列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        return new ArrayList<>(cities.values());
    }

    @RequestMapping(value = "/", method = POST)
    public String postCity(@ModelAttribute City city) {
        // 处理"/cities/"的POST请求，用来创建city
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        cities.put(city.getId(), city);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = GET)
    public City getCity(@PathVariable Long id) {
        // 处理"/cities/{id}"的GET请求，用来获取url中id值的city信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return cities.get(id);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public String putCity(@PathVariable Long id, @ModelAttribute City city) {
        // 处理"/cities/{id}"的PUT请求，用来更新city信息
        City c = cities.get(id);
        BeanUtils.copyProperties(city, c);
        cities.put(id, c);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public String deleteCity(@PathVariable Long id) {
        // 处理"/cities/{id}"的DELETE请求，用来删除city
        cities.remove(id);
        return "success";
    }

}
