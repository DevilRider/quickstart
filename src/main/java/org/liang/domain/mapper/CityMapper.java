package org.liang.domain.mapper;

import java.util.List;

import org.liang.domain.entity.City;

public interface CityMapper {
    
    List<City> findAll();

    int save(City city);
}
