package com.uni.controllers;

import com.uni.entities.GameRequest;
import com.uni.services.GameRequestService;
import io.javalin.http.Context;

public class GameRequestController {
    private GameRequestService gameRequestService;

    public GameRequestController(GameRequestService gameRequestService) { this.gameRequestService = gameRequestService; }

    public void createGameRequest(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            GameRequest gameRequest = ctx.bodyAsClass(GameRequest.class);
            ctx.status(201);
            GameRequest savedGameRequest = gameRequestService.createRequest(gameRequest, devId);
            ctx.json(savedGameRequest);
        }
    }

    public void deleteGameRequest(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            GameRequest gameRequest = ctx.bodyAsClass(GameRequest.class);
            if (gameRequestService.deleteRequest(gameRequest.getGameId(), gameRequest.getUserId(), devId)) {
                ctx.status(204);
            } else {
                ctx.status(404);
            }
        }
    }
    public void retrieveAllRefereeAndGames(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ctx.json(gameRequestService.getAllGamesAndReferees(devId));
        }
    }
}
