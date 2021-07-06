package pl.microservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.microservice.model.MessageService;
import pl.microservice.model.Message;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
public
class MessageController {
    private final MessageService service;


    MessageController(final MessageService service) {
        this.service = service;
    }

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody @Valid Message messageToCreate) {
        Message result = service.save(messageToCreate);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(value = "/message", params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<Message>> readAllMessages() {

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/message")
    public ResponseEntity<Page<Message>> readAllMessages(Pageable page) {

        return new ResponseEntity<>(service.findAll(page), HttpStatus.OK);
    }

    @GetMapping(value = "/message/{id}")
    public ResponseEntity<Message> readMessage(@PathVariable UUID id) {

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable UUID id, @RequestBody @Valid Message messageToUpdate) {

        if (!service.existById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        messageToUpdate.setId(id);
        service.save(messageToUpdate);

        return service.findById(id)
                .map(ResponseEntity::ok).get();
    }

    @GetMapping("/message/random/{count}")
    public ResponseEntity<List<Message>> getRandom(@PathVariable int count) {

        if (count > service.findAll().size()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Message> result = service.random(count);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}