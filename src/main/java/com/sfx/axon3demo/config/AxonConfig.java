package com.sfx.axon3demo.config;

import org.axonframework.config.EventHandlingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public EventHandlingConfiguration eventHandlingConfiguration() {
        return new EventHandlingConfiguration();
    }

    @Autowired
    public void configureProcessors(EventHandlingConfiguration eventHandlingConfiguration) {
        eventHandlingConfiguration.registerTrackingProcessor("commandDelivery");
    }
}

