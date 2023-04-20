package com.uni.controllers;

import com.uni.entities.TeamRequest;
import com.uni.exceptions.ResourceNotFoundException;
import com.uni.services.RegistrationService;
import io.javalin.http.Context;

import java.util.NoSuchElementException;

public class TeamRequestController {

    private RegistrationService registrationService;

    public TeamRequestController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void createTeamRequest(Context ctx) {
        String devId = ctx.header("dev");
        if (devId == null) {
            ctx.json("Missing Dev Id");
        } else {
            TeamRequest teamRequest = ctx.bodyAsClass(TeamRequest.class);
            ctx.status(201);
            TeamRequest savedRequest = registrationService.createRequest(teamRequest, devId);
            ctx.json(savedRequest);
        }
    }

    public void getAllTeamRequests(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            String team = ctx.queryParam("team");
            String userId = ctx.queryParam("userId");

            if (team == null && userId == null) {
                ctx.json(registrationService.getAllTeamRequests(devId));
            } else if (team != null) {
                ctx.json(registrationService.filterTeamRequestsByTeam(team, devId));
            } else if (userId != null) {
                ctx.json(registrationService.filterTeamRequestsByPlayer(Integer.parseInt(userId), devId));
            }
        }
    }

    public void approveRequest(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            int id = Integer.parseInt(ctx.pathParam("id"));

            try {
                TeamRequest request = this.registrationService.approveRequest(id, devId);
                ctx.json(request);
            } catch (ResourceNotFoundException e) {
                ctx.status(404);
            }
        }
    }

    public void denyRequest(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            int id = Integer.parseInt(ctx.pathParam("id"));

            try {
                TeamRequest request = this.registrationService.denyRequest(id, devId);
                ctx.json(request);
            } catch (ResourceNotFoundException e) {
                ctx.status(404);
            }
        }
    }


}
