package com.sfx.axon3demo.rest;

import com.sfx.axon3demo.command.CreateCommandDelivery;
import com.sfx.axon3demo.command.SetCommandDeliveryDate;
import com.sfx.axon3demo.events.CommandDateSetEvent;
import com.sfx.axon3demo.events.CommandDeliveryCreateEvent;
import com.sfx.axon3demo.replay.TrackingProcessorService;
import com.sfx.axon3demo.rest.requests.CreateCommandRequest;
import com.sfx.axon3demo.rest.requests.SetCommandDateRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommandDeliveryControllerTest {
    @MockBean
    private CommandGateway commandGateway;
    private CommandDeliveryController commandDeliveryController;
    @MockBean
    private TrackingProcessorService trackingProcessorService;

    @Before
    public void setUp() throws Exception {
        commandDeliveryController = new CommandDeliveryController(commandGateway, trackingProcessorService);
    }

    @Test
    public void should_handle_create_Command_Delivery_request() {
        CreateCommandRequest createCommandRequest = new CreateCommandRequest();
        createCommandRequest.setCommandName("test command");
        String commandId = UUID.randomUUID().toString();
        when(commandGateway.sendAndWait(any(CreateCommandDelivery.class))).thenReturn(new CommandDeliveryCreateEvent(commandId, createCommandRequest.getCommandName()));

        ResponseEntity responseEntity = commandDeliveryController.createCommandDelivery(createCommandRequest);

        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void should_handle_set_Command_Delivery_date_request() {
        SetCommandDateRequest setCommandDateRequest = new SetCommandDateRequest();
        setCommandDateRequest.setDate(new Date().getTime());
        String commandId = UUID.randomUUID().toString();
        when(commandGateway.sendAndWait(any(SetCommandDeliveryDate.class))).thenReturn(new CommandDateSetEvent(commandId, setCommandDateRequest.getDate()));

        ResponseEntity responseEntity = commandDeliveryController.setCommandDeliveryDate(setCommandDateRequest);

        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
}