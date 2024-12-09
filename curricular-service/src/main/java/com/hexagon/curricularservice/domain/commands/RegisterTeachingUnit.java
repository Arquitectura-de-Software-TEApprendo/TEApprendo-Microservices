package com.hexagon.curricularservice.domain.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class RegisterTeachingUnit {
    @TargetAggregateIdentifier
    private Long id;
    private String name;
    private String duration;
    private String purpose;
    private LocalDateTime createdAt;
}