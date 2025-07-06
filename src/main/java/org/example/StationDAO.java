package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationDAO {
    private static final Logger LOGGER = Logger.getLogger(StationDAO.class.getName());
    public List<Station> getAllStations(){
        List<Station> stations = new ArrayList<>();

        String sql = "SELECT * FROM stations";

        try(Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Station station = new Station(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("line"),
                        rs.getString("color")
                );
                stations.add(station);
            }
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Database Error", e);
        }
        return stations;
    }
}
