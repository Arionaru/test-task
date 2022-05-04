package com.example.testtask.service;


import com.example.testtask.domain.Clan;

public interface ClanService {

    void incGold(long id, long gold);

    void decGold(long id, long gold);

    Clan getClan(long id);
}
