package com.hexagon.gameservice.domain.events;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class GameRegistered {
    private Long id;
    private String name;
    private String type;
    private String difficulty;
    private String topic;
    private LocalDateTime createdAt;
}
