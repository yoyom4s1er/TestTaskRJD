package com.example.testtask.repository;

import com.example.testtask.model.RailwayAdministration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AdministrationRepository {

    private final String SELECT_ALL = "SELECT * FROM `Железнодорожные администрации (ЖА) СНГ`";
    private final String SELECT_BY_CODE = "SELECT * FROM `Железнодорожные администрации (ЖА) СНГ` WHERE `код ЖА`=?";
    private final String SELECT_BY_FULLNAME = "SELECT * FROM `Железнодорожные администрации (ЖА) СНГ` WHERE `полное наименование ЖА`=?";
    private final String SELECT_BY_ABBREVIATION = "SELECT * FROM `Железнодорожные администрации (ЖА) СНГ` WHERE `аббревиатура ЖА`=?";
    private final String INSERT = "INSERT INTO `Железнодорожные администрации (ЖА) СНГ` VALUES (?,?,?)";
    private final String DELETE_BY_CODE = "DELETE FROM `Железнодорожные администрации (ЖА) СНГ` WHERE `код ЖА`=?";
    private final String UPDATE = "UPDATE `Железнодорожные администрации (ЖА) СНГ` set `код ЖА`=?, `полное наименование ЖА`=?, `аббревиатура ЖА`=? where `код ЖА`=?";

    DataSource dataSource;

    public List<RailwayAdministration> getAll() {
        List<RailwayAdministration> railwayAdministrations = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);

            while (result.next()) {
                railwayAdministrations.add(extractAdministration(result));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return railwayAdministrations;
    }

    public boolean save(RailwayAdministration railwayAdministration) {
        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setObject(1, railwayAdministration.getCode());
            statement.setObject(2, railwayAdministration.getFullName());
            statement.setObject(3, railwayAdministration.getAbbreviation());

            statement.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public RailwayAdministration getByCode(int code) {
        RailwayAdministration administration = null;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_CODE);
            statement.setLong(1, code);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                administration = extractAdministration(result);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return administration;
    }

    public RailwayAdministration getByFullName(String name) {
        RailwayAdministration administration = null;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_FULLNAME);
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                administration = extractAdministration(result);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return administration;
    }

    public RailwayAdministration getByAbbreviation(String abbreviation) {
        RailwayAdministration administration = null;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ABBREVIATION);
            statement.setString(1, abbreviation);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                administration = extractAdministration(result);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return administration;
    }

    public boolean deleteByCode(int code) {

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_BY_CODE);
            statement.setInt(1, code);

            if (statement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean updateValues(int code, RailwayAdministration administration){
        System.out.println(administration);
        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, administration.getCode());
            statement.setString(2, administration.getFullName());
            statement.setString(3, administration.getAbbreviation());
            statement.setInt(4, code);

            if (statement.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private RailwayAdministration extractAdministration(ResultSet resultSet) throws SQLException{
        return new RailwayAdministration(
                resultSet.getInt("код ЖА"),
                resultSet.getString("полное наименование ЖА"),
                resultSet.getString("аббревиатура ЖА")
        );
    }
}
