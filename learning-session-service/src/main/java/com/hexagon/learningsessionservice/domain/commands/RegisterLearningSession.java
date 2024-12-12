package com.hexagon.learningsessionservice.domain.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class RegisterLearningSession {
    @TargetAggregateIdentifier
    private Long id;
    private String duration;
    private String beginning;
    private String development;
    private String conclusion;
    private LocalDateTime createdAt;
}