package com.uni.services;

import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;

import java.util.List;

public interface SchedulingService {

    List<Venue> getAllVenues(String devId);
    List<Game> getAllGames(String devId);
    List<Season> getAllSeasons(String devId);
    Game scheduleGame(Game game, String devId);
    Season scheduleSeason(Season incomingSeason, String devId);
}
