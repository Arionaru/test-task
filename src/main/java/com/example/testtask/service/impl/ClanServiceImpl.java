package com.example.testtask.service.impl;

import com.example.testtask.domain.Clan;
import com.example.testtask.exception.NoSuchClanException;
import com.example.testtask.exception.NoSuchMoneyException;
import com.example.testtask.repository.ClanRepository;
import com.example.testtask.service.ClanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClanServiceImpl implements ClanService {
    private final ClanRepository clanRepository;

    @Override
    @Transactional
    public void decGold(long id, long gold) {
        Clan clan = getClan(id);
        long clanGold = clan.getGold();
        if (clanGold > gold) {
            clan.setGold(clan.getGold() - gold);
            clanRepository.save(clan);
        } else {
            throw new NoSuchMoneyException();
        }
    }

    @Override
    @Transactional
    public void incGold(long id, long gold) {
        Clan clan = getClan(id);
        clan.setGold(clan.getGold() + gold);
        clanRepository.save(clan);
    }

    public Clan getClan(long id) {
        return clanRepository.findOneForUpdate(id).orElseThrow(NoSuchClanException::new);
    }
}
