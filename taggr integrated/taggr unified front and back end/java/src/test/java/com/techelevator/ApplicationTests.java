package com.techelevator;

import com.techelevator.jdbc.JdbcPhotoDAO;
import com.techelevator.jdbc.JdbcTagDAO;
import com.techelevator.model.AddPhotoJSON;
import com.techelevator.model.Tag;
import com.techelevator.model.Photo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.test.context.support.WithMockUser;

import java.sql.SQLException;
import java.util.List;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.SingleConnectionDataSource;
//
//import static org.junit.Assert.*;

@SpringBootTest
public class ApplicationTests {

    private JdbcPhotoDAO daoPhoto;
    private JdbcTagDAO daoTag;
    private static SingleConnectionDataSource dataSource;

    @BeforeClass
    public static void setupDataSource(){
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:8080/taggr");
        dataSource.setUsername("danmess");
        dataSource.setPassword("password");
        dataSource.setAutoCommit(false);
    }

    @AfterClass
    public static void closeDataSource() throws SQLException {
        dataSource.destroy();;
    }

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AddPhotoJSON newPhotoJSON = new AddPhotoJSON("https://www.instagram.com/p/CVqNH9DpOj4/", "winter squash apple cider toasts", "testing 123456, supercalifraglisticexpiallidocious, fall, squash");
        AddPhotoJSON newPhotoJSON2 = new AddPhotoJSON("https://www.instagram.com/p/CVqNH9DpOj4/", "winter squash apple cider toasts", "testing 123456, supercalifraglisticexpiallidocious, fall, squash");
        AddPhotoJSON newPhotoJSON3 = new AddPhotoJSON("https://www.instagram.com/p/CVVoqcuJfqO/", "fries", "testing 123456, supercalifraglisticexpiallidocious, fall, squash");
    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

//    @Test
//    void contextLoads() {
//    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void save_new_photo_and_confirm(){
        AddPhotoJSON newPhotoJSON = new AddPhotoJSON("https://www.instagram.com/p/CVqNH9DpOj4/", "winter squash apple cider toasts", "testing 123456, supercalifraglisticexpiallidocious, fall, squash");
        List<Tag> tagsList = daoTag.createTagsSetFromCSV(newPhotoJSON.getTagsAsCSV(), @WithMockUser);
        daoPhoto.createNewPhotoAndAddToUserSQL(
    }

}
