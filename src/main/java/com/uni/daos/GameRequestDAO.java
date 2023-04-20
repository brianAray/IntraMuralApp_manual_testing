package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.datautils.DatabaseStore;
import com.uni.entities.GameRequest;
import com.uni.exceptions.DatabaseConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameRequestDAO implements CrudDAO<GameRequest> {

    /*
    ++++++++++++++++++++++++++++++++++++++++++++++++++++
    GAME REQUESTS TABLE DOES NOT EXIST
    ++++++++++++++++++++++++++++++++++++++++++++++++++++
    GAME REQUESTS TABLE DOES NOT EXIST
    ++++++++++++++++++++++++++++++++++++++++++++++++++++
    GAME REQUESTS TABLE DOES NOT EXIST
    ++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    private static GameRequestDAO gameRequestDAO = null;

    private static HashMap<String, DatabaseStore> database = null;
    public static GameRequestDAO getSingleton(HashMap<String, DatabaseStore> data){
        if(gameRequestDAO == null){
            database = data;
            gameRequestDAO = new GameRequestDAO();
        }
        return gameRequestDAO;
    }
    @Override
    public GameRequest save(GameRequest gameRequest, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
//        try(Connection connection = ConnectionUtil.getConnection()){
//            String sql = "insert into game_requests values (default, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1,gameRequest.getGameId());
//            ps.setInt(2, gameRequest.getUserId());
//
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
//
//            int key = rs.getInt("game_request_id");
//            gameRequest.setGameRequestId(key);
//
//            return gameRequest;
//
//        } catch (SQLException exception){
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
        return gameRequest;
    }


    public boolean delete(int gameId, int userId, String devId){
//        try(Connection connection = ConnectionUtil.getConnection()){
//            String sql = "delete from game_requests where game = ? and user_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, gameId);
//            ps.setInt(2, userId);
//
//            int recordsDeleted = ps.executeUpdate();
//
//            if (recordsDeleted >= 1) {
//                return true;
//            } else {
//                return false;
//            }
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
        return false;
    }

    @Override
    public List<GameRequest> findAll(String devId) {
//        try(Connection connection = ConnectionUtil.getConnection()){
//            String sql = "select game_requests.*, game.venue, game.season from game_requests left join game on game_requests.game=game.game_id";
//            List<GameRequest> gameRequests = new ArrayList<>();
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()){
//                GameRequest gameRequest = new GameRequest();
//                gameRequest.setGameRequestId(rs.getInt("game_request_id"));
//                gameRequest.setGameId(rs.getInt("game"));
//                gameRequest.setUserId(rs.getInt("user_id"));
//                gameRequest.setVenue(rs.getString("venue"));
//                gameRequest.setSeason(rs.getString("season"));
//                gameRequests.add(gameRequest);
//            }
//            return gameRequests;
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
        return null;
    }

    @Override
    public void update(GameRequest gameRequest, String devId) {

    }
}
