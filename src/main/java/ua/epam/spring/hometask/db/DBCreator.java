package ua.epam.spring.hometask.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Yevheniia_Blokhina
 * Date: 4/27/2016
 * Time: 5:40 PM
 */
public class DBCreator {

    @Autowired
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void initDB() {
        createAuditoriumsTable();
        createEventsTable();
        createTicketsTable();
        createUsersTable();
        createCountersTable();
    }

    private void createAuditoriumsTable() {
        jdbcTemplate.execute(CREATE_AUDITORIUM_TABLE);
    }

    private void createCountersTable() {
//        jdbcTemplate.execute(CREATE__TABLE);
    }

    private void createUsersTable() {
        jdbcTemplate.execute(CREATE_USER_TABLE);
    }


    private void createTicketsTable() {
        jdbcTemplate.execute(CREATE_TICKETS_TABLE);
    }

    private void createEventsTable() {
        jdbcTemplate.execute(CREATE_EVENT_TABLE);
    }


    private static final String CREATE_AUDITORIUM_TABLE = "CREATE TABLE IF NOT EXISTS AUDITORIUMS " +
            "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "NAME CHAR(20), " +
            "SEATS INTEGER, " +
            "VIPS VARCHAR(100), " +
            "PRIMARY KEY(ID))";

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS USERS " +
            "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "BIRTH_DATE TIMESTAMP, " +
            "FIRST_NAME CHAR(20), " +
            "LAST_NAME CHAR(20), " +
            "EMAIL VARCHAR(30), " +
            "BOOKED_TICKETS INTEGER DEFAULT 0, " +
            "PRIMARY KEY(ID))";

    private static final String CREATE_TICKETS_TABLE = "CREATE TABLE IF NOT EXISTS TICKETS " +
            "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "EVENT VARCHAR(50), " +
            "SEAT_NUM INTEGER, " +
            "USER_EMAIL VARCHAR(20), " +
            "DATE VARCHAR(10), " +
            "PRIMARY KEY(ID))";


    private static final String CREATE_EVENT_TABLE = "CREATE TABLE IF NOT EXISTS EVENTS " +
            "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "NAME VARCHAR(50), " +
            "TICKET_PRICE FLOAT, " +
            "RATING VARCHAR(10), " +
            "AUDITORIUM VARCHAR(50), " +
            "PRIMARY KEY(ID))";

    private static final String CREATE_DATE_EVENT_TABLE = "";
}
