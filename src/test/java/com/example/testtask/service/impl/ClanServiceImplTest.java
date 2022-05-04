package com.example.testtask.service.impl;

import com.example.testtask.AbstractIntegrationTest;
import com.example.testtask.domain.Clan;
import com.example.testtask.exception.NoSuchMoneyException;
import com.example.testtask.repository.ClanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

class ClanServiceImplTest extends AbstractIntegrationTest {
    @Autowired
    ClanServiceImpl clanService;
    @Autowired
    ClanRepository clanRepository;

    @Test
    void incGoldTest() {
        Clan testName = clanRepository.save(Clan.builder().name("testName").build());
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        IntStream.range(0, 100).forEach(
                number -> {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                            clanService.incGold(testName.getId(), 1));
                    futures.add(future);
                }
        );
        futures.forEach(CompletableFuture::join);
        Clan savedClan = clanRepository.findById(testName.getId()).orElseThrow();
        Assertions.assertEquals(100, savedClan.getGold());
    }

    @Test
    void decGoldSussesTest() {
        Clan testClan = clanRepository.save(Clan.builder().name("testClan").gold(100).build());
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        IntStream.range(0, 99).forEach(
                number -> {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                            clanService.decGold(testClan.getId(), 1));
                    futures.add(future);
                }
        );
        futures.forEach(CompletableFuture::join);
        Clan savedClan = clanRepository.findById(testClan.getId()).orElseThrow();
        Assertions.assertEquals(1, savedClan.getGold());
    }

    @Test
    void decGoldFailedTest() {
        Clan testClan = clanRepository.save(Clan.builder().name("testClan").gold(100).build());
        NoSuchMoneyException exception = Assertions.assertThrows(
                NoSuchMoneyException.class,
                () -> clanService.decGold(testClan.getId(), 110)
        );
        Assertions.assertEquals("Нет таких денег!", exception.getMessage());
        Clan savedClan = clanRepository.findById(testClan.getId()).orElseThrow();
        Assertions.assertEquals(100, savedClan.getGold());
    }
}