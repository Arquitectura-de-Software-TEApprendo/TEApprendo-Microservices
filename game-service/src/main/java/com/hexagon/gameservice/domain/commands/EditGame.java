package com.hexagon.gameservice.domain.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class EditGame {
    @TargetAggregateIdentifier
    private Long id;
    private String name;
    private String type;
    private String difficulty;
    private String topic;
    private LocalDateTime updatedAt;
}
