package com.hexagon.learningsessionservice.domain.events;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class LearningSessionRegistered {
    private Long id;
    private String duration;
    private String beginning;
    private String development;
    private String conclusion;
    private LocalDateTime createdAt;
}