package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Item;
import com.haliri.israj.appcore.handler.impl.ResponseHandlerImpl;
import com.haliri.israj.appcore.strategy.content.DeleteDataStrategy;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
public class ItemStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<Item> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    @Autowired
    ResponseHandlerImpl responseHandlerImpl;

    public List<Item> getListData(String type) {
        getDataStrategy = (parameters) -> {
            String sql = "SELECT id_item, title, description, create_date, update_date, create_by, update_by, type, information\n" +
                    "FROM content.item WHERE type = ? limit 5";

            return jdbcTemplate.query(sql, new Object[]{parameters}, new BeanPropertyRowMapper(Item.class));
        };

        return (List<Item>) getDataStrategy.process(type);
    }

    public List<Item> getListDataById(Map param) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> objParam = (Map<String, Object>) parameters;

            String sql = "SELECT id_item, title, description, create_date, update_date, create_by, update_by, type, information\n" +
                    "FROM content.item WHERE type = ? and id_item = ?";

            return jdbcTemplate.query(sql, new Object[]{objParam.get("type"), Integer.valueOf(objParam.get("idItem").toString())}, new BeanPropertyRowMapper(Item.class));
        };

        return (List<Item>) getDataStrategy.process(param);
    }

    public List<Item> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;

            String sql = "SELECT t.*" +
                    "FROM\n" +
                    "   (SELECT ROW_NUMBER() OVER() AS rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "               FROM\n" +
                    "                   (SELECT COUNT(id_item) OVER() total_count,id_item,title,description,create_date,update_date,create_by,update_by,type as content_type,information\n" +
                    "                    FROM content.item \n" +
                    "                    WHERE title LIKE  '%" + param.get("search") + "%' OR type = '%" + param.get("type") + "%' \n" +
                    "                    ORDER BY content.item.id_item DESC) " +
                    "             t)\n" +
                    "     t)\n" +
                    "t\n" +
                    "OFFSET " + param.get("start") + "::INTEGER LIMIT " + param.get("perPage") + "::INTEGER";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Item.class));
        };

        return (List<Item>) getDataStrategy.process(allparameters);
    }


    @Transactional
    public void saveData(Item Item) {
        saveOrUpdateDataStrategy = (Item parameters) -> {
            String sql = "INSERT INTO content.item(\n" +
                    "title, description, create_date, create_by, type, information)\n" +
                    "VALUES (?, ?, CURRENT_DATE, ?, ?, ?)";
            jdbcTemplate.update(sql, parameters.getTitle(), parameters.getDescription(), parameters.getCreateBy(),
                    parameters.getContentType().name(), parameters.getInformation());
        };

        saveOrUpdateDataStrategy.process(Item);
    }

    @Transactional
    public void updateData(Item Item) {
        saveOrUpdateDataStrategy = (Item parameters) -> {
            String sql = "UPDATE content.item\n" +
                    "SET title = ?, description = ?, update_date = ?, update_by = ?, type = ?, information = ?\n" +
                    "WHERE id_item = ?";
            jdbcTemplate.update(sql, parameters.getTitle(), parameters.getDescription(),
                    parameters.getUpdateDate(), parameters.getUpdateBy(),
                    parameters.getContentType().name(), parameters.getInformation(), Item.getIdItem());
        };

        saveOrUpdateDataStrategy.process(Item);
    }

    @Transactional
    public void deleteData(Integer id) {
        deleteDataStrategy = (parameters) -> {
            String sql = "DELETE FROM content.item\n" +
                    "WHERE id_item = ?";
            jdbcTemplate.update(sql, parameters);
        };

        deleteDataStrategy.process(id);
    }
}
