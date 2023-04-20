package com.uni.services;

import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.entities.Team;
import com.uni.entities.TeamRequest;

import java.util.List;

public interface RegistrationService {

    Team registerTeam(Team team, String devId);
    List<Team> getAllTeams(String devId);
    ImUser getUserFromLoginCredentials(LoginCredentials loginCredentials, String devId);


    ImUser registerUser(ImUser registrationInfo, String devId);

    ImUser updateUser(ImUser updateInfo, String devId);

    List<ImUser> retrieveAllUsers(String devId);

    List<TeamRequest> getAllTeamRequests(String devId);
    List<TeamRequest> filterTeamRequestsByTeam(String team, String devId);
    TeamRequest createRequest(TeamRequest teamRequest, String devId);
    TeamRequest approveRequest(int TeamRequest, String devId);
    TeamRequest denyRequest(int TeamRequest, String devId);
    void updateRole(int id, String role, String devId);
    List<TeamRequest> filterTeamRequestsByPlayer(int parseInt, String devId);
    Team getTeamByTeamName(String teamName, String devId);
    List<ImUser> retrievePlayersByTeam(String teamName, String devId);
}
