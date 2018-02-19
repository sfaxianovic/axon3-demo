package com.sfx.axon3demo.domain;

import com.sfx.axon3demo.command.CreateCommandDelivery;
import com.sfx.axon3demo.command.SetCommandDeliveryDate;
import com.sfx.axon3demo.events.CommandDateSetEvent;
import com.sfx.axon3demo.events.CommandDeliveryCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class CommandDelivery {
    @AggregateIdentifier
    private String id;
    private String commandName;
    private Date commandDate;

    @CommandHandler
    public CommandDelivery(CreateCommandDelivery createCommandDelivery) {
        apply(new CommandDeliveryCreateEvent(createCommandDelivery.getCommandId(), createCommandDelivery.getCommandName()));
    }
    @CommandHandler
    public void handle(SetCommandDeliveryDate commandDeliveryDate){
        apply(new CommandDateSetEvent(commandDeliveryDate.getCommandId(), commandDeliveryDate.getDate()));
    }
    protected CommandDelivery(){}
    @EventSourcingHandler
    public void on(CommandDeliveryCreateEvent commandDeliveryCreateEvent) {
        this.id = commandDeliveryCreateEvent.getCommandId();
        this.commandName = commandDeliveryCreateEvent.getCommandName();
    }
    @EventSourcingHandler
    public void on(CommandDateSetEvent commandDateSetEvent) {
        this.commandDate = new Date(commandDateSetEvent.getDate());
    }
}
