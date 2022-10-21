package com.javarush;

import com.javarush.consts.Environment;
import com.javarush.consts.SQLQuery;

import com.javarush.models.booking.AircraftsData;
import com.javarush.models.booking.Seats;
import com.javarush.models.echo.Post;
import com.javarush.models.echo.Tenats;
import com.javarush.models.echo.Users;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(Environment.DB_URL, Environment.DB_USER, Environment.DB_PASSWORD)) {

            Statement statement =  connection.createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
            ResultSet resultSetOfTenatns = statement.executeQuery(SQLQuery.SELECT_TENATS);

            resultSetOfTenatns.next();
            resultSetOfTenatns.next();


            Tenats tenats = Tenats.builder()
                    .tenantId(resultSetOfTenatns.getLong(1))
                    .build();
            System.out.println(tenats);


            PreparedStatement preparedStatementForUsers = connection.prepareStatement(SQLQuery.SELECT_USERS);
            preparedStatementForUsers.setLong(1, tenats.getTenantId());

            ResultSet resultSetOfUsers = preparedStatementForUsers.executeQuery();
            resultSetOfUsers.next();
            resultSetOfUsers.next();
            Users user = Users.builder()
                    .tenantId(resultSetOfUsers.getLong(1))
                    .userId(resultSetOfUsers.getLong(2))
                    .build();
            System.out.println(user);

            Post post = Post.builder()
                    .postId(112L)
                    .tenantId(user.getTenantId())
                    .userId(user.getUserId())
                    .build();
            System.out.println(post);

            PreparedStatement preparedStatementForInsert = connection.prepareStatement(SQLQuery.INSERT_POST);
            preparedStatementForInsert.setLong(1, post.getTenantId());
            preparedStatementForInsert.setLong(2, post.getPostId());
            preparedStatementForInsert.setLong(3, post.getUserId());

            System.out.println(preparedStatementForInsert.executeUpdate());



        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    private static void getMetadata(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());
        //info about columns
        System.out.println(resultSetMetaData.getColumnName(1) + " - column name");
        System.out.println(resultSetMetaData.getColumnLabel(2) + " - column label");
        System.out.println(resultSetMetaData.getColumnType(4) + " - column type");
        System.out.println(resultSetMetaData.getColumnTypeName(2) + " - column type class");
        System.out.println(resultSetMetaData.getColumnClassName(4) + " - column class name");
        //info about tables and etc.
        System.out.println(resultSetMetaData.getTableName(1) + " - table name");
        System.out.println(resultSetMetaData.getCatalogName(1) + " - catalog name");
        System.out.println(resultSetMetaData.getSchemaName(1) + " - schema name");
        System.out.println(resultSetMetaData.isAutoIncrement(1) + " - isAutoIncrement");
        System.out.println(resultSetMetaData.isNullable(1) + " - isNullable");
    }

    private static void getDataFromTicketFlightsRow(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getRow() + " row number");
        System.out.println(resultSet.getDouble(1));
        System.out.println(resultSet.getInt(2));
        System.out.println(resultSet.getString(3));
        System.out.println(resultSet.getDouble(4));
        System.out.println();
    }

    private static List<AircraftsData> getAircraftsData(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(SQLQuery.SELECT_AIRCRAFTS);
        List<AircraftsData> resultFromDB = new ArrayList<>();
        while (resultSet.next()) {
            AircraftsData aircraftsData = AircraftsData.builder()
                    .aircraftCode(resultSet.getString("aircraft_code"))
                    .model(resultSet.getString("model"))
                    .range(resultSet.getString("range"))
                    .build();
            resultFromDB.add(aircraftsData);
        }
        return resultFromDB;
    }

    private static List<Seats> getSeats(ResultSet resultSetForFlights) throws SQLException {
        List<Seats> resultFlightsFromDB = new ArrayList<>();
        while (resultSetForFlights.next()) {
            Seats flight = Seats.builder()
                    .aircraftCode(resultSetForFlights.getString("aircraft_code"))
                    .seatNo(resultSetForFlights.getString("seat_no"))
                    .fareConditions(resultSetForFlights.getString("fare_conditions"))
                    .build();
            resultFlightsFromDB.add(flight);
        }
        return resultFlightsFromDB;
    }
}