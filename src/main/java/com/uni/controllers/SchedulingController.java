package com.uni.controllers;

import com.uni.daos.GameDAO;
import com.uni.daos.SeasonDAO;
import com.uni.daos.VenueDAO;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;
import com.uni.services.SchedulingService;
import com.uni.services.SchedulingServiceImpl;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;

public class SchedulingController {


//    private SchedulingService schedulingService = new SchedulingServiceImpl(VenueDAO.getSingleton(), GameDAO.getSingleton(), SeasonDAO.getSingleton());
    private SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    public void getAllVenues(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ctx.json(schedulingService.getAllVenues(devId));
        }
    }

    public void scheduleGame(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            Game incomingGame = ctx.bodyAsClass(Game.class);
            Game scheduledGame = schedulingService.scheduleGame(incomingGame, devId);
            ctx.status(201);
            ctx.json(scheduledGame);
        }
    }

    public void scheduleSeason(Context ctx) {
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            Season incomingSeason = ctx.bodyAsClass(Season.class);
            Season scheduledSeason = schedulingService.scheduleSeason(incomingSeason, devId);
            ctx.status(201);
            ctx.json(scheduledSeason);
        }
    }

    public void getAllGames(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ctx.json(schedulingService.getAllGames(devId));
        }
    }

    public void getAllSeasons(Context ctx){
        String devId = ctx.header("dev");
        if(devId == null){
            ctx.json("Missing Dev Id");
        }else {
            ctx.json(schedulingService.getAllSeasons(devId));
        }
    }
}
