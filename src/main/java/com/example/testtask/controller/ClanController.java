package com.example.testtask.controller;

import com.example.testtask.service.ClanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/clan")
@RequiredArgsConstructor
public class ClanController {
    private final ClanService clanService;

    @GetMapping("/{id}/inc-gold")
    public void incGold(@PathVariable long id, long gold) {
        clanService.incGold(id, gold);
    }

    @GetMapping("/{id}/dec-gold")
    public void decGold(@PathVariable long id, long gold) {
        clanService.decGold(id, gold);
    }
}
