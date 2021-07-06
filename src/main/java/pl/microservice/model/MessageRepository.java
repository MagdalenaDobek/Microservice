package pl.microservice.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface MessageRepository extends JpaRepository<Message, UUID> {
}
