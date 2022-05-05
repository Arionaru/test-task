package com.example.testtask.controller;

import com.example.testtask.service.ClanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/clan")
@RequiredArgsConstructor
public class ClanController {
    private final ClanService clanService;

    @PostMapping("/{clanId}/inc-gold")
    public void incGold(@PathVariable long clanId, @RequestParam long gold, @RequestParam long userId) {
        clanService.incGold(clanId, gold, userId);
    }

    @PostMapping("/{clanId}/dec-gold")
    public void decGold(@PathVariable long clanId, @RequestParam long gold, @RequestParam long userId) {
        clanService.decGold(clanId, gold, userId);
    }
}
