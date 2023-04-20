package com.uni.controllers;


import com.uni.entities.Team;
import com.uni.services.RegistrationService;
import io.javalin.http.Context;


public class TeamController {

    private RegistrationService registrationService;

    public TeamController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void registerTeam(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            Team team = ctx.bodyAsClass(Team.class);
            registrationService.registerTeam(team, devId);
            ctx.status(204);
        }
    }

    public void retrieveAllTeams(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ctx.json(registrationService.getAllTeams(devId));
        }
    }

    public void retrieveTeamByTeamName(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            String teamName = ctx.pathParam("teamname");

            ctx.json(registrationService.getTeamByTeamName(teamName, devId));
        }
    }
}
