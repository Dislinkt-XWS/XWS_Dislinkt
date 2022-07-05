package com.example.messageservice.Controller;

import com.example.messageservice.DTO.MessageDTO;
import com.example.messageservice.Model.Message;
import com.example.messageservice.Service.MessageService;
import com.xwsdislinkt.userservice.NotificationRequest;
import com.xwsdislinkt.userservice.NotificationResponse;
import com.xwsdislinkt.userservice.UserServiceGrpc;
import org.bson.types.ObjectId;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/messages")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    ModelMapper modelMapper;

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    @PostMapping
    public ResponseEntity<MessageDTO> create(@RequestBody @Validated MessageDTO dto){
        var message = modelMapper.map(dto, Message.class);

        System.out.println("Targeting grpc server: " + userServiceStub.getChannel());
        NotificationRequest notificationRequest = NotificationRequest.newBuilder().setSenderId(dto.getSenderId()).setUserId(dto.getReceiverId()).setText("New message").build();
        NotificationResponse notificationResponse = userServiceStub.addNotification(notificationRequest);

        return new ResponseEntity<>(modelMapper.map(
                messageService.save(message), MessageDTO.class), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<MessageDTO>> get(){
        var messages = messageService.findAll();
        List<MessageDTO> messageDTOS = messages
                .stream()
                .map(post -> modelMapper.map(post, MessageDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/{user1}/{user2}")
    public ResponseEntity<List<MessageDTO>> getChat(@PathVariable("user1") String user1, @PathVariable("user2") String user2){
        var messages = messageService.getChat(user1, user2);
        List<MessageDTO> messageDTOS = messages
                .stream()
                .map(post -> modelMapper.map(post, MessageDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
    }

}
