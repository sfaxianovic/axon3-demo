package com.sfx.axon3demo.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class SetCommandDeliveryDate {
    @TargetAggregateIdentifier
    private final String commandId;
    private final Long date;

    public SetCommandDeliveryDate(String commandId, Long date) {

        this.commandId = commandId;
        this.date = date;
    }

    public String getCommandId() {
        return commandId;
    }

    public Long getDate() {
        return date;
    }
}
