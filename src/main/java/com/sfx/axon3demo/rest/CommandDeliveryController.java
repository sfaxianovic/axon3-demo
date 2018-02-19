package com.sfx.axon3demo.rest;

import com.sfx.axon3demo.command.CreateCommandDelivery;
import com.sfx.axon3demo.command.SetCommandDeliveryDate;
import com.sfx.axon3demo.replay.TrackingProcessorService;
import com.sfx.axon3demo.rest.requests.CreateCommandRequest;
import com.sfx.axon3demo.rest.requests.SetCommandDateRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommandDeliveryController {

    private CommandGateway commandGateway;
    private TrackingProcessorService trackingProcessorService;

    @Autowired
    public CommandDeliveryController(CommandGateway commandGateway, TrackingProcessorService trackingProcessorService) {
        this.commandGateway = commandGateway;
        this.trackingProcessorService = trackingProcessorService;
    }

    @PostMapping(path = "/create-command", consumes = "application/json")
    public ResponseEntity<String> createCommandDelivery(@RequestBody final CreateCommandRequest createCommandRequest) {
        String commandId = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new CreateCommandDelivery(commandId, createCommandRequest.getCommandName()));
        return new ResponseEntity<>(commandId, HttpStatus.CREATED);
    }

    @PostMapping(path = "set-delivery-date", produces = "application/json")
    public ResponseEntity setCommandDeliveryDate(@RequestBody final SetCommandDateRequest setCommandDateRequest) {
        Object result = commandGateway.sendAndWait(new SetCommandDeliveryDate(setCommandDateRequest.getId(), setCommandDateRequest.getDate()));
        return new ResponseEntity(result, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/replay")
    public ResponseEntity replayEvents(){
        trackingProcessorService.startReplay("commandDelivery");
        return new ResponseEntity<>("Ok", HttpStatus.NO_CONTENT);
    }
}
