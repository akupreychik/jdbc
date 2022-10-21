package com.javarush.consts;

public class SQLQuery {
    //booking schema
    public static final String LIMIT = "LIMIT 10";
    public static final String WHERE_PASSENGER_ID = " where seat_no = ? ";
    public static final String SELECT_AIRCRAFTS = "SELECT * FROM aircrafts_data";
    public static final String SELECT_BOOKINGS = "SELECT * FROM bookings";
    public static final String SELECT_SEATS = "SELECT * FROM seats";
    public static final String SELECT_TICKET_FLIGHTS = "SELECT * FROM bookings.ticket_flights " + LIMIT;
    public static final String SELECT_FLIGHTS = "SELECT * FROM flights " + LIMIT;

    //echo schema
    public static final String SELECT_USERS = "SELECT * from echo.users WHERE echo.users.tenant_id = ?";
    public static final String SELECT_TENATS = "SELECT * FROM echo.tenants";
    public static final String INSERT_POST = "INSERT INTO echo.posts (tenant_id, post_id, author_id) VALUES (?, ?, ?)";
}
