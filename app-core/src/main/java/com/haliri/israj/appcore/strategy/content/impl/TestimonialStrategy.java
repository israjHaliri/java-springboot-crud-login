package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Testimonial;
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
public class TestimonialStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<Testimonial> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    @Autowired
    ResponseHandlerImpl responseHandlerImpl;

    public List<Testimonial> getListData() {
        getDataStrategy = (parameters) -> {
            String sql = "SELECT * FROM content.testimonial ORDER BY create_date DESC LIMIT 5";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Testimonial.class));
        };

        return (List<Testimonial>) getDataStrategy.process(null);
    }

    public List<Testimonial> getListDataById(Integer id) {
        getDataStrategy = (parameters) -> {
            String sql = "SELECT * FROM content.testimonial WHERE id_testimonial = ?";
            return jdbcTemplate.query(sql, new Object[]{parameters}, new BeanPropertyRowMapper(Testimonial.class));
        };

        return (List<Testimonial>) getDataStrategy.process(id);
    }

    public List<Testimonial> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;

            String sql = "SELECT t.*\n" +
                    "FROM\n" +
                    "   (SELECT ROW_NUMBER() OVER() AS rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "                   FROM\n" +
                    "                    (SELECT COUNT(id_testimonial) OVER() total_count,id_testimonial,subject,description,age,create_date,update_date\n" +
                    "                    FROM content.testimonial \n" +
                    "                    WHERE subject LIKE  '%" + param.get("search") + "%' \n" +
                    "                    ORDER BY content.testimonial.id_testimonial DESC \n" +
                    "                    ) t\n" +
                    "                 ) t\n" +
                    "          ) t\n" +
                    "OFFSET " + param.get("start") + "::INTEGER LIMIT " + param.get("perPage") + "::INTEGER";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Testimonial.class));
        };

        return (List<Testimonial>) getDataStrategy.process(allparameters);
    }


    @Transactional
    public void saveData(Testimonial contentGuestBook) {
        saveOrUpdateDataStrategy = (Testimonial parameters) -> {
            String sql = "INSERT INTO content.testimonial\n" +
                    "(subject, description, age, create_date, update_date)\n" +
                    "VALUES (?, ?, ?, current_date, current_date)";

            jdbcTemplate.update(sql, parameters.getSubject(), parameters.getDescription(), parameters.getAge());
        };

        saveOrUpdateDataStrategy.process(contentGuestBook);
    }

    @Transactional
    public void updateData(Testimonial testimonial) {
        saveOrUpdateDataStrategy = (Testimonial parameters) -> {
            String sql = "UPDATE content.testimonial\n" +
                    "SET subject = ?, description = ?, age = ?, update_date = current_date\n" +
                    "WHERE id_testimonial = ?";
            jdbcTemplate.update(sql, parameters.getSubject(), parameters.getDescription(), parameters.getAge(), parameters.getIdTestimonial());
        };

        saveOrUpdateDataStrategy.process(testimonial);
    }

    @Transactional
    public void deleteData(Integer id) {
        deleteDataStrategy = (parameters) -> {
            String sql = "DELETE FROM content.testimonial\n" +
                    "WHERE id_testimonial = ?";
            jdbcTemplate.update(sql, parameters);
        };

        deleteDataStrategy.process(id);
    }
}
