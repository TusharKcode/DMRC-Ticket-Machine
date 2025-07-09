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
                String name = rs.getString("name");
                double distance;

                switch (name){
                    case "AIIMS" -> distance = 8.0;
                    case "Rajiv Chowk" -> distance = 0.0;
                    case "Green Park" -> distance = 6.0;
                    case "Dwarka Sector 21" -> distance = 22.0;
                    case "Central Secretariat" -> distance = 2.0;
                    case "HUDA City Centre" -> distance = 28.0;
                    case "Noida City Centre" -> distance = 25.0;
                    // Add more stations as needed
                    default -> distance = 5.0; // Default fallback distance
                }

                Station station = new Station(
                        rs.getInt("id"),
                        name,
                        rs.getString("line_color"),
                        distance
                        );

                stations.add(station);
            }
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Database Error", e);
        }
        return stations;

    }
}
