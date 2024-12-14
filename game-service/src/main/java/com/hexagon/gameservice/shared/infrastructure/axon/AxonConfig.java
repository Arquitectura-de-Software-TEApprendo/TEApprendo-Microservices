package com.hexagon.gameservice.shared.infrastructure.axon;

import com.hexagon.gameservice.domain.model.aggregates.Game;
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
    public Repository<Game> gameEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Game.class)
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
