package com.sfx.axon3demo.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateCommandDelivery {
    @TargetAggregateIdentifier
    private String commandId;
    private String commandName;

    public CreateCommandDelivery(String commandId, String commandName) {
        this.commandId = commandId;
        this.commandName = commandName;
    }

    public String getCommandId() {
        return commandId;
    }

    public String getCommandName() {
        return commandName;
    }
}
