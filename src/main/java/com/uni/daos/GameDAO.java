package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.datautils.DatabaseStore;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.entities.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameDAO implements CrudDAO<Game> {

    private static GameDAO gameDAO = null;

    private static HashMap<String, DatabaseStore> database = null;
    public static GameDAO getSingleton(HashMap<String, DatabaseStore> data){

        if(gameDAO == null){
            database = data;
            gameDAO = new GameDAO();
        }

        return gameDAO;
    }

    private  GameDAO(){}

    @Override
    public Game save(Game game, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<Game> games = data.getGames();
        game.setGameId(games.size() + 1);
        games.add(game);
        data.setGames(games);

        return game;
//        try(Connection conn = ConnectionUtil.getConnection()){
//            String sql = "insert into game values (default, ?, ?, ?, ?, ?, ?, ?, ?::game_outcome)";
//            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1,game.getVenueTitle());
//            ps.setString(2, game.getSeasonTitle());
//            ps.setString(3,game.getHomeTeam());
//            ps.setString(4,game.getAwayTeam());
//            ps.setInt(5,game.getHomeScore());
//            ps.setInt(6,game.getAwayScore());
//            ps.setLong(7,game.getGameStart());
//            ps.setString(8, game.getOutcome());
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
//            int key = rs.getInt("game_id");
//            game.setGameId(key);
//            return game;
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }

    @Override
    public List<Game> findAll(String devId){
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<Game> games = data.getGames();
        return games;
//        try(Connection conn = ConnectionUtil.getConnection()){
//            String sql = "select * from game";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            List<Game> games = new ArrayList();
//
//            while (rs.next()){
//                Game game = new Game();
//                game.setGameId(rs.getInt("game_id"));
//                game.setVenueTitle(rs.getString("venue"));
//                game.setSeasonTitle(rs.getString("season"));
//                game.setHomeTeam(rs.getString("home_team"));
//                game.setAwayTeam(rs.getString("away_team"));
//                game.setHomeScore(rs.getInt("home_score"));
//                game.setAwayScore(rs.getInt("away_score"));
//                game.setGameStart(rs.getLong("game_start"));
//                game.setOutcome(rs.getString("game_outcome"));
//                games.add(game);
//
//            }
//            return  games;
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }

    @Override
    public void update(Game game, String devId) {

    }
}
