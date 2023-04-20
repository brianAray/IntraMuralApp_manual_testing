package com.uni.services;

import com.uni.daos.GameDAO;
import com.uni.daos.SeasonDAO;
import com.uni.daos.VenueDAO;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;

import java.util.List;

public class SchedulingServiceImpl implements SchedulingService{

    private VenueDAO venueDAO;
    private GameDAO gameDAO;
    private SeasonDAO seasonDAO;

    public SchedulingServiceImpl(VenueDAO venueDAO, GameDAO gameDAO, SeasonDAO seasonDAO){
        this.venueDAO = venueDAO;
        this.gameDAO = gameDAO;
        this.seasonDAO = seasonDAO;
    }

    @Override
    public List<Venue> getAllVenues(String devId) {
        return this.venueDAO.findAll(devId);
    }

    @Override
    public List<Game> getAllGames(String devId) {
        return this.gameDAO.findAll(devId);
    }

    @Override
    public List<Season> getAllSeasons(String devId) {
        return this.seasonDAO.findAll(devId);
    }

    @Override
    public Game scheduleGame(Game game, String devId) {
        return this.gameDAO.save(game, devId);
    }

    @Override
    public Season scheduleSeason(Season incomingSeason, String devId) {
        return this.seasonDAO.save(incomingSeason, devId);
    }
}
