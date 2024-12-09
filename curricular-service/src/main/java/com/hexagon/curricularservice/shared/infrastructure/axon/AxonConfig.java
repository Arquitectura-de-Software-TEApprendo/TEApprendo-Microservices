package com.hexagon.curricularservice.shared.infrastructure.axon;

import com.hexagon.curricularservice.domain.model.aggregates.TeachingUnit;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {
    @Primary
    @Bean
    public Repository<TeachingUnit> teachingUnitEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(TeachingUnit.class)
            .eventStore(eventStore)
            .build();
    }
}
