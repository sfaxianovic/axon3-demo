package com.sfx.axon3demo.query;

import com.sfx.axon3demo.events.CommandDateSetEvent;
import com.sfx.axon3demo.events.CommandDeliveryCreateEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@ProcessingGroup("commandDelivery")
@Component
public class CommandDeliveryQuery {

    @EventHandler
    public void on(CommandDeliveryCreateEvent commandDeliveryCreateEvent) {
        System.out.println(commandDeliveryCreateEvent.getCommandName());
    }

    @EventHandler
    public void on(CommandDateSetEvent commandDateSetEvent){
        System.out.println(commandDateSetEvent.getDate());
    }
}
