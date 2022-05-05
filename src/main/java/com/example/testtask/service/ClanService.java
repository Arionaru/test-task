package com.example.testtask.service;


public interface ClanService {

    void incGold(long clanId, long gold, long userId);

    void decGold(long clanId, long gold, long userId);

}
