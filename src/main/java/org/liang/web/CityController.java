package org.liang.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.liang.domain.entity.City;
import org.liang.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService service;

    /**
     * 获取所有城市.
     * 
     * <pre>
     *  处理"/cities/"的GET请求，用来获取城市列表
     *         还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
     * </pre>
     */
    @RequestMapping(value = "/", method = GET)
    public List<City> getCityList() {
        return service.fetchAll();
    }

    /**
     * 创建城市.
     * 
     * <pre>
     * 处理"/cities/"的POST请求，用来创建city
     *         除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
     * </pre>
     * 
     * @param city
     */
    @RequestMapping(value = "/", method = POST)
    public String postCity(@ModelAttribute City city) {
        boolean success = service.save(city);
        return success ? "success" : "failed";
    }

    /**
     * 获取指定 城市.
     * 
     * <pre>
     * 处理"/cities/{id}"的GET请求，用来获取url中id值的city信息
     *       url中的id可通过@PathVariable绑定到函数的参数中
     * </pre>
     * 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = GET)
    public City getCity(@PathVariable Long id) {
        return service.fetchById(id);
    }

    /**
     * 更新城市.
     * 
     * <pre>
     * 处理"/cities/{id}"的PUT请求，用来更新city信息
     * </pre>
     * 
     * @param id
     * @param city
     */
    @RequestMapping(value = "/{id}", method = PUT)
    public String putCity(@PathVariable Long id, @ModelAttribute City city) {
        boolean success = service.updateCity(id, city);
        return success ? "success" : "failed";
    }

    /**
     * 删除城市.
     * 
     * <pre>
     * 处理"/cities/{id}"的DELETE请求，用来删除city。
     * </pre>
     * 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public String deleteCity(@PathVariable Long id) {
        boolean success = service.deleteCity(id);
        return success ? "success" : "failed";
    }

}
