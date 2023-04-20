package com.uni.services;

import com.uni.entities.GameRequest;
import java.util.*;

public interface GameRequestService {

    GameRequest createRequest(GameRequest gameRequest, String devId);

    boolean deleteRequest(int gameId, int userId, String devId);
    List<GameRequest> getAllGamesAndReferees(String devId);
}
