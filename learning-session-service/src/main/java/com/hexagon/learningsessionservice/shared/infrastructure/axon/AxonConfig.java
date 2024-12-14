package com.hexagon.learningsessionservice.shared.infrastructure.axon;

import com.hexagon.learningsessionservice.domain.model.aggregates.LearningSession;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
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
    public Repository<LearningSession> learningSessionEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(LearningSession.class)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);

        return xStream;
    }
}
