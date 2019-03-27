package com.demo.mavenTutorial.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.ResultSet;

@Repository
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @Column
    String countryCode;

    @Column
    String district;

    @Column
    String population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public City maoRow(ResultSet rs, int rowNum) throws Exception {
        City cityDao = new City();
        cityDao.setId(rs.getInt("id"));
        cityDao.setName(rs.getString("name"));
        cityDao.setCountryCode(rs.getString("countryCode"));
        cityDao.setDistrict(rs.getString("district"));
        cityDao.setPopulation(rs.getString("population"));

        return cityDao;
    }
}