package com.sfx.axon3demo.domain;

import com.sfx.axon3demo.command.CreateCommandDelivery;
import com.sfx.axon3demo.command.SetCommandDeliveryDate;
import com.sfx.axon3demo.events.CommandDateSetEvent;
import com.sfx.axon3demo.events.CommandDeliveryCreateEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class CommandDeliveryTest {
    private FixtureConfiguration<CommandDelivery> fixture;
    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(CommandDelivery.class);
    }

    @Test
    public void should_generate_command_delivery_event_when_receive_create_command() {
        String commandId = UUID.randomUUID().toString();
        String commandName = "first command";
        fixture.given()
                .when(new CreateCommandDelivery(commandId, commandName))
                .expectEvents(new CommandDeliveryCreateEvent(commandId, commandName));
    }

    @Test
    public void should_generate_command_delivery_date_set_event_when_receive_set_date_command() {
        String commandId = UUID.randomUUID().toString();
        String commandName = "first command";

        long commandDate = new Date().getTime();
        fixture.given(new CommandDeliveryCreateEvent(commandId, commandName))
                .when(new SetCommandDeliveryDate(commandId, commandDate))
                .expectEvents(new CommandDateSetEvent(commandId, commandDate));
    }
}