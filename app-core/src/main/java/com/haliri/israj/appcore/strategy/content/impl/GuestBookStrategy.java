package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.GuestBook;
import com.haliri.israj.appcore.strategy.content.DeleteDataStrategy;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Service
public class GuestBookStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<GuestBook> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    public List<GuestBook> getOneMonthListData() {
        getDataStrategy = (parameter) -> {
            String sql = "SELECT guest_book.create_date, COUNT (guest_book.create_date) total_count FROM content.guest_book WHERE guest_book.create_date BETWEEN\n" +
                    "NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER-30\n" +
                    "AND NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER GROUP BY guest_book.create_date";

            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(GuestBook.class));
        };

        return (List<GuestBook>) getDataStrategy.process(null);
    }

    public List<GuestBook> getListData() {
        getDataStrategy = (parameter) -> {
            String sql = "SELECT * FROM content.guest_book ORDER BY id_guest_book ASC LIMIT 5 ";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(GuestBook.class));
        };

        return (List<GuestBook>) getDataStrategy.process(null);
    }

    public List<GuestBook> getListDataById(Integer id) {
        getDataStrategy = (parameter) -> {
            String sql = "SELECT * FROM content.guest_book WHERE id_guest_book = ? ";
            return jdbcTemplate.query(sql,new Object[]{parameter}, new BeanPropertyRowMapper(GuestBook.class));
        };

        return (List<GuestBook>) getDataStrategy.process(id);
    }

    public List<GuestBook> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;

            String sql = "SELECT t.*\n" +
                    "FROM\n" +
                    "   (SELECT ROW_NUMBER() OVER() AS rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "                   FROM\n" +
                    "                    (SELECT COUNT(id_guest_book) OVER() total_count,id_guest_book,username,create_date\n" +
                    "                    FROM content.guest_book \n" +
                    "                    WHERE username LIKE  '%" + param.get("search") + "%' \n" +
                    "                    ORDER BY content.guest_book.id_guest_book DESC \n" +
                    "                    ) t\n" +
                    "                 ) t\n" +
                    "          ) t\n" +
                    "WHERE t.rn BETWEEN " + param.get("start") + "::INTEGER AND " + param.get("length") + "::INTEGER";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(GuestBook.class));
        };

        return (List<GuestBook>) getDataStrategy.process(allparameters);
    }

    @Transactional
    public void saveData(GuestBook guestBook) {
        saveOrUpdateDataStrategy = (GuestBook parameters) -> {
            String sql = "INSERT INTO content.guest_book (username, create_date) VALUES (?, CURRENT_DATE )";
            jdbcTemplate.update(sql, parameters.getUsername());
        };

        saveOrUpdateDataStrategy.process(guestBook);
    }

    @Transactional
    public void deleteData(Integer id) {
        deleteDataStrategy = (parameters) -> {
            String sql = "DELETE FROM content.guest_book\n" +
                    "WHERE id_guest_book = ?";
            jdbcTemplate.update(sql, parameters);
        };

        deleteDataStrategy.process(id);
    }
}
