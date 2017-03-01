package org.liang.service.impl;

import java.util.List;

import org.liang.domain.entity.City;
import org.liang.domain.mapper.CityMapper;
import org.liang.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @Cacheable(value = "cityCache", keyGenerator = "wiselyKeyGenerator")
    public List<City> fetchAll() {
        return cityMapper.findAll();
    }

    @Override
    @CacheEvict(value = "cityCache", keyGenerator = "wiselyKeyGenerator")
    public boolean save(City city) {
        int rst = cityMapper.save(city);
        return rst != 0 ? true : false;
    }

    @Override
    @Cacheable(value = "cityCache", keyGenerator = "wiselyKeyGenerator")
    public City fetchById(Long id) {
        return cityMapper.findOne(id);
    }

    @Override
    @CachePut(value = "cityCache", keyGenerator = "wiselyKeyGenerator")
    public boolean updateCity(Long id, City city) {
        city.setId(id);
        int rst = cityMapper.update(city);
        return rst != 0 ? true : false;
    }

    @Override
    @CacheEvict(value = "cityCache", keyGenerator = "wiselyKeyGenerator")
    public boolean deleteCity(Long id) {
        int rst = cityMapper.delete(id);
        return rst != 0 ? true : false;
    }

}
