package com.uni.controllers;

import com.uni.dtos.PlayerCard;
import com.uni.entities.StatBasketball;
import com.uni.services.StatisticsService;
import io.javalin.http.Context;

public class StatisticsController {

    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void getPLayerCardById(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PlayerCard playerCard = this.statisticsService.getPlayerCardByUserId(id, devId);
            ctx.json(playerCard);
        }
    }

    public void getAllBasketballStatsByGameId(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            int gameId = Integer.parseInt(ctx.pathParam("gameid"));

            ctx.json(statisticsService.getAllBasketballStatsByGameId(gameId, devId));
        }
    }

    public void addOrUpdateBasketballStat(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            StatBasketball stat = ctx.bodyAsClass(StatBasketball.class);

            ctx.json(statisticsService.addOrUpdateBasketballStat(stat, devId));
        }
    }


}
