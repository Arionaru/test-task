package com.example.testtask.service.impl;

import com.example.journalapi.domain.Status;
import com.example.journalapi.domain.action.ClanAction;
import com.example.testtask.domain.Clan;
import com.example.testtask.exception.NoSuchClanException;
import com.example.testtask.exception.NoSuchMoneyException;
import com.example.testtask.repository.ClanRepository;
import com.example.testtask.service.ClanService;
import com.example.testtask.service.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClanServiceImpl implements ClanService {
    private final ClanRepository clanRepository;
    private final JournalService journalService;

    @Override
    @Transactional
    public void decGold(long clanId, long gold, long userId) {
        Clan clan = getClan(clanId);
        long clanGold = clan.getGold();
        if (clanGold > gold) {
            clan.setGold(clan.getGold() - gold);
            clanRepository.save(clan);
            journalService.sendMessage(ClanAction.DEC_GOLD, Status.SUCCESS, clanId, gold, userId);
        } else {
            throw new NoSuchMoneyException();
        }
    }

    @Override
    @Transactional
    public void incGold(long clanId, long gold, long userId) {
        Clan clan = getClan(clanId);
        clan.setGold(clan.getGold() + gold);
        clanRepository.save(clan);
        journalService.sendMessage(ClanAction.INC_GOLD, Status.SUCCESS, clanId, gold, userId);
    }

    private Clan getClan(long clanId) {
        return clanRepository.findOneForUpdate(clanId).orElseThrow(NoSuchClanException::new);
    }
}
