package org.liang.service;

import java.util.List;

import org.liang.domain.entity.City;

/**
 * 城市业务接口.
 *  
 * @author L.Yang
 * @version v1.0 2017年2月28日  下午4:09:29
 */
public interface CityService {

    List<City> fetchAll();
    
    boolean save(City city);
    
    City fetchById(Long id);
    
    boolean updateCity(Long id, City city);
    
    boolean deleteCity(Long id);
}
