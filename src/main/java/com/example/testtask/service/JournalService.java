package com.example.testtask.service;

import com.example.journalapi.domain.Status;
import com.example.journalapi.domain.action.ClanAction;

public interface JournalService {
    void sendMessage(ClanAction clanAction, Status status, long clanId, long gold, long userId);
}
