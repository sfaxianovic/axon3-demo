package com.sfx.axon3demo.events;

public class CommandDeliveryCreateEvent {
    private final String commandId;
    private final String commandName;

    public CommandDeliveryCreateEvent(String commandId, String commandName) {
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
