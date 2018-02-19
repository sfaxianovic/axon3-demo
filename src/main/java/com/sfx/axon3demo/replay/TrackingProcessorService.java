package com.sfx.axon3demo.replay;

import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.tokenstore.jpa.TokenEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class TrackingProcessorService {

    private final TokenJpaRepository repository;
    private final EventHandlingConfiguration eventHandlingConfiguration;

    @Autowired
    public TrackingProcessorService(EventHandlingConfiguration eventHandlingConfiguration, TokenJpaRepository repository) {
        this.eventHandlingConfiguration = eventHandlingConfiguration;
        this.repository = repository;
    }

    public void startReplay(String name) {
        final TokenEntry.PK id = new TokenEntry.PK(name, 0);
        final TokenEntry one = this.repository.findOne(id);
        final Supplier<? extends RuntimeException> notFoundSupplier = () -> new IllegalArgumentException("Processor " + name + " not registered.");
        if (one == null) {
            throw notFoundSupplier.get();
        }
        this.eventHandlingConfiguration.getProcessor(name).orElseThrow(notFoundSupplier).shutDown();
        this.repository.delete(id);
        this.eventHandlingConfiguration.getProcessor(name).orElseThrow(notFoundSupplier).start();
    }
}
