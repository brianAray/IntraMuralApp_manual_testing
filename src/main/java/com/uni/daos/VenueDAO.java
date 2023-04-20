package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.datautils.DatabaseStore;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.exceptions.SeasonCreationException;
import com.uni.entities.Venue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VenueDAO implements CrudDAO<Venue> {

    private static VenueDAO venueDAO = null;
    private static HashMap<String, DatabaseStore> database = null;

    public static VenueDAO getSingleton(HashMap<String, DatabaseStore> data){

        if(venueDAO == null){
            database = data;
            venueDAO = new VenueDAO();
        }
        return venueDAO;
    }

    private VenueDAO() {}

    @Override
    public Venue save(Venue venue, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<Venue> venues = data.getVenues();
        venues.add(venue);
        data.setVenues(venues);

        return venue;

//        try (Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "insert into venue (title) values (?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, venue.getTitle());
//
//            ps.executeUpdate();
//
//            return venue;
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new SeasonCreationException();
//        }
    }

    @Override
    public List<Venue> findAll(String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<Venue> venues = data.getVenues();
        return venues;
//        try(Connection conn = ConnectionUtil.getConnection()){
//            String sql = "select * from venue";
//            List<Venue> venues = new ArrayList();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()){
//                Venue venue = new Venue(rs.getString("title"));
//                venues.add(venue);
//            }
//
//            return venues;
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }

    @Override
    public void update(Venue venue, String devId) {

    }
}
