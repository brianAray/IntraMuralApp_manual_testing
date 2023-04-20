package com.uni.services;

import com.uni.daos.TeamDAO;
import com.uni.daos.TeamRequestDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.entities.Team;
import com.uni.entities.TeamRequest;
import com.uni.exceptions.PasswordMismatchException;
import com.uni.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class RegistrationServiceImpl implements RegistrationService{

    private TeamDAO teamDAO;
    private UserDAO userDao;
    private TeamRequestDAO teamRequestDAO;

    public RegistrationServiceImpl(TeamDAO teamDAO, UserDAO userDao, TeamRequestDAO teamRequestDAO) {
        this.teamDAO = teamDAO;
        this.userDao = userDao;
        this.teamRequestDAO = teamRequestDAO;
    }

    /*
        Team
    */
    @Override
    public Team registerTeam(Team team, String devId) {
        return this.teamDAO.save(team, devId);
    }

    @Override
    public List<Team> getAllTeams(String devId) {
        return this.teamDAO.findAll(devId);
    }

    /*
        User
     */
    @Override
    public ImUser getUserFromLoginCredentials(LoginCredentials loginCredentials, String devId) {
        ImUser imUser = this.userDao.getByUsername(loginCredentials.getUsername(), devId);

        if(!imUser.getPassword().equals(loginCredentials.getPassword())){
            throw new PasswordMismatchException();
        }
        return imUser;
    }

    @Override
    public ImUser registerUser(ImUser registrationInfo, String devId) {
        registrationInfo.setRole("player");
        ImUser user = userDao.save(registrationInfo, devId);

        return user;
    }

    @Override
    public ImUser updateUser(ImUser updateInfo, String devId) {
        userDao.update(updateInfo, devId);

        return updateInfo;
    }

    @Override
    public void updateRole(int id, String role, String devId) {
        userDao.updateRole(id, role, devId);
    }

    @Override
    public List<TeamRequest> filterTeamRequestsByPlayer(int playerId, String devId) {
        return this.teamRequestDAO.findAll(devId).stream().filter(t -> t.getRequesterId() == playerId).collect(Collectors.toList());
    }

    @Override
    public Team getTeamByTeamName(String teamName, String devId) {
        return this.teamDAO.findAll(devId).stream().filter(t -> t.getName().equals(teamName)).findFirst().get();
    }

    @Override
    public List<ImUser> retrievePlayersByTeam(String teamName, String devId) {
        return this.userDao.retrieveUserByTeam(teamName, devId);
    }

    @Override
    public List<ImUser> retrieveAllUsers(String devId) {
        return this.userDao.findAll(devId);
    }

    /*
        Team Requests
     */
    @Override
    public List<TeamRequest> getAllTeamRequests(String devId) {
        return this.teamRequestDAO.findAll(devId);
    }

    @Override
    public List<TeamRequest> filterTeamRequestsByTeam(String team, String devId) {
        return this.teamRequestDAO.findAll(devId).stream().filter(t -> t.getTeamName().equals(team)).collect(Collectors.toList());
    }

    @Override
    public TeamRequest createRequest(TeamRequest teamRequest, String devId) {
        teamRequest.setTeamRequestStatus("pending");
        return this.teamRequestDAO.save(teamRequest, devId);
    }

    @Override
    public TeamRequest approveRequest(int requestId, String devId) {

        TeamRequest teamRequest = null;

        for(TeamRequest t : teamRequestDAO.findAll(devId)){
            if(t.getTeamRequestId() == requestId){
                teamRequest = t;
            }
        }
        if(teamRequest == null){
            throw new ResourceNotFoundException(requestId, TeamRequest.class);
        }
        teamRequest.setTeamRequestStatus("accepted");
        teamRequestDAO.update(teamRequest, devId);

        return teamRequest;
    }

    @Override
    public TeamRequest denyRequest(int requestId, String devId) {
        TeamRequest teamRequest = null;

        for(TeamRequest t : teamRequestDAO.findAll(devId)){
            if(t.getTeamRequestId() == requestId){
                teamRequest = t;
            }
        }
        if(teamRequest == null){
            throw new ResourceNotFoundException(requestId, TeamRequest.class);
        }
        teamRequest.setTeamRequestStatus("denied");
        teamRequestDAO.update(teamRequest, devId);

        return teamRequest;
    }

}
