package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.datautils.DatabaseStore;
import com.uni.entities.Team;
import com.uni.entities.TeamRequest;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.exceptions.NoUsernameFoundException;
import com.uni.entities.ImUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserDAO implements CrudDAO<ImUser> {

    private static UserDAO userDAO = null;
    private static HashMap<String, DatabaseStore> database = null;

    public static UserDAO getSingleton(HashMap<String, DatabaseStore> data){

        if(userDAO == null) {
            database = data;
            userDAO = new UserDAO();
        }

        return userDAO;
    }

    private UserDAO() {}

    public ImUser getByUsername(String username, String devId){
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ImUser imUser = data.getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
        if(imUser != null){
            return imUser;
        }else{
            throw new NoUsernameFoundException();
        }
//        try(Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "select * from im_user where username = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1,username);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                ImUser user = new ImUser();
//                user.setUserId(rs.getInt("user_id"));
//                user.setUsername(username);
//                user.setPassword(rs.getString("password"));
//                user.setRole(rs.getString("role"));
//                user.setHeightInches(rs.getInt("height"));
//                user.setWeightLbs(rs.getInt("weight"));
//                user.setProfilePic(rs.getString("profile_pic"));
//                user.setHideBiometrics(rs.getBoolean("display_biometrics"));
//                return user;
//            }
//            throw new NoUsernameFoundException();
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }

    }

    public ImUser save(ImUser imUser, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<ImUser> users = data.getUsers();
        imUser.setUserId(users.size() + 1);
        users.add(imUser);
        data.setUsers(users);

        return imUser;
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "insert into im_user (username, password, role, height, weight, profile_pic, display_biometrics) VALUES (?, ?, ?::im_role, ?, ?, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, imUser.getUsername());
//            ps.setString(2, imUser.getPassword());
//            ps.setString(3, imUser.getRole());
//            ps.setInt(4, imUser.getHeightInches());
//            ps.setInt(5, imUser.getWeightLbs());
//            ps.setString(6, imUser.getProfilePic());
//            ps.setBoolean(7, imUser.isHideBiometrics());
//
//            ps.executeUpdate();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
//            imUser.setUserId(rs.getInt(1));
//
//            return imUser;
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new ImUserCreationException();
//        }
    }

    public List<ImUser> findAll(String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        List<ImUser> users = data.getUsers();
        return users;
//        try (Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "select * from im_user";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            List<ImUser> users = new ArrayList<>();
//
//            while (rs.next()) {
//                ImUser user = new ImUser();
//                user.setUserId(rs.getInt("user_id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setRole(rs.getString("role"));
//                user.setHeightInches(rs.getInt("height"));
//                user.setWeightLbs(rs.getInt("weight"));
//                user.setProfilePic(rs.getString("profile_pic"));
//                user.setHideBiometrics(rs.getBoolean("display_biometrics"));
//
//                users.add(user);
//            }
//
//            return users;
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }

    public void update(ImUser imUser, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<ImUser> users = data.getUsers();
        users.set(imUser.getUserId() - 1, imUser);
        data.setUsers(users);
//        try(Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "update im_user set username = ?, password = ?, role = ?::im_role, height = ?, weight = ?, profile_pic = ?, display_biometrics = ? where user_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, imUser.getUsername());
//            ps.setString(2, imUser.getPassword());
//            ps.setString(3, imUser.getRole());
//            ps.setInt(4, imUser.getHeightInches());
//            ps.setInt(5, imUser.getWeightLbs());
//            ps.setString(6, imUser.getProfilePic());
//            ps.setBoolean(7, imUser.isHideBiometrics());
//            ps.setInt(8, imUser.getUserId());
//
//            ps.executeUpdate();
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }

    public ImUser findById(int id, String devId){
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        try{
            List<ImUser> users = data.getUsers();
            return users.get(id - 1);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
            throw new NoUsernameFoundException();
        }
//        try(Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "select * from im_user where user_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1,id);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                ImUser user = new ImUser();
//                user.setUserId(rs.getInt("user_id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setRole(rs.getString("role"));
//                user.setHeightInches(rs.getInt("height"));
//                user.setWeightLbs(rs.getInt("weight"));
//                user.setProfilePic(rs.getString("profile_pic"));
//                user.setHideBiometrics(rs.getBoolean("display_biometrics"));
//                return user;
//            }
//            throw new NoUsernameFoundException();
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }

    }

    public void updateRole(int id, String role, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<ImUser> users = data.getUsers();
        ImUser userToUpdate = users.get(id - 1);
        userToUpdate.setRole(role);
        users.set(id - 1, userToUpdate);
        data.setUsers(users);
//        try(Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "update im_user set role = ?::im_role where user_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, role);
//            ps.setInt(2, id);
//
//            ps.executeUpdate();
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }

    public List<ImUser> retrieveUserByTeam(String teamName, String devId) {
        DatabaseStore data = null;
        if(database != null){
            data = database.get(devId);
        }
        ArrayList<ImUser> users = data.getUsers();
        ArrayList<Team> teams = data.getTeams();
        ArrayList<TeamRequest> teamRequests = data.getTeamRequests();

        List<TeamRequest> acceptedTeamRequests = teamRequests
                .stream()
                .filter(request -> {
                    return request.getTeamRequestStatus().equals("accepted") && request.getTeamName().equals(teamName);
                })
                .collect(Collectors.toList());

        List<ImUser> usersInTeams = new ArrayList<>();
        for(TeamRequest teamRequest : acceptedTeamRequests){
            int requesterId = teamRequest.getRequesterId();
            usersInTeams.add(users.get(requesterId - 1));
        }
        for(Team team: teams){
            int captainId = team.getCaptain();
            usersInTeams.add(users.get(captainId - 1));
        }

        return usersInTeams;
//        try(Connection connection = ConnectionUtil.getConnection()) {
//            String sql = "SELECT iu.* FROM team_requests tr INNER JOIN im_user iu ON tr.user_id = iu.user_id WHERE tr.team = ? AND tr.status = 'accepted' UNION " +
//                    "select iu.* from team t inner join im_user iu on t.captain = iu.user_id where t.name = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, teamName);
//            ps.setString(2, teamName);
//            ResultSet rs = ps.executeQuery();
//
//            List<ImUser> users = new ArrayList<>();
//
//            while(rs.next()) {
//                ImUser user = new ImUser();
//                user.setUserId(rs.getInt("user_id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setRole(rs.getString("role"));
//                user.setHeightInches(rs.getInt("height"));
//                user.setWeightLbs(rs.getInt("weight"));
//                user.setProfilePic(rs.getString("profile_pic"));
//                user.setHideBiometrics(rs.getBoolean("display_biometrics"));
//
//                users.add(user);
//            }
//
//            return users;
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//            throw new DatabaseConnectionException();
//        }
    }
}
