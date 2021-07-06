package pl.microservice.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService  {


    List<Message> findAll();

    Page<Message> findAll(Pageable page);

    Optional<Message> findById(UUID id);

    boolean existById(UUID id);

    Message save(Message entity);

    List<Message> random(Integer count);

}