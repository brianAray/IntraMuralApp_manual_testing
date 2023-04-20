package com.uni.services;

import com.uni.daos.GameRequestDAO;
import com.uni.entities.GameRequest;

import java.util.List;

public class GameRequestImpl implements GameRequestService{

    private GameRequestDAO gameRequestDAO;

    public GameRequestImpl(GameRequestDAO gameRequestDAO) { this.gameRequestDAO = gameRequestDAO; }

    @Override
    public GameRequest createRequest(GameRequest gameRequest, String devId) {
        return this.gameRequestDAO.save(gameRequest, devId);
    }

    @Override
    public boolean deleteRequest(int gameId, int userId, String devId) {
        return this.gameRequestDAO.delete(gameId, userId, devId);
    }

    @Override
    public List<GameRequest> getAllGamesAndReferees(String devId) { return this.gameRequestDAO.findAll(devId); }
}
