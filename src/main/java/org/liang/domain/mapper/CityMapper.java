package org.liang.domain.mapper;

import java.util.List;

import org.liang.domain.entity.City;

public interface CityMapper {
    
    List<City> findAll();

    City findOne(Long id);
    
    int save(City city);
    
    int delete(Long id);
    
    int update(City city);
}
