package com.uni.services;

import com.uni.dtos.PlayerCard;
import com.uni.entities.StatBasketball;

import java.util.List;

public interface StatisticsService {

    PlayerCard getPlayerCardByUserId(int id, String devId);

    List<StatBasketball> getAllBasketballStatsByGameId(int id, String devId);

    StatBasketball addOrUpdateBasketballStat(StatBasketball stat, String devId);
}
