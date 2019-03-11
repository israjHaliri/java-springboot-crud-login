package com.haliri.israj.appcore.content;

import com.haliri.israj.appcore.domain.content.GuestBook;
import com.haliri.israj.appcore.strategy.content.impl.GuestBookStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestBookStrategyTests {

    @Autowired
    GuestBookStrategy guestBookStrategy;

    @Test
    public void getData() throws SQLException {
        guestBookStrategy.getListData();
    }

    @Test
    public void inserProfile() throws SQLException {
        GuestBook param = new GuestBook();
        param.setUsername("jono");
        guestBookStrategy.saveData(param);
    }
}
