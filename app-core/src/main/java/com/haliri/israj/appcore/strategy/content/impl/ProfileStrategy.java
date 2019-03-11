package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Profile;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
@Transactional
public class ProfileStrategy {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<Profile> saveOrUpdateDataStrategy;

    public Profile getData() {
        getDataStrategy = (parameter) -> {
            String sql = "SELECT address, phone, email, lat, lon, create_date, update_date\n" +
                    "FROM content.profile ORDER BY create_date DESC LIMIT 1";

            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(Profile.class));
        };

        return (Profile) getDataStrategy.process(null);
    }

    @Transactional
    public void saveOrUpdate(Profile profile) {
        saveOrUpdateDataStrategy = (Profile parameters) -> {
            if (parameters.getCreateDate() == null || parameters.getCreateDate().equals("")) {
                String sql = "INSERT INTO content.profile(\n" +
                        "address, phone, email, lat, lon, create_date, update_date)\n" +
                        "VALUES (?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE)";
                jdbcTemplate.update(sql, parameters.getAddress(), parameters.getPhone(), parameters.getEmail(), parameters.getLat(), parameters.getLon());
            } else {
                String sql = "UPDATE content.profile\n" +
                        "SET address=?, phone=?, email=?, lat=?, lon=?, update_date=CURRENT_DATE\n" +
                        "WHERE create_date=?";
                jdbcTemplate.update(sql, parameters.getAddress(), parameters.getPhone(), parameters.getEmail(), parameters.getLat(), parameters.getLon(), parameters.getCreateDate());
            }
        };

        saveOrUpdateDataStrategy.process(profile);
    }
}
