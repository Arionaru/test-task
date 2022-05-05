package com.example.testtask.service.impl;

import com.example.journalapi.domain.EventDto;
import com.example.journalapi.domain.Source;
import com.example.journalapi.domain.Status;
import com.example.journalapi.domain.action.ClanAction;
import com.example.journalapi.service.KafkaMessageSender;
import com.example.testtask.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final KafkaMessageSender journalMessageSender;

    @Override
    public void sendMessage(ClanAction clanAction, Status status, long clanId, long gold, long userId) {
        Map<String, Object> description = new HashMap<>();
        description.put("userId", String.valueOf(userId));
        description.put("gold", String.valueOf(gold));
        EventDto eventDto = EventDto.builder()
                .action(clanAction.toString())
                .eventTime(LocalDateTime.now())
                .source(Source.CLAN.toString())
                .sourceId(String.valueOf(clanId))
                .description(description)
                .status(status.toString())
                .build();
        journalMessageSender.send(eventDto, UUID.randomUUID().toString());
    }
}
