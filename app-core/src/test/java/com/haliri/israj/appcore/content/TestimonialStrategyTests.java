package com.haliri.israj.appcore.content;

import com.haliri.israj.appcore.domain.content.Testimonial;
import com.haliri.israj.appcore.strategy.content.impl.TestimonialStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestimonialStrategyTests {

    @Autowired
    TestimonialStrategy testimonialStrategy;

    @Test
    public void getListDataPerPage() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","jono");
        testimonialStrategy.getListDataPerPage(param);
    }

    @Test
    public void saveData() throws SQLException {
        Testimonial param = new Testimonial();
        param.setAge(11);
        param.setSubject("jono");
        param.setDescription("testing");
        testimonialStrategy.saveData(param);
    }

    @Test
    public void updateData() throws SQLException {
        Testimonial param = new Testimonial();
        param.setAge(11);
        param.setSubject("jono2");
        param.setDescription("testing2");
        param.setIdTestimonial(3);
        testimonialStrategy.updateData(param);
    }

    @Test
    public void deleteData() throws SQLException {
        testimonialStrategy.deleteData(3);
    }

    @Test
    public void getListData() throws SQLException {
        testimonialStrategy.getListData();
    }
}
