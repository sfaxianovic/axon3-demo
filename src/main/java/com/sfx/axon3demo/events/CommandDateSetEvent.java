package com.sfx.axon3demo.events;

public class CommandDateSetEvent {
    private final String commandId;
    private final Long date;

    public CommandDateSetEvent(String commandId, Long date) {
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
