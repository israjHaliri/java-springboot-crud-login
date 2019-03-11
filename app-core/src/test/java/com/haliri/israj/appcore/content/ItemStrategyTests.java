package com.haliri.israj.appcore.content;

import com.haliri.israj.appcore.constant.ContentType;
import com.haliri.israj.appcore.domain.content.Attachment;
import com.haliri.israj.appcore.domain.content.Item;
import com.haliri.israj.appcore.strategy.content.impl.AttachmentStrategy;
import com.haliri.israj.appcore.strategy.content.impl.ItemStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemStrategyTests {

    @Autowired
    ItemStrategy itemStrategy;

    @Autowired
    AttachmentStrategy attachmentStrategy;

    @Test
    public void getListData() throws SQLException {
        itemStrategy.getListData(ContentType.ARTICLE.name());
    }

    @Test
    public void getListDataPerPage() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","");
        itemStrategy.getListDataPerPage(param);
    }

    @Test
    public void getListDataPerPageAttachment() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","");
        param.put("type", ContentType.ARTICLE.name());
        attachmentStrategy.getListDataPerPage(param);
    }

    @Test
    public void saveData() throws SQLException {
        Item Item = new Item();
        Item.setTitle("Test insert");
        Item.setDescription("Testing insert");
        Item.setCreateDate(new Date(2017-10-1));
        Item.setUpdateDate(new Date(2017-10-1));
        Item.setCreateBy("admin.content@gmail.com");
        Item.setUpdateBy("admin.content@gmail.com");
        Item.setContentType(ContentType.ARTICLE);
        Item.setInformation("-");
        Item.setIdItem(null);
        itemStrategy.saveData(Item);
    }

    @Test
    public void updateData() throws SQLException {
        Item Item = new Item();
        Item.setTitle("Test update");
        Item.setDescription("Testing update");
        Item.setCreateDate(new Date(2017-10-2));
        Item.setUpdateDate(new Date(2017-10-2));
        Item.setCreateBy("admin.content@gmail.com");
        Item.setUpdateBy("admin.content@gmail.com");
        Item.setContentType(ContentType.ARTICLE);
        Item.setInformation("-");
        Item.setIdItem(2);
        itemStrategy.updateData(Item);
    }

    @Test
    public void saveAttachment() throws SQLException {
        Attachment attachment = new Attachment();
        attachment.setIdAttachment(1);
        attachment.setItemId(2);
        attachment.setFile("file.txt");
        attachment.setContentType(ContentType.ARTICLE);
//        attachmentStrategy.saveData(attachment);
    }

    @Test
    public void deleteData() throws SQLException {
        itemStrategy.deleteData(1);
    }
}
