package com.techelevator;

import com.techelevator.jdbc.JdbcPhotoDAO;
import com.techelevator.jdbc.JdbcTagDAO;
import com.techelevator.model.AddPhotoJSON;
import com.techelevator.model.Photo;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.SQLException;
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
        AddPhotoJSON newPhotoJSON = new AddPhotoJSON("https://www.instagram.com/p/CVqNH9DpOj4/", "winter squash apple cider toasts", "testing tags, supercalifraglisticexpiallidocious, fall, squash");
    }

//    @Test
//    void contextLoads() {
//    }

}
