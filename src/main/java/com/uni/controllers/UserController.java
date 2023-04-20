package com.uni.controllers;

import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.exceptions.InvalidDevIdException;
import com.uni.services.RegistrationService;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    private RegistrationService registrationService;

    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void retrieveAllUsers(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else{
            List<ImUser> users = registrationService.retrieveAllUsers(devId);
            ctx.json(users);
        }
    }

    public void updateRole(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else{
            int id = Integer.parseInt(ctx.pathParam("id"));
            ImUser roleInformation = ctx.bodyAsClass(ImUser.class);
            String role = roleInformation.getRole();

            registrationService.updateRole(id, role, devId);
            ctx.status(204);
        }
    }

    public void register(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ImUser registrationInfo = ctx.bodyAsClass(ImUser.class);

            ImUser registeredUser = registrationService.registerUser(registrationInfo, devId);

            ctx.sessionAttribute("user", registeredUser);
            ctx.status(201);
            ctx.json(registeredUser);
        }
    }

    public void update(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ImUser updateInfo = ctx.bodyAsClass(ImUser.class);

            ImUser loggedInUser = ctx.sessionAttribute("user");

            updateInfo.setUserId(loggedInUser.getUserId());
            updateInfo.setRole(loggedInUser.getRole());

            ImUser user = registrationService.updateUser(updateInfo, devId);
            ctx.json(user);
        }
    }

    public void login(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else{
            LoginCredentials credentials = ctx.bodyAsClass(LoginCredentials.class);
            ImUser user = registrationService.getUserFromLoginCredentials(credentials, devId);
            ctx.sessionAttribute("user",user);
            ctx.json(user);
        }
    };

    public void logout(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ctx.req.getSession().invalidate();
        }
    }


    public void retrievePlayersByTeam(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            String teamName = ctx.pathParam("teamname");

            ctx.json(registrationService.retrievePlayersByTeam(teamName, devId));
        }
    }
}
